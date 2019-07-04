package hva.ewa.rest;

import hva.ewa.model.Employee;
import hva.ewa.service.EmployeeRepositoryService;
import hva.ewa.service.impl.EmployeeRespositoryServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employees") // http://localhost:8080/VodafoneZiggoApi-1.2/services/rest/employees
public class EmployeeResource {
    private EmployeeRepositoryService service;

    public EmployeeResource() {
        service = EmployeeRespositoryServiceImpl.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployees() {
        if (service.getAllEmployees().isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.OK).entity(service.getAllEmployees()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEmployee(Employee employee){
        boolean isSaved = service.saveEmployee(employee);
        if(isSaved) {
            return Response.status(Response.Status.CREATED).entity(employee).build();
        }else{
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }
}
