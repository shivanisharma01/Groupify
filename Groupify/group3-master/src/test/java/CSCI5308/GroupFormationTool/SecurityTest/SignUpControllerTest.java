package CSCI5308.GroupFormationTool.SecurityTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import org.junit.jupiter.api.Assertions;

import CSCI5308.GroupFormationTool.Security.SignupController;

@SpringBootTest
@AutoConfigureMockMvc
public class SignUpControllerTest{
	@Autowired 
	private MockMvc mockMvc;
	 
	@Autowired SignupController signupController;
	
	@Test
	public void processSignUpTestUserExists() throws Exception {
		mockMvc.perform(get("/signup"))
		.andExpect(status().isOk())
		.andExpect(view().name("signup"));
	}
	
	@Test
    public void processSignUpTest() {
        ModelAndView mav = signupController.processSignup(
                "B-1234", "123456", "123456",
                "Shivani", "S", "Shivani@sharma.com"
        );
        Assertions.assertNotNull(mav);
    }
	
	@Test
    public void processSignUpTestPasswordInValid() {
        ModelAndView mav = signupController.processSignup(
                "B-1234", "1234", "1234",
                "Shivani", "S", "shivani@sharma.com"
        );
        Assertions.assertNotNull(mav);
    }
}
