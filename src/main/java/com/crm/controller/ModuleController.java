package com.crm.controller;


import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crm.common.util.result.Result;
import com.crm.entity.Test;
import com.crm.service.TestService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 功能模块 前端控制器
 * </p>
 *
 * @author zy
 * @since 2021-08-23
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)   //跨域处理
public class ModuleController {

    @Autowired
    TestService testService;


    /**
     * 页面：系统管理-权限管理
     */
    //  权限管理
    //  参数：Test(把搜索条件当做module等其他对象传入)、页码相关
    @GetMapping("/systemModuleManage")
    public Result systemModuleManage(@RequestParam(defaultValue = "1") Integer currentPage, @RequestBody Test test) {
        //页码相关
        Page<Test> page = new Page<>(currentPage, 5);
        Page<Test> testPage = testService.selectSystemModuleManageByTest(page, test);

        return Result.succ(200, "成功", testPage);
    }

    //  权限添加
    //  参数：Test(值放入role等其他对象) 注意role.status需要传入数值，而非字符串
    @PostMapping("/systemModuleAdd")
    public Result systemModuleAdd(@RequestBody Test test) {

        try {
            testService.selectSystemModuleAdd(test);
        } catch (Exception e) {
            System.out.println("there is nothing---ModuleController");
        }
        return Result.succ(200, "成功", test);
    }


    //  权限详情
    //  参数：Test(值放入role等其他对象)
    @GetMapping("/systemModuleDetail")
    public Result systemModuleDetail(@RequestBody Test test) {

        return Result.succ(200, "成功", testService.systemModuleDetail(test));
    }

    //  权限删除(逻辑删除，仅将status改为0)
    //  参数  Test(module.modulename)
    @GetMapping("/systemModuleDelete")
    public Result systemModuleDelete(@RequestBody Test test) {

        testService.systemModuleDelete(test);

        return Result.succ(200, "成功", test);
    }

    //  权限报表
    //  参数  Test(module.modulename)
    @GetMapping("/systemModuleReport")
    public Result systemModuleReport(@RequestParam(defaultValue = "1") Integer currentPage, @RequestBody Test test) {

        //页码相关
        Page<Test> page = new Page<>(currentPage, 5);

        //该页面和 用户管理页面部分字段重复，直接调用 selectSystemUserManageByTest 方法
        Page<Test> testPage = testService.selectSystemUserManageByTest(page, test);

        //查询 权限  权限范围
        testPage = testService.systemModuleReport(testPage);

        return Result.succ(200, "成功", testPage);
    }

}
