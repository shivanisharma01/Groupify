package CSCI5308.GroupFormationTool.Security;

import java.util.List;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.ModelAndView;

public interface ISecurityAbstractFactory {

	IPasswordPolicyPersistence returnPasswordPolicyDB();
	PasswordPolicy returnPasswordPolicyInstance(IPasswordPolicyPersistence passwordPersistenceObj);
	IPasswordEncryption returnBCryptPasswordEncryptionInstance();
	CustomAuthenticationManager returnCustomAuthenticationManagerInstance();
	SimpleGrantedAuthority returnSimpleGrantedAuthorityInstance(String role);
	List<GrantedAuthority> returnGrantedAuthorityListInstance();
	UsernamePasswordAuthenticationToken returnUsernamePasswordAuthenticationTokenInstance(
            Object principal, Object credentials, List<GrantedAuthority> grantedAuthorities);
	BadCredentialsException returnBadCredentialsExceptionInstance(String erroCode);
	AuthenticationServiceException createAuthenticationServiceExceptionInstance(String erroCode);
	IUserPasswordRelationship returnUserPasswordRelationship();
	IUserPasswordRelationshipPersistence returnUserPasswordRelationshipDB();
	BCryptPasswordEncoder createBCryptPasswordEncoder();
	ModelAndView returnModelAndViewInstance(String mapping);
	List<String> returnStringListInstance();
}
