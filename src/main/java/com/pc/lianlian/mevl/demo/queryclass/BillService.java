package com.pc.lianlian.mevl.demo.queryclass;

import com.google.common.collect.Lists;
import com.pc.something.User;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 *
 * @author pengchao
 * @since 2022/8/22 15:09
 */
public class BillService extends QueryService<BillService.Bill> {

    @Override
    public Bill doQuery(Map<String, Object> inputParamMap, String activity) {
        //模拟查询
        Bill bill = new Bill();
        bill.setProductIds(Lists.newArrayList(1003L,1004L));
        bill.setUserId(1002L);
        bill.setId((Long) inputParamMap.get("billId"));
        System.out.println("查询成功: " + bill);
        return bill;
    }

    @Data
    public static class Bill {
        private Long id;
        private Long userId;
        private List<Long> productIds;
    }
}
