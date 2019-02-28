package com.ht000.test.demo.controller;


import com.ht000.test.demo.service.redisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@Api(tags = "测试redis", description = "测试redis")
@RequestMapping("/redis")
public class redisController {
    @Autowired
    redisService redisService;

    @ApiOperation("insert")
    @PostMapping("/insert")
    public Map insert(@ApiParam("请求参数对象") @RequestBody Map<String, Object> paramMap) {
        Map resultMap = new HashMap();
        String key = paramMap.get("key").toString();
        String value = paramMap.get("value").toString();
        if (redisService.set(key,value)){
            resultMap.put("nb","ok");
        }else{
            resultMap.put("不对","有问题");
        }
        return resultMap;
    }

    @ApiOperation("插入列表")
    @PostMapping("/insertList")
    public Map insertList(@ApiParam("请求参数对象") @RequestBody Map<String, Object> paramMap) {
        Map resultMap = new HashMap();
        String key = paramMap.get("key").toString();
        String value =paramMap.get("value").toString();
        if (redisService.lPush(key,value)){
            resultMap.put("nb","ok");
        }else{
            resultMap.put("不对","有问题");
        }
        return resultMap;
    }

    @ApiOperation("getList")
    @PostMapping("/getList")
    public Map getList(@ApiParam("请求参数对象") @RequestBody Map<String, Object> paramMap) {
        Map resultMap = new HashMap();
        String key = paramMap.get("key").toString();
        List value = redisService.lRange(key,1,4);
        resultMap.put("value是",value);
        return resultMap;
    }

    @ApiOperation("getInfo")
    @PostMapping("/getInfo")
    public Map getInfo(@ApiParam("请求参数对象") @RequestBody Map<String, Object> paramMap) {
        Map resultMap = new HashMap();
        String key = paramMap.get("key").toString();
        String value = redisService.get(key).toString();
        resultMap.put("value是",value);
        return resultMap;
    }
}
