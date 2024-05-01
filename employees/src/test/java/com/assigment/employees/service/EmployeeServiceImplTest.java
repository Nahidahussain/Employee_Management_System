package com.assigment.employees.service;

import com.assigment.employees.dto.EmployeePayload;
import com.assigment.employees.dto.EmployeeRequestDto;
import com.assigment.employees.dto.EmployeeResponsedto;
import com.assigment.employees.model.Employee;
import com.assigment.employees.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceImplTest {

    @Autowired
    EmployeeServiceImpl employeeService;

    @MockBean
    EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void addEmployeeTest() {

        EmployeeRequestDto employeeRequestDto = EmployeeRequestDto.builder()
                .department("IT")
                .amount(1000)
                .currency("INR")
                .empName("Nahida")
                .joiningDate("may-06-2023")
                .exitDate("jun-06-2024")
                .build();
        List<EmployeeRequestDto> employeeRequestDtoList = new ArrayList<>();
        employeeRequestDtoList.add(employeeRequestDto);
        EmployeePayload employeePayload = new EmployeePayload();
        employeePayload.setEmployees(employeeRequestDtoList);

        Employee employee = Employee.builder()
                .empName("Nahida")
                .amount(20000)
                .department("IT")
                .currency("INR")
                .joiningDate("may-06-2023")
                .exitDate("jun-04-2024")
                .build();
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);

        Mockito.when(employeeRepository.findAll()).thenReturn(employeeList);

        employeeService.addEmployee(employeePayload);

        Assertions.assertEquals("Nahida",employeeList.get(0).getEmpName());
        Assertions.assertEquals(20000,employeeList.get(0).getAmount());
        Assertions.assertEquals("IT",employeeList.get(0).getDepartment());
        Assertions.assertEquals("INR",employeeList.get(0).getCurrency());
        Assertions.assertEquals("may-06-2023",employeeList.get(0).getJoiningDate());
        Assertions.assertEquals("jun-04-2024",employeeList.get(0).getExitDate());
        Assertions.assertEquals(1000,employeePayload.getEmployees().get(0).getAmount());
    }

    @Test
    void getAllEmployeeEligibleForBonusTest() {



    }
}