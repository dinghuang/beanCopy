package org.dinghuuang.bean.conversion.bigInter.dto;

import java.math.BigInteger;

/**
 * @version 1.0
 * @Author: edisionding
 * @Description:
 * @Date: since 2022/7/27 11:40
 * @Modify By: edisionding
 */
public class BigInterToStringBDTO {

    private BigInteger value;

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
            "value='" + value + '\'' +
            '}';
    }
}
