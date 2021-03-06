package cn.wsg.commons.system.csv;

import cn.wsg.commons.function.Getter;
import cn.wsg.commons.util.MapUtilsExt;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.commons.lang3.ArrayUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A descriptor of part properties of beans that matches the data in format of csv.
 *
 * @param <T> type of beans that be matched with data in format of csv
 * @author Kingen
 */
public final class CsvTemplate<T> {

    private final Map<String, Getter<T, ?>> getters = new LinkedHashMap<>(4);
    private final Map<String, CsvSetter<T>> setters = new LinkedHashMap<>(4);

    private CsvTemplate() {
    }

    /**
     * Creates a template for the given class, including specified properties of the given class.
     *
     * @param clazz      type of beans to be read and written
     * @param mapper     jackson used to deserialize a string to target value
     * @param properties properties to be included in this template. All available properties will
     *                   be included if the array is empty.
     * @throws IllegalArgumentException if any given property is not contained in the class
     * @see BeanInfo
     */
    public static <T> CsvTemplate<T> create(Class<T> clazz, ObjectMapper mapper, String... properties)
        throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
        Map<String, PropertyDescriptor> descriptors = new HashMap<>(properties.length);
        for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
            MapUtilsExt.putIfAbsentOrElseThrow(descriptors, propertyDescriptor.getName(), propertyDescriptor);
        }
        String[] props = properties;
        if (ArrayUtils.isEmpty(props)) {
            props = descriptors.keySet().toArray(new String[0]);
        }
        CsvTemplate<T> template = new CsvTemplate<>();
        TypeFactory factory = mapper.getTypeFactory();
        for (String property : props) {
            PropertyDescriptor descriptor = descriptors.get(property);
            if (null == descriptor) {
                throw new IllegalArgumentException(
                    String.format("%s doesn't contain a property of '%s'.", clazz, property));
            }
            Method getter = descriptor.getReadMethod();
            Method setter = descriptor.getWriteMethod();
            if (null != getter) {
                // exclude getClass()
                if ("getClass".equals(getter.getName()) && 0 == getter.getParameterCount()) {
                    continue;
                }
                template.putGetter(property, t -> {
                    try {
                        return getter.invoke(t);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new IllegalStateException(e);
                    }
                });
            }
            if (null != setter) {
                Type type = setter.getGenericParameterTypes()[0];
                template.putSetter(property, (bean, value) -> {
                    JavaType javaType = factory.constructType(type);
                    try {
                        setter.invoke(bean, mapper.convertValue(value, javaType));
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        throw new IllegalStateException(e);
                    }
                });
            }
        }
        return template;
    }

    /**
     * Creates a template for a map.
     */
    public static <V> CsvTemplate<Map<String, V>> createMap(ObjectMapper mapper,
        Map<String, Class<? extends V>> classes) {
        CsvTemplate<Map<String, V>> template = builder();
        TypeFactory factory = mapper.getTypeFactory();
        for (Map.Entry<String, Class<? extends V>> entry : classes.entrySet()) {
            String header = entry.getKey();
            template.putGetter(header, map -> map.get(header));
            template.putSetter(header, (map, value) -> {
                JavaType javaType = factory.constructType(entry.getValue());
                map.put(header, mapper.convertValue(value, javaType));
            });
        }
        return template;
    }

    public static <T> CsvTemplate<T> builder() {
        return new CsvTemplate<>();
    }

    public <V> CsvTemplate<T> putGetter(String header, Getter<T, V> getter) {
        getters.put(header, getter);
        return this;
    }

    public CsvTemplate<T> putSetter(String header, CsvSetter<T> setter) {
        setters.put(header, setter);
        return this;
    }

    Map<String, Getter<T, ?>> getGetters() {
        return getters;
    }

    Map<String, CsvSetter<T>> getSetters() {
        return setters;
    }
}
