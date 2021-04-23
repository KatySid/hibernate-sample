package ru.geekbrains.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

    public class ProductDAO {

        private SessionFactory factory;


        public ProductDAO(SessionFactory factory) {
            this.factory = factory;
        }

        public Optional findById(Long id) {
            try (Session session = factory.getCurrentSession()) {
                session.beginTransaction();
                Optional<Product> optionalProduct = Optional.ofNullable(session.get(Product.class, id));
                session.getTransaction().commit();
                return optionalProduct;
            }
        }

        public List<Product> findByAll() {
            try (Session session = factory.getCurrentSession()) {
                session.beginTransaction();
                List<Product> products = session.createQuery("from Product").getResultList();
                session.getTransaction().commit();
                return products;
            }
        }

        public void deleteById(Long id) {
            try (Session session = factory.getCurrentSession()) {
                session.beginTransaction();
                session.delete(session.get(Product.class, id));
                session.getTransaction().commit();
            }
        }

        public void saveOrUpdate(Product product) {
            try (Session session = factory.getCurrentSession()) {
                session.beginTransaction();
                session.saveOrUpdate(product);
                session.getTransaction().commit();
            }
        }
    }
