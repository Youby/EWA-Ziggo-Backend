/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hva.ewa.rest;

import hva.ewa.model.Category;
import hva.ewa.model.CategoryAndDate;
import hva.ewa.model.Question;
import hva.ewa.model.Questionnaire;
import hva.ewa.service.QuestionnaireRepositoryService;
import hva.ewa.service.impl.QuestionnaireRepositoryServiceImpl;
//import hva.ewa.service.impl.QuestionnaireRepositoryServiceImpl;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;


//http://localhost:8080/VodafoneZiggoApi-1.2/services/rest/question

@Path("/question")
public class QuestionResource {

    /**
 * A reference to the repository service
 */
private QuestionnaireRepositoryService service;

    public QuestionResource() {
        service = QuestionnaireRepositoryServiceImpl.getInstance();
    }

    /*
    ---------------------------------------------------------------------------------
    HIERZO ZIJN METHODES DIE MET QUESTION TE MAKEN HEBBEN MET QUESTION OPHALEN/POSTEN
    ---------------------------------------------------------------------------------
     */

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Question> getAllQuestion() {

        return service.getAllQuestion();
    }

    @Path("/true")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Long getAllSolvedQuestion() {

        return service.getAllSolvedQuestion();

    }

    @Path("/questionnaire/questions/{questionnaireId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Question> getAllQuestionOfQuestionnaire(@PathParam("questionnaireId") int questionnaireId) {

        return service.getQuestionsOfQuestionnaire(questionnaireId);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addQuestion(Question question) {

        Question existingQuestion = service.getQuestionFromId(question.getId());

       if (existingQuestion == null) {
            service.addQuestion(question);
            return Response.status(Response.Status.CREATED).entity(question).build();
      } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("question already exists").build();
        }
    }




    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getQuestion(@PathParam("id") int id) {
        Question question = service.getQuestionFromId(id);
        if (question != null) {
            return Response.status(Response.Status.OK).entity(question).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/active/{id}")
    public Response getActiveQuestionnaire(@PathParam("id") int id) {
        Questionnaire questionnaire = service.getActiveQuestionnaire(id);


        if (questionnaire != null) {
            return Response.status(Response.Status.OK).entity(questionnaire).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/active/questions/{id}")

    public Collection<Question> getQuestionsOfActiveQuestionnaire(@PathParam("id") int id) {

        return service.getActiveQuestionnaireQuestions(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/active/{questionnaireId}")
    public Response setActiveQuestionnaire(@PathParam("questionnaireId") int questionnaireId) {

        Questionnaire questionnaire =service.getQuestionnaire(questionnaireId);
         service.setActiveQuestionnaire(questionnaire);
        System.out.println("vindbaretext "+ questionnaire.getQuestions());

        if (questionnaire != null) {
            return Response.status(Response.Status.OK).entity(questionnaire).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }


    @POST
    @Path("/questionnaire/{questionnaireId}/question/{questionId}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response addQuestionToQuestionnaire (@PathParam("questionnaireId") int id, @PathParam("questionId") int questionId) {



        service.addQuestionToQuestionnaire(id,questionId);
        return Response.status(Response.Status.OK).build();


    }


    /*
    ---------------------------------------------------------------------------
    HIER ZIJN DE METHODES DIE TE MAKEN HEBBEN MET QUESTIONNAIRES POSTEN/OPHALEN
    ---------------------------------------------------------------------------
     */

    @POST
    @Path("/questionnaire/{questionnaireId}/user/{customerId}")
    @Produces(MediaType.APPLICATION_JSON)

    public Response addQuestionnaireToUser (@PathParam("customerId") int customerId, @PathParam("questionnaireId") int questionnaireId) {


        service.addQuestionnaireToCustomer(customerId,questionnaireId);
        return Response.status(Response.Status.OK).build();

    }


    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/questionnaire/{id}")
    public Response getQuestionnaire(@PathParam("id") int id) {
        System.out.println("eerste gedeelte");
        Questionnaire questionnaire = service.getQuestionnaire(id);
        System.out.println("tweede gedeelte");
        if (questionnaire != null) {
            return Response.status(Response.Status.OK).entity(questionnaire).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }
    @Path("/questionnaire/user/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Questionnaire> getQuestionnairesFromUser(@PathParam("userId") int userId) {

       return service.getQuestionnairesFromUser(userId);
    }

    @Path("/questionnaire")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Questionnaire> getAllQuestionnaire() {

        return service.getAllQuestionnaire();
    }

    @POST
    @Path("/questionnaire/{categoryId}/{date}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response addQuestionnaire (@PathParam("categoryId") int id,@PathParam("date") long date, Questionnaire questionnaire) {



            questionnaire.setCreated(new Timestamp(date));

            service.addQuestionnaire(id,questionnaire);
            return Response.status(Response.Status.CREATED).entity(questionnaire).build();

    }


    /*
    ---------------------------------------------------------------------
    HIER ZIJN DE METHODES DIE TE MAKEN HEBBEN MET CATEGORY OPHALEN/POSTEN
    ---------------------------------------------------------------------
     */
    @POST
    @Path("/category")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    public Response addCategory(Category category) {
        System.out.println("dit is het begin");
        Category existingCategory = service.getCategory(category.getId());

        System.out.println("we zijn hier voorbij");
        if (existingCategory == null) {
            service.addCategory(category);
            return Response.status(Response.Status.CREATED).entity(category).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).entity("user already exists").build();
        }
    }



    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/category/{id}")
    public Response getCategory(@PathParam("id") int id) {
        Category category = service.getCategory(id);
        if (category != null) {
            return Response.status(Response.Status.OK).entity(category).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    @Path("/userquestionnaires/{userId}/category")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<CategoryAndDate> getCategoryFromQuestionnaireFromUser(@PathParam("userId") int userId) {

        return service.getCategoriesFromQuestionnaireFromUser(userId);
    }





    //
//    @GET
//    @Path("/{questionId}")
//    @Produces(APPLICATION_JSON)
//    public Response getQuestion(
//                @PathParam("categorieId") int categorieId,
//                @PathParam("questionId") int questionId) {
//        Response resp;
//        Category category = service.getCategorieFromId(categorieId);
//        if(category == null) {
//            return Response.status(Response.Status.NOT_FOUND).
//                    entity(new ClientError("Question not found for id " + categorieId)).build();
//        }
//
//        Question question = service.getQuestionOfCategorie(category, questionId);
//
//        if(question == null) {
//            resp = Response.status(Response.Status.NOT_FOUND).
//                    entity(new ClientError("Resource not found for question id " + questionId)).build();
//        } else {
//            resp = Response.status(Response.Status.OK).
//                    entity(question).build();
//
//        }
//
//        return resp;
//    }
//
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/delete/{id}")
    public Response deleteQuestion(@PathParam("id") int id){


        Question question = service.getQuestionFromId(id);


            service.deleteQuestion(question);
            service.setResponce("Succes");

            return Response.ok().build();
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateQuestion(Question updateQuestion) {

       service.updateQuestion(updateQuestion);



        return Response.ok(updateQuestion).build();
    }



}
