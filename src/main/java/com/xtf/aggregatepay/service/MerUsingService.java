package com.xtf.aggregatepay.service;

import com.xtf.aggregatepay.core.BaseService;
import com.xtf.aggregatepay.dao.MerUsingDao;
import com.xtf.aggregatepay.entity.DictItem;
import com.xtf.aggregatepay.entity.MerUsing;
import com.xtf.aggregatepay.util.EhcacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MerUsingService extends BaseService<MerUsing> {
    @Autowired
    private MerUsingDao merUsingDao;
    @Scheduled(cron = "*/2 * * * * ?")
    public void delExpiredMerUsing(){
        DictItem dictItem=(DictItem) EhcacheUtil.getInstance().get(DictItem.class.getSimpleName(),"mer_time_interval");
        merUsingDao.delExpiredData(dictItem==null?60:Integer.parseInt(dictItem.getDictItemVal()));
    }
}
