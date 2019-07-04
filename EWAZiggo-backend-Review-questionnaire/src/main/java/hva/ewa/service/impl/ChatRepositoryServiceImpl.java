package hva.ewa.service.impl;

import hva.ewa.model.Chat;
import hva.ewa.model.Message;
import hva.ewa.model.embeddable.MessageId;
import hva.ewa.service.ChatRepositoryService;
import hva.ewa.service.RepositoryService;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChatRepositoryServiceImpl extends RepositoryService implements ChatRepositoryService {

    private static ChatRepositoryServiceImpl instance = new ChatRepositoryServiceImpl();

    public static ChatRepositoryService getInstance() {
        return instance;
    }

    @Override
    public void saveChat(Chat chat) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(chat);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Chat getChat(String id) {
        EntityManager em = getEntityManager();
        Chat chat = em.find(Chat.class, id);
        em.close();
        return chat;
    }

    @Override
    public void updateChat(Chat chat) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(chat);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Chat> getAllChats() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT c FROM Chat c ");
        List<Chat> chats = query.getResultList();
        em.close();
        return chats;
    }

    @Override
    public Integer getAmountOfChats() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT COUNT(c) FROM Chat c ");
        Long count = (Long) query.getSingleResult();
        em.close();
        return count.intValue();
    }

    @Override
    public void saveMessages(List<Message> messages, Chat chat) {
        EntityManager em = getEntityManager();
        for (int i = 0; i < messages.size(); i++) {
            Message message = messages.get(i);
            MessageId id = new MessageId(chat, i);
            message.setId(id);
            message.setTimestamp(new Timestamp(message.getSent()));
            em.getTransaction().begin();
            em.persist(message);
            em.getTransaction().commit();
        }
        em.close();
    }

    @Override
    public Map<String, Long> getAmountOfChatsByMonth() {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT MONTHNAME(c.created), COUNT(c.created) FROM Chat c GROUP BY MONTH(c.created)");
        List<Object[]> list = query.getResultList();
        Map<String, Long> stats = new HashMap<>();
        for (Object[] obj : list) {
            stats.put((String) obj[0], (Long) obj[1]);
        }
        em.close();
        return stats;
    }

    @Override
    public Double getCustomerSatisfaction() {
        EntityManager em = getEntityManager();
        Long chatAmount =(Long) em.createQuery("SELECT COUNT(c) FROM Chat c WHERE c.rating IS NOT NULL").getSingleResult();
        Integer totalScore = (chatAmount.intValue() * 5);
        Query query = em.createQuery("SELECT SUM(rating) FROM Chat c ");
        Long score = (Long) query.getSingleResult();
        em.close();
        return (score.doubleValue() / totalScore.doubleValue() * 100.0);
    }


}
