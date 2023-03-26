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
@TableName("`order`")
public class Order {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer uprepairid;
    private Integer repairmanid;
    private Date starttime;
    private Integer status;
}
