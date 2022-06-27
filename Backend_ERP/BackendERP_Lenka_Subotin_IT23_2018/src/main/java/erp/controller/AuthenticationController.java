package erp.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

@RestController
@Api(value = "Authentication endpoints.")
@RequestMapping("/auth")
public class AuthenticationController {

		private final AuthenticationService authenticationService;
	    private final UserServiceImplementation userService;

	    public AuthenticationController(AuthenticationService authenticationService,UserServiceImplementation userService) {
	        this.authenticationService = authenticationService;
	        this.userService = userService;
	    }

	    @PostMapping("/logIn")
	    @ApiOperation(value = "User logging in.", response = UserDto.class
	    )
	    public ResponseEntity<TokenState> createAuthenticationToken(@RequestBody AuthenticationRequest request) {
	        return ResponseEntity.ok(authenticationService.createAuthenticationToken(request));
	    }


	    @PostMapping("/signIn")
	    @ApiOperation(value = "New User signing up.")
	    public ResponseEntity<UserDto> createCustomer(@Valid @RequestBody UserCreateUpdateDto userCreateDto) {
	        userService.insertUser(userCreateDto, "Customer");
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }

	    @PostMapping("/admin")
	    @PreAuthorize("hasAuthority('Admin')")
	    @ApiOperation(value = "New Admin signing up.")
	    public ResponseEntity<UserCreateUpdateDto> createAdmin(@Valid @RequestBody UserCreateUpdateDto userCreateDto) {
	        userService.insertUser(userCreateDto, "Admin");
	        return new ResponseEntity<>(HttpStatus.CREATED);

	    }
}
