//package hva.ewa.rest;
//import hva.ewa.model.Category;
//import hva.ewa.rest.model.ClientError;
//import hva.ewa.service.QuestionnaireRepositoryService;
//import hva.ewa.service.impl.QuestionnaireRepositoryServiceImpl;
//
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import java.util.List;
//
//@Path("flashcards")
//public class CategorieResource {
//
//    /** a reference to the repository service */
//    private QuestionnaireRepositoryService service;
//
//    public CategorieResource() {
//        service = QuestionnaireRepositoryServiceImpl.getInstance();
//    }
//
//    /**
//     * Get all categories
//     * @return a JSON representation of a list of cards
//     */
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Category> getAllCategorie() {
//        return service.getAllCategorie();
//    }
//
//    /**
//     * Getting a specific categorie
//     * @param id
//     * @return
//     */
//    @GET
//    @Path("/{categorieId}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getCategorie(@PathParam("categorieId") int id) {
//
//        Category fc = service.getCategorieFromId(id);
//
//        if(fc == null) {
//            return Response.status(Response.Status.NOT_FOUND).
//                    entity(new ClientError("resource not found for id " + id)).build();
//        } else {
//            return Response.status(Response.Status.OK).entity(fc).build();
//        }
//    }
//
//    /**
//     * Getting the question sub-resource
//     * @return
//     */
//    @Path("/{categorieId}/questions")
//    public QuestionResource getQuestionResource() {
//        return new QuestionResource();
//    }
//
//}
