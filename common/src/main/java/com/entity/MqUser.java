package com.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MqUser implements Serializable {


    private Integer id;

    private String name;

    private String age;

    private Double relation;


}
