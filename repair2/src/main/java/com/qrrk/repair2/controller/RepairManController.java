package com.qrrk.repair2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.qrrk.repair2.entity.RepairMan;
import com.qrrk.repair2.service.RepairManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class RepairManController {

    @Autowired
    private RepairManService repairManService;

//    员工列表
    @RequestMapping("/list")
    public String listRepairMan(Model model,@RequestParam(value ="pn",defaultValue = "1") Integer pn){
        Page<RepairMan> repairManPage= new Page<>(pn, 4);
        Page<RepairMan> page = repairManService.page(repairManPage);
        model.addAttribute("repairmans",page);
        return "regulation/list";
    }
//    删除员工
    @GetMapping("/delete/{id}")
    public String deleteRepairMan(@PathVariable int id,
                                  @RequestParam(value ="pn",defaultValue = "1") Integer pn,
                                    RedirectAttributes redirectAttributes){

        repairManService.removeById(id);
        redirectAttributes.addAttribute("pn",pn);
        return "redirect:/list";
    }
//  修改员工
    @RequestMapping("/update/{id}")
    public String updateRepairMan(@PathVariable int id,String name,String password,String sex,int age,String phone,String section,
                                  @RequestParam(value ="pn",defaultValue = "1") Integer pn,
                                  RedirectAttributes redirectAttributes){
        RepairMan repairMan = new RepairMan(id,name,password,sex,age,phone,section);
        repairManService.updateById(repairMan);
        redirectAttributes.addAttribute("pn",pn);

        return "redirect:/list";
    }
//  添加员工
    @RequestMapping("/addman")
    public String addRepairMan(String name,String password,String sex,int age,String phone,String section){
        RepairMan repairMan = new RepairMan();
        repairMan.setName(name);
        repairMan.setSex(sex);
        repairMan.setPassword(password);
        repairMan.setAge(age);
        repairMan.setPhone(phone);
        repairMan.setSection(section);
        repairManService.save(repairMan);
        return "redirect:list";

    }
//   查找员工
    @RequestMapping("/selectRepairman")
    public String selectRepairman(Model model,String name,String section){
        QueryWrapper<RepairMan> repairManQueryWrapper = new QueryWrapper<>();
        repairManQueryWrapper.like("name",name).like("section",section);
        List<RepairMan> repairmans = repairManService.list(repairManQueryWrapper);
        model.addAttribute("repairmans",repairmans);
        return "regulation/select";

    }

}
