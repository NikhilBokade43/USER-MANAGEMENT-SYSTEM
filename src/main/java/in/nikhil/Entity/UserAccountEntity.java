package in.nikhil.Entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "USER_ACCOUNTS")
public class UserAccountEntity {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastname;
	
	@Column(name = "EMAIL", unique = true)
	private String email;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "PHNO")
	private Long phno;
	
	@Column(name = "DOB")
	@DateTimeFormat(pattern = "YYYY-MM-dd")
	private LocalDate dob;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "CITY_CODE")
	private Integer cityCode;
	
	@Column(name = "STATE_CODE")
	private Integer StateCode;
	
	@Column(name = "COUNTRY_CODE")
	private Integer countryCode;
	
	@Column(name = "ACC_STATUS")
	private String accStatus;
	
	@Column(name = "CREATED_DATE" , updatable = false)
	@CreationTimestamp
	private LocalDate createdDate;
	
	
	@Column(name = "UPDATED_DATE", insertable = false)
	@UpdateTimestamp
	private LocalDate updatedDate;

}
