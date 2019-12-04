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
public class SysDept implements Serializable {
    /**
    * 部门id
    */
    private Integer id;

    /**
    * 部门名称
    */
    private String name;

    /**
    * 上级部门id
    */
    private Integer parentId;

    /**
    * 部门层级
    */
    private String level;

    /**
    * 部门在当前层级下的顺序，由小到大
    */
    private Integer seq;

    /**
    * 备注
    */
    private String remark;

    /**
    * 操作者
    */
    private String operator;

    /**
    * 最后一次操作时间
    */
    private Date operateTime;

    /**
    * 最后一次更新操作者的ip地址
    */
    private String operateIp;

    private static final long serialVersionUID = 1L;
}