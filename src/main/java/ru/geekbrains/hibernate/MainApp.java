package ru.geekbrains.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Collectors;

public class MainApp {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PrepareDataApp.class);
        ProductDAO dao = context.getBean("dao", ProductDAO.class);
        System.out.println(dao.findByAll());
        System.out.println(dao.findById(Long.valueOf(1)));
        Product product= new Product("apple", 500);
        dao.saveOrUpdate(product);
        System.out.println(dao.findByAll());
        dao.deleteById(Long.valueOf(1));
        System.out.println(dao.findByAll());

    }




}
