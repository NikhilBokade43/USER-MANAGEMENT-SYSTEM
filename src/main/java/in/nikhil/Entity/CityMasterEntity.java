package in.nikhil.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "CITIES_MASTER")
public class CityMasterEntity {
	
	@Id
	@Column(name = "CITY_CODE")
	private Integer cityCode;
	@Column(name = "CITY_NAME")
	private String cityName;
	@Column(name = "STATE_CODE")
	private Integer stateCode;
}
