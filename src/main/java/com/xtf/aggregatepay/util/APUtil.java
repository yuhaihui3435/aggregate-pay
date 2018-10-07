package com.xtf.aggregatepay.util;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.xtf.aggregatepay.Consts;
import com.xtf.aggregatepay.entity.DictItem;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * 简介
 * <p>
 * 项目名称:   [aggregate-pay]
 * 包:        [com.xtf.aggregatepay.util]
 * 类名称:    [APUtil]
 * 类描述:    []
 * 创建人:    [于海慧]
 * 创建时间:  [2018/9/8]
 * 修改人:    []
 * 修改时间:  []
 * 修改备注:  []
 * 版本:     [v1.0]
 */
public class APUtil {
    public static boolean isValidDateYYYYMMDD(String str) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }

    public static String getAgentKey(){
        DictItem dictItem=((DictItem)EhcacheUtil.getInstance().get(DictItem.class.getSimpleName(),Consts.AGENT.agentKey.name()));
        return dictItem.getDictItemVal();
    }

    public static String getAgentNum(){
        DictItem dictItem=((DictItem)EhcacheUtil.getInstance().get(DictItem.class.getSimpleName(),Consts.AGENT.agentNum.name()));
        return dictItem.getDictItemVal();
    }
    public static String getT1RateCode(){
        DictItem dictItem=((DictItem)EhcacheUtil.getInstance().get(DictItem.class.getSimpleName(),"my_t1_rateCode"));
        return dictItem.getDictItemVal();
    }
    public static String getTsRateCode(){
        DictItem dictItem=((DictItem)EhcacheUtil.getInstance().get(DictItem.class.getSimpleName(),"my_ts_rateCode"));
        return dictItem.getDictItemVal();
    }
    /**
     * @return 形如 yyyyMMddHHmmssSSS-Z0000019558195832297 的(38位)保证唯一的递增的序列号字符串，
     * 主要用于数据库的主键，方便基于时间点的跨数据库的异步数据同步。
     * 前半部分是currentTimeMillis，后半部分是nanoTime（正数）补齐20位的字符串，
     * 如果通过System.nanoTime()获取的是负数，则通过nanoTime = nanoTime+Long.MAX_VALUE+1;
     * 转化为正数或零。
     */
    public static String getTimeMillisSequence(){
        long nanoTime = System.nanoTime();
        String preFix="";
        if (nanoTime<0){
            preFix="A";//负数补位A保证负数排在正数Z前面,解决正负临界值(如A9223372036854775807至Z0000000000000000000)问题。
            nanoTime = nanoTime+Long.MAX_VALUE+1;
        }else{
            preFix="Z";
        }
        String nanoTimeStr = String.valueOf(nanoTime);

        int difBit=String.valueOf(Long.MAX_VALUE).length()-nanoTimeStr.length();
        for (int i=0;i<difBit;i++){
            preFix = preFix+"0";
        }
        nanoTimeStr = preFix+nanoTimeStr;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS"); //24小时制
        String timeMillisSequence=sdf.format(System.currentTimeMillis())+"-"+nanoTimeStr.substring(0,11);
        return timeMillisSequence;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 费率编号检查  10003200标准格式
     * @param rateCode
     * @return
     */
    public static boolean checkRateCode(String rateCode){

        if(StrUtil.isBlank(rateCode)){
            return false;
        }

        if(rateCode.length()!=8){
            return false;
        }

        if(!(rateCode.startsWith("1")||rateCode.startsWith("2"))){
            return false;
        }

        if(!NumberUtil.isInteger(rateCode)){
            return false;
        }
        return true;
    }

    public static BigDecimal getRate(String rateCode){
        String str=rateCode.substring(1,6);
        BigDecimal bigDecimal=new BigDecimal(str);
        if(str.endsWith("0")){
            str=str.substring(0,str.length()-1);
            return new BigDecimal(str).divide(new BigDecimal(1000));
        }else{
            return new BigDecimal(str).divide(new BigDecimal(10000));
        }


    }

    public static BigDecimal getZs(String rateCode){
        String str=rateCode.substring(6,8);
        str=str.replaceAll("0","");
        return new BigDecimal(str).divide(new BigDecimal(10));
    }

    public static void main(String[] args) {
        System.out.printf(""+getRate("10004901"));
        System.out.printf(""+getZs("10004511"));
    }

}
