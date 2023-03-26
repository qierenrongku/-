package com.qrrk.repair2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.qrrk.repair2.entity.Admin;
import com.qrrk.repair2.entity.Order;
import com.qrrk.repair2.entity.RepairInfo;
import com.qrrk.repair2.entity.RepairMan;
import com.qrrk.repair2.service.AdminService;
import com.qrrk.repair2.service.OrderService;
import com.qrrk.repair2.service.RepairInfoService;
import com.qrrk.repair2.service.RepairManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@Controller
public class WebController {
    @Autowired
    private RepairInfoService repairInfoService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private RepairManService repairManService;

    @Autowired
    private OrderService orderService;
    //维修提交页面
    @RequestMapping("/repair")
    public String index() {
        return "repair";
    }

    //上报维修信息
    @RequestMapping("/submitInfo")
    public String success(String name, String number, String address, String info, String phone) {
        RepairInfo repairInfo = new RepairInfo();
        repairInfo.setName(name);
        repairInfo.setNumber(number);
        repairInfo.setAddress(address);
        repairInfo.setInfo(info);
        repairInfo.setPhone(phone);
        repairInfo.setSubtime(new Date());
        repairInfo.setStatus(0);
        repairInfoService.save(repairInfo);
        return "redirect:repair.html";
    }

    //前往登陆页面
    @RequestMapping("/gologin")
    public String gologin() {
        return "redirect:login.html";
    }



    // 前往短信通知页面
    @RequestMapping("/gosend")
    public String goSend() {
        return "regulation/send";
    }


    //登陆前往管理页面
    @RequestMapping("/login")
    public String login(String username, String password, Model model, HttpSession session,String key) {
        if("0".equals(key)){
            if (adminService.getOne(new QueryWrapper<Admin>().eq("username", username).eq("password", password), false) == null) {
                model.addAttribute("msg", "用户名或密码错误");
                return "redirect:login.html";
            } else {
                session.setAttribute("login", username);
                return "regulation/hello";
            }
        }else {
            if (repairManService.getOne(new QueryWrapper<RepairMan>().eq("phone", username).eq("password", password), false) == null) {
                model.addAttribute("msg", "用户名或密码错误");
                return "redirect:login.html";
            } else {
                session.setAttribute("login", username);
                return "user/hello";
            }
        }


    }

    @RequestMapping("/exit")
    public String loginout(HttpSession session) {
        session.removeAttribute("login");
        return "redirect:login.html";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "regulation/hello";
    }

    @GetMapping("/update/{id}")
    public String updateRepairMan(@PathVariable int id, Model model) {
        model.addAttribute("id", id);
        RepairMan updateman = repairManService.getById(id);
        model.addAttribute("updateman", updateman);
        return "regulation/update";
    }
    @GetMapping("/updateorder/{id}")
    public String updateOrder(@PathVariable int id, Model model) {
        model.addAttribute("id", id);
        Order updateorder = orderService.getById(id);
        model.addAttribute("updateorder", updateorder);
        return "regulation/updateorder";
    }

    @RequestMapping("/goadd")
    public String addRepairMan() {
        return "regulation/addrepairman";
    }

    @RequestMapping("/goselect")
    public String selectRepairMan(Model model) {
        List<RepairMan> repairmans = repairManService.list();
        model.addAttribute("repairmans",repairmans);
        return "regulation/select";
    }
}
