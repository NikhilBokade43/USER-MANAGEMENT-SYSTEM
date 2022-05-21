package in.nikhil.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.nikhil.Binding.UnlockForm;
import in.nikhil.Service.UserServiceInterface;

@RestController
public class UnlockRestController {
	
	@Autowired
	private UserServiceInterface service;
	
	@PostMapping("/unlockAccount")
	public String unlockUserAccount(@RequestBody UnlockForm form) {
		return service.unlockAccount(form);
	}
	
}
