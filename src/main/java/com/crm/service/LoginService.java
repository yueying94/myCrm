package com.crm.service;

import com.crm.entity.Test;

public interface LoginService {

    //用户登录，并获取相应权限(模块)
    Test empLogin(Test test) throws Exception;

}
