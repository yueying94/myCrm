package com.crm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class Emp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 雇员的编号
     */
    @TableId(value = "empid", type = IdType.AUTO)
    private Integer empid;

    /**
     * 雇员的姓名
     */
    private String ename;

    /**
     * 雇员的登陆密码
     */
    private String pwd;

    /**
     * 登陆名称
     */
    private String logname;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 性别
     */
    private String sex;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 数据的创建时间
     */
    private LocalDateTime createtime;


}
