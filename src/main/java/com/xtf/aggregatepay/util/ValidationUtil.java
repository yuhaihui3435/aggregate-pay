package com.xtf.aggregatepay.util;

import org.hibernate.validator.HibernateValidator;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.Set;

/**
 * 简介
 * <p>
 * 项目名称:   [aggregate-pay]
 * 包:        [com.xtf.aggregatepay.util]
 * 类名称:    [ValidationUtil]
 * 类描述:    []
 * 创建人:    [于海慧]
 * 创建时间:  [2018/9/8]
 * 修改人:    []
 * 修改时间:  []
 * 修改备注:  []
 * 版本:     [v1.0]
 */
public class ValidationUtil {
    private static Validator validator = Validation
            .byProvider(HibernateValidator.class).configure().failFast(true).buildValidatorFactory().getValidator();

    public static <T> void validate(T obj) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj);
        // 抛出检验异常
        StringBuilder stringBuilder=new StringBuilder();
        if (constraintViolations.size() > 0) {
            constraintViolations.stream().forEach(c->buildMsg(c,stringBuilder));
            throw new ValidationException(stringBuilder.toString());
        }
    }


    private static String buildMsg(ConstraintViolation constraintViolation,StringBuilder stringBuilder){

        if(stringBuilder.length()==0){
            stringBuilder.append(constraintViolation.getPropertyPath()+":"+constraintViolation.getMessage());
        }else {
            stringBuilder.append(",").append(constraintViolation.getPropertyPath()+":"+constraintViolation.getMessage());
        }

        return stringBuilder.toString();
    }
}
