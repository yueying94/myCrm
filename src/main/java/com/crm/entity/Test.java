package com.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.List;

import com.crm.vo.DeptOptionVo;
import com.crm.vo.PositionOptionVo;
import com.crm.vo.RoleOptionVo;
import com.crm.vo.SystemUserManageSetVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 获取前端页面传递的数据，并分配给实体类
 * </p>
 *
 * @author zy
 * @since 2021-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Component
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "testid", type = IdType.AUTO)
    private Integer testid;

    //emp属性
    private Emp emp;

    //dept属性
    private Dept dept;

    //position
    private Position position;

    //角色
    private Role role;

    //权限
    private Module module;


    //由后端返回前端的 数据================================
    //角色集合(一个用户，多个角色)
    private List<Role> roles;

    //权限集合(一个角色，多个权限)
    private List<Module> modules;

    //部门集合(一个角色，多个部门)
    private List<Dept> depts;

    //操作集合(一个角色，多个部门)
    private List<Operation> operations;


    //以字符串形式接收前端数据 =============================
    //角色 名称  集合
    private List<String> rolelist;

    //权限  名称  集合
    private List<String> modulelist;

    //部门  名称  集合
    private List<String> deptlist;

    //操作集  名称  集合
    private List<String> operationlist;

    //页面左侧目录
    private List<LeftDirectory> leftDirectoryList;

    //  返回  用户管理界面的 表格数据
    private List<SystemUserManageSetVo> systemUserManageSetVoList;


    //查询所有的部门名称
    private List<PositionOptionVo> positionOptionVos;

    //查询所有的职位名称
    private List<DeptOptionVo> deptOptionVos;

    //查询所有的角色名称
    private List<RoleOptionVo> roleOptionVos;



//    @@@Test(testid=null, emp=null, dept=null, position=null, role=null, module=Module(moduleid=null, modulename=hhhh, top=null, url=null, enable=null), roles=null, modules=null, depts=null, operations=null, onamelist=[1, 2, 3])
//    class com.crm.entity.Test
}
