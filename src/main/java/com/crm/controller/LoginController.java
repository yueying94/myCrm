package com.crm.controller;

import com.crm.common.util.result.Result;
import com.crm.entity.Test;
import com.crm.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)   //跨域处理
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/empLogin")
    public Result empLogin(@RequestBody Test test) {
        System.out.println("weishnem");
        //根据  用户名和密码判断是否登陆成功，以及他拥有的权限
        try {
            test = loginService.empLogin(test);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (test.getLeftDirectoryList()!=null) {
            return Result.succ(200,"成功",test);
        }

        return Result.fail(400,"null",null);
    }


}
