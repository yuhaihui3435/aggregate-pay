package com.xtf.aggregatepay.util;

import org.beetl.sql.core.NameConversion;
import org.beetl.sql.core.annotatoin.Table;
import org.beetl.sql.core.kit.StringKit;

/**
 *
 * 去掉表名中的_T后缀
 *
 *
 */

public class DelSuffixConversion extends NameConversion {
    @Override
    public String getTableName(Class<?> aClass) {
        Table table = (Table)aClass.getAnnotation(Table.class);
        return table != null ? table.name() : StringKit.enCodeUnderlined(aClass.getSimpleName());

    }
    @Override
    public String getClassName(String tableName) {
        int index=tableName.lastIndexOf("_t");
        if(tableName.length()-2==index){
            tableName=tableName.substring(0,index);
        }
        String temp = StringKit.deCodeUnderlined(tableName.toLowerCase());
        return StringKit.toUpperCaseFirstOne(temp);
    }

    @Override
    public String getColName(Class<?> aClass, String s) {
        return StringKit.enCodeUnderlined(s);
    }

    @Override
    public String getPropertyName(Class<?> aClass, String s) {
        return StringKit.deCodeUnderlined(s.toLowerCase());
    }
}
