package pe.edu.upc.puffleshop.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class ErrorMessageBuilder {
	public static ErrorMessageRest build (Errors errors) {
		ErrorMessageRest errorMessageRest 
			= new ErrorMessageRest(400, "Bad Request", "Error en los datos enviados: ");
		
		for (ObjectError objectError : errors.getAllErrors()) {
			errorMessageRest.addError(objectError.getObjectName() + ": " + objectError.getDefaultMessage());
		}
		
		return errorMessageRest;
	}
}