package in.nikhil.Binding;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.Data;

@Data
public class RegForm {

	private String firstName;
	private String lastname;
	private String email;
	private Long phno;
	@DateTimeFormat(pattern = "YYYY-MM-dd")
	private LocalDate dob;
	private String gender;
	private Integer cityCode;
	private Integer StateCode;
	private Integer countryCode;
}
