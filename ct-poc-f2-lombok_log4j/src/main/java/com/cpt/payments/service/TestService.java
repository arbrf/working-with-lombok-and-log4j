package com.cpt.payments.service;

import org.springframework.stereotype.Service;

import com.cpt.payments.pojo.copy.AddRequest;
import com.cpt.payments.utils.HMAC256Utils;
import com.google.gson.Gson;

@Service
public class TestService {
    private String secretkey = "ecom-ct-secret123";

    public int validateAndProcess(AddRequest req, String clientsignature) {
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(req);
        System.out.println("InputString: " + jsonRequest);
        String generatedSig = HMAC256Utils.generateSignature(secretkey, jsonRequest);
        System.out.println("Generated Signature : " + generatedSig);
        if(generatedSig.equals(clientsignature)) {
        	System.out.println(generatedSig+"  Generated Sig  Clieant Signature :"+clientsignature);
        	return req.getNum1()+req.getNum2();}
        	
        return -1; // Replace with your actual processing logic
    }
}
