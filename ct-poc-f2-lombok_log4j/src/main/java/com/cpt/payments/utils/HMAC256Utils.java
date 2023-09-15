package com.cpt.payments.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HMAC256Utils {

	private HMAC256Utils() {	}

	/**
	 * Generates an HMACSHA256 signature for a given JSON input string using the provided secret key.
	 *
	 * @param secretKey The secret key used for HMACSHA256.
	 * @param jsonInput The JSON input string to be signed.
	 * @return The HMACSHA256 signature as a Base64-encoded string.
	 */
	public static String generateSignature(String secretKey, String jsonInput) {
	    String signature = null;
	    try {
	        // Create a SecretKeySpec object from the secret key
	        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");

	        // Create an HMACSHA256 instance
	        Mac mac = Mac.getInstance("HmacSHA256");
	        mac.init(secretKeySpec);

	        // Convert the JSON input to bytes
	        byte[] data = jsonInput.getBytes();

	        // Calculate the HMAC signature
	        byte[] hmacBytes = mac.doFinal(data);

	        // Encode the result in Base64 to get a human-readable string
	        signature = Base64.getEncoder().encodeToString(hmacBytes);
	        System.out.println(signature);

	    } catch (NoSuchAlgorithmException | InvalidKeyException e) {
	        e.printStackTrace();
	    }

	    return signature;
	}
}
