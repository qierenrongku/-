package com.qrrk.repair2.controller;


import com.qrrk.repair2.sms.SmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SendMessageController {
    @Autowired
    private SmsUtils smsUtils;
    @RequestMapping("/sendMessage")
    public String send(String name,String rphone,String phone,String address,String info){
        String[] phonelist ={rphone};
        String[] params ={name,address,phone,info};
        smsUtils.sendMessage(phonelist,params);
        return "regulation/send";
    }

}
