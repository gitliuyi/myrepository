package framework.webmvc.servlet;

import lombok.Data;

import java.util.Map;

/*
create by Jack on 2019/4/20
 */
@Data
public class ModelAndView {

    private String view;
    private Map<String,Object> model;

    public ModelAndView(String view, Map<String, Object> model) {
        this.view = view;
        this.model = model;
    }
}