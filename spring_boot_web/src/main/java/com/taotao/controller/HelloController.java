package com.taotao.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

/**
 * @ClassName HelloController
 * @Description
 * @Author tyq
 * @Date 2020/4/27 17:38
 * @Version V1.0
 **/
@RestController
public class HelloController {

    @RequestMapping(value = "/hello")
    public String sayHi () {
        return "welcome to springboot";
    }

    @RequestMapping("/success")
    public Map<String, Object > success (Map<String,Object> map){
        map.put("hello","成功");
        map.put("zhangsan","张三");
        map.put("lisi","<h1>lisi</h1>");
        map.put("wangwu","wangwu");
        map.put("users", Arrays.asList("张三","lisi","wangwu"));
        return map;
    }
}
