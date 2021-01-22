package CSCI5308.GroupFormationTool.CoursesTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class InstructorAdminControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void instructorAdminTest() throws Exception{
		String courseID="1";
		org.assertj.core.api.Assertions.assertThatThrownBy(() -> mvc.perform(get("/course/instructoradmin")
				.param("courseID", courseID))
				.andExpect(view().name("course/instructoradmin")));
	} 

}
