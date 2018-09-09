package com.xtf.aggregatepay.util;

import com.xtf.aggregatepay.Consts;
import com.xtf.aggregatepay.entity.DictItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
}
