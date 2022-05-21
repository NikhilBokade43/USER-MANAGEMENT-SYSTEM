package in.nikhil.Binding;

import lombok.Data;

@Data
public class UnlockForm {
	
	private String emailId;
	private String tempPass;
	private String newPass;
	private String confirmPass;
	
}
