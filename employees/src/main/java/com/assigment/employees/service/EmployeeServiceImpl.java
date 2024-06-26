package com.assigment.employees.service;

import com.assigment.employees.dto.*;
import com.assigment.employees.model.Department;
import com.assigment.employees.model.Employee;
import com.assigment.employees.repository.DepartmentRepository;
import com.assigment.employees.repository.EmployeeRepository;
import com.assigment.employees.transformer.EmployeeTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;


    public List<EmployeeResponsedto> addEmployee(EmployeePayload employeePayload){
        List<EmployeeRequestDto> employeeRequestDtoList = employeePayload.getEmployees();

        List<Employee> employeeList = employeeRequestDtoList.stream().map(employeeRequestDto -> EmployeeTransformer.employeeRequestDtoToEmployee(employeeRequestDto)).collect(Collectors.toList());
        List<Employee> savedEmployeeList = employeeRepository.saveAll(employeeList);

        List<Department> departmentList = savedEmployeeList.stream().map(employee -> Department.builder()
                .department(employee.getDepartment())
                .employee(employee)
                .build()).collect(Collectors.toList());
        departmentRepository.saveAll(departmentList);

        // prepare the response dto from the saved entity
        List<EmployeeResponsedto> employeeResponsedtoList = savedEmployeeList.stream().map(employee -> EmployeeTransformer.employeeToEmployeeResponseDto(employee)).collect(Collectors.toList());
        return employeeResponsedtoList;
    }

    public BonusEmployeeResponsePayload getAllEmployeeEligibleForBonus(String bonusDate){

        Date date = getDateFromStringDate(bonusDate);
        List<Employee> employeeList = employeeRepository.findAll();

        List<Employee> bonusList = employeeList.stream()
                .filter(employee -> {
                    Date joiningDate = getDateFromStringDate(employee.getJoiningDate());
                    Date exitDate = getDateFromStringDate(employee.getExitDate());
                    return date.after(joiningDate) && date.before(exitDate);
                })
                .collect(Collectors.toList());


        Map<String, List<Employee>> employeesByCurrencySorted = bonusList.stream()
                .collect(Collectors.groupingBy(
                        Employee::getCurrency,
                        TreeMap::new,
                        Collectors.toList()
                ));

        List<BonusEmployeeDataDto> bonusEmployeeDataDtoList = employeesByCurrencySorted.keySet().stream()
                .map(key -> {
                    List<Employee> employeeList1 = employeesByCurrencySorted.get(key);
                    List<EmployeesDto> employeesDtoList = employeeList1.stream()
                            .map(EmployeeTransformer::employeeToEmployeesDto)
                            .sorted(Comparator.comparing(EmployeesDto::getEmpName))
                            .collect(Collectors.toList());

                    return BonusEmployeeDataDto.builder()
                            .currency(key)
                            .employees(employeesDtoList)
                            .build();
                })
                .collect(Collectors.toList());

        return BonusEmployeeResponsePayload.builder()
                .data(bonusEmployeeDataDtoList)
                .build();

    }

    private Date getDateFromStringDate(String stringDate){

        // Create a SimpleDateFormat object with the appropriate format
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM-dd-yyyy");
        Date date = null;
        try {
            // Parse the string into a Date object
            date = dateFormat.parse(stringDate);
        } catch (ParseException e) {
            // Handle parsing exception
            e.printStackTrace();
        }

        return date;
    }
}
