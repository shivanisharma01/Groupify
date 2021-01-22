package CSCI5308.GroupFormationTool.Survey;

import java.util.List;
import java.util.Map;

public interface ISurveyResponsePersistence {

	public Map<Long, Map<Integer, List<String>>> loadSurveyResponse(long courseId);
}
