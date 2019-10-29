package pe.edu.upc.puffleshop.validator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ErrorMessageRest {
	private Date date;
	private int status;
	private String error;
	private String message;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<String> errors;
	
	public ErrorMessageRest(int status, String error, String message) {
		this.date = new Date();
		this.status = status;
		this.error = error;
		this.message = message;
		this.errors = new ArrayList<>();
	}

	public void addError( String error ) {
		errors.add(error);
	}

	public Date getDate() {
		return date;
	}

	public int getStatus() {
		return status;
	}

	public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}

	public List<String> getErrors() {
		return errors;
	}
	
}