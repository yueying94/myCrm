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
public class ModuleOperation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模块与操作的关系编号
     */
    @TableId(value = "moid", type = IdType.AUTO)
    private Integer moid;

    /**
     * 模块的编号
     */
    private Integer moduleid;

    /**
     * 功能编号
     */
    private Integer operationid;

    /**
     * 组件所在路径
     */
    private String cpath;

    /**
     * 组件名称
     */
    private String component;

    /**
     * 可用权限名称
     */
    private String rule;

    /**
     * 组件对应的请求路径
     */
    private String path;

    /**
     * 是否启用为菜单
     */
    private Integer enable;


}
