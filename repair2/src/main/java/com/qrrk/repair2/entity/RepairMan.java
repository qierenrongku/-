package com.qrrk.repair2.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("repairman")
public class RepairMan {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String password;
    private String sex;
    private Integer age;
    private String phone;
    private String section;
}
