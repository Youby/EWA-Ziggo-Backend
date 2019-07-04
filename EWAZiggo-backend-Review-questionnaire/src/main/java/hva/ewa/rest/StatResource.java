package hva.ewa.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hva.ewa.service.ChatRepositoryService;
import hva.ewa.service.QuestionnaireRepositoryService;
import hva.ewa.service.impl.ChatRepositoryServiceImpl;
import hva.ewa.service.impl.QuestionnaireRepositoryServiceImpl;
import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/statistics") //http://localhost:8080/VodafoneZiggoApi-1.2/services/rest/statistics
public class StatResource {

    private ChatRepositoryService service;

    private QuestionnaireRepositoryService questionnaireRepositoryService;
    public StatResource() {
        service = ChatRepositoryServiceImpl.getInstance();
        questionnaireRepositoryService = QuestionnaireRepositoryServiceImpl.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatistics() {
        JSONObject obj = new JSONObject();
        Map<String, Long> chats = service.getAmountOfChatsByMonth();
        try {
            obj.put("satisfaction", service.getCustomerSatisfaction());
            obj.put("totalChats", service.getAmountOfChats());
            obj.put("chatsPerMonth", chats);
            obj.put("problemSolved", questionnaireRepositoryService.getAllSolvedQuestion());
            return Response.status(Response.Status.OK).entity(obj).build();
        } catch (NullPointerException nx) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }
}
