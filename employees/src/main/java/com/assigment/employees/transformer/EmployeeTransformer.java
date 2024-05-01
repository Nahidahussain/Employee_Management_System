package com.assigment.employees.transformer;

import com.assigment.employees.dto.EmployeeRequestDto;
import com.assigment.employees.dto.EmployeeResponsedto;
import com.assigment.employees.dto.EmployeesDto;
import com.assigment.employees.model.Employee;

public class EmployeeTransformer {

    public static Employee employeeRequestDtoToEmployee(EmployeeRequestDto employeeRequestDto){
        return Employee.builder()
                .empName(employeeRequestDto.getEmpName())
                .amount(employeeRequestDto.getAmount())
                .department(employeeRequestDto.getDepartment())
                .currency(employeeRequestDto.getCurrency())
                .joiningDate(employeeRequestDto.getJoiningDate())
                .exitDate(employeeRequestDto.getExitDate())
                .build();
    }

    public static EmployeeResponsedto employeeToEmployeeResponseDto(Employee employee){

        return EmployeeResponsedto.builder()
                .id(employee.getId())
                .empName(employee.getEmpName())
                .amount(employee.getAmount())
                .currency(employee.getCurrency())
                .department(employee.getDepartment())
                .joiningDate(employee.getJoiningDate())
                .exitDate(employee.getExitDate())
                .build();
    }

    public static EmployeesDto employeeToEmployeesDto(Employee employee){
        return EmployeesDto.builder()
                .amount(employee.getAmount())
                .empName(employee.getEmpName())
                .build();
    }
}
