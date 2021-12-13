package org.hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = {"/hello-basic", "/hello-go"}, method = RequestMethod.GET)
    public String helloBasic() {
        log.info("helloBasic");
        return HttpStatus.OK.toString();
    }

    /**
     * PathVariable
     * 변수명 같으면 생략가능
     * @PathVariable("userId") String userId -> @PathVariable String userId
     * /mapping/userA
     * @return
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String id) {
        log.info("mappingPath userId = {}", id);
        return HttpStatus.OK.toString();
    }

    @GetMapping("/mapping/{userId}/order/{orderId}")
    public String mappingPath(@PathVariable("userId") String id, @PathVariable("orderId") Long orderId) {
        log.info("mappingPath userId = {}, orderId = {}", id, orderId);
        return HttpStatus.OK.toString();
    }

    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes() {
        log.info("mappingConsume");
        return HttpStatus.OK.toString();
    }

    @PostMapping(value = "/mapping-produces", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduces() {
        log.info("mappingProduces");
        return HttpStatus.OK.toString();
    }

}
