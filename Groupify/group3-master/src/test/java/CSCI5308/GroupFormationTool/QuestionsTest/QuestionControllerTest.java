package CSCI5308.GroupFormationTool.QuestionsTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.web.util.NestedServletException;

import CSCI5308.GroupFormationTool.Questions.Question;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void questionCreateTest() throws Exception{
		String instructorID="1";
		try {
			 mockMvc.perform(get("/questionCreate/{instructorID}", "1")
					 .param("instructorID", instructorID))
			 		 .andExpect(status().is2xxSuccessful());
		}catch(NestedServletException ne) {
			System.out.println("Exception" +ne);
		}
	}
		 
	@Test
	public void questionManagerTest() throws Exception{
		String instructorID="1";
		String sortFlag="1";
		try {
			mockMvc.perform(get("/question/{instructorID}/{sortFlag}", "1", "1")
					.param("instructorID", instructorID)
					.param("sortFlag", sortFlag))
					.andExpect(status().is2xxSuccessful());
		}catch(NestedServletException ne) {
			System.out.println("Exception" +ne);
		}
	}
	
	@Test
	public void sortQuestionTest() throws Exception {
		String instructorID="1";
		String sortFlag="1";
		org.assertj.core.api.Assertions.assertThatThrownBy(() -> mockMvc.perform(get("/question/sortQuestion/{instructorID}/{sortFlag}", "1", "1")
				.param("instructorID", instructorID)
				.param("sortFlag", sortFlag))
				.andExpect(status().is3xxRedirection()));
	}
	
	@Test
	public void loadQuestionsTest() throws Exception{
		String questionID="1";
		org.assertj.core.api.Assertions.assertThatThrownBy(() -> mockMvc.perform(get("/question/loadQuestion/{questionID}", "1")
				.param("questionID", questionID))
				.andExpect(status().is3xxRedirection()));
	}
	
	@Test
	public void questionPostCreateTest() throws Exception{
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		String instructorID="1";
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		org.assertj.core.api.Assertions.assertThatThrownBy(() -> mockMvc.perform(post("/questionCreate/{instructorID}", "1")
				.param("instructorID", instructorID)
				.flashAttr("question", question))
				.andExpect(status().is3xxRedirection()));
	}
	

	@Test
	public void answerManagerTest() throws Exception {
		IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
		Question question = questionAbstractFactoryTest.returnQuestionInstance();
		String quesTitle = "New-Course";
		String quesType = "mcq";
		String instructorID="1";
		String optList = "1";
		Model model=null;
		org.assertj.core.api.Assertions.assertThatThrownBy(() -> mockMvc.perform(post("/question/answerCreate")
				.param("quesTitle", quesTitle)
				.param("quesType", quesType)
				.param("instructorID", instructorID)
				.param("optList", optList) 
				.flashAttr("model", model)
				.flashAttr("question", question))
				.andExpect(status().is3xxRedirection()));
	}
				
}	

