package com.crm.service.impl;

import com.crm.entity.Emp;
import com.crm.mapper.DeptMapper;
import com.crm.mapper.EmpMapper;
import com.crm.mapper.PositionMapper;
import com.crm.mapper.RoleMapper;
import com.crm.service.EmpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zy
 * @since 2021-08-23
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {


}
