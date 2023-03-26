package com.qrrk.repair2.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qrrk.repair2.entity.Order;
import com.qrrk.repair2.entity.RepairMan;
import com.qrrk.repair2.service.OrderService;
import com.qrrk.repair2.service.RepairInfoService;
import com.qrrk.repair2.service.RepairManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private RepairInfoService repairInfoService;
    @Autowired
    private RepairManService repairManService;
    @RequestMapping("/goaddorder")
    public String goaddOrder(){
        return "regulation/addorder";
    }

    @RequestMapping("/listorder")
    public String listOrder(Model model, @RequestParam(value = "pn",defaultValue = "1")Integer pn){
        Page<Order> orderPage= new Page<>(pn, 4);
        Page<Order> page = orderService.page(orderPage);
        model.addAttribute("orders",page);
        return "regulation/listorders";
    }
    @GetMapping("/deleteorder")
    public String deleteInfo(@RequestParam int id,@RequestParam(value ="pn",defaultValue = "1") Integer pn,
                             RedirectAttributes redirectAttributes){
        orderService.removeById(id);
        redirectAttributes.addAttribute("pn",pn);
        return "redirect:listorder";
    }
    @RequestMapping("/addorder")
    public String addOrder(Integer uprepairid,Integer repairmanid){
        if(repairInfoService.getById(uprepairid)!=null&&repairManService.getById(repairmanid)!=null){
            Order order = new Order();
            order.setUprepairid(uprepairid);
            order.setRepairmanid(repairmanid);
            order.setStatus(0);
            order.setStarttime(new Date());
            orderService.save(order);
            return "redirect:listorder";
        }else {
            return "redirect:goaddorder";
        }


    }
//    @GetMapping("/updateorder")
//    public String updateInfo(@RequestParam int id,@RequestParam(value ="pn",defaultValue = "1") Integer pn,
//                             RedirectAttributes redirectAttributes){
//        Order order = orderService.getById(id);
//        order.setStatus(1);
//        orderService.updateById(order);
//
//        redirectAttributes.addAttribute("pn",pn);
//        return "redirect:listorder";
//    }
    @RequestMapping("/updateorder/{id}")
    public String updateOrder(@PathVariable Integer id,Date date,String uprepairid,String repairmanid,String status,
                                  @RequestParam(value ="pn",defaultValue = "1") Integer pn,
                                  RedirectAttributes redirectAttributes){
        Order order = new Order(id, Integer.parseInt(uprepairid),Integer.parseInt(repairmanid), date, 0);
        if("1".equals(status)){
            order.setStatus(1);
        }

        orderService.updateById(order);
        redirectAttributes.addAttribute("pn",pn);
        System.out.println("++++++++++++++++++++++");
        return "redirect:/listorder";
    }

}
