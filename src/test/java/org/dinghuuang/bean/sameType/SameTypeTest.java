package org.dinghuuang.bean.sameType;

import org.dinghuuang.bean.sameType.dto.SameTypeADTO;
import org.dinghuuang.bean.sameType.dto.SameTypeBDTO;
import org.dinghuuang.bean.util.BeanUtils;
import com.github.houbb.data.factory.core.util.DataUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * @version 1.0
 * @Author: edisionding
 * @Description:
 * @Date: since 2022/7/27 11:37
 * @Modify By: edisionding
 */
public class SameTypeTest {

    @Test
    public void sameTypeTest() {
        //测试相同类型情况下的转换
        SameTypeADTO sameTypeADTO = DataUtil.build(SameTypeADTO.class);
        SameTypeBDTO sameTypeBDTO = new SameTypeBDTO();
        BeanUtils.copy(sameTypeADTO, sameTypeBDTO);
        Assert.assertEquals(sameTypeADTO.toString(), sameTypeBDTO.toString());
    }

}
