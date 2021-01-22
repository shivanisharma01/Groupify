package CSCI5308.GroupFormationTool.QuestionsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import CSCI5308.GroupFormationTool.Questions.Options;

@SpringBootTest
public class OptionsTest {
    
    @Test
    public void getOptionID() {
    	IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
        Options option = questionAbstractFactoryTest.returnOptionInstance();
        option.setOptionID(1);
        assertEquals(1, option.getOptionID(), "At get option ID test");
	}

    @Test
	public void getOptionText() {
    	IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
        Options option = questionAbstractFactoryTest.returnOptionInstance();
        option.setOptionText("Test");
        assertEquals("Test", option.getOptionText(), "At get option Text test");
	}

    @Test
	public void setOptionText() {
    	IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
        Options option = questionAbstractFactoryTest.returnOptionInstance();
        option.setOptionText("Test");
        assertEquals("Test", option.getOptionText(), "At set option Text test");
	}

    @Test
	public void setOptionID() {
    	IQuestionAbstractFactoryTest questionAbstractFactoryTest = QuestionsConfigurationTest.instance().getQuestionAbstractFactory();
        Options option = questionAbstractFactoryTest.returnOptionInstance();
        option.setOptionID(1);
        assertEquals(1, option.getOptionID(), "At set option ID test");
	}
}