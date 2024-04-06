package cn.kingen.commons.system.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotated on field to represent a column in an Excel sheet.
 *
 * @author wangsg
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelColumn {

    String header();
}
