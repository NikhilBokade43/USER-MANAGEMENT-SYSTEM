package in.nikhil.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nikhil.Entity.UserAccountEntity;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Integer> {
	
	public UserAccountEntity findByEmailAndPassword(String email, String password);
	
	public UserAccountEntity findByEmail(String email);
}
