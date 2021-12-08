package dev.spider.gw.entity;

import dev.spider.gw.valid.DefaultValidGroup;
import dev.spider.gw.valid.HelloValidGroup;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author spider
 */
@Data
public class Req implements Serializable {
    @NotBlank(message = "姓名为空", groups = {HelloValidGroup.ValidA.class,DefaultValidGroup.Default.class})
    private String name;

    @NotNull(message = "技术栈为空", groups = DefaultValidGroup.class)
    private List<String> pl;

    @NotBlank(message = "年龄为空", groups = HelloValidGroup.ValidB.class)
    private String age;

    @NotNull(message = "类型不能为空")
    private Integer type;
}
