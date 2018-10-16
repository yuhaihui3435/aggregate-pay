package com.xtf.aggregatepay.service;

import com.xtf.aggregatepay.core.BaseService;
import com.xtf.aggregatepay.dao.MerUsingDao;
import com.xtf.aggregatepay.entity.DictItem;
import com.xtf.aggregatepay.entity.MerUsing;
import com.xtf.aggregatepay.util.EhcacheUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MerUsingService extends BaseService<MerUsing> {
    @Autowired
    private MerUsingDao merUsingDao;
    @Scheduled(cron = "0/2 * * * * ?")
    public void delExpiredMerUsing(){
//        log.info("执行定时清理商户占用数据");
        DictItem dictItem=(DictItem) EhcacheUtil.getInstance().get(DictItem.class.getSimpleName(),"mer_time_interval");
        merUsingDao.delExpiredData(dictItem==null?60:Integer.parseInt(dictItem.getDictItemVal()));
    }
}
