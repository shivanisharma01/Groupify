package CSCI5308.GroupFormationTool.AccessControlTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.AccessControl.User;

@SpringBootTest
public class UserNotificationTest  {

    @Test
    public void sendUserLoginCredentials() {
    	IUserAbstractFactoryTest userAbstractFactoryTest = UserConfigurationTest.instance().getUserAbstractFactory();
        UserDBMock userDB = userAbstractFactoryTest.returnUserDBMockInstance();
        User user = userAbstractFactoryTest.returnUserInstance();
        userDB.loadUserByID(1, user);
        assertEquals(user.getBannerID(), "B00000000", "Test failed at user id");
        assertEquals(user.getPassword(), "Pass@123", "Test failed at user password");
    }   
}