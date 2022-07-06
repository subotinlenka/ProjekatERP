package erp.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import erp.dto.UserCreateUpdateDto;
import erp.dto.UserDto;
import erp.model.security.AuthenticationRequest;
import erp.model.security.TokenState;
import erp.service.AuthenticationService;
import erp.service.implementation.UserServiceImplementation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@Api(tags = { "Authentication endpoints"})
@RequestMapping("/auth")
public class AuthenticationController {

		private final AuthenticationService authenticationService;
	    private final UserServiceImplementation userService;
	    
		private static final String SUCCESS = "Success!";

	    public AuthenticationController(AuthenticationService authenticationService,UserServiceImplementation userService) {
	        this.authenticationService = authenticationService;
	        this.userService = userService;
	    }

	    @PostMapping("/logIn")
	    @ApiOperation(value = "User logging in.", response = UserDto.class, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<TokenState> createAuthenticationToken(@RequestBody AuthenticationRequest request) {
	        return ResponseEntity.ok(authenticationService.createAuthenticationToken(request));
	    }

	    @PostMapping("/signUp")
	    @ApiOperation(value = "New User signing up.", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> createCustomer(@Valid @RequestBody UserCreateUpdateDto userCreateDto) {
	        userService.insertUser(userCreateDto, "Customer");
	        return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED);
	    }

	    @Secured("Admin")
	    @PostMapping("/admin")
	    @ApiOperation(value = "New Admin signing up.", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> createAdmin(@Valid @RequestBody UserCreateUpdateDto userCreateDto) {
	        userService.insertUser(userCreateDto, "Admin");
	        return new ResponseEntity<>(SUCCESS, HttpStatus.CREATED);

	    }
}
