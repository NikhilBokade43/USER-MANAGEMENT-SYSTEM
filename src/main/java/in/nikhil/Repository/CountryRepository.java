package in.nikhil.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nikhil.Entity.CountryMasterEntity;

public interface CountryRepository extends JpaRepository<CountryMasterEntity, Integer> {

}
