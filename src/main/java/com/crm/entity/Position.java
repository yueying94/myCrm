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
 * 这里的职位是一个简化的操作
 * </p>
 *
 * @author zy
 * @since 2021-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Component
public class Position implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 职位编号
     */
    @TableId(value = "positionid", type = IdType.AUTO)
    private Integer positionid;

    /**
     * 职位的名称
     */
    private String pname;


}
