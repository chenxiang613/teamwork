package com.future.teamwork.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.future.teamwork.service.RoleService;
import com.future.teamwork.test.DateTest;

@Controller
@RequestMapping("binding")
public class BindingParamsController {

    @Autowired
    private RoleService roleService;


    @RequestMapping(value = "/bindingPage", method = RequestMethod.GET)
    public ModelAndView bindingPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/binding/bindingParams");
        return modelAndView;
    }
    
    @RequestMapping(value = "/bindingParamsEdit",method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView bindingParamsEdit(DateTest dateTest) {
    	System.err.println(dateTest.getDescpt().getTime());
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("/binding/bindingParams");
        return modelAndView;
    }
    
    //restful风格的参数传递方法，将参数放在请求路径里面，如示例中的name
    @RequestMapping(value = "/rest/{name}", method = RequestMethod.GET)
    public ModelAndView rest(@PathVariable("name") String name) {
    	System.err.println(name);
    	ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/binding/bindingParams");
        return modelAndView;
    }

}
