package com.crm.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;


/**
 *    SystemUserManager.vue 表格内容
 *    SystemUserEdit.vue 修改内容（前端---→后端）
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Component
public class SystemUserManageSetVo implements Serializable {

   private Integer eid;

   private String logname;

   private String ename;

   private String pwd;

   private String deptname;

   private String sex;

   private Integer status;

   private String statusGet;

   private String pname;

   private String phone;

   private String email;

   private String[] rnames;

   //角色 名称  集合
   private String rname ;

}
