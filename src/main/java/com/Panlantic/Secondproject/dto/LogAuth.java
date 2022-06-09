package com.Panlantic.Secondproject.dto;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

public class LogAuth {


        private String operation;
        private Date dateOperation;
        private String statusOfAuth;



    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public void setStatusOfAuth(String statusOfAuth) {
        this.statusOfAuth= statusOfAuth;
    }
    public void setOperation (String operation) {
        this.operation= operation;
    }

    public String getStatusOfAuth() {
        return statusOfAuth;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public String getOperation() {
        return operation;
    }
    public void transfer(String status, String operation)  {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8090/logauth";
        Date date = new Date();
        String k= "[" +date + "]"+" "+"Operation:" + "" + operation + "-" + status;
        HttpEntity<String> requestEntity = new HttpEntity<String>(k);
        ResponseEntity<String> trObjLog= restTemplate.postForEntity(url, requestEntity, String.class);
    }
}


