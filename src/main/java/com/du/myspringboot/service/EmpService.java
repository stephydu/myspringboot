package com.du.myspringboot.service;

import com.du.myspringboot.entity.Emp;
import com.du.myspringboot.mapper.EmpMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//默认该类的所有方法都开启事务
@Transactional(rollbackFor = Exception.class)
public class EmpService {

    @Resource
    private EmpMapper empMapper = null;
    //不开启事务的方法，以方法為準
    @Transactional(propagation = Propagation.NOT_SUPPORTED , readOnly = true)
    public Emp findById(Integer empno) {
        return empMapper.findById(empno);
    }

    public List<Map> findDepts(String dname, Float sal, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Map param = new HashMap();
        param.put("pdname", dname);
        param.put("psal", sal);
        return empMapper.findDepts(param);
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(Emp emp) {
        empMapper.create(emp);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(Emp emp) {
        empMapper.update(emp);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer empno) {
        empMapper.delete(empno);
    }

}
