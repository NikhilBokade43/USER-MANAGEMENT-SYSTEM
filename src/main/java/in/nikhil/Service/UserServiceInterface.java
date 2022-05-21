package in.nikhil.Service;

import java.util.Map;

import in.nikhil.Binding.LoginForm;
import in.nikhil.Binding.RegForm;
import in.nikhil.Binding.UnlockForm;

public interface UserServiceInterface {
	//returns success or failure
	public String userLogin(LoginForm form);
	public String registerUser(RegForm regForm);
	public String forgotPassword(String emailId);
	public String unlockAccount(UnlockForm unlockform);
	public String emailCheck(String emailId);
	public Map<Integer, String> loadCountries();
	public Map<Integer, String> loadStates(Integer countryCode);
	public Map<Integer,String> loadCities(Integer stateCode);
}
