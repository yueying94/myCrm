package com.crm.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crm.entity.*;
import com.crm.mapper.*;
import com.crm.service.TestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.crm.vo.DeptOptionVo;
import com.crm.vo.PositionOptionVo;
import com.crm.vo.RoleOptionVo;
import com.crm.vo.SystemUserManageSetVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zy
 * @since 2021-08-25
 */
@Transactional
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {
    @Autowired
    TestMapper testMapper;

    @Autowired
    EmpMapper empMapper;

    @Autowired
    DeptMapper deptMapper;

    @Autowired
    PositionMapper positionMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    DeptEmpPositionMapper deptEmpPositionMapper;

    @Autowired
    DeptEmpPosition deptEmpPosition;

    @Autowired
    EmpRoleMapper empRoleMapper;

    @Autowired
    EmpRole empRole;

    @Autowired
    RoleModule roleModule;

    @Autowired
    RoleModuleMapper roleModuleMapper;

    @Autowired
    ModuleMapper moduleMapper;

    @Autowired
    ModuleOperationMapper moduleOperationMapper;

    @Autowired
    OperationMapper operationMapper;

    @Autowired
    ModuleOperation moduleOperation;


    @Autowired
    Role role;

    @Autowired
    LeftDirectory leftDirectory;


    /**
     * 页面：系统管理-用户管理 ====================================================
     */
    //  用户管理    xml
    //  返回用户信息、部门信息、职位信息、角色信息
    @Override
    public Page<Test> selectSystemUserManageByTest(Page<Test> page, Test test) {

        Page<Test> testPage = testMapper.selectSystemUserManageByTest(page, test);
        List<SystemUserManageSetVo> systemUserManageSetVos = new ArrayList<SystemUserManageSetVo>();
        List<PositionOptionVo> positionOptionVos = new ArrayList<PositionOptionVo>();
        List<DeptOptionVo> deptOptions = new ArrayList<DeptOptionVo>();
        List<RoleOptionVo> roleOptionVos = new ArrayList<RoleOptionVo>();


        //一个用户对应多个角色
        //对所有用户进行遍历后，重新组装  角色集合roles  信息
        for (int i = 0; i < testPage.getRecords().size(); i++) {
            //找出某用户对应的empid
            Integer empid = testPage.getRecords().get(i).getEmp().getEmpid();

            //找出empid 对应的roleid的集合
            List<EmpRole> empRoleByMapper = empRoleMapper.selectList(new QueryWrapper<EmpRole>().eq("empid", empid));

            //将empRoleByMapper的roleid抽离出来，赋值给一个集合
            List<Integer> roleIds = empRoleByMapper.stream().map(EmpRole::getRoleid).collect(Collectors.toList());

            if (roleIds.size() != 0) {    //  用户存在对应的角色时，才进行查询 赋值

                //查询 roleid对应的角色，并赋值进testPage
                List<Role> roles = roleMapper.selectList(new QueryWrapper<Role>().in("roleid", roleIds));

                //将roles 的rolename抽离出来，赋值给一个集合
                List<String> rolenameList = roles.stream().map(Role::getRname).collect(Collectors.toList());

                //集合赋值
                testPage.getRecords().get(i).setRolelist(rolenameList);
            }
        }

        //  数据处理    对返回数据赋值(表格数据)
        for (int i = 0; i < testPage.getRecords().size(); i++) {
            SystemUserManageSetVo systemUserManageSetVo = new SystemUserManageSetVo();

            if (testPage.getRecords().get(i).getEmp() != null) {
                systemUserManageSetVo.setEid(testPage.getRecords().get(i).getEmp().getEmpid());
                systemUserManageSetVo.setLogname(testPage.getRecords().get(i).getEmp().getLogname());
                systemUserManageSetVo.setEname(testPage.getRecords().get(i).getEmp().getEname());
                systemUserManageSetVo.setSex(testPage.getRecords().get(i).getEmp().getSex());
                systemUserManageSetVo.setStatus(testPage.getRecords().get(i).getEmp().getStatus());
                systemUserManageSetVo.setPhone(testPage.getRecords().get(i).getEmp().getPhone());
                systemUserManageSetVo.setEmail(testPage.getRecords().get(i).getEmp().getEmail());
            }
            if (testPage.getRecords().get(i).getDept() != null) {
                systemUserManageSetVo.setDeptname(testPage.getRecords().get(i).getDept().getDeptname());
            }
            if (testPage.getRecords().get(i).getPosition() != null) {
                systemUserManageSetVo.setPname(testPage.getRecords().get(i).getPosition().getPname());
            }
            if (testPage.getRecords().get(i).getRolelist() != null) {
                systemUserManageSetVo.setRname(StringUtils.join(testPage.getRecords().get(i).getRolelist(), ","));
            }

            //创建一个集合，接收信息
            systemUserManageSetVos.add(systemUserManageSetVo);
        }

        //查询所有的部门名称
        List<Dept> depts = deptMapper.selectList(new QueryWrapper<>());
        for (int i = 0; i < depts.size(); i++) {
            DeptOptionVo deptOption = new DeptOptionVo();
            deptOption.setLabel(depts.get(i).getDeptname());
            deptOption.setValue(depts.get(i).getDeptname());

            deptOptions.add(deptOption);
        }

        //查询所有的职位名称
        List<Position> positions = positionMapper.selectList(new QueryWrapper<>());
        for (int i = 0; i < positions.size(); i++) {
            PositionOptionVo positionOptionVo = new PositionOptionVo();
            positionOptionVo.setLabel(positions.get(i).getPname());
            positionOptionVo.setValue(positions.get(i).getPname());

            positionOptionVos.add(positionOptionVo);
        }

        //查询所有的角色名称
        List<Role> roles = roleMapper.selectList(new QueryWrapper<>());
        for (int i = 0; i < roles.size(); i++) {
            RoleOptionVo roleOptionVo = new RoleOptionVo();
            roleOptionVo.setLabel(roles.get(i).getRname());
            roleOptionVo.setValue(roles.get(i).getRname());

            roleOptionVos.add(roleOptionVo);
        }

        //      组装数据
        testPage.getRecords().get(0).setDeptOptionVos(deptOptions);
        testPage.getRecords().get(0).setPositionOptionVos(positionOptionVos);
        testPage.getRecords().get(0).setRoleOptionVos(roleOptionVos);

        testPage.getRecords().get(0).setSystemUserManageSetVoList(systemUserManageSetVos);


        return testPage;
    }

    //  用户添加
    @Override
    public Integer systemUserAdd(Test test) throws Exception {

        //新增emp信息，并获取自增主键 empid(已存入test对象中)
        int insert = empMapper.insert(test.getEmp());

        //获取部门id 、职位id 和 角色id，并赋回test
        test.setDept(deptMapper.selectOne(new QueryWrapper<Dept>().eq("deptname", test.getDept().getDeptname())));
        test.setPosition(positionMapper.selectOne(new QueryWrapper<Position>().eq("pname", test.getPosition().getPname())));

        //组装实体类
        deptEmpPosition.setEmpid(test.getEmp().getEmpid());
        deptEmpPosition.setDeptid(test.getDept().getDeptid());
        deptEmpPosition.setPositionid(test.getPosition().getPositionid());

        //对用户的角色 名称集合进行遍历，逐个添加
        for (int i = 0; i < test.getRolelist().size(); i++) {

            //找到对应 角色对象
            Role roleByMapper = roleMapper.selectOne(new QueryWrapper<Role>().eq("rname", test.getRolelist().get(i)));

            //组装实体类
            empRole.setEmpid(test.getEmp().getEmpid());
            empRole.setRoleid(roleByMapper.getRoleid());
            empRoleMapper.insert(empRole);
        }

        //添加emp关系表dept_emp_position
        int insert1 = deptEmpPositionMapper.insert(deptEmpPosition);
        if (insert != 0 && insert1 != 0) {
            return insert;
        } else {
            throw new Exception("systemUserAdd方法异常");
        }
    }

    //  用户详情
    @Override
    public Test systemUserDetail(Test test) {

        test = testMapper.selectSystemUserDetail(test);

        //一个用户对应多个角色，找出角色id的集合
        //找出empid 对应的roleid的集合
        List<EmpRole> empRoleByMapper = empRoleMapper.selectList(new QueryWrapper<EmpRole>().eq("empid", test.getEmp().getEmpid()));

        //将empRoleByMapper的roleid抽离出来，赋值给一个集合
        List<Integer> roleIds = empRoleByMapper.stream().map(EmpRole::getRoleid).collect(Collectors.toList());

        //查询 roleid对应的角色
        List<Role> roles = roleMapper.selectList(new QueryWrapper<Role>().in("roleid", roleIds));

        //将roles 的rolename抽离出来，赋值给一个集合
        List<String> rolenameList = roles.stream().map(Role::getRname).collect(Collectors.toList());

        test.setRoles(roles);
        test.setRolelist(rolenameList);
        return test;
    }

    //  用户修改
    @Override
    public boolean systemUserUpdate(Test test) {

        //更改emp表内容
        empMapper.updateById(test.getEmp());

        //获取部门id 、职位id，并赋回test
        test.setDept(deptMapper.selectOne(new QueryWrapper<Dept>().eq("deptname", test.getDept().getDeptname())));
        test.setPosition(positionMapper.selectOne(new QueryWrapper<Position>().eq("pname", test.getPosition().getPname())));

        //组装实体类
        deptEmpPosition.setEmpid(test.getEmp().getEmpid());
        deptEmpPosition.setDeptid(test.getDept().getDeptid());
        deptEmpPosition.setPositionid(test.getPosition().getPositionid());

        //更改关联表 dept_emp_position
        deptEmpPositionMapper.update(deptEmpPosition, new QueryWrapper<DeptEmpPosition>().eq("empid", deptEmpPosition.getEmpid()));


        //删除现有  用户-角色 关系表
        empRoleMapper.delete(new QueryWrapper<EmpRole>().eq("empid", test.getEmp().getEmpid()));

        //新增 用户-角色 关系表
        //通过角色名称获取 id，并于empid组装成 实体类  加入数据表
        for (int i = 0; i < test.getRolelist().size(); i++) {
            //获取每一个角色对应的角色id
            Role role = roleMapper.selectOne(new QueryWrapper<Role>().eq("rname", test.getRolelist().get(i)));

            empRole.setEmpid(test.getEmp().getEmpid());
            empRole.setRoleid(role.getRoleid());

            empRoleMapper.insert(empRole);
        }

        return true;
    }


    //  用户删除
    @Override
    public boolean systemUserDelete(Test test) {

        Emp emp = empMapper.selectOne(new QueryWrapper<Emp>().eq("logname", test.getEmp().getLogname()));
        emp.setStatus(0);
        int i = empMapper.updateById(emp);
        return i != 0;
    }


    /**
     * 页面：系统管理-角色==================================================================
     */
    //  角色管理     xml
    //  返回角色信息、部门信息、权限信息
    @Override
    public Page<Test> selectSystemRoleManageByTest(Page<Test> page, Test test) {

        //根据条件查询 角色信息
        Page<Test> testPage = testMapper.selectSystemRoleManageByTest(page, test);

        //一个角色对应多个权限
        //对所有用户进行遍历后，重新组装  信息
        for (int i = 0; i < testPage.getRecords().size(); i++) {
            //找出某角色对应的roleid
            Integer roleid = testPage.getRecords().get(i).getRole().getRoleid();

            //找出roleid 对应的moduleid的集合
            List<RoleModule> roleModuleByMapper = roleModuleMapper.selectList(new QueryWrapper<RoleModule>().eq("roleid", roleid));

            //将roleModuleByMapper的moduleid抽离出来，赋值给一个集合
            List<Integer> moduleIds = roleModuleByMapper.stream().map(RoleModule::getModuleid).collect(Collectors.toList());

            //查询 roleid对应的角色，并赋值进testPage
            List<Module> modules = moduleMapper.selectList(new QueryWrapper<Module>().in("moduleid", moduleIds));


            List<String> modulenamelist = modules.stream().map(Module::getModulename).collect(Collectors.toList());

            testPage.getRecords().get(i).setModulelist(modulenamelist);

        }

        // 一个角色，多个部门
        // TODO: 2021/8/26  角色---部门关系表未创建，数据未查询；可仿照 角色-权限 查询后放入test中


        return testPage;
    }


    //角色添加
    @Override
    public Integer selectSystemRoleAdd(Test test) throws Exception {

        //新增role信息，并获取自增主键 roleid(已存入test对象中)
        int insert = roleMapper.insert(test.getRole());

        //获取部门id(集合) 、权限id (集合)，并赋回test
        // TODO: 2021/8/26  角色---部门关系表未创建,数据未查询；可仿照 角色-权限 查询后放入test中
//        for (int i = 0; i < test.getDeptlist.size(); i++) {
//            //找到对应 角色对象
//            Dept deptByMapper = deptMapper.selectOne(new QueryWrapper<Dept>().eq("deptname", test.getDeptlist.get(i).getDeptname()));
//            //组装实体类,并插入数据库中
//        }

        //对用户的权限集合进行遍历，逐个添加
        for (int i = 0; i < test.getModulelist().size(); i++) {

            //找到对应 权限对象
            Module moduleByMapper = moduleMapper.selectOne(new QueryWrapper<Module>().eq("modulename", test.getModulelist().get(i)));

            //组装实体类
            roleModule.setRoleid(test.getRole().getRoleid());
            roleModule.setModuleid(moduleByMapper.getModuleid());
            roleModuleMapper.insert(roleModule);
        }
        if (insert != 0) {
            return insert;
        } else {
            throw new Exception("selectSystemRoleAdd方法异常");
        }
    }

    //角色详情
    @Override
    public Test systemRoleDetail(Test test) {

        test = testMapper.selectSystemRoleDetail(test);
        System.out.println(test.getRole());
        //一个角色对应多个权限
        //找出roleid 对应的role-module的集合
        List<RoleModule> moduleByMapper = roleModuleMapper.selectList(new QueryWrapper<RoleModule>().eq("roleid", test.getRole().getRoleid()));

        //将empRoleByMapper的 权限id 抽离出来，赋值给一个集合
        List<Integer> moduleIds = moduleByMapper.stream().map(RoleModule::getModuleid).collect(Collectors.toList());

        //查询 权限id对应的    权限对象和权限名称
        List<Module> modules = moduleMapper.selectList(new QueryWrapper<Module>().in("moduleid", moduleIds));
        List<String> modulenamebymapper = modules.stream().map(Module::getModulename).collect(Collectors.toList());

        return test.setModulelist(modulenamebymapper);

    }

    //  角色修改
    @Override
    public boolean systemRoleUpdate(Test test) {
        //更改role表内容 (id 始终不变)
        roleMapper.updateById(test.getRole());

        //根据 roleid 删除 role-module 关系表
        roleModuleMapper.delete(new QueryWrapper<RoleModule>().eq("roleid", test.getRole().getRoleid()));

        //根据传入的 modulename 获取对应 moduleid 集合，组装实体类后传入数据库
        for (int i = 0; i < test.getModulelist().size(); i++) {

            //获取moduleid
            Module moduleByMapper = moduleMapper.selectOne(new QueryWrapper<Module>().eq("modulename", test.getModulelist().get(i)));

            //组装实体类，并插入数据库
            roleModule.setRoleid(test.getRole().getRoleid());
            roleModule.setModuleid(moduleByMapper.getModuleid());
            roleModuleMapper.insert(roleModule);
        }

        return true;
    }

    //角色删除(逻辑删除，仅更改status)
    @Override
    public boolean systemRoleDelete(Test test) {

        Role role = roleMapper.selectOne(new QueryWrapper<Role>().eq("rname", test.getRole().getRname()));
        role.setStatus(0);
        int i = roleMapper.updateById(role);

        return i != 0;
    }


    /**
     * 页面：系统管理-权限==================================================================
     */
    //  权限管理     xml
    @Override
    public Page<Test> selectSystemModuleManageByTest(Page<Test> page, Test test) {

        //根据条件查询 角色信息
        Page<Test> testPage = testMapper.selectSystemModuleManageByTest(page, test);
        System.out.println("@@@testPage.getRecords()" + testPage.getRecords());

        //一个权限对应多个角色
        //对所有用户进行遍历后，重新组装  信息
        for (int i = 0; i < testPage.getRecords().size(); i++) {
            //找出某权限 对应的moduleid
            Integer moduleid = testPage.getRecords().get(i).getModule().getModuleid();

            //找出moduleid 对应的的集合roleid
            List<RoleModule> roleModuleByMapper = roleModuleMapper.selectList(new QueryWrapper<RoleModule>().eq("moduleid", moduleid));

            //将roleModuleByMapper的moduleid抽离出来，赋值给一个集合
            List<Integer> roleIds = roleModuleByMapper.stream().map(RoleModule::getRoleid).collect(Collectors.toList());

            //查询 roleid对应的角色，并赋值进testPage
            List<Role> roles = roleMapper.selectList(new QueryWrapper<Role>().in("roleid", roleIds));

            //将roles 的rolename抽离出来，赋值给一个集合
            List<String> rolenameList = roles.stream().map(Role::getRname).collect(Collectors.toList());

            testPage.getRecords().get(i).setRolelist(rolenameList);

        }

        return testPage;
    }


    //  权限添加
    @Override
    public Integer selectSystemModuleAdd(Test test) throws Exception {

        //新增module信息，并获取自增主键 moduleid(已存入test对象中)
        int insert = moduleMapper.insert(test.getModule());

        //对用户的 operation权限集合进行遍历，逐个添加
        for (int i = 0; i < test.getOperationlist().size(); i++) {

            //找到对应 operation对象
            Operation operationByMapper = operationMapper.selectOne(new QueryWrapper<Operation>().eq("oname", test.getOperationlist().get(i)));

            //组装实体类
            moduleOperation.setModuleid(test.getModule().getModuleid());
            moduleOperation.setOperationid(operationByMapper.getOperationid());
            moduleOperationMapper.insert(moduleOperation);
        }
        if (insert != 0) {
            return insert;
        } else {
            throw new Exception("selectSystemModuleAdd方法异常");
        }
    }

    //  权限详情
    @Override
    public Test systemModuleDetail(Test test) {

        test = testMapper.selectSystemModuleDetail(test);

        //一个权限对应多个操作
        //找出moduleid 对应的module-operation的集合
        List<ModuleOperation> operatioByMapper = moduleOperationMapper.selectList(new QueryWrapper<ModuleOperation>().eq("moduleid", test.getModule().getModuleid()));

        //将empRoleByMapper的 权限id 抽离出来，赋值给一个集合
        List<Integer> operationIds = operatioByMapper.stream().map(ModuleOperation::getOperationid).collect(Collectors.toList());

        //查询 操作id对应的操作名称
        List<Operation> operations = operationMapper.selectList(new QueryWrapper<Operation>().in("operationid", operationIds));

        List<String> operationnamelist = operations.stream().map(Operation::getOname).collect(Collectors.toList());

        return test.setOperationlist(operationnamelist);
    }


    //  权限修改
    @Override
    public boolean systemModuleUpdate(Test test) {
        //更改module表内容 (id 始终不变)
        moduleMapper.updateById(test.getModule());

        //根据 moduleid 删除 module-operation 关系表
        moduleOperationMapper.delete(new QueryWrapper<ModuleOperation>().eq("moduleid", test.getModule().getModuleid()));

        //根据传入的 operationname 获取对应 operationid 集合，组装实体类后传入数据库
        for (int i = 0; i < test.getOperationlist().size(); i++) {

            //获取operationid
            Operation operationByMapper = operationMapper.selectOne(new QueryWrapper<Operation>().eq("", test.getOperationlist().get(i)));

            //组装实体类，并插入数据库
            moduleOperation.setModuleid(test.getModule().getModuleid());
            moduleOperation.setOperationid(operationByMapper.getOperationid());
            moduleOperationMapper.insert(moduleOperation);
        }

        return true;
    }


    //  权限删除(逻辑删除，仅更改enable)
    @Override
    public boolean systemModuleDelete(Test test) {

        Module module = moduleMapper.selectOne(new QueryWrapper<Module>().eq("modulename", test.getModule().getModulename()));

        module.setEnable(0);
        int i = moduleMapper.updateById(module);

        return i != 0;
    }

    //  权限报表
    @Override
    public Page<Test> systemModuleReport(Page<Test> testPage) {
        //当前共计查出多少条记录
        List<Test> records = testPage.getRecords();

        //根据角色查询权限  张三 李四  王五……
        //从 张三  开始查询
        for (int i = 0; i < records.size(); i++) {

            //从 张三的第一个角色 开始查询
            for (int j = 0; j < records.get(i).getRolelist().size(); j++) {

                String getrolenamebylist = records.get(i).getRolelist().get(i);
                Role role = roleMapper.selectOne(new QueryWrapper<Role>().eq("rname", getrolenamebylist));

                //一个角色对应多个权限
                //找出roleid 对应的role-module的集合
                List<RoleModule> moduleByMapper = roleModuleMapper.selectList(new QueryWrapper<RoleModule>().eq("roleid", role.getRoleid()));

                //权限id 抽离出来，赋值给一个集合
                List<Integer> moduleIds = moduleByMapper.stream().map(RoleModule::getModuleid).collect(Collectors.toList());

                //查询 权限id对应的权限名称
                List<Module> modulesNameByMapper = moduleMapper.selectList(new QueryWrapper<Module>().in("moduleid", moduleIds));
                List<String> modulesnamebylist = modulesNameByMapper.stream().map(Module::getModulename).collect(Collectors.toList());

                //将权限放回modules
                if (j == 0 && records.get(i).getModulelist().size() == 0) {   //如果含有多个角色，角色含有多个权限
                    records.get(i).setModulelist(modulesnamebylist);
                } else { //在modules 已有数据的情况下，需要逐个添加modules

                    //逐个将module 权限放入modules
                    for (int k = 0; k < moduleByMapper.size(); k++) {
                        records.get(i).getModulelist().add(modulesnamebylist.get(k));
                    }
                }
            }

            //准备放置 权限范围
            //从 张三的第一个权限 开始查询
            for (int j = 0; j < records.get(i).getModulelist().size(); j++) {

                String getmodulenamebylist = records.get(i).getModulelist().get(j);
                Module module = moduleMapper.selectOne(new QueryWrapper<Module>().eq("modulename", getmodulenamebylist));

                //一个权限对应多个操作
                //找出moduleid 对应的module-operation的集合
                List<ModuleOperation> operatioByMapper = moduleOperationMapper.selectList(new QueryWrapper<ModuleOperation>().eq("moduleid", module.getModuleid()));

                //将empRoleByMapper的 权限id 抽离出来，赋值给一个集合
                List<Integer> operationIds = operatioByMapper.stream().map(ModuleOperation::getOperationid).collect(Collectors.toList());

                //查询 操作id对应的操作名称(operations 对象集合)
                List<Operation> operationsNameByMapper = operationMapper.selectList(new QueryWrapper<Operation>().in("operationid", operationIds));

                List<String> operationBylist = operationsNameByMapper.stream().map(Operation::getOname).collect(Collectors.toList());
                //将权限放回modules
                if (j == 0) {   //如果含有多个角色，角色含有多个权限
                    records.get(i).setOperationlist(operationBylist);
                } else { //在modules 已有数据的情况下，需要逐个添加modules
                    //逐个将module 权限放入modules
                    for (int k = 0; k < operationBylist.size(); k++) {
                        records.get(i).getOperationlist().add(operationBylist.get(k));
                    }
                }
            }
        }

        return testPage;
    }

    /**
     * 登录界面
     * 对权限进行封装，形成 label children的格式(左侧目录格式)
     */
    @Override   //  递归-1
    public Test diguiparent(Test test) {

        List<Module> rootMenu = test.getModules();

        List<LeftDirectory> menuList = new ArrayList<LeftDirectory>();

        //  如果是 一级目录，直接放进 集合
        for (Module module : rootMenu) {
            if (module.getTop() == 0) {

                LeftDirectory leftDirectory = new LeftDirectory();
                leftDirectory.setLabel(module.getModulename());
                leftDirectory.setId(module.getModuleid());
                leftDirectory.setUrl(module.getUrl());

                menuList.add(leftDirectory);
            }
        }
        //  对已有的一级菜单，添加子菜单(递归)
        for (LeftDirectory directory : menuList) {

            directory.setChildren(diguichildren(directory.getId(), rootMenu));
        }

        //  将获取的数据，存入test中
        test.setLeftDirectoryList(menuList);

        return test;
    }

    @Override   //  递归-2
    public List<LeftDirectory> diguichildren(Integer id, List<Module> modules) {

        // 子菜单
        List<LeftDirectory> childList = new ArrayList<>();

        //根据 上级目录id 进行对比,获取上目录的子菜单
        for (Module module : modules) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (module.getTop() == id) {

                LeftDirectory leftDirectory = new LeftDirectory();
                leftDirectory.setLabel(module.getModulename());
                leftDirectory.setId(module.getModuleid());
                leftDirectory.setUrl(module.getUrl());
                leftDirectory.setComponentname(module.getComponentname());

                childList.add(leftDirectory);
            }
        }

        //  对子菜单进行遍历，查看他们是否含有  下一级菜单
        for (LeftDirectory directory : childList) {
            directory.setChildren(diguichildren(directory.getId(), modules));
        }

        //如果节点下没有子节点，返回一个空List（递归退出）
        if (childList.size() == 0) {
            return new ArrayList<LeftDirectory>();
        }

        return childList;
    }
}
