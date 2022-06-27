package erp.model.security;

public class TokenState {

	private String accessToken;
	private int expiresIn;
	 
	public TokenState() {
	
	}
	
	public TokenState(String accessToken, int expiresIn) {
		super();
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
 
}
