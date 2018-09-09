package com.xtf.aggregatepay;

import com.xtf.aggregatepay.util.DelSuffixConversion;
import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.core.db.SQLiteStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.gen.GenConfig;

public class BeetlGenCode {

    public static void main(String[] args) throws Exception {

        ConnectionSource source = ConnectionSourceHelper.getSimple("com.mysql.jdbc.Driver", "jdbc:mysql://140.143.10.128:53306/aggregate_pay_db?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&zeroDateTimeBehavior=convertToNull", "ht", "Yuhaihui@3435#$(*&");
        DBStyle mysql = new MySqlStyle();
        SQLLoader loader = new ClasspathLoader("/sql");
        DelSuffixConversion nc = new  DelSuffixConversion();
        SQLManager sqlManager = new SQLManager(mysql, loader, source, nc,new Interceptor[]{new DebugInterceptor()});
        GenConfig config = new GenConfig();
        config.setIgnorePrefix("_T");
        config.preferBigDecimal(true);
        config.setBaseClass("com.xtf.aggregatepay.core.BaseEntity");
        String table="AP_CODE_T";
        sqlManager.genPojoCode(table,"com.xtf.aggregatepay.entity",config);
        sqlManager.genSQLFile(table,config);
    }


}
