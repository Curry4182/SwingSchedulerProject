import java.util.ArrayList;

public class Schedule extends CommonSchedule{
	private String location; //���� ��Ҹ� �����Ѵ�.
	private String professor; //���Ǹ� ����ϴ� �������� �����Ѵ�.

	public Schedule(int type, String title, String professor, String location, ArrayList<DayAndTime> dayAndTime) {
		super.setInfo(type, title, dayAndTime);
		this.location = location;
		this.professor = professor;
	}

	public Schedule(int type, String title, ArrayList<DayAndTime> dayAndTime) {
		super.setInfo(type, title, dayAndTime);
	}

	public String getLocation() {
		return location;
	}

	public String getProfessor() {
		return professor;
	}

}
