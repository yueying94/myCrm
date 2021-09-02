package com.crm.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 *  SystemUserManager.vue 页面的数据传递（搜索栏 前端--→后端）
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Component
public class SystemUserManageVo implements Serializable {

  //页码
  private Integer currentPage = 1;

  //页面尺寸
  private Integer pageSize = 5;


  private String userNameValue;

  private String logNameValue;

  private String deptValue;

  private String statusValue;

  private String positionValue;

  private String roleValue;

  private String sexValue;




}
