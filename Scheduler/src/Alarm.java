import java.util.ArrayList;

public class Alarm implements Runnable {
	private ArrayList<Schedule> allSchedule; //사용자가 추가한 모든 일정 정보를 관리한다.
	private ArrayList<Integer> alarmTime; //모든 일정의 ‘일정 시작 1시간 전’ 시간을 저장한다.
	private ArrayList<Integer> holidayDate; //공휴일의 날짜를 저장한다.
	private ArrayList<String> holidayText; //공휴일 문구를 저장한다.
	private int alarmState; //사용자가 설정한 알림 상태를 저장한다, 1이면 울리고, 0이면 울리지 않는것같음
	private Thread th; //알림발생시간에 알림 팝업을 발생시키는 스레드이다.

	//알림 OnOff 를 위한 쓰레드를 동작할 Int, KCH
	static int threadOnOff = 0;
	
	public Alarm() {
		this.alarmState = 0;
	}
	
	public void setAlarmState(int alarmState) {
		this.alarmState = alarmState;
	}
	
	public int getAlarmState() {
		return alarmState;
	}

	//Seq(5) 알림설정 관련 변경 KCH
	public int changeAlarmState() {
		if(alarmState == 0) {
			alarmState = 1;
			startAlarmSystem();
			return 0;
		}else {
			alarmState = 0;
			stopAlarmSystem();
			return 1;
		}
	}
	
	//Runnable 인터페이스의 run()함수를 오버라이딩한 함수이다.
	//알림 기능을 스레드로 실행한다.
	//alarmTime의 시간과 현재시간이 동일해질 때 alertActivityAlarm함수로 알림을 발생시킨다.
	//공휴일의 날짜와 현재 날짜가 동일하면 오전 8시50분에 alertHolidayAlarm함수로 알림을 발생시킨다.
	@Override
	public void run() {
		//알림설정 KCH
		threadOnOff = 1;
		while (threadOnOff == 1) {	//일단 시퀀스대로 그림인데, 공휴일이랑 현재시간 따는거 구현해볼게
//			if (공휴일) {
//				alertHolidayAlarm();
//			} else {
//				if (alarmTime = currenttime) {
//					alertActivityAlarm();
//				}
//			}
		}
	}

	//Seq(5) 알림발생 관련 KCH ------------------------------------------------------
	//사용자가 알림을 on으로 설정했을 때 호출되는 함수이다. 알림 스레드를 실행한다.
	public void startAlarmSystem() {
		ArrayList <Integer> startTimeList =	getAllStartTime();
		run();
	}

	public ArrayList<Integer> getAllStartTime() {
		//스케줄 리스트안에 들어있는 일정들
		for (int i = 0; i < allSchedule.size(); i++) {
			//일정 안에 들어있는 시간들
			ArrayList<DayAndTime> arr = allSchedule.get(i).dayAndTime;
			for (int j = 0; j < arr.size(); j ++) {
				//시간들 중에 시작시간 -1 한 시간
				//여기 시간 단위 계산해서 - 진행해야함!!!!!!!!!!!!!!!!!!!!!!!!!!!
				//지금은 11:00을 1100이라 저장할 것이라 예측해서 - 100만 했음
				alarmTime.add(arr.get(j).startTime -100);
			}
		}
		return alarmTime;
	}
	//----------------------------------------------------------------------------
	//사용자가 알림을 off로 설정했을 때 호출되는 함수이다. 알림 스레드를 종료한다. 
	public void stopAlarmSystem() {
		//알림 설정 KCH
		threadOnOff = 0;
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
