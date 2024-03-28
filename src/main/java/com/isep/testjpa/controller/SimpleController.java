package com.isep.testjpa.controller;

import com.isep.testjpa.repository.EmpRepository;
import com.isep.testjpa.model.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SimpleController {

    @Autowired
    private EmpRepository empRepository;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String hello(@Param("name") String name) {
        return "Hello " + name;
    }

    @RequestMapping(value="/employees", method= RequestMethod.GET)
    public List<Emp> getEmployees() {
        return empRepository.findAll();
    }

    @RequestMapping(value="/employee", method= RequestMethod.GET)
    public Optional<Emp> getEmpID(Long id) {
        return empRepository.findById(id);
    }

    @RequestMapping(value="/employeeUpdate", method= RequestMethod.POST)
    public Optional<Emp> updateEmpID(Long id, String ename, String efirst, String job, Long mgr, Long sal) {

        Emp empToUpdate = empRepository.getOne(id);
        empToUpdate.setEname(ename);
        empToUpdate.setEfirst(efirst);
        empToUpdate.setJob(job);
        empToUpdate.setMgr(mgr);
        empToUpdate.setSal(sal);
        empRepository.save(empToUpdate);

        return empRepository.findById(id);
    }

    @RequestMapping(value="/employeeDelete", method= RequestMethod.POST)
    public void deleteByID(Long id) {
        empRepository.deleteById(id);
    }

    @PostMapping(value="/employees")
    public Emp addEmployee(@RequestBody Emp emp) {
        return empRepository.save(emp);
    }

}


