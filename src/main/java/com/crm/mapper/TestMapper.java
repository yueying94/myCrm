package com.crm.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crm.entity.Emp;
import com.crm.entity.Role;
import com.crm.entity.Test;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zy
 * @since 2021-08-25
 */
@Repository
public interface TestMapper extends BaseMapper<Test> {

    /**
     * 对应页面：系统管理-用户管理
     * 返回用户信息、部门信息、职位信息、角色信息
     */
    Page<Test> selectSystemUserManageByTest(Page<Test> page,@Param("test") Test test);
    //详情
    Test selectSystemUserDetail(@Param("test") Test test);


    /**
     * 页面：角色管理
     */
    Page<Test> selectSystemRoleManageByTest(Page<Test> page, @Param("test") Test test);
    //详情
    Test selectSystemRoleDetail(@Param("test") Test test);


    /**
     * 页面：权限管理
     */
    Page<Test> selectSystemModuleManageByTest(Page<Test> page, @Param("test") Test test);
    //详情
    Test selectSystemModuleDetail(@Param("test") Test test);

}
