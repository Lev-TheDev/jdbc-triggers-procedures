package br.com.dio.persistence.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeEntity {

    private long id;

    private String name;

    private BigDecimal salary;

    private OffsetDateTime birthday;

    //private ContactEntity contact;

    private List<ContactEntity> contacts = new ArrayList<>();

    private List<ModuleEntity> modules = new ArrayList<>();

}