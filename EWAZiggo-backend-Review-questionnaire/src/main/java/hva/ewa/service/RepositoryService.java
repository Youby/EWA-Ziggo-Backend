package hva.ewa.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RepositoryService {
    private EntityManagerFactory  entityManagerFactory = Persistence.createEntityManagerFactory("vodafoneziggoPU");
    protected EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
