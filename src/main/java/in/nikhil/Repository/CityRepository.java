package in.nikhil.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nikhil.Entity.CityMasterEntity;

public interface CityRepository extends JpaRepository<CityMasterEntity, Integer> {
	
	public List<CityMasterEntity> findByStateCode(Integer stateCode);
}
