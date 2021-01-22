package CSCI5308.GroupFormationTool.AccessControl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUser {

	private static CurrentUser uniqueInstance = null;
	Logger logger = LoggerFactory.getLogger(CurrentUser.class);
	
	private CurrentUser() {}

	public static CurrentUser instance() {
		if (null == uniqueInstance) {
			uniqueInstance = new CurrentUser();
		}
		return uniqueInstance;
	}

	public User getCurrentAuthenticatedUser() {
		IUserAbstractFactory userAbstractFactory = UserConfiguration.instance().getUserAbstractFactory();
		IUserPersistence userDB = UserConfiguration.instance().getUserDB();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication.isAuthenticated()) {
			String bannerID = authentication.getPrincipal().toString();
			User user = userAbstractFactory.returnUserInstance();
			userDB.loadUserByBannerID(bannerID, user);
			if (user.isValidUser()) {
				logger.info("Fetching Current User Status");
				return user;
			}
		}
		return null;
	}
}
