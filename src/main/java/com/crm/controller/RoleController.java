package com.crm.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crm.common.util.result.Result;
import com.crm.entity.Role;
import com.crm.entity.Test;
import com.crm.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zy
 * @since 2021-08-23
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)   //跨域处理
public class RoleController {

    @Autowired
    TestService testService;

    /**
     * 页面：系统管理-角色管理
     */
    //  角色管理
    //  参数：Test(值放入role等其他对象)、页码相关
    @GetMapping("/systemRoleManage")
    public Result systemRoleManage(@RequestParam(defaultValue = "1") Integer currentPage, @RequestBody Test test) {
        //页码相关
        Page<Test> page = new Page<>(currentPage, 5);
        Page<Test> testPage = testService.selectSystemRoleManageByTest(page, test);

        return Result.succ(200, "成功", testPage);
    }


    //  角色添加
    //  参数：Test(值放入role等其他对象) 注意role.status需要传入数值，而非字符串
    @PostMapping("/systemRoleAdd")
    public Result systemRoleAdd(@RequestBody Test test) {

        try {
            testService.selectSystemRoleAdd(test);
        } catch (Exception e) {
            System.out.println("there is nothing---RoleController");
        }

        return Result.succ(200, "成功", test);
    }

    //  角色详情
    //  参数：Test(值放入role等其他对象) 注意至少要有role.rname
    @GetMapping("/systemRoleDetail")
    public Result systemRoleDetail(@RequestBody Test test) {

        return Result.succ(200, "成功", testService.systemRoleDetail(test));
    }


    //  角色修改
    //  参数  Test(role.roleid；用户在 角色管理页面，点击角色修改时，需要将用户角色id传入后端)
    @PostMapping("/systemRoleUpdate")
    public Result systemRoleUpdate(@RequestBody Test test) {

        testService.systemRoleUpdate(test);

        return Result.succ(200, "成功", test);
    }


    //  角色删除(逻辑删除，仅将status改为0)
    //  参数  Test(emp.logname)
    @GetMapping("/systemRoleDelete")
    public Result systemUserDelete(@RequestBody Test test) {

        testService.systemRoleDelete(test);

        return Result.succ(200, "成功", test);
    }

}










