package org.hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView resView1() {
        return new ModelAndView("response/hello").addObject("data", "hello");
    }

    @RequestMapping("/response-view-v2")
    public String resView2(Model model) {
        model.addAttribute("data", "hello");
        return "response/hello";
    }

    @RequestMapping("/response/hello")
    public void resView3(Model model) {
        model.addAttribute("data", "hello");
    }
}
