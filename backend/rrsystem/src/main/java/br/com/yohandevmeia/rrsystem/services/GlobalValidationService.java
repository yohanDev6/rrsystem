package br.com.yohandevmeia.rrsystem.services;

public abstract class GlobalValidationService {

	/**
    * Checks the ID used in services.
    * 
    * @param id ID to be validated.
    * @throws IllegalArgumentException if the ID is invalid.
    */
   public static void verifyId(long id) {
       if (id <= 0) throw new IllegalArgumentException("Invalid ID: " + id);
   }

   /**
    * Checks that the object used in the service is not null.
    * 
    * @param object Object to be validated.
    * @param message Exception message in case of null.
    * @throws IllegalArgumentException if the object is null.
    */
   public static void verifyObject(Object object, String message) {
       if (object == null) throw new IllegalArgumentException(message);
   }
   
   /**
    * Checks that the email used in the Administrator and Client services is not null.
    * 
    * @param email Email to be validated.
    * @param message Exception message in case of null or empty.
    * @throws IllegalArgumentException if the email is invalid.
    */
   public static void verifyEmail(String email) {
	   if (email.isEmpty() || email == null) throw new IllegalArgumentException("Email: " + email + " is invalid");
   }
}
