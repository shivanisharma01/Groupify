package CSCI5308.GroupFormationTool.AccessControl;

import java.util.List;
import java.util.Random;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public interface IUserAbstractFactory {

	User returnUserInstance();
	User returnUserInstance(String bannerID, IUserPersistence userDB);
	User returnUserInstance(long userID, IUserPersistence userDB);
	List<User> returnUserListInstance();
	MailSSLSocketFactory returnMailSSLSocketFactoryInstance();
	MimeMessage returnMimeMessageInstance(Session sessionObj);
	InternetAddress returnInternetAddressInstance(String address);
	IUserPersistence returnUserDB();
	SimpleMailMessage returnSimpleMailMessageInstance();
	IUserNotifications returnUserNotification();
	Random returnRandomInstance();
	char[] returnCharacterInstance(int length);
	PasswordAuthentication returnPasswordAuthenticationInstance(String fromSender, String fromPassword);
}
