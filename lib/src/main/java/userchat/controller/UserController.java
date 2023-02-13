package userchat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import userchat.constants.CommonConstants;
import userchat.dto.ChatMessage;
import userchat.entity.ChatDetails;
import userchat.entity.User;
import userchat.respository.UserRespository;
import userchat.service.UserService;
import userchat.views.UserViews;

// TODO: Auto-generated Javadoc
/**
 * The Class UserController.
 */
@RestController
@Validated
@CrossOrigin

/**
 * Instantiates a new user controller.
 */
@RequiredArgsConstructor
@RequestMapping(value = CommonConstants.BASE_CONTEXT_PATH)
@ApiOperation(tags = "userChat", value = "User: manages users/agent data", notes = "User: manages users/agent data")
public class UserController {

	/** The user service. */
	@Autowired
	private UserService userService;

	/** The user repo. */
	@Autowired
	private UserRespository userRepo;

	/**
	 * Creates the user agent.
	 *
	 * @param user the user
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@Operation(summary = "create new user/agent")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "user/agent saved successfully"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Unexpected error has occurred") })
	@PostMapping(value = "/user", produces = "application/json")
	public ResponseEntity<User> createUserAgent(
			@RequestBody @JsonView(UserViews.CreateUserView.class) final User user) throws Exception {
		var result = userService.createUser(user);
		
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	/**
	 * Gets the user agent.
	 *
	 * @return the user agent
	 */
	@Operation(summary = "get all new user/agent")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "user/agent saved successfully"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Unexpected error has occurred") })
	@GetMapping(value = "/user", produces = "application/json")
	public ResponseEntity<List<User>> getUserAgent() {
		return new ResponseEntity<>(userRepo.findAll(), HttpStatus.CREATED);
	}

	/**
	 * Put chat session.
	 *
	 * @param chatDetails the chat details
	 * @return the response entity
	 */
	@ApiOperation(value = "update chat for the user")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "chat updated successfully"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Unexpected error has occurred") })
	@PutMapping(value = "/user/chatSession", produces = "application/json")
	public ResponseEntity<String> putChatSession(@RequestBody final ChatDetails chatDetails) {
		return new ResponseEntity<>("sccuess", HttpStatus.ACCEPTED);
	}

	/**
	 * Check payment.
	 *
	 * @param chatDetails the chat details
	 * @return the response entity
	 */
	@ApiOperation(value = "check payment of the user")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "payment checked successfully"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Unexpected error has occurred") })
	@PostMapping(value = "/user/checkPayment", produces = "application/json")
	public ResponseEntity<String> checkPayment(@RequestBody final ChatDetails chatDetails) {
		return new ResponseEntity<>("sccuess", HttpStatus.ACCEPTED);
	}
}
