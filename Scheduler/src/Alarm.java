import java.util.ArrayList;

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
		while (threadOnOff == 1) {	//�ϴ� ��������� �׸��ε�, �������̶� ����ð� ���°� �����غ���
//			if (������) {
//				alertHolidayAlarm();
//			} else {
//				if (alarmTime = currenttime) {
//					alertActivityAlarm();
//				}
//			}
		}
	}

	//Seq(5) �˸��߻� ���� KCH ------------------------------------------------------
	//����ڰ� �˸��� on���� �������� �� ȣ��Ǵ� �Լ��̴�. �˸� �����带 �����Ѵ�.
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
	}
	
	//����ڿ��� ������ ������ ����� �˸� �˾��� ����. 
	public void alertHolidayAlarm() {
	}
	
	//������ ������ ũ�Ѹ��Ͽ� ������ ������ holidayText�� �����Ѵ�. 
	public void crawlingHolidayInf() {
	}
	
	//allSchedule�� getAllStartTime�Լ����� ��ȯ���� ���� alarmTime�� �����Ѵ�. 
	public void updateAlarm() {
	}
}
