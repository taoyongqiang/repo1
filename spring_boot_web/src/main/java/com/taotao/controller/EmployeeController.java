package com.taotao.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.taotao.dao.DepartmentDao;
import com.taotao.dao.EmployeeDao;
import com.taotao.entities.Department;
import com.taotao.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.Collection;

/**
 * @ClassName EmployeeController
 * @Description
 * @Author tyq
 * @Date 2020/5/4 13:36form-signin
 * @Version V1.0
 **/
@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;
    // 查询所有员工返回列表页面
    @GetMapping("/emps")
    public String list (Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps" , employees);
        return "emps/list";
    }

    // 查询部门信息并到添加页面
    @GetMapping("/emp")
    public String toAddPage (Model model) {
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emps/add";
    }

    // 添加员工成功跳转到员工列表页
    @PostMapping("/emp")
    public String addEmployee(Employee employee){
        System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    // 查询需要修改的员工信息回显到修改页面
    @GetMapping("/emp/{id}")
    public String toUpdatePage (@PathVariable("id") Integer id, Model model) {
        System.out.println(id);
        // 查询需要修改的员工信息
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);

        //查询部门信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);

        // 添加页面和修改页面是公用一个页面
        return "emps/add";
    }
    // 修改员工信息跳转到员工列表
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    // 删除员工
    @DeleteMapping("/emp/{id}")
    public String delEmployee (@PathVariable("id") Integer id) {
        System.out.println(id);
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
