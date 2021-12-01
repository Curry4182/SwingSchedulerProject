import java.util.ArrayList;

public class Alarm implements Runnable {
	private ArrayList<Schedule> allSchedule; //����ڰ� �߰��� ��� ���� ������ �����Ѵ�.
	private ArrayList<Integer> alarmTime; //��� ������ ������ ���� 1�ð� ���� �ð��� �����Ѵ�.
	private ArrayList<Integer> holidayDate; //�������� ��¥�� �����Ѵ�.
	private ArrayList<String> holidayText; //������ ������ �����Ѵ�.
	private int alarmState; //����ڰ� ������ �˸� ���¸� �����Ѵ�, 1�̸� �︮��, 0�̸� �︮�� �ʴ°Ͱ���
	private Thread th; //�˸��߻��ð��� �˸� �˾��� �߻���Ű�� �������̴�.
	
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
	
	//Runnable �������̽��� run()�Լ��� �������̵��� �Լ��̴�.
	//�˸� ����� ������� �����Ѵ�.
	//alarmTime�� �ð��� ����ð��� �������� �� alertActivityAlarm�Լ��� �˸��� �߻���Ų��.
	//�������� ��¥�� ���� ��¥�� �����ϸ� ���� 8��50�п� alertHolidayAlarm�Լ��� �˸��� �߻���Ų��.
	@Override
	public void run() {
	}
	
	//����ڰ� �˸��� on���� �������� �� ȣ��Ǵ� �Լ��̴�. �˸� �����带 �����Ѵ�. 
	public void startAlarmSystem() {
	}
	
	//����ڰ� �˸��� off�� �������� �� ȣ��Ǵ� �Լ��̴�. �˸� �����带 �����Ѵ�. 
	public void stopAlarmSystem() {
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
