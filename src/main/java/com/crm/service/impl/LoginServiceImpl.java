package com.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crm.entity.*;
import com.crm.mapper.EmpMapper;
import com.crm.mapper.ModuleMapper;
import com.crm.mapper.RoleModuleMapper;
import com.crm.service.LoginService;
import com.crm.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional  //事务
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    TestService testService;

    @Autowired
    EmpMapper empMapper;

    @Autowired
    ModuleMapper moduleMapper;

    @Autowired
    RoleModule roleModule;

    @Autowired
    RoleModuleMapper roleModuleMapper;

    @Autowired
    Module module;



    @Override
    public Test empLogin(Test test) throws Exception {

        //该用户是否存在，密码是否正确
        Emp emp = empMapper.selectOne(new QueryWrapper<Emp>()
                .eq("ename", test.getEmp().getEname())
                .eq("pwd", test.getEmp().getPwd()));
        if (emp==null){
            throw new Exception("用户不存在");
        }
        test.setEmp(emp);
        System.out.println("@@@@@@emp"+emp);
        System.out.println("@@@@@@test.getEmp()"+test.getEmp());

        //获取用户角色 rname集合(参数 empid)
        test =  testService.systemUserDetail(test);

        //获取 roleid的集合
        List<Integer> roleids = test.getRoles().stream().map(Role::getRoleid).collect(Collectors.toList());

        //找出roleid 对应的role-module的集合
        List<RoleModule> rolemodules = roleModuleMapper.selectList(new QueryWrapper<RoleModule>().in("roleid", roleids));

        //获取 moduleid的集合
        List<Integer> moduleids = rolemodules.stream().map(RoleModule::getModuleid).collect(Collectors.toList());

        //找出 module 的集合
        List<Module> modules = moduleMapper.selectList(new QueryWrapper<Module>().in("moduleid", moduleids));

        test.setModules(modules);
        //递归
        test =  testService.diguiparent(test);

        return test;
    }
}
