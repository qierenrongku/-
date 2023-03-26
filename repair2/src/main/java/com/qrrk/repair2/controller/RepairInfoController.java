package com.qrrk.repair2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.qrrk.repair2.entity.Order;
import com.qrrk.repair2.entity.RepairInfo;
import com.qrrk.repair2.service.OrderService;
import com.qrrk.repair2.service.RepairInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class RepairInfoController {
    @Autowired
    private RepairInfoService repairInfoService;
    @Autowired
    private OrderService orderService;

    @RequestMapping("/listinfo")
    public String listRepairMan(Model model, @RequestParam(value = "pn",defaultValue = "1")Integer pn){
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("status",1);
        List<Order> orders = orderService.list(orderQueryWrapper);
        for (Order order:orders
             ) {
            Integer uprepairid = order.getUprepairid();
            RepairInfo byId = repairInfoService.getById(uprepairid);
            byId.setStatus(1);
            repairInfoService.updateById(byId);
        }
        Page<RepairInfo> repairInfoPage= new Page<>(pn, 4);
        Page<RepairInfo> page = repairInfoService.page(repairInfoPage);
        model.addAttribute("repairinfos",page);

        return "regulation/listrepairinfo";
    }

    @GetMapping("/deleteinfo")
    public String deleteInfo(@RequestParam int id,@RequestParam(value ="pn",defaultValue = "1") Integer pn,
                             RedirectAttributes redirectAttributes){
        repairInfoService.removeById(id);
        redirectAttributes.addAttribute("pn",pn);
        return "redirect:listinfo";
    }



}
