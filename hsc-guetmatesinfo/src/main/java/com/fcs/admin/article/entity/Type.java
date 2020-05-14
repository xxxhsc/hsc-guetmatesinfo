package com.fcs.admin.article.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.List;

/**
 * @Author: hsc
 * @Description:
 * @Date: 2020/5/10 23:38
 */
@Data
@TableName("t_type")
public class Type {
private  int id;
private  String label;
private  int parentid;

 @TableField(exist = false)
 List<Type> childrenType;

 }
