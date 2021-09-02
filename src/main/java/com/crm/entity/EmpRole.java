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
public class EmpRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  雇员与角色的关系编号
     */
    @TableId(value = "erid", type = IdType.AUTO)
    private Integer erid;

    /**
     * 雇员编号
     */
    private Integer empid;

    /**
     * 角色的编号
     */
    private Integer roleid;


}
