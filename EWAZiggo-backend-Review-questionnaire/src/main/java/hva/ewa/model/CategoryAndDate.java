package hva.ewa.model;

import java.sql.Timestamp;

public class CategoryAndDate {
    public String categoryname;
    public Timestamp questionnaireDate;

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public Timestamp getQuestionnaireDate() {
        return questionnaireDate;
    }

    public void setQuestionnaireDate(Timestamp questionnaireDate) {
        this.questionnaireDate = questionnaireDate;
    }

    public CategoryAndDate(String categoryname, Timestamp questionnaireDate) {
        this.categoryname = categoryname;
        this.questionnaireDate = questionnaireDate;
    }

}
