package com.example.lovestory.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@TableName("user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @TableId(value = "id")// 主键必须有TableId注解
    private Integer id;

    private String name;

    private String age;

    private Double relation;
}
