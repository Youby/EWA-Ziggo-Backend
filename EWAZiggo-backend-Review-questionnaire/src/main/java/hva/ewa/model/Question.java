package hva.ewa.model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Question implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "question")
    private String question;

    @Column(name = "solved")
    private Boolean solved;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "questionnaire", nullable = true)
    private Questionnaire questionnaire;

    public Question() {
    }

    public Question(int id, String title, String question) {
        setId(id);
        setTitle(title);
        setQuestion(question);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public Boolean isSolved() {
        return solved;
    }

    public void setSolved(Boolean solved) {
        this.solved = solved;
    }
}
