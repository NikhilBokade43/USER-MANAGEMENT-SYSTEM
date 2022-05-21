package in.nikhil.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.nikhil.Binding.RegForm;
import in.nikhil.Service.UserServiceInterface;

@RestController
public class UserRegController {
	
	@Autowired
	private UserServiceInterface service;
	
	@GetMapping("/email/{emailId}")
	public String emailCheck(@PathVariable String emailId) {
		return service.emailCheck(emailId);	
	}
	
	@GetMapping("/countries")
	public Map<Integer, String> getCountries(){
		return service.loadCountries();
	}
	
	@GetMapping("/states/{countryCode}")
	public Map<Integer, String> getStates(@PathVariable Integer countryCode){
		return service.loadStates(countryCode);	
	}
	
	@GetMapping("/cities/{stateCode}")
	public Map<Integer, String> getCities(@PathVariable Integer stateCode){
		return service.loadCities(stateCode);
	}
	
	@PostMapping("/register")
	public String userReg(@RequestBody RegForm regForm) {
		return service.registerUser(regForm);
	}
	
}
