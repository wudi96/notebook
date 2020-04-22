package com.notebook.serialize;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author wudi
 */
@Getter
@Setter
@Accessors(chain = true)
public class Person implements Serializable {

    private int age;

    private String name;

    private String sex;

}
