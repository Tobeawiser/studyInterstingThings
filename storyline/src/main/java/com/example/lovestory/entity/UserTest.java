package com.example.lovestory.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTest implements Comparable {

    @TableId(value = "id")// 主键必须有TableId注解
    private Integer id;

    private String name;

    private String age;
    @TableField(exist = false)
    private Double relation;

    @Override
    public int compareTo(Object o) {
        UserTest another = (UserTest) o;
        return this.getName().compareTo(another.getName());
    }
}
