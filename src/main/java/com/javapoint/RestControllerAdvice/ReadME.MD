# Rest Controller advice

incase of any exceptions, exception handler defined in the ControllerAdvice will be invoked and
it takes the necessary actions.

```

	  @ExceptionHandler(CustomizedException.class)
	  @ResponseBody
	  public ResponseEntity<?> exceptionHandler(CustomizedException ex){
		  
		  System.out.println(" inside controller advice exceptionHandler "+ ex.getMessage());
		  
		  ResponseErrorMessage respErrorMsg = new ResponseErrorMessage();
		  respErrorMsg.setErrorCode("001");
		  respErrorMsg.setErrorMessage(ex.getMessage());
		  return new ResponseEntity<ResponseErrorMessage>(respErrorMsg, HttpStatus.NOT_FOUND);
		  
		  
	  }
    
```
