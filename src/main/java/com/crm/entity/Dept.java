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
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门编号
     */
    @TableId(value = "deptid", type = IdType.AUTO)
    private Integer deptid;

    /**
     * 部门名称
     */
    private String deptname;

    /**
     * 是否启用
     */
    private Integer enable;

    /**
     * 上级部门编号，如果自己就是顶级这里就没有数据
     */
    private Integer superior;

    /**
     * 是否为顶级部门（0：不是，1：是）
     */
    private Integer top;


}
