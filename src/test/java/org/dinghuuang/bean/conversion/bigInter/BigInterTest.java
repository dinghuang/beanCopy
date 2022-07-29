package org.dinghuuang.bean.conversion.bigInter;

import org.dinghuuang.bean.conversion.bigInter.dto.BigInterToStringADTO;
import org.dinghuuang.bean.conversion.bigInter.dto.BigInterToStringBDTO;
import org.dinghuuang.bean.util.BeanUtils;
import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

/**
 * @version 1.0
 * @Author: edisionding
 * @Description:
 * @Date: since 2022/7/28 15:47
 * @Modify By: edisionding
 */
public class BigInterTest {


    @Test
    public void stringToBigInterTest() {
        BigInterToStringADTO bigInterToStringADTO = new BigInterToStringADTO();
        bigInterToStringADTO.setValue("123");
        BigInterToStringBDTO bigInterToStringBDTO = new BigInterToStringBDTO();
        BeanUtils.copy(bigInterToStringADTO, bigInterToStringBDTO);
        Assert.assertEquals(bigInterToStringADTO.toString(), bigInterToStringBDTO.toString());

    }

    @Test
    public void bigInterToStringTest() {
        BigInterToStringBDTO bigInterToStringBDTO = new BigInterToStringBDTO();
        bigInterToStringBDTO.setValue(new BigInteger("123"));
        BigInterToStringADTO bigInterToStringADTO = new BigInterToStringADTO();
        BeanUtils.copy(bigInterToStringBDTO, bigInterToStringADTO);
        Assert.assertEquals(bigInterToStringBDTO.toString(), bigInterToStringADTO.toString());

    }

}
