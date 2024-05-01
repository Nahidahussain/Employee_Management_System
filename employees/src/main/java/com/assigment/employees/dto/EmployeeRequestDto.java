package com.assigment.employees.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeRequestDto {

    String empName;
    String department;
    long amount;
    String currency;
    String joiningDate;
    String exitDate;
}
