import java.util.ArrayList;

public class CommonSchedule {
	protected int type; //일정의 종류를 저장한다.(강의일정(0), 기타일정(1))
	protected String title; //일정명을 저장한다.
	protected ArrayList<DayAndTime> dayAndTime; //일정의 시작시간, 종료시간, 요일을 모두 저장한다.
	
	public void setInfo(int type, String title, ArrayList<DayAndTime> dayAndTime) {
		this.type = type;
		this.title = title;
		this.dayAndTime = dayAndTime;
	}

	public ArrayList<DayAndTime> getDayAndTime(){
		return dayAndTime;
	}
}