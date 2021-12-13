package org.hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.hello.springmvc.basic.HelloData;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void reqParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {} age = {}", username, age);

        response.getWriter().write(HttpStatus.OK.toString());
    }

    //query string /request-param-v2?username=w&age=1
    @ResponseBody // @RestController
    @RequestMapping("/request-param-v2")
    public String reqParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {

        log.info("username = {}, age = {}", memberName, memberAge);

        return HttpStatus.OK.toString();
    }

    //query string /request-param-v2?username=w&age=1
    @ResponseBody // @RestController
    @RequestMapping("/request-param-v3")
    public String reqParamV3(
            @RequestParam String username,
            @RequestParam int age) {

        log.info("username = {}, age = {}", username, age);

        return HttpStatus.OK.toString();
    }


    //query string /request-param-v2?username=w&age=1
    @ResponseBody // @RestController
    @RequestMapping("/request-param-v4")
    public String reqParamV4(
            String username,
            int age) {

        log.info("username = {}, age = {}", username, age);

        return HttpStatus.OK.toString();
    }

    //query string /request-param-v2?username=w&age=1
    @ResponseBody // @RestController
    @RequestMapping("/request-param-required")
    public String reqParamRequired(
            @RequestParam(required = true, defaultValue = "guest") String username, // default있으면 required의미없음.
            @RequestParam(required = false) int age) { // null처리 x -> Integer :: http 스펙대로 전송해도 오류.

        log.info("username = {}, age = {}", username, age);

        return HttpStatus.OK.toString();
    }

    //query string /request-param-v2?username=w&age=1
    @ResponseBody // @RestController
    @RequestMapping("/request-param-map")
    public String reqParamMap(
            @RequestParam Map<String, Object> paramMap) {

        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));

        return HttpStatus.OK.toString();
    }

    @ResponseBody // @RestController
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData data) {

        log.info("username = {}, age = {}", data.getUsername(), data.getAge());

        return HttpStatus.OK.toString();
    }

    @ResponseBody // @RestController
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData data) {

        log.info("username = {}, age = {}", data.getUsername(), data.getAge());

        return HttpStatus.OK.toString();
    }

}
