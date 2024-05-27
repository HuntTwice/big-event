package com.zc.bigeventbackend.validation;

import com.zc.bigeventbackend.anno.State;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//onstraintValidator<A,T> 必须提供两个泛型，A表示给哪个注解提供校验规则，T表示校验的数据类型
public class StateValidation implements ConstraintValidator<State,String> {
    @Override
    public void initialize(State constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    /**
     * 提供校验的规则
     * @param value 将来要校验的数据
     * @param constraintValidatorContext
     * @return 返回false校验不通过，true表示校验通过
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        if (value.equals("已发布")||value.equals("草稿"))
            return true;
        return false;
    }
}
