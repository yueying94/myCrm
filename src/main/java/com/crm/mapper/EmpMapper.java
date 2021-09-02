package com.crm.mapper;

import com.crm.entity.Emp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zy
 * @since 2021-08-23
 */
@Repository
public interface EmpMapper extends BaseMapper<Emp> {

}
