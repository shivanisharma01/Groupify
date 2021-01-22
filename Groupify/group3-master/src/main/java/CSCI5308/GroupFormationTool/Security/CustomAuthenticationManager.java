package CSCI5308.GroupFormationTool.Security;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import CSCI5308.GroupFormationTool.AccessControl.*;

public class CustomAuthenticationManager implements AuthenticationManager {
	private static final String ADMIN_BANNER_ID = "B-000000";

	private Authentication checkAdmin(String password, User u, Authentication authentication)
			throws AuthenticationException {
		ISecurityAbstractFactory securityAbstractFactory = SecurityConfig.instance().getSecurityAbstractFactory();
		// The admin password is not encrypted because it is hardcoded in the DB.
		if (password.equals(u.getPassword())) {
			// Grant ADMIN rights system-wide, this is used to protect controller mappings.
			List<GrantedAuthority> rights = securityAbstractFactory.returnGrantedAuthorityListInstance();
			rights.add(securityAbstractFactory.returnSimpleGrantedAuthorityInstance("ADMIN"));
			// Return valid authentication token.
			UsernamePasswordAuthenticationToken token;
			token = securityAbstractFactory.
					returnUsernamePasswordAuthenticationTokenInstance(authentication.getPrincipal(), 
							authentication.getCredentials(), rights);
			return token;
		} else {
			throw securityAbstractFactory.returnBadCredentialsExceptionInstance("1000");
		}
	}

	private Authentication checkNormal(String password, User u, Authentication authentication)
			throws AuthenticationException {
		ISecurityAbstractFactory securityAbstractFactory = SecurityConfig.instance().getSecurityAbstractFactory();
		IPasswordEncryption passwordEncryption = SecurityConfig.instance().getPasswordEncryption();
		if (passwordEncryption.matches(password, u.getPassword())) {
			// Grant USER rights system-wide, this is used to protect controller mappings.
			List<GrantedAuthority> rights = securityAbstractFactory.returnGrantedAuthorityListInstance();
			rights.add(securityAbstractFactory.returnSimpleGrantedAuthorityInstance("USER"));
			// Return valid authentication token.
			UsernamePasswordAuthenticationToken token;
			token = securityAbstractFactory.
					returnUsernamePasswordAuthenticationTokenInstance(authentication.getPrincipal(), 
							authentication.getCredentials(), rights);
			return token;
		} else {
			throw securityAbstractFactory.returnBadCredentialsExceptionInstance("1000");
		}
	}

	// Authenticate against our database using the input banner ID and password.
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		IUserAbstractFactory userAbstractFactory = UserConfiguration.instance().getUserAbstractFactory();
		ISecurityAbstractFactory securityAbstractFactory = SecurityConfig.instance().getSecurityAbstractFactory();
		String bannerID = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();
		IUserPersistence userDB = UserConfiguration.instance().getUserDB();
		User u;
		try {
			u = userAbstractFactory.returnUserInstance(bannerID, userDB);
		} catch (Exception e) {
			throw securityAbstractFactory.createAuthenticationServiceExceptionInstance("1000");
		}
		if (u.isValidUser()) {
			if (bannerID.toUpperCase().equals(ADMIN_BANNER_ID)) {
				return checkAdmin(password, u, authentication);
			} else {
				return checkNormal(password, u, authentication);
			}
		} else {
			// No user with this banner id found.
			throw securityAbstractFactory.returnBadCredentialsExceptionInstance("1001");
		}
	}
}
