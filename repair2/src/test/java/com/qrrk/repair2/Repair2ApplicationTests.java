package com.qrrk.repair2;

import com.qrrk.repair2.service.OrderService;
import com.qrrk.repair2.service.RepairInfoService;
import com.qrrk.repair2.service.RepairManService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Repair2ApplicationTests {
    @Autowired
    private OrderService orderService;
    @Autowired
    private RepairManService repairManService;
    @Autowired
    private RepairInfoService repairInfoService;
    @Test
    void contextLoads() {
//        System.out.println(repairInfoService.list());
        System.out.println(orderService.list());

    }

}
