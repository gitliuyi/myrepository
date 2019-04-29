package framework.aop.config;

import lombok.Data;

/*
create by Jack on 2019/4/21
 */
@Data
public class AopConfig {
    private String pointCut;
    private String aspectBefore;
    private String aspectAfter;
    private String aspectClass;

}