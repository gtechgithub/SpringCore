package ApplicationContextAwareSpringEvent;

import org.springframework.stereotype.Component;

@Component
public class CustomEvent   {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
