package in.nikhil.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.nikhil.Binding.LoginForm;
import in.nikhil.Service.UserServiceInterface;

@RestController
public class LoginRestController {
	
	@Autowired
	private UserServiceInterface service;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginForm form) {
		String status = service.userLogin(form);
		
		return status;
		
	}
	

}
