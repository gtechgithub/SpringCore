package controllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudentControllerAdvice {

	  @ExceptionHandler(CustomizedException.class)
	  @ResponseBody
	  public ResponseEntity<?> exceptionHandler(CustomizedException ex){
		  
		  System.out.println(" inside controller advice exceptionHandler "+ ex.getMessage());
		  
		  ResponseErrorMessage respErrorMsg = new ResponseErrorMessage();
		  respErrorMsg.setErrorCode("001");
		  respErrorMsg.setErrorMessage(ex.getMessage());
		  return new ResponseEntity<ResponseErrorMessage>(respErrorMsg, HttpStatus.NOT_FOUND);
		  
		  
	  }
	
}
