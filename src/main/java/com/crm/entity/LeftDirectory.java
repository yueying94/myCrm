package com.crm.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;


/**
 * <p>
 * 页面左侧目录
 * </p>
 *
 * @author zy
 * @since 2021-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Component
public class LeftDirectory implements Serializable {

    //  本级目录名称
    private String label;

    //  本级目录对应  component名称
    private String componentname;

    //  本级目录    id
    private Integer id;

    //  本级目录    url
    private String url;



    //  子目录集合
    private List<LeftDirectory> children;

}
