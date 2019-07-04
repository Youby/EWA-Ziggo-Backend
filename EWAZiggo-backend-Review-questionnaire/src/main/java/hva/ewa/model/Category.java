package hva.ewa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Category implements Serializable {
    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy="category", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private Collection<Questionnaire> questionnaires;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Questionnaire  addQuestionnaire(Questionnaire questionnaire){
//        questionnaires.add(questionnaire);
        return questionnaire;

    }
    public Collection<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(Collection<Questionnaire> questionnaires) {
        this.questionnaires = questionnaires;
    }
}
