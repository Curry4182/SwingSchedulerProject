import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Alarm implements Runnable {
	private ArrayList<Schedule> allSchedule; //����ڰ� �߰��� ��� ���� ������ �����Ѵ�.
	private ArrayList<Integer> alarmTime; //��� ������ ������ ���� 1�ð� ���� �ð��� �����Ѵ�.
	private ArrayList<Integer> holidayDate; //�������� ��¥�� �����Ѵ�.
	private ArrayList<String> holidayText; //������ ������ �����Ѵ�.
	private int alarmState; //����ڰ� ������ �˸� ���¸� �����Ѵ�, 1�̸� �︮��, 0�̸� �︮�� �ʴ°Ͱ���
	private Thread th; //�˸��߻��ð��� �˸� �˾��� �߻���Ű�� �������̴�.


	//�˸� OnOff �� ���� �����带 ������ Int, KCH
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

	//Seq(5) �˸����� ���� ���� KCH
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
	
	//Runnable �������̽��� run()�Լ��� �������̵��� �Լ��̴�.
	//�˸� ����� ������� �����Ѵ�.
	//alarmTime�� �ð��� ����ð��� �������� �� alertActivityAlarm�Լ��� �˸��� �߻���Ų��.
	//�������� ��¥�� ���� ��¥�� �����ϸ� ���� 8��50�п� alertHolidayAlarm�Լ��� �˸��� �߻���Ų��.
	@Override
	public void run() {
		//�˸����� KCH
		threadOnOff = 1;
		while (threadOnOff == 1) {
			//NowDate
			Date date = new Date(System.currentTimeMillis());
			//DateFormat
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm");
			String dateString = simpleDateFormat.format(date);
			//Split String, Struct ex: [2021, 12, 02, 16, 00]
			String[] dateArr = dateString.split("[.]");
			//current Time, ex: 1600
			int currentTime = Integer.parseInt(dateArr[3] + dateArr[4]);
			//call API with Year	Call here Temporary!!!! CHECK FOR ALARM!!!!
			crawlingHolidayInf(dateArr[0]);
			//current Day, ex: 20211201
			String nowDate = dateArr[0] + dateArr[1] + dateArr[2];
			for (int j: holidayDate) {	//Compare Holiday
				if (Objects.equals(holidayDate.get(j), Integer.valueOf(nowDate))) {
					if (currentTime == 850) {	//08:50 Holiday Alarm
						alertHolidayAlarm(j);	//j for alert user what holiday is it
					}
				} else {
					//compare time, and alert
					for (int i : alarmTime){
						if (alarmTime.get(i) == currentTime) {
							alertActivityAlarm();
						}
					}
				}
			}
		}
	}

	//Seq(5) �˸��߻� ���� KCH ------------------------------------------------------
	//����ڰ� �˸��� on ���� �������� �� ȣ��Ǵ� �Լ��̴�. �˸� �����带 �����Ѵ�.
	public void startAlarmSystem() {
		ArrayList <Integer> startTimeList =	getAllStartTime();
		run();
	}

	public ArrayList<Integer> getAllStartTime() {
		//������ ����Ʈ�ȿ� ����ִ� ������
		for (int i = 0; i < allSchedule.size(); i++) {
			//���� �ȿ� ����ִ� �ð���
			ArrayList<DayAndTime> arr = allSchedule.get(i).dayAndTime;
			for (int j = 0; j < arr.size(); j ++) {
				//�ð��� �߿� ���۽ð� -1 �� �ð�
				//���� �ð� ���� ����ؼ� - �����ؾ���!!!!!!!!!!!!!!!!!!!!!!!!!!!
				//������ 11:00�� 1100�̶� ������ ���̶� �����ؼ� - 100�� ����
				alarmTime.add(arr.get(j).startTime -100);
			}
		}
		return alarmTime;
	}
	//----------------------------------------------------------------------------
	//����ڰ� �˸��� off�� �������� �� ȣ��Ǵ� �Լ��̴�. �˸� �����带 �����Ѵ�. 
	public void stopAlarmSystem() {
		//�˸� ���� KCH
		threadOnOff = 0;
	}
	
	//����ڿ��� ������� �����ð��� ����� �˸� �ľ��� ����.
	public void alertActivityAlarm() {
		//�˸��� JOptionPane �̿��϶�� �ϳ� KCH
		JOptionPane.showMessageDialog(null, "���� �ð�, ���� �̸�", "Ȱ�� �˸�", JOptionPane.PLAIN_MESSAGE);
	}
	
	//����ڿ��� ������ ������ ����� �˸� �˾��� ����. 
	public void alertHolidayAlarm(int j) {
		JOptionPane.showMessageDialog(null, "Today is " + holidayText.get(j), "Holiday Alarm", JOptionPane.PLAIN_MESSAGE);
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
			holidayDate = null;	//reset data
			holidayText = null;	//reset data
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
		Node node = (Node)nodeList.item(0);
		if (node == null) {
			return null;
		}
		return node.getNodeValue();
	}
	
	
	//allSchedule�� getAllStartTime�Լ����� ��ȯ���� ���� alarmTime�� �����Ѵ�.  
	public void updateAlarm() {
	}
}
