package contract;

public class ReturnMessage {

	private int code;
	private String message;
	
	public ReturnMessage(){
		this.code = 0;
		this.message = "Message not initialized.";
	}
	
	public void ok(){
		this.code = 1;
		this.message = "Operation was successful.";
	}
	
	public void duplicateExists(){
		this.code = -1;
		this.message = "Username already exists.";
	}
	
	public void invalidUsernameOrPassword(){
		this.code=-2;
		this.message="Either username or password was invalid.";
	}
	
	public int getCode(){
		return this.code;
	}
	
	public String getMessage(){
		return this.message;
	}
}

