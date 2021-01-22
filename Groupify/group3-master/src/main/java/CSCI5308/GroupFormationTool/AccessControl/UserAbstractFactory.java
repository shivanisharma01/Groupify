package CSCI5308.GroupFormationTool.AccessControl;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import com.sun.mail.util.MailSSLSocketFactory;

public class UserAbstractFactory implements IUserAbstractFactory {

	@Override
	public User returnUserInstance() {
		return new User();
	}

	@Override
	public List<User> returnUserListInstance() {
		return new ArrayList<User>(); 
	}

	@Override
	public MailSSLSocketFactory returnMailSSLSocketFactoryInstance() {
		MailSSLSocketFactory mailSslObj = null;
		try {
			mailSslObj = new MailSSLSocketFactory();
		} catch (GeneralSecurityException e) {
			//Logging Required
			e.printStackTrace();
		}
		return mailSslObj;
	}

	@Override
	public MimeMessage returnMimeMessageInstance(Session sessionObj) {
		return new MimeMessage(sessionObj);
	}

	@Override
	public InternetAddress returnInternetAddressInstance(String address) {
		InternetAddress internetAddress = null;
		try {
			internetAddress =  new InternetAddress(address);
		} catch (AddressException e) {
			//Logging Required
			e.printStackTrace();
		}
		return internetAddress;
	}

	@Override
	public IUserPersistence returnUserDB() {
		return new UserDB();
	}

	@Override
	public SimpleMailMessage returnSimpleMailMessageInstance() {
		return new SimpleMailMessage();
	}

	@Override
	public IUserNotifications returnUserNotification() {
		return new UserNotification();
	}

	@Override
	public Random returnRandomInstance() {
		return new Random();
	}

	@Override
	public char[] returnCharacterInstance(int length) {
		return new char[length];
	}

	@Override
	public User returnUserInstance(String bannerID, IUserPersistence userDB) {
		return new User(bannerID, userDB);
	}

	@Override
	public PasswordAuthentication returnPasswordAuthenticationInstance(String fromSender, String fromPassword) {
		return new PasswordAuthentication(fromSender, fromPassword);
	}

	@Override
	public User returnUserInstance(long userID, IUserPersistence userDB) {
		return new User(userID, userDB);
	}

}
