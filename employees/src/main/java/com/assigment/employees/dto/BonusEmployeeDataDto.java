package com.assigment.employees.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BonusEmployeeDataDto {

    String currency;

    List<EmployeesDto> employees;
}
