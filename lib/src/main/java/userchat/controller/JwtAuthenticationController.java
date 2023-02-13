package userchat.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import userchat.constants.CommonConstants;
import userchat.dto.JwtResponse;
import userchat.entity.Privilege;
import userchat.entity.User;
import userchat.respository.UserRespository;
import userchat.service.JwtUserDetailsService;
import userchat.utils.JwtTokenUtil;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = CommonConstants.BASE_CONTEXT_PATH)
@ApiOperation(tags = "userChat", value = "User: token creation", notes = "User: token creation")
public class JwtAuthenticationController {

	/** The authentication manager. */
	@Autowired
	private AuthenticationManager authenticationManager;

	/** The jwt user details service. */
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	/** The jwt token util. */
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private UserRespository userRepo;

	/**
	 * Creates the authentication token.
	 *
	 * @param user the authentication request
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@PostMapping(value = "/user/token")
	public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody User user) throws Exception {

		authenticate(user.getPhoneNumber(), user.getPassword());

		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(user.getPhoneNumber());

		final String token = jwtTokenUtil.generateToken(userDetails);
		
		var updatedUser = userRepo.findByPhoneNumber(user.getPhoneNumber());
		ArrayList<String> roles = new ArrayList<>();
		for (Privilege role : updatedUser.get().getPrivileges()) {
			roles.add(role.getName());
		}

		return ResponseEntity.ok(new JwtResponse(token, roles.toString()));
	}

	/**
	 * Authenticate.
	 *
	 * @param username the username
	 * @param password the password
	 * @throws Exception the exception
	 */
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}