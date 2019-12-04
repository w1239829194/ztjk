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
public class SysRoleAcl implements Serializable {
    private Integer id;

    /**
    * 角色id
    */
    private Integer roleId;

    /**
    * 权限id
    */
    private Integer aclId;

    /**
    * 操作者
    */
    private String operator;

    /**
    * 最后一次更新的时间
    */
    private Date operateTime;

    /**
    * 最后一次更新者的ip
    */
    private String operateIp;

    private static final long serialVersionUID = 1L;
}