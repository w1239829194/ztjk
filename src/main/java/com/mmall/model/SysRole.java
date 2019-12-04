package com.mmall.model;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysRole implements Serializable {
    /**
    * 角色id
    */
    private Integer id;

    /**
    * 角色名
    */
    private String name;

    /**
    * 角色的类型，1：管理员角色，2：其他
    */
    private Integer type;

    /**
    * 状态，1：可用，0：冻结
    */
    private Integer status;

    /**
    * 备注
    */
    private String remark;

    /**
    * 操作者
    */
    private String operator;

    /**
    * 最后一次更新的时间
    */
    private Date operateTime;

    /**
    * 最后一次更新者的ip地址
    */
    private String operateIp;

    private static final long serialVersionUID = 1L;
}