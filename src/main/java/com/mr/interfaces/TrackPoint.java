package com.mr.interfaces;

import java.lang.annotation.*;

@Documented
// 只允许加在方法上
@Target(ElementType.METHOD)
// 运行时保留，支持反射解析
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPoint {
    // value属性，语法糖，不传默认空字符串
    String value() default "";
    // 是否打印请求参数，默认true
    boolean printParam() default true;
    // 接口业务描述
    String desc() default "";
}
