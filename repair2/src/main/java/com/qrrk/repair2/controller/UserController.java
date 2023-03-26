package com.qrrk.repair2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qrrk.repair2.entity.Order;
import com.qrrk.repair2.entity.RepairInfo;
import com.qrrk.repair2.entity.RepairMan;
import com.qrrk.repair2.service.OrderService;
import com.qrrk.repair2.service.RepairInfoService;
import com.qrrk.repair2.service.RepairManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private RepairManService repairManService;
    @Autowired
    private RepairInfoService repairInfoService;
    @Autowired
    private OrderService orderService;
    @RequestMapping("/userinfo")
    public String userInfo(HttpSession session, Model model){
        Object login = session.getAttribute("login");
        QueryWrapper<RepairMan> repairmanQueryWrapper = new QueryWrapper<>();
        repairmanQueryWrapper.eq("phone",login);
        RepairMan repairMan = repairManService.getOne(repairmanQueryWrapper);
        model.addAttribute("repairman",repairMan);
        return "user/userinfo";
    }
    @RequestMapping("/userlistinfo")
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

        return "user/listrepairinfo";
    }

    @RequestMapping("/selectRepairinfo")
    public String selectRepairman(Model model,Integer uprepairid){
        RepairInfo repairInfo = repairInfoService.getById(uprepairid);
        model.addAttribute("repairInfo",repairInfo);
        return "user/select";

    }
    @RequestMapping("/userlistorder")
    public String listOrder(Model model, @RequestParam(value = "pn",defaultValue = "1")Integer pn,HttpSession session){
        Page<Order> orderPage= new Page<>(pn, 4);
        Object login = session.getAttribute("login");
        QueryWrapper<RepairMan> repairmanQueryWrapper = new QueryWrapper<>();
        repairmanQueryWrapper.eq("phone",login);
        RepairMan repairMan = repairManService.getOne(repairmanQueryWrapper);
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("repairmanid",repairMan.getId());
        Page<Order> page = orderService.page(orderPage,orderQueryWrapper);
        model.addAttribute("orders",page);
        return "user/listorders";
    }
    @GetMapping("/userupdateorder")
    public String updateInfo(@RequestParam int id,@RequestParam(value ="pn",defaultValue = "1") Integer pn,
                             RedirectAttributes redirectAttributes){
        Order order = orderService.getById(id);
        order.setStatus(1);
        orderService.updateById(order);

        redirectAttributes.addAttribute("pn",pn);
        return "redirect:userlistorder";
    }
}
