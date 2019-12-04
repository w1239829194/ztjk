package com.mmall.service;

import com.google.common.base.Preconditions;
import com.mmall.dao.SysDeptMapper;
import com.mmall.exception.ParamException;
import com.mmall.model.SysDept;
import com.mmall.param.DeptParam;
import com.mmall.util.BeanValidator;
import com.mmall.util.LevelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

@Service
public class SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    public void save(DeptParam param){
        BeanValidator.check(param);//数据校验
        if (checkExist(param.getId(),param.getParentId(),param.getName())){
            throw new ParamException("同一层级下存在相同的部门名称");
        }
        SysDept sysDept = SysDept.builder().name(param.getName()).parentId(param.getParentId())
                .seq(param.getSeq()).remark(param.getRemark()).build();
        //存入拼接的上级
        sysDept.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()),param.getParentId()));
        sysDept.setOperator("system");
        sysDept.setOperateIp("127.0.0.1");
        sysDept.setOperateTime(new Date());
        sysDeptMapper.insertSelective(sysDept);
    }

    private boolean checkExist(Integer id, Integer parentId, String name){
        return sysDeptMapper.countByNameAndParentId(parentId,name,id) > 0;
    }

    /**
     * 查询上级
     * @return
     */
    private String getLevel(Integer primaryId){
        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(primaryId);
        if (sysDept == null){
            return null;
        }
        return sysDept.getLevel();
    }

    public void update(DeptParam param){
        BeanValidator.check(param);//数据校验
        if (checkExist(param.getId(),param.getParentId(),param.getName())){
            throw new ParamException("同一层级下存在相同的部门名称");
        }
        SysDept before = sysDeptMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before,"待更新的部门不存在");//判空并返回异常
        SysDept after = SysDept.builder().id(param.getId()).name(param.getName()).parentId(param.getParentId())
                .seq(param.getSeq()).remark(param.getRemark()).build();
        //存入拼接的上级
        after.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()),param.getParentId()));
        after.setOperator("system-update");
        after.setOperateIp("127.0.0.1");
        after.setOperateTime(new Date());
        updateWithChild(before,after);
    }

    @Transient
    public void updateWithChild(SysDept before, SysDept after) {
        String newLevelPrefix = after.getLevel(); //新level
        String oldLevelPrefix = before.getLevel();//旧level
        if (!after.getLevel().equals(before.getLevel())){
            List<SysDept> deptList = sysDeptMapper.getChildDeptListByLevel(oldLevelPrefix);
            if (CollectionUtils.isNotEmpty(deptList)){
                for (SysDept sysDept : deptList) {
                    String level = sysDept.getLevel();
                    if (level.indexOf(oldLevelPrefix) == 0){
                        level=newLevelPrefix+level.substring(oldLevelPrefix.length());//将旧的level替换为新的
                        sysDept.setLevel(level);
                    }
                }
                sysDeptMapper.batchUpdateLevel(deptList);
            }
        }
        sysDeptMapper.updateByPrimaryKey(after);
    }
}
