package CSCI5308.GroupFormationTool.Security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ModelAndView;

public class SecurityAbstractFactory implements ISecurityAbstractFactory {

	@Override
	public IPasswordPolicyPersistence returnPasswordPolicyDB() {
		return new PasswordPolicyDB();
	}

	@Override
	public PasswordPolicy returnPasswordPolicyInstance(IPasswordPolicyPersistence passwordPersistenceObj) {
		return new PasswordPolicy(passwordPersistenceObj);
	}

	@Override
	public IPasswordEncryption returnBCryptPasswordEncryptionInstance() {
		return new BCryptPasswordEncryption();
	}

	@Override
	public CustomAuthenticationManager returnCustomAuthenticationManagerInstance() {
		return new CustomAuthenticationManager();
	}

	@Override
	public SimpleGrantedAuthority returnSimpleGrantedAuthorityInstance(String role) {
		return new SimpleGrantedAuthority(role);
	}

	@Override
	public List<GrantedAuthority> returnGrantedAuthorityListInstance() {
		return new ArrayList<GrantedAuthority>();
	}

	@Override
	public UsernamePasswordAuthenticationToken returnUsernamePasswordAuthenticationTokenInstance(Object principal,
			Object credentials, List<GrantedAuthority> grantedAuthorities) {
		return new UsernamePasswordAuthenticationToken(principal, credentials, grantedAuthorities);
	}

	@Override
	public BadCredentialsException returnBadCredentialsExceptionInstance(String erroCode) {
		 return new BadCredentialsException(erroCode);
	}

	@Override
	public AuthenticationServiceException createAuthenticationServiceExceptionInstance(String erroCode) {
		return new AuthenticationServiceException(erroCode);
	}

	@Override
	public IUserPasswordRelationship returnUserPasswordRelationship() {
		return new UserPasswordRelationship();
	}

	@Override
	public IUserPasswordRelationshipPersistence returnUserPasswordRelationshipDB() {
		return new UserPasswordRelationshipDB();
	}

	@Override
	public BCryptPasswordEncoder createBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public ModelAndView returnModelAndViewInstance(String mapping) {
		return new ModelAndView(mapping);
	}

	@Override
	public List<String> returnStringListInstance() {
		return new ArrayList<String>();
	}

}
