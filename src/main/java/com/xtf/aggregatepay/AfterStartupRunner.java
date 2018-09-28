package com.xtf.aggregatepay;

import com.xtf.aggregatepay.service.ApCodeService;
import com.xtf.aggregatepay.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 简介
 * <p>
 * 项目名称:   [aggregate-pay]
 * 包:        [com.xtf.aggregatepay]
 * 类名称:    [AfterStartupRunner]
 * 类描述:    []
 * 创建人:    [于海慧]
 * 创建时间:  [2018/9/3]
 * 修改人:    []
 * 修改时间:  []
 * 修改备注:  []
 * 版本:     [v1.0]
 */
@Component
public class AfterStartupRunner implements ApplicationRunner
{
    @Autowired
    private DictService dictService;
    @Autowired
    private ApCodeService apCodeService;
    @Value("${pic.path}")
    private String picPath;



    @Override
    public void run(ApplicationArguments args) throws Exception {
        allAddInCache();
        //创建上传图片保存路径
        File file=new File(picPath);
        if (!file.exists())
            file.mkdir();
    }

    private void allAddInCache(){
        dictService.dictCache();
        apCodeService.apCodeCache();
    }

}
