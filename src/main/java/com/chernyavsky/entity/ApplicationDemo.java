package com.chernyavsky.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ApplicationDemo {

    public static void main(String[] args) {

        try(SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession()) {
            Employee employee = session.find(Employee.class, 1L);
            System.out.println(employee);
        }
    }
}
