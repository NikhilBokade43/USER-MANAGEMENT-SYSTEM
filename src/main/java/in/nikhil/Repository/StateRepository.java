package in.nikhil.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nikhil.Entity.StateMasterEntity;

public interface StateRepository extends JpaRepository<StateMasterEntity, Integer> {
	
	public List<StateMasterEntity> findByCountryCode(Integer countryCode);

}
