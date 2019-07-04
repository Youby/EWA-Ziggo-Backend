package hva.ewa.rest;

import hva.ewa.model.Chat;
import hva.ewa.model.Message;
import hva.ewa.model.embeddable.MessageId;
import hva.ewa.service.ChatRepositoryService;
import hva.ewa.service.impl.ChatRepositoryServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.List;


/**
 * The chat REST resource
 *
 * @author José Niemel
 */
@Path("chat") // http://localhost:8080/VodafoneZiggoApi-1.2/services/rest/chat
public class ChatResource {

    private ChatRepositoryService service;

    public ChatResource() {
        service = ChatRepositoryServiceImpl.getInstance();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{date}")
    public Response saveChat(@PathParam("date") long date, Chat chat) {
        chat.setCreated(new Timestamp(date));
        System.out.println("Chat is being saved...");
        List<Message> messages = chat.getMessages();
        service.saveChat(chat);
        service.saveMessages(messages, chat);
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/rating")
    public Response rateChat(@PathParam("id") String id, int rating) {
        System.out.println("RATING = " + rating);
        Chat chat = service.getChat(id);
        if (chat == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        chat.setRating(rating);
        service.updateChat(chat);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllChats() {
        if (service.getAllChats().isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.OK).entity(service.getAllChats()).build();
        }
    }

}
