package hva.ewa.service.impl;

import hva.ewa.model.Employee;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import hva.ewa.service.jwt.JWTUtils;
import java.security.Key;
import java.util.Date;
import java.util.List;

import hva.ewa.rest.model.WebToken;
import hva.ewa.service.UserRepositoryService;
import hva.ewa.model.User;

import javax.persistence.*;
import javax.persistence.criteria.*;
import javax.ws.rs.core.UriInfo;

/**
 *
 * A simple implementation of the repository service
 * using memory. Some predefined cards are added
 * during class loading.
 *
 * @author Emre Efe
 */

public class UserRepositoryServiceImpl implements UserRepositoryService {

    private EntityManagerFactory entityManagerFactory;

    // A singleton reference
    private static UserRepositoryServiceImpl instance;

    // An instance of the service is created and during class initialisation
    static {
        instance = new UserRepositoryServiceImpl();
        instance.loadExamples();
    }

    //  Method to get a reference to the instance (singleton)
    public static UserRepositoryService getInstance() {
        return instance;
    }

    private UserRepositoryServiceImpl() {

        entityManagerFactory = Persistence.createEntityManagerFactory("vodafoneziggoPU");
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    @Override
    public List<User> getAllUsers() {

        EntityManager em = entityManagerFactory.createEntityManager();

        List<User> users =
                em.createQuery("SELECT u FROM User u").getResultList();

        em.close();

        return users;
    }

    @Override
    public User getUser(User user) {

        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<User> q = cb.createQuery(User.class);
        Root<User> c = q.from(User.class);
        Predicate emailPredicate = cb.equal(c.get("email"), user.getEmail());
        q.where(emailPredicate);
        TypedQuery<User> query = em.createQuery(q);

        try {
            user = query.getSingleResult();
        } catch (NoResultException e) {
            user = null;
        }

        em.close();

        return user;
    }

    @Override
    public User getUserFromId(int id) {

        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<User> q = cb.createQuery(User.class);
        Root<User> c = q.from(User.class);
        Predicate idPredicate = cb.equal(c.get("id"), id);
        q.where(idPredicate);
        TypedQuery<User> query = em.createQuery(q);

        User user;
        try {
            user = query.getSingleResult();
        } catch (NoResultException e) {
            user = null;
        }

        em.close();

        return user;
    }

    @Override
    public void addUser(User user) {

        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

        em.close();
    }

    @Override
    public void deleteUser(User user){

        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        user = em.merge(user);
        em.remove(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void changeUser(User user){
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        user = em.merge(user);
        em.persist(user);
        em.getTransaction().commit();

        em.close();
    }

    @Override
    public User checkCredentials(String email, String password) {

        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<User> q = cb.createQuery(User.class);
        Root<User> c = q.from(User.class);
        Predicate emailPredicate = cb.equal(c.get("email"), email);
        Predicate passwordPredicate = cb.equal(c.get("password"), password);
        q.where(emailPredicate, passwordPredicate);
        TypedQuery<User> query = em.createQuery(q);

        User user;
        try {
            user = query.getSingleResult();
        } catch (NoResultException e) {
            user = null;
        }

        em.close();

        return user;
    }

    /**
     * Reverted old login. If we want to use JWT, uncomment the code below.
     * Uncomment in the following:
     * UserResource, UserRepositoryService, UserRepositoryServiceImpl.
     */
//    @Override
//    public Boolean checkCredentials(String userEmail, String password) {
//
//        EntityManager em = getEntityManager();
//        Query query = em.createQuery("SELECT q FROM User q WHERE email = " + "\'" + userEmail + "\'");
//        User user = (User) query.getSingleResult();
//        if(!password.equals(user.getPassword())) {
//            return false;
//        }
//
//        em.close();
//
//        return true;
//    }


    private void loadExamples() {

        User us = new User("t", "t");
        addUser(us);
    }

    private String getRole(String userEmail) {

        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT q FROM User q WHERE email = " + "\'" + userEmail + "\'");
        User user = (User) query.getSingleResult();
        Employee employee = em.find(Employee.class, user.getIdUser());

        if (employee != null) {
            if (employee.getDepartment().equalsIgnoreCase("admin")){
                return "admin";
            }
            if (employee.getDepartment().equalsIgnoreCase("kcc")){
                return "kcc";
            }
        }

        em.close();

        return "customer";
    }

    public String issueToken(String email, UriInfo uri) {
        EntityManager em = getEntityManager();
        Key key = JWTUtils.getKey();

        // Get the user
        Query query = em.createQuery("SELECT q FROM User q WHERE email = " + "\'" + email + "\'");
        User user = (User) query.getSingleResult();

        // Could have more than one role, but now it is just one
        String role = getRole(email);

        String jwtToken = Jwts.builder()
                .setSubject(email)
                .claim("role",role)
                .claim("name", user.getName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+15*60*1000)) // 15 minutes
                .signWith(key,SignatureAlgorithm.HS512)
                .compact();

        return jwtToken;
    }
}
