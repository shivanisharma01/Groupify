package CSCI5308.GroupFormationTool.AccessControl;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.mail.javamail.JavaMailSender;
import com.sun.mail.util.MailSSLSocketFactory;

public class UserNotification implements IUserNotifications {

	JavaMailSender mailsender;
	String senderMail = "pvhome303@gmail.com";
	String senderPassword = "vptnebrafmgerlyt";
	Logger logger = LoggerFactory.getLogger(UserNotification.class);

	@Override
	public void sendUserLoginCredentials(User user, String rawPassword) {
		String subject = "Group 3 Tool - Account Credentials ";
		String body = "Hello " + user.getFirstName() + "," + "\n" + "You are enrolled by your Instructor/TA"
				+ "\nYour account has been created with below credentials: " + "\n" + "\nUsername: "
				+ user.getBannerID() + "\nPassword: " + rawPassword + "\n\n" + "\n\nRegards,\nGroup3 ASDC";
		mail(senderMail, senderPassword, user.getEmail(), subject, body);

	}

	private void mail(String fromSender, String fromPassword, String toReciever, String sub, String message) {

		try {
			IUserAbstractFactory userAbstractFactory = UserConfiguration.instance().getUserAbstractFactory();
			MailSSLSocketFactory mailSslObj;
			Properties propertiesObj = System.getProperties();
			propertiesObj.put("mail.smtp.starttls.enable", "true");
			propertiesObj.setProperty("mail.smtp.host", "smtp.gmail.com");
			propertiesObj.setProperty("mail.smtp.port", "587");
			propertiesObj.setProperty("mail.smtp.auth", "true");
			mailSslObj = userAbstractFactory.returnMailSSLSocketFactoryInstance();
			mailSslObj.setTrustAllHosts(true);
			propertiesObj.put("mail.smtp.ssl.socketFactory", mailSslObj);
			Session sessionObj = Session.getInstance(propertiesObj, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return userAbstractFactory.returnPasswordAuthenticationInstance(fromSender, fromPassword);
				}
			});
			MimeMessage messageObj = userAbstractFactory.returnMimeMessageInstance(sessionObj);
			messageObj.setFrom(userAbstractFactory.returnInternetAddressInstance(fromSender));
			messageObj.addRecipient(Message.RecipientType.TO, userAbstractFactory.returnInternetAddressInstance(toReciever));
			messageObj.setSubject(sub);
			messageObj.setText(message);
			Transport.send(messageObj);
			logger.info("Mail is sent to user "+toReciever);
		} catch (Exception e) {
			logger.error("Issue while sending mail ", e);
		}

	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}
