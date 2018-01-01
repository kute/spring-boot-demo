package com.kute.demo.controller;

import com.google.common.collect.Maps;
import com.kute.demo.config.PropertiesBean;
import com.kute.demo.po.UserData;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by kute on 2017/12/7.
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private static Map<String, UserData> map = Maps.newConcurrentMap();

    @Autowired
    private PropertiesBean propertiesBean;

    @ApiOperation(value = "获取property bean属性")
    @GetMapping("/name")
    public String getName() {
        return propertiesBean.getName();
    }

    @ApiOperation(value = "获取具体用户", notes = "id为long类型")
    @ApiImplicitParam(name = "id", value = "long类型id", required = true, paramType = "path", dataTypeClass = Long.class)
    @RequestMapping(value = "/name/{id}", method = RequestMethod.GET)
    public UserData getName(@PathVariable Long id) {
        return map.getOrDefault(id.toString(), new UserData("000", "default name"));
    }

    @ApiOperation(value = "获取具体用户", notes = "id name")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "long类型id", required = true, paramType = "path", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "name", value = "String", required = true, paramType = "path", dataTypeClass = String.class)
    })
    @RequestMapping(value = "/name/{id}/{name}", method = RequestMethod.GET)
    public UserData getName(
            @PathVariable Long id,
            @PathVariable String name) {
        return map.getOrDefault(id.toString(), new UserData("000", "default name"));
    }

}
