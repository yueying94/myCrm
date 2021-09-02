package com.crm.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crm.common.util.result.Result;
import com.crm.entity.Dept;
import com.crm.entity.Emp;
import com.crm.entity.Position;
import com.crm.entity.Test;
import com.crm.service.TestService;
import com.crm.vo.SystemUserManageSetVo;
import com.crm.vo.SystemUserManageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zy
 * @since 2021-08-23
 */
@RestController
@RequestMapping("/sys")
@CrossOrigin(origins = "*", maxAge = 3600)   //跨域处理
public class EmpController {

    @Autowired
    TestService testService;

    @Autowired
    Test test;

    @Autowired
    Emp emp;

    @Autowired
    Dept dept;

    @Autowired
    Position position;


    /**
     *  页面：系统管理-用户管理
     *  Java BeanUtil.copyProperties(); 对象属性拷贝
     *  需要用到多实例bean，prototype？
     */
    //  用户管理
    //  参数：Test(值放入emp等其他对象)、页码相关
    @PostMapping("/account")
//    public Result selectSystemUserManage(@RequestParam(defaultValue = "1") Integer currentPage, @RequestBody Test test) {
    public Result selectSystemUserManage(@RequestBody SystemUserManageVo systemUserManageVo) {


        System.out.println(systemUserManageVo);
        //组装数据
        emp.setStatus(Objects.equals(systemUserManageVo.getStatusValue(), "有效") ?1:0);
        emp.setEname(systemUserManageVo.getUserNameValue());
        emp.setLogname(systemUserManageVo.getLogNameValue());

        dept.setDeptname(systemUserManageVo.getDeptValue());

        position.setPname(systemUserManageVo.getPositionValue());

        test.setEmp(emp);
        test.setDept(dept);
        test.setPosition(position);

        //页码相关
        Page<Test> page = new Page<>(systemUserManageVo.getCurrentPage(), systemUserManageVo.getPageSize());
        Page<Test> testPage = testService.selectSystemUserManageByTest(page, test);

        return Result.succ(200, "成功", testPage);
    }

    //  用户添加    未添加备注信息,也未创建数据表
    @PostMapping("/userAdd")
    public Result systemUserAdd(@RequestBody Test test) {

        try {
            testService.systemUserAdd(test);
        } catch (Exception e) {
            System.out.println("there is nothing---EmpController");
        }
        //成功后，返回主键  empid
        return Result.succ(200, "成功", test);
    }

    //  用户详情
    //  参数  Test(emp.logname)
    @GetMapping("/userDetail")
    public Result systemUserDetail(@RequestBody Test test) {

        return Result.succ(200, "成功", testService.systemUserDetail(test));
    }

    //  用户修改
    //  参数  Test(emp.empid；用户在用户管理页面，点击用户修改时，需要向后端查询用户id，并在修改时传入)
    @PostMapping("/userUpdate")
    public Result systemUserUpdate(@RequestBody SystemUserManageSetVo systemUserManageSetVo) {

        System.out.println(systemUserManageSetVo);  //从页面接收到的信息(字符串，未组装对象)

        Test test = new Test();
        testService.systemUserUpdate(test);

        return Result.succ(200, "成功", test);
    }

    //  用户删除(逻辑删除，仅将status改为0)
    //  参数  Test(emp.logname)
    @GetMapping("/userDelete")
    public Result systemUserDelete(@RequestBody Test test) {

        testService.systemUserDelete(test);

        return Result.succ(200, "成功", test);
    }
}
