package com.assigment.employees.transformer;

import com.assigment.employees.dto.DepartmentRequestDto;
import com.assigment.employees.model.Department;

public class DepartmentTransformer {

    public static Department departmentRequestDtoToDepartment(DepartmentRequestDto departmentRequestDto){
        return Department.builder()
                .department(departmentRequestDto.getDepartment())
                .build();
    }
}
