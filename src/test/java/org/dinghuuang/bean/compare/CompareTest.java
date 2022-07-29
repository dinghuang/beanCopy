package org.dinghuuang.bean.compare;

import org.dinghuuang.bean.compare.dto.SameTypeADTOConversionSameTypeBDTO;
import org.dinghuuang.bean.sameType.dto.SameTypeADTO;
import org.dinghuuang.bean.sameType.dto.SameTypeBDTO;
import org.dinghuuang.bean.util.BeanUtils;
import org.dinghuuang.bean.util.OldUtils;
import com.github.houbb.data.factory.core.util.DataUtil;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

/**
 * @version 1.0
 * @Author: edisionding
 * @Description:
 * @Date: since 2022/7/28 16:15
 * @Modify By: edisionding
 */
public class CompareTest {

    @Test
    public void singleThreadOldTest() {
        //目前的性能对比
        SameTypeADTO sameTypeADTO = DataUtil.build(SameTypeADTO.class);
        //先提前放到缓存中
        SameTypeBDTO temp = new SameTypeBDTO();
        BeanUtils.copy(sameTypeADTO, temp);
        int[] ints = new int[]{100, 1000, 10000, 100000, 1000000, 10000000};
        for (int anInt : ints) {
            long time0 = System.currentTimeMillis();
            for (int i = 0; i < anInt; i++) {
                SameTypeBDTO sameTypeBDTO = new SameTypeBDTO();
                OldUtils.copyPropertiesIgnoreCase(sameTypeADTO, sameTypeBDTO);
            }
            long time3 = System.currentTimeMillis();
            System.out.println(anInt + " OldUtils spend " + (time3 - time0) + "ms");

            long time01 = System.currentTimeMillis();
            for (int i = 0; i < anInt; i++) {
                SameTypeBDTO sameTypeBDTO = new SameTypeBDTO();
                BeanUtils.copy(sameTypeADTO, sameTypeBDTO);
            }
            long time31 = System.currentTimeMillis();
            System.out.println(anInt + " BeanUtils spend " + (time31 - time01) + "ms");

            long time02 = System.currentTimeMillis();
            for (int i = 0; i < anInt; i++) {
                SameTypeBDTO sameTypeBDTO = new SameTypeBDTO();
                SameTypeADTOConversionSameTypeBDTO.conversion(sameTypeADTO, sameTypeBDTO);
            }
            long time32 = System.currentTimeMillis();
            System.out.println(anInt + " Java spend " + (time32 - time02) + "ms");
            System.out.println("=========================================================");
        }

    }

    @Test
    public void multipleThreadTest() {
        //并发测试
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            service.execute(() -> {
                SameTypeADTO sameTypeADTO = DataUtil.build(SameTypeADTO.class);
                SameTypeBDTO sameTypeBDTO = new SameTypeBDTO();
                BeanUtils.copy(sameTypeADTO, sameTypeBDTO);
            });

        }
    }
}
