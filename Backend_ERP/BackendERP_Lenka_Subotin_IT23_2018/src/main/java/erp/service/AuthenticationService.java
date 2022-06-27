package erp.service;

import erp.model.security.AuthenticationRequest;
import erp.model.security.TokenState;

public interface AuthenticationService {

	TokenState createAuthenticationToken(AuthenticationRequest request);
}
