package com.example.lovestory.entity;

import com.example.lovestory.annotation.EnumValid;
import com.example.lovestory.enums.AgeEnum;
import com.example.lovestory.enums.NameEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReq {

    @NotNull
    @Max(100)
    @Min(20)
    private Integer id;

    @EnumValid(value = NameEnum.class)
    private String name;

    @EnumValid(value = AgeEnum.class)
    private String age;


    private Double relation;

    @NotEmpty
    private List<String> list;
}
