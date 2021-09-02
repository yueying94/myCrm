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
 * 功能模块
 * </p>
 *
 * @author zy
 * @since 2021-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Component
public class Module implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模块编号
     */
    @TableId(value = "moduleid", type = IdType.AUTO)
    private Integer moduleid;

    /**
     * 模块的说明
     */
    private String modulename;

    /**
     * 上级模块的编号
     */
    private Integer top;

    /**
     * 模块对应的请求地址
     */
    private String url;

    /**
     * 是否启用
     */
    private Integer enable;

    /**
     * component名称
     */
    private String componentname;


}
