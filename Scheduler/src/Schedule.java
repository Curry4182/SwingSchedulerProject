import java.util.ArrayList;

public class Schedule extends CommonSchedule{
	private final String location; //강의 장소를 저장한다.
	private final String professor; //강의를 담당하는 교수명을 저장한다.

	public Schedule(int type, String title, String professor, String location, ArrayList<DayAndTime> dayAndTime) {
		super.setInfo(type, title, dayAndTime);
		this.location = location;
		this.professor = professor;
	}

	public String getLocation() {
		return location;
	}

	public String getProfessor() {
		return professor;
	}

}