package com.crm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crm.entity.LeftDirectory;
import com.crm.entity.Module;
import com.crm.entity.Role;
import com.crm.entity.Test;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zy
 * @since 2021-08-25
 */
public interface TestService extends IService<Test> {

    /**
     * 页面：系统管理
     */
    //  用户管理   返回用户信息、部门信息、职位信息、角色信息
    Page<Test> selectSystemUserManageByTest(Page<Test> page, Test test);

    //  用户添加
    Integer systemUserAdd(Test test) throws Exception;

    //  用户详情
    Test systemUserDetail(Test test);

    //  用户修改
    boolean systemUserUpdate(Test test);

    //  用户删除
    boolean systemUserDelete(Test test);

    /**
     * 页面：角色管理
     */
    //  角色管理
    Page<Test> selectSystemRoleManageByTest(Page<Test> page, Test test);

    //  角色添加
    Integer selectSystemRoleAdd(Test test) throws Exception;

    //  角色详情
    Test systemRoleDetail(Test test);

    //  用户修改
    boolean systemRoleUpdate(Test test);

    //  角色删除
    boolean systemRoleDelete(Test test);

    /**
     * 页面：权限管理
     */
    //  权限管理
    Page<Test> selectSystemModuleManageByTest(Page<Test> page, Test test);

    //  权限添加
    Integer selectSystemModuleAdd(Test test) throws Exception;

    //权限详情
    Test systemModuleDetail(Test test);

    //  角色修改
    boolean systemModuleUpdate(Test test);

    //角色删除(逻辑删除，仅更改status)
    boolean systemModuleDelete(Test test);

    Page<Test> systemModuleReport(Page<Test> test);

    //对权限进行封装，形成 label children的格式(左侧目录格式)
    //Test modulePackage(Test test);

    //  递归-1
    Test diguiparent(Test test);

    //  递归-1
    List<LeftDirectory> diguichildren(Integer id , List<Module> modules );



}
