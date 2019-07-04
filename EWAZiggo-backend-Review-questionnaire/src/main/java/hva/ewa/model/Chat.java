package hva.ewa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
public class Chat implements Serializable {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "rating")
    private Integer rating;

    @Transient
    private List<Message> messages;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }


    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id='" + id + '\'' +
                ", created=" + created +
                ", rating=" + rating +
                ", messages=" + messages +
                '}';
    }
}
