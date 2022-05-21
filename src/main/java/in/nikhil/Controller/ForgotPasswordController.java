package in.nikhil.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import in.nikhil.Service.UserServiceInterface;

@RestController
public class ForgotPasswordController {
	
	@Autowired
	private UserServiceInterface service;
	
	@GetMapping("/forgotpassword/{emailId}")
	public String forgotPassword(@PathVariable String emailId) {
		
		return service.forgotPassword(emailId);
	}
}
