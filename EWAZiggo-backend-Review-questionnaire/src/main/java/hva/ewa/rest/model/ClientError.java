/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hva.ewa.rest.model;

import java.io.Serializable;

/**
 * A simple class representing a client error
 * 
 * @author Emre Efe
 */
public class ClientError implements Serializable {
    
    private String message;
    
    public ClientError(String message) {
        setMessage(message);
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
    
}
