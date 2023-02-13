package userchat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import userchat.entity.User;
import userchat.respository.UserRespository;

@Service
public class UserService {
	
	@Autowired
	private UserRespository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public User createUser(User user) throws Exception {
		if(user.getPhoneNumber().length() != 10) {
			throw new Exception("phone number should be of 10 digit");
		}
		User userUpdated = new User();
		userUpdated.setName(user.getName());
		userUpdated.setPhoneNumber(user.getPhoneNumber());
		userUpdated.setPassword(passwordEncoder.encode(user.getPassword()));
		userUpdated.setPrivileges(user.getPrivileges());
		return userRepo.save(userUpdated);
	}

}
