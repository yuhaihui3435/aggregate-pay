package com.xtf.aggregatepay.service;

import cn.hutool.core.util.RandomUtil;
import com.xtf.aggregatepay.core.BaseService;
import com.xtf.aggregatepay.entity.MerInfo;
import com.xtf.aggregatepay.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService extends BaseService<Product> {

    @Autowired
    private MerInfoService merInfoService;
    public Product pickProduct(String merNum, BigDecimal price){
        MerInfo merInfo=merInfoService.findByMercNum(merNum);
        List<Product> productList=tpl(Product.builder().industryCode(merInfo.getCustomMccType()).price(price).build());
        if(productList.size()==0)return null;
        return productList.get(RandomUtil.randomInt(productList.size()));

    }

}
