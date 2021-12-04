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
	
	public String getTitle() {
		return title;
	}
	
	public ArrayList<DayAndTime> getDayAndTime(){
		return dayAndTime;
	}
	
	public ArrayList<Integer> getAllStartTime(){
		ArrayList<Integer> startTimes = new ArrayList<Integer>();
		
		for(int i=0; i<dayAndTime.size(); i++) {
			startTimes.set(i, dayAndTime.get(i).startTime + 1);
		}
		
		return startTimes;
	}
	
	//type을 반환한다. 0은 강의 일정을 , 1은 기타일정을 의미한다
	public int getType() {
		return type;
	}
}