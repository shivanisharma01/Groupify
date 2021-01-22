package CSCI5308.GroupFormationTool.GroupFormation;

public interface IGroupFormationPolicy {
	
	public int getGroupSize();
	public void setGroupSize(int groupSize);
	public int getQuestionID();
	public void setQuestionID(int questionID);
	public int getQuestionType();
	public void setQuestionType(int questionType);
	public long getCourseID();
	public void setCourseID(long courseID);
	public String getGroupBy();
	public void setGroupBy(String groupBy);
	public int getCompareValue();
	public void setCompareValue(int compareValue);

}
