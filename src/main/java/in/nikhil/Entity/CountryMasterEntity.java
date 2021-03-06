package in.nikhil.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "COUNTRY_MASTER")
public class CountryMasterEntity {
	
	@Id
	@Column(name = "COUNTRY_CODE")
	private Integer countryCode;
	
	@Column(name = "COUNTRY_NAME")
	private String countryName;

}
