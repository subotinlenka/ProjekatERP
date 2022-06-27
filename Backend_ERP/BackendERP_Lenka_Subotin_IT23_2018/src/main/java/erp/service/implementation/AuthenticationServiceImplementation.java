package erp.service.implementation;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import erp.model.User;
import erp.model.security.AuthenticationRequest;
import erp.model.security.TokenState;
import erp.security.TokenUtils;
import erp.service.AuthenticationService;

@Service
public class AuthenticationServiceImplementation implements AuthenticationService {

	private final TokenUtils tokenUtils;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImplementation(TokenUtils tokenUtils, AuthenticationManager authenticationManager) {
        this.tokenUtils = tokenUtils;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public TokenState createAuthenticationToken(AuthenticationRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();

        return new TokenState(tokenUtils.generateToken(user.getUsername()), tokenUtils.getExpiredIn());
    }
}
