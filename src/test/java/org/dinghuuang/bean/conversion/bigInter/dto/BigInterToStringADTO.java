package org.dinghuuang.bean.conversion.bigInter.dto;

/**
 * @version 1.0
 * @Author: edisionding
 * @Description:
 * @Date: since 2022/7/27 11:40
 * @Modify By: edisionding
 */
public class BigInterToStringADTO {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
            "value='" + value + '\'' +
            '}';
    }
}
