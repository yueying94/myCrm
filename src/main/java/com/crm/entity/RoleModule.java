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
public class RoleModule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色和模块的关系编号
     */
    @TableId(value = "rmid", type = IdType.AUTO)
    private Integer rmid;

    /**
     * 角色的编号
     */
    private Integer roleid;

    /**
     * 模块的编号
     */
    private Integer moduleid;


}
