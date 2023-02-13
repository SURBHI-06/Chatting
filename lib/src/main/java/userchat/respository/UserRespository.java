package userchat.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import userchat.entity.User;


@Repository
public interface UserRespository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

	Optional<User> findByPhoneNumber(String phoneNumber);

}
