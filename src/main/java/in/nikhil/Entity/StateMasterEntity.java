package in.nikhil.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "STATE_MASTER")
public class StateMasterEntity {
	
	@Id
	@Column(name = "STATE_CODE")
	private Integer stateCode;
	@Column(name = "STATE_NAME")
	private String stateName;
	@Column(name = "COUNTRY_CODE")
	private Integer countryCode;
}
