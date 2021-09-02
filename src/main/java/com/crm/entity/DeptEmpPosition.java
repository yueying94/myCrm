package com.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 
 * </p>
 *
 * @author zy
 * @since 2021-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Component
public class DeptEmpPosition implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 雇员、部门和职位三者的数据关联id
     */
    @TableId(value = "sid", type = IdType.AUTO)
    private Integer sid;

    /**
     * 部门的编号
     */
    private Integer deptid;

    /**
     * 雇员的编号
     */
    private Integer empid;

    /**
     * 雇员对应的职位编号
     */
    private Integer positionid;


}
