package com.crm.service.impl;

import com.crm.entity.Position;
import com.crm.mapper.PositionMapper;
import com.crm.service.PositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 这里的职位是一个简化的操作 服务实现类
 * </p>
 *
 * @author zy
 * @since 2021-08-23
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements PositionService {

}
