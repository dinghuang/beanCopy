package org.dinghuuang.bean.extend;

import org.dinghuuang.bean.extend.dto.ExtendA2DTO;
import org.dinghuuang.bean.extend.dto.ExtendADTO;
import org.dinghuuang.bean.extend.dto.ExtendBDTO;
import org.dinghuuang.bean.util.BeanUtils;
import com.github.houbb.data.factory.core.util.DataUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @version 1.0
 * @Author: edisionding
 * @Description:
 * @Date: since 2022/7/28 14:29
 * @Modify By: edisionding
 */
public class ExtendTest {

    @Test
    public void extendTest() {
        //测试相同类型情况下的转换
        ExtendADTO extendADTO = DataUtil.build(ExtendADTO.class);
        ExtendBDTO extendBDTO = new ExtendBDTO();
        BeanUtils.copy(extendADTO, extendBDTO);
        Assert.assertEquals(extendADTO.toString(), extendBDTO.toString());
        ExtendA2DTO extendA2DTO = DataUtil.build(ExtendA2DTO.class);
        ExtendBDTO extendB2DTO = new ExtendBDTO();
        BeanUtils.copy(extendA2DTO, extendB2DTO);
        Assert.assertEquals(extendA2DTO.toString(), extendB2DTO.toString());
    }

}
