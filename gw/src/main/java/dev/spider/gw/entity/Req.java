package dev.spider.gw.entity;

import dev.spider.gw.valid.ValidHello;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author spider
 */
public class Req implements Serializable {
    @NotBlank(message = "姓名为空", groups = ValidHello.ValidA.class)
    private String name;
    @NotNull(message = "技术栈为空")
    private List<String> pl;
    @NotBlank(message = "年龄为空", groups = ValidHello.ValidB.class)
    private String age;
    @NotNull(message = "类型不能为空")
    private int type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPl() {
        return pl;
    }

    public void setPl(List<String> pl) {
        this.pl = pl;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
