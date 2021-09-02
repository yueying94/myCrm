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
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色编号
     */
    @TableId(value = "roleid", type = IdType.AUTO)
    private Integer roleid;

    /**
     * 角色的名称
     */
    private String rname;

    /**
     * 角色的描述
     */
    private String description;

    /**
     * 角色的状态（是否启用，1对应启用，0对应未启用）
     */
    private Integer status;


}
