import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.*;

public class Alarm implements Runnable {
	private ArrayList<Schedule> allSchedule; //사용자가 추가한 모든 일정 정보를 관리한다.
	private ArrayList<Integer> holidayDate; //공휴일의 날짜를 저장한다.
	private ArrayList<String> holidayText; //공휴일 문구를 저장한다.
	private int alarmState; //사용자가 설정한 알림 상태를 저장한다, 1이면 울리고, 0이면 울리지 않는것같음
	boolean isHoliday = false;

	//알림 OnOff 를 위한 쓰레드를 동작할 Int, KCH
	static int threadOnOff = 0;
	
	public Alarm(int state) {	//객체 생성시 state 설정
		this.alarmState = state;
	}

	public int getAlarmState() {
		return alarmState;
	}

	public void setAllSchedule(ArrayList<Schedule> schedule) {
		allSchedule = schedule;
	}

	//Seq(5) 알림설정 관련 변경 KCH
	public void changeAlarmState() {
		if(alarmState == 0) {
			alarmState = 1;
			startAlarmSystem();
		}else {
			alarmState = 0;
			stopAlarmSystem();
		}
	}
	
	//Runnable 인터페이스의 run()함수를 오버라이딩한 함수이다.
	//알림 기능을 스레드로 실행한다.
	//alarmTime 의 시간과 현재시간이 동일해질 때 alertActivityAlarm 함수로 알림을 발생시킨다.
	//공휴일의 날짜와 현재 날짜가 동일하면 오전 8시50분에 alertHolidayAlarm 함수로 알림을 발생시킨다.
	@Override
	public void run() {
		//알림설정을 위한 변수, 1이면 실행, 0이면 종료
		threadOnOff = 1;
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				while (threadOnOff == 1) {	//1일 때 무한루프
					//NowDate
					Date date = new Date(System.currentTimeMillis());
					//DateFormat
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.E요일", Locale.KOREAN);
					String dateString = simpleDateFormat.format(date);
					//Split String, Struct ex: [2021, 12, 02, 16, 00]
					String[] dateArr = dateString.split("[.]");
					//current Time, ex: 1600
					int currentTime = Integer.parseInt(dateArr[3] + dateArr[4]);
					//call API with Year	Call here Temporary!!!! CHECK FOR ALARM!!!!
					//current Day, ex: 20211201
					String nowDate = dateArr[0] + dateArr[1] + dateArr[2];
					//공휴일인지 판별하는
					//현재 날짜를 가져오고, 공휴일 비교하는 로직
					for (int j = 0; j < holidayDate.size(); j++) {	//Compare Holiday
						//공휴일일 때
						if (Objects.equals(holidayDate.get(j), Integer.valueOf(nowDate))) {
							isHoliday = true;
							if (currentTime == 850) {	//08:50 이면 Holiday Alarm
								alertHolidayAlarm(j);	//j for alert user what holiday is it
								break;
							}
							break;
						} else {
							isHoliday = false;
						}
					}
					//공휴일이 아니면
					if (!isHoliday) {
						for (Schedule schedule : allSchedule) {    //모든 일정 중에
							for (int k = 0; k < schedule.dayAndTime.size(); k++) {    //모든 일정이 포함하는 시간 중에
								if ((schedule.dayAndTime.get(k).startTime - 100) == currentTime    //현재시간 == 일정시간 - 1시간 &&
										&& schedule.dayAndTime.get(k).day.equals(dateArr[5])            //오늘 날짜 == 일정 날짜
								) {    //현재시각이 일정시간 - 100이면
									alertActivityAlarm(schedule.title, schedule.dayAndTime.get(k).startTime);    //알림발생
								}
								System.out.println("현재시각: " + nowDate + " " + currentTime + " " + dateArr[5] + " 일정 시간, 요일: "
										+ schedule.dayAndTime.get(k).startTime + " " + schedule.dayAndTime.get(k).day);
							}
						}
					}
					//1분 대기 - 분당 한번 알림을 주기 위해서
					try {
						Thread.sleep(60000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, 0, 1);
	}

	//사용자가 알림을 on 으로 설정했을 때 호출되는 함수이다. 알림 스레드를 실행한다.
	public void startAlarmSystem() {
		//NowDate
		Date date = new Date(System.currentTimeMillis());
		//DateFormat
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");//EX: 2021
		String dateString = simpleDateFormat.format(date);
		crawlingHolidayInf(dateString);	//공휴일 크롤링

		run();
	}

	//사용자가 알림을 off 로 설정했을 때 호출되는 함수이다. 알림 스레드를 종료한다.
	public void stopAlarmSystem() {
		//알림 설정 KCH
		threadOnOff = 0;
	}
	
	//사용자에게 일정명과 일정시간을 출력한 알림 파업을 띄운다.
	public void alertActivityAlarm(String scheduleName, int time) {
		//알림은 JOptionPane 이용하라고 하네 KCH
		JOptionPane.showMessageDialog(null, "일정 시간: " +time / 100+ "시"
				+ time % 100 + "분\n"
				+ "일정: " + scheduleName, "알림", JOptionPane.PLAIN_MESSAGE);
	}
	
	//사용자에게 공휴일 문구를 출력한 알림 팝업을 띄운다. 
	public void alertHolidayAlarm(int j) {
		JOptionPane.showMessageDialog(null, "오늘은 " + holidayText.get(j), "공휴일 알람", JOptionPane.PLAIN_MESSAGE);
	}
	
	//Use Holiday API to get holiday, input Year (ex: "2021")
	public void crawlingHolidayInf(String year) {
		//API URL
		String url = "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getHoliDeInfo?serviceKey=W8ehC9X8gh1OEHqMZh92XTL%2BOOkcr9Q3mErTlyrS%2Bh9qRF0CP9SGn6c4DdrJMaWPkKz5Ln1oT4RhyAT6A1EZcA%3D%3D&numOfRows=50&solYear="+year;
		try {
			Document document = DocumentBuilderFactory
					.newInstance()
					.newDocumentBuilder()
					.parse(url);
			document.getDocumentElement().normalize();
			NodeList nodeList = document.getElementsByTagName("item");
			holidayDate = new ArrayList<>();	//reset data
			holidayText = new ArrayList<>();	//reset data
			for (int i = 0; i<nodeList.getLength(); i++) {
				Node node = nodeList.item(i);

				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element)node;
					holidayText.add(getTagValue("dateName", element));	//insert data, format ex: "Christmas"
					holidayDate.add(Integer.valueOf(Objects.requireNonNull(getTagValue("locdate", element))));//insert arr data, format ex: 20211225
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Used to get Holiday in crawlingHolidayInf method
	private static String getTagValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = nodeList.item(0);
		if (node == null) {
			return null;
		}
		return node.getNodeValue();
	}
}