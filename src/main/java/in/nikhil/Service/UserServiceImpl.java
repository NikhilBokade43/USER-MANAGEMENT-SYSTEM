package in.nikhil.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;



import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nikhil.Binding.LoginForm;
import in.nikhil.Binding.RegForm;
import in.nikhil.Binding.UnlockForm;
import in.nikhil.Entity.CityMasterEntity;
import in.nikhil.Entity.CountryMasterEntity;
import in.nikhil.Entity.StateMasterEntity;
import in.nikhil.Entity.UserAccountEntity;
import in.nikhil.Repository.CityRepository;
import in.nikhil.Repository.CountryRepository;
import in.nikhil.Repository.StateRepository;
import in.nikhil.Repository.UserAccountRepository;
import in.nikhil.Utils.EmailUtils;

@Service
public class UserServiceImpl implements UserServiceInterface {
	
	@Autowired
	private UserAccountRepository repo;
	@Autowired
	private StateRepository stateRepo;
	@Autowired
	private CountryRepository countryRepo;
	@Autowired
	private CityRepository cityRepo;
	@Autowired
	private EmailUtils emailUtils;
	
	@Override
	public String userLogin(LoginForm form) {
		UserAccountEntity entity = repo.findByEmailAndPassword(form.getEmail() , form.getPassword());
		if (entity == null) {
			return "Invalid Credentials";
		}
		if (entity !=null && entity.getAccStatus().equals("LOCKED") ) {
			return "ACCOUNT IS IN LOCKED STATE";
		}
		return "LOGIN SUCCESSFUL";
	}

	@Override
	public String registerUser(RegForm regForm) {
		
		UserAccountEntity entity = new UserAccountEntity();
		BeanUtils.copyProperties(regForm, entity);
		entity.setAccStatus("LOCKED");
		entity.setPassword(generateRandomPassword());
		repo.save(entity);
		
		String email = regForm.getEmail();
		String subject = "User Registration - Nikhil";
		String body = readMailBodyContent("UnlockUserAccountRegistration.txt", entity);
		
		boolean isSent = emailUtils.sendEmail(email, body, subject);
		if(isSent) {
			return "Registration Successfull !!";
		}
		
		return "Registration Failed !!";
	}

	@Override
	public String forgotPassword(String emailId) {
		UserAccountEntity entity = repo.findByEmail(emailId);
		if(entity == null) {
			return "Invalid Email";
		}
		
		//ToDO if record available send password to email 
		String fileName = "UnlockUserAccountRegistration.txt";
		String Subject = "Recover Password";
		String body = readMailBodyContent(fileName, entity);
		
		boolean isSent = emailUtils.sendEmail(emailId, body, Subject);
		if(isSent) {
			return "password sent to registered email";
		}
		
		return null;
	}

	@Override
	public String unlockAccount(UnlockForm unlockform) {
		if (!unlockform.getNewPass().equals(unlockform.getConfirmPass())){
			return "confirm password and new password must be same";
		}
		
		UserAccountEntity entity = repo.findByEmailAndPassword(unlockform.getEmailId(), unlockform.getTempPass());
		
		if(entity == null) {
			return "Incorrect temporary password";
		}
		
		entity.setPassword(unlockform.getConfirmPass());
		entity.setAccStatus("UNLOCKED");
		
		repo.save(entity);
		return "Account Unlocked";
	}

	@Override
	public String emailCheck(String emailId) {
		UserAccountEntity entity = repo.findByEmail(emailId);
		if(entity != null) {
			return "Duplicate";
		}
		
		return "Unique";
	}

	@Override
	public Map<Integer, String> loadCountries() {
		Map<Integer, String> countryMap = new HashMap<>();
		List<CountryMasterEntity> countriesList = countryRepo.findAll();
		for(CountryMasterEntity eachEntity : countriesList) {
			countryMap.put(eachEntity.getCountryCode(), eachEntity.getCountryName());	
		}
		return countryMap;
	}

	@Override
	public Map<Integer, String> loadStates(Integer countryCode) {
		Map<Integer, String> statesMap = new HashMap<Integer, String>();
 		List<StateMasterEntity> statesMasterList = stateRepo.findByCountryCode(countryCode);
		
		for(StateMasterEntity eachEntity : statesMasterList) {
			statesMap.put(eachEntity.getStateCode(), eachEntity.getStateName());
		}
		
		return statesMap;
	}

	@Override
	public Map<Integer, String> loadCities(Integer stateCode) {
		Map<Integer, String> cityMap = new HashMap<Integer, String>();
		
		List<CityMasterEntity> citiesMasterList = cityRepo.findByStateCode(stateCode);
		
		for(CityMasterEntity eachEntity : citiesMasterList) {
			cityMap.put(eachEntity.getCityCode(), eachEntity.getCityName());
		}
		return cityMap;
	}
	
	private String generateRandomPassword() {
		int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	    System.out.println(generatedString);
		return generatedString;
	}
	
	private String readMailBodyContent(String fileName, UserAccountEntity userEntity ) {
		
		String mailbody= null;
		
		try {
			StringBuffer sb = new StringBuffer();
			
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while(line != null) {
				sb.append(line);
				line = br.readLine();
			}
			mailbody  = sb.toString();
			mailbody = mailbody.replace( "FIRSTNAME", userEntity.getFirstName());
			mailbody = mailbody.replace( "LASTNAME", userEntity.getLastname());
			mailbody = mailbody.replace( "EMAIL", userEntity.getEmail());
			mailbody = mailbody.replace( "TEMP-PWD", userEntity.getPassword());
			
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mailbody;
		
	}
	

}
