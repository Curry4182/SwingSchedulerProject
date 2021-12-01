import java.util.ArrayList;

public class Alarm implements Runnable {
	private ArrayList<Schedule> allSchedule; //사용자가 추가한 모든 일정 정보를 관리한다.
	private ArrayList<Integer> alarmTime; //모든 일정의 ‘일정 시작 1시간 전’ 시간을 저장한다.
	private ArrayList<Integer> holidayDate; //공휴일의 날짜를 저장한다.
	private ArrayList<String> holidayText; //공휴일 문구를 저장한다.
	private int alarmState; //사용자가 설정한 알림 상태를 저장한다, 1이면 울리고, 0이면 울리지 않는것같음
	private Thread th; //알림발생시간에 알림 팝업을 발생시키는 스레드이다.
	
	public Alarm() {
		this.alarmState = 0;
	}
	
	public void setAlarmState(int alarmState) {
		this.alarmState = alarmState;
	}
	
	public int getAlarmState() {
		return alarmState;
	}
	
	public int changeAlarmState() {
		if(alarmState == 0) {
			alarmState = 1;
			return 0;
		}else {
			alarmState = 0;
			return 1;
		}
	}
	
	//Runnable 인터페이스의 run()함수를 오버라이딩한 함수이다.
	//알림 기능을 스레드로 실행한다.
	//alarmTime의 시간과 현재시간이 동일해질 때 alertActivityAlarm함수로 알림을 발생시킨다.
	//공휴일의 날짜와 현재 날짜가 동일하면 오전 8시50분에 alertHolidayAlarm함수로 알림을 발생시킨다.
	@Override
	public void run() {
	}
	
	//사용자가 알림을 on으로 설정했을 때 호출되는 함수이다. 알림 스레드를 실행한다. 
	public void startAlarmSystem() {
	}
	
	//사용자가 알림을 off로 설정했을 때 호출되는 함수이다. 알림 스레드를 종료한다. 
	public void stopAlarmSystem() {
	}
	
	//사용자에게 일정명과 일정시간을 출력한 알림 파업을 띄운다.
	public void alertActivityAlarm() {
	}
	
	//사용자에게 공휴일 문구를 출력한 알림 팝업을 띄운다. 
	public void alertHolidayAlarm() {
	}
	
	//공휴일 정보를 크롤링하여 공휴일 문구를 holidayText에 저장한다. 
	public void crawlingHolidayInf() {
	}
	
	//allSchedule의 getAllStartTime함수에서 반환받은 값을 alarmTime에 저장한다. 
	public void updateAlarm() {
	}
}
