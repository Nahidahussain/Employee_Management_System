package com.assigment.employees.controller;

import com.assigment.employees.dto.*;
import com.assigment.employees.model.Employee;
import com.assigment.employees.repository.EmployeeRepository;
import com.assigment.employees.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tci")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeService;

    @PostMapping("/employee-bonus")
    public ResponseEntity<List<EmployeeResponsedto>> addEmployee(@RequestBody EmployeePayload employeeRequestDtos){

        try{
            List<EmployeeResponsedto> employeeResponsedtos = employeeService.addEmployee(employeeRequestDtos);
            return new ResponseEntity<>(employeeResponsedtos, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/employee-bonus")
    public ResponseEntity<BonusEmployeeResponsePayload> getAllEmployeeEligibleForBonus(@RequestParam String bonusDate){

        try {
            BonusEmployeeResponsePayload bonusEmployeeResponsePayload = employeeService.getAllEmployeeEligibleForBonus(bonusDate);
            bonusEmployeeResponsePayload.setErrorMessage("");

            return new ResponseEntity<>(bonusEmployeeResponsePayload,HttpStatus.OK);

        }catch (Exception e){
            BonusEmployeeResponsePayload bonusEmployeeResponsePayload = new BonusEmployeeResponsePayload();
            bonusEmployeeResponsePayload.setErrorMessage(e.getMessage());
            bonusEmployeeResponsePayload.setData(null);

            return new ResponseEntity<>(bonusEmployeeResponsePayload,HttpStatus.BAD_REQUEST);
        }
    }
}
