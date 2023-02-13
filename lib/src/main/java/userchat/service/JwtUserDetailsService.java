package userchat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import userchat.dto.CustomUser;
import userchat.respository.UserRespository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRespository userRepo;

	@Override
	public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
		var user = userRepo.findByPhoneNumber(phoneNumber);
		if (user.isPresent()) {
			//return new User("javainuse", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
			//		new ArrayList<>());
			return new CustomUser(user.get());
		} else {
			throw new UsernameNotFoundException("User not found with phoneNumber: " + phoneNumber);
		}
	}
}
