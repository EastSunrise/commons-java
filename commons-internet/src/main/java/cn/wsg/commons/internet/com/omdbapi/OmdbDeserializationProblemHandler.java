package cn.wsg.commons.internet.com.omdbapi;

import cn.wsg.commons.lang.NumberUtilsExt;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;

import java.io.IOException;

/**
 * @author Kingen
 */
class OmdbDeserializationProblemHandler extends DeserializationProblemHandler {

    private static final String NA = "N/A";

    @Override
    public Object handleWeirdStringValue(DeserializationContext ctxt, Class<?> targetType, String valueToConvert,
        String failureMsg) throws IOException {
        if (NA.equals(valueToConvert)) {
            return null;
        }
        if (Integer.class.equals(targetType) || int.class.equals(targetType)) {
            return (int)NumberUtilsExt.parseCommaSeparatedNumber(valueToConvert);
        }
        if (Long.class.equals(targetType) || long.class.equals(targetType)) {
            return NumberUtilsExt.parseCommaSeparatedNumber(valueToConvert);
        }
        return super.handleWeirdStringValue(ctxt, targetType, valueToConvert, failureMsg);
    }
}
