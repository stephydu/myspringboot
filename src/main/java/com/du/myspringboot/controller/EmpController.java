package com.du.myspringboot.controller;

import com.du.myspringboot.entity.Emp;
import com.du.myspringboot.service.EmpService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class EmpController {
    @Resource
    private EmpService empService = null;
    @RequestMapping("/emp/{id}")

    public Emp findById(@PathVariable("id") Integer id){
        Emp emp = empService.findById(id);
        return emp;
    }

    @RequestMapping("/emp/list")
    public List<Map> findDepts(String dname , Float sal,int pageNum, int pageSize){
        List<Map> list = empService.findDepts(dname , sal, pageNum, pageSize);

        return list;
    }

    @RequestMapping("/emp/create")
    public Emp create(){
        Emp emp = new Emp();
        emp.setSal(1000f);
        emp.setComm(0f);
        emp.setDeptno(30);
        emp.setEname("lao qi");
        emp.setHiredate(new Date());
        emp.setJob("Teacher");
        emp.setMgr(null);
        empService.create(emp);
        return emp;
    }

    @RequestMapping("/emp/update")
    public Emp update(){
        Emp emp = empService.findById(7900);
        emp.setSal(emp.getSal() * 2);
        empService.update(emp);
        return emp;
    }

    @RequestMapping("/emp/delete")
    public String delete(Integer empno){
        empService.delete(empno);
        return "Delete success!!!!";
    }
}
