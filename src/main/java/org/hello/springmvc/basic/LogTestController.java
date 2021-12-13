package org.hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
    // private final Logger log = LoggerFactory.getLogger(LogTestController.class);
    //private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        log.trace("trace = {}", name);
        log.debug("debug = {}", name);
        log.info("log = {}", name);
        log.warn("warn = {}", name);
        log.error("error = {}", name);

        return HttpStatus.OK.toString();
    }
}
