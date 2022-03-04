package com.autotest.pojo;

/**
 * @ClassName DemoData
 * @Description TODO
 * @Author tyx
 * @Date 2022/3/4 10:40
 */
public class DemoData {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "DemoData{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
