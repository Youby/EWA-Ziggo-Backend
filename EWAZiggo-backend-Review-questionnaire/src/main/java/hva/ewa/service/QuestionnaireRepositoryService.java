package hva.ewa.service;

import hva.ewa.model.Category;
import hva.ewa.model.CategoryAndDate;
import hva.ewa.model.Question;
import hva.ewa.model.Questionnaire;

import java.util.Collection;
import java.util.List;


public interface QuestionnaireRepositoryService {

    List<Category> getAllCategorie();

    List<Question> getAllQuestion();

    List<Questionnaire> getAllQuestionnaire();

    Long getAllSolvedQuestion();


    Question getQuestionFromId(int questionId);


    void addCategory(Category cat);

    void setResponce(Question question);

    void deleteQuestion(Question question);

    void addQuestion(Question question);

    Questionnaire setActiveQuestionnaire(Questionnaire questionnaire);

    Collection<Question> getQuestionsOfQuestionnaire(int questionnaireId);

    Question getQuestionOfQuestionnaire(Questionnaire questionnaire, int questionId);

    Questionnaire getQuestionnaire(int id);

    Questionnaire getActiveQuestionnaire(int categoryId);

    Collection<Question> getActiveQuestionnaireQuestions(int categoryId);


    Category getCategory(int id);

    Collection<Questionnaire> getQuestionnairesFromUser(int userId);

    Collection<CategoryAndDate> getCategoriesFromQuestionnaireFromUser(int userId);

    Questionnaire getQuestionnaireFromUser(int userId, int questionId);

    Question updateQuestion(Question question);

    void addQuestionnaire(int category, Questionnaire questionnaire);

    void addQuestionToQuestionnaire(int questionnaireId, int questionId);

    void addQuestionnaireToCustomer(int user, int questionnaireId);

    void setResponce(String test);

}
