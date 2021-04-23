package ru.geekbrains.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@org.springframework.context.annotation.Configuration
@ComponentScan ("ru.gekbrains.hibernate")
public class PrepareDataApp {
   @Bean
   public SessionFactory factory(){
     SessionFactory factory = new Configuration()
              .configure("hibernate.cfg.xml")
              .buildSessionFactory();
       try (Session session = factory.getCurrentSession()){
           String sql = Files.lines(Paths.get("full.sql")).collect(Collectors.joining(" "));
           session.beginTransaction();
           session.createNativeQuery(sql).executeUpdate();
           session.getTransaction().commit();

       } catch (IOException e) {
           e.printStackTrace();
       }
       return factory;
   }
   @Bean(value = "dao")
    public ProductDAO productDAO(){
       return new ProductDAO(factory());
   }
}
