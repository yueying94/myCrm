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
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 功能编号
     */
    @TableId(value = "operationid", type = IdType.AUTO)
    private Integer operationid;

    /**
     * 功能名称
     */
    private String oname;

    /**
     * 功能说明
     */
    private String description;


}
