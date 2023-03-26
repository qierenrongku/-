package com.qrrk.repair2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("uprepair")
public class RepairInfo {
    @TableId(type = IdType.AUTO)
    private  Integer id;
    private Date subtime;
    private String name;
    private String number;
    private String address;
    private String info;
    private String phone;
    private Integer status;
}
