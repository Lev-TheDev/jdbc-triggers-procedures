package br.com.dio;

import br.com.dio.persistence.*;
import br.com.dio.persistence.entity.ContactEntity;
import br.com.dio.persistence.entity.EmployeeEntity;
import br.com.dio.persistence.entity.ModuleEntity;
import net.datafaker.Faker;
import org.flywaydb.core.Flyway;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Stream;

import static java.time.ZoneOffset.UTC;

public class Main {

    private final static EmployeeDAO dao = new EmployeeDAO();
    private final static EmployeeParamDAO employeeDAO = new EmployeeParamDAO();
    private final static EmployeeAuditDAO employeeAuditDAO = new EmployeeAuditDAO();
    private final static ContactDAO contactDAO = new ContactDAO();
    private final static ModuleDAO moduleDAO = new ModuleDAO();
    private final static Faker faker = new Faker(Locale.of("pt", "BR"));

    public static void main(String[] args) {
        /*try(var connection = ConnectionUtil.getConnection()) {
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        var flyway = Flyway.configure()
                .dataSource("jdbc:mysql://localhost/jdbc-sample", "root", "q8w3NB25!")
                .load();
        flyway.migrate();

        /*var insert = new EmployeeEntity();
        insert.setName("Kenny");
        insert.setSalary(new BigDecimal("1900"));
        insert.setBirthday(OffsetDateTime.now().minusYears(20));
        System.out.println(insert);
        employeeDAO.insertWithProcedure(insert);
        System.out.println(insert);*/
        employeeDAO.findAll().forEach(System.out::println);

        //System.out.println(dao.findById(3));

        /*var update = new EmployeeEntity();
        update.setId(insert.getId());
        //update.setId(3);
        update.setName("Christie");
        update.setSalary(new BigDecimal("5500"));
        update.setBirthday(OffsetDateTime.now().minusYears(36).minusDays(10));
        employeeDAO.update(update);*/

        //employeeDAO.delete(insert.getId());

        /*employeeAuditDAO.findAll().forEach(System.out::println);
        System.out.println("---------------------------------------------------");
        employeeDAO.findAll().forEach(System.out::println);*/

        /*var entities = Stream.generate(() -> {
            var employee = new EmployeeEntity();
            employee.setName(faker.name().fullName());
            employee.setSalary(new BigDecimal(faker.number().digits(4)));
            employee.setBirthday(OffsetDateTime.of(LocalDate.now().minusYears(faker.number().numberBetween(40, 20)), LocalTime.MIN, UTC));
            return employee;
        }).limit(100).toList();

        employeeDAO.insertBatch(entities);*/

        /*var employee = new EmployeeEntity();
        employee.setName("Baros");
        employee.setSalary(new BigDecimal("3200"));
        employee.setBirthday(OffsetDateTime.now().minusYears(25));
        System.out.println(employee);
        employeeDAO.insert(employee);
        System.out.println(employee);

        var contact = new ContactEntity();
        contact.setDescription("barosbaros@gmail.com");
        contact.setType("e-mail");
        contact.setEmployee(employee);
        contactDAO.insert(contact);*/

        //System.out.println(employeeDAO.findById(1));

        /*var employee = new EmployeeEntity();
        employee.setName("Maykon");
        employee.setSalary(new BigDecimal("7000"));
        employee.setBirthday(OffsetDateTime.now().minusYears(35));
        employeeDAO.insert(employee);
        System.out.println("Successfully inserted: " + employee + "\n");

        var contact1 = new ContactEntity();
        contact1.setDescription("pscytrex@maykon.com");
        contact1.setType("e-mail");
        contact1.setEmployee(employee);
        contactDAO.insert(contact1);

        var contact2 = new ContactEntity();
        contact2.setDescription("67999751359");
        contact2.setType("celular");
        contact2.setEmployee(employee);
        contactDAO.insert(contact2);*/

        //System.out.println(employeeDAO.findById(112));
        //employeeDAO.findAll().forEach(System.out::println);

        /*var entities = Stream.generate(() -> {
            var employee = new EmployeeEntity();
            employee.setName(faker.name().fullName());
            employee.setSalary(new BigDecimal(faker.number().digits(4)));
            employee.setBirthday(OffsetDateTime.of(LocalDate.now().minusYears(faker.number().numberBetween(40, 20)), LocalTime.MIN, UTC));
            employee.setModules(new ArrayList<>());
            var moduleAmount = faker.number().numberBetween(1, 4);
            for (int i = 0; i < moduleAmount; i++) {
                var module = new ModuleEntity();
                module.setId(i +1);
                employee.getModules().add(module);
            }
            return employee;
        }).limit(3).toList();
        entities.forEach(employeeDAO::insert);*/

        //moduleDAO.findAll().forEach(System.out::println);
    }

}