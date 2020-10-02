package com.lsnu.entitis;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author Jenson
 * @since 2020/10/2 11:03
 **/
@Data
@Accessors(chain = true)
public class Student implements Serializable {

    private Integer id;
    private String name;
    private Integer age;

}
