package cn.kingen.commons.convert;

import cn.kingen.commons.lang.CodeSupplier;
import cn.kingen.commons.util.EnumUtils;
import javax.persistence.AttributeConverter;

/**
 * This class provides a skeleton implementation to convert an enum to a code calling {@link CodeSupplier#getCode()} and
 * back again.
 */
public abstract class CodeEnumAttributeConverter<E extends Enum<E> & CodeSupplier>
    implements AttributeConverter<E, Integer> {

    private final Class<E> clazz;

    public CodeEnumAttributeConverter(Class<E> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Integer convertToDatabaseColumn(E e) {
        return e == null ? null : e.getCode();
    }

    @Override
    public E convertToEntityAttribute(Integer code) {
        return code == null ? null : EnumUtils.ofCode(clazz, code);
    }
}
