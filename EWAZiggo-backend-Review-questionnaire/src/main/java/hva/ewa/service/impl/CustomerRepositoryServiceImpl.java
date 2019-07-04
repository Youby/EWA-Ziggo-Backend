package hva.ewa.service.impl;

import hva.ewa.model.Customer;
import hva.ewa.model.Appointment;
import hva.ewa.model.Equipment;
import hva.ewa.service.CustomerRepositoryService;
import hva.ewa.service.RepositoryService;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class CustomerRepositoryServiceImpl extends RepositoryService implements CustomerRepositoryService {

    private static CustomerRepositoryServiceImpl instance = new CustomerRepositoryServiceImpl();

    public static CustomerRepositoryService getInstance() {
        return instance;
    }
    EntityManager customerManager = getEntityManager();


    @Override
    public Customer getCustomer(int id) {

        Customer customer = customerManager.find(Customer.class, id);
        System.out.println(customer);
        return customer;

//        EntityManager em = getEntityManager();
//         Customer customer = em.find(Customer.class, id);
//         em.close();
//         return customer;

    }

    @Override
    public void addCustomer(Customer customer) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Appointment> getAppointments(int id) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT m FROM Appointment m WHERE m.id.customer.id = " + id);
        List<Appointment> appointments = query.getResultList();
        em.close();
        return appointments;
    }

    @Override
    public List<Equipment> getEquipment(int id) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT m FROM Equipment m WHERE m.id.customer.id = " + id);
        List<Equipment> equipment = query.getResultList();
        em.close();
        return equipment;
    }
}
