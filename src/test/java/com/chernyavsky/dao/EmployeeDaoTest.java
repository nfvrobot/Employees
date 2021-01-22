package com.chernyavsky.dao;

import com.chernyavsky.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import static org.junit.Assert.assertNotNull;

public class EmployeeDaoTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @AfterClass
    public static void after() {
        FACTORY.close();
    }

    @Before
    public void clean() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();
            session.createQuery("delete from Employee").executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Test
    public void checkSaveEntity() {
        Employee ivan = new Employee("Ivan");
        try (Session session = FACTORY.openSession()) {
            Serializable employeeId = session.save(ivan);
            assertNotNull("id is not null", employeeId);
        }
    }

    @Test
    public void checkFindEmployee() {
        Employee ivan = new Employee("Fedor");
        try (Session session = FACTORY.openSession()) {
            Serializable employeeId = session.save(ivan);
            assertNotNull("id is not null", employeeId);
            Employee employee = session.find(Employee.class, employeeId);
            assertNotNull("Entity not null", employee);
        }
    }
}
