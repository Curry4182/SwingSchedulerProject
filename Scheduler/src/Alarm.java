import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

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
			//����ð� �޾Ƽ� ó���ϴ� ����
			Date date = new Date(System.currentTimeMillis());
			String dateString = date.toString();
			String[] dateArr = dateString.split("[: ]");
			int currentTime = Integer.parseInt(dateArr[3] + dateArr[4]);
			//dateArr �� ������ ���� ������ ���� : Wed Dec 01 16 09 17 KST 2021
			if (true) {	//������ API ���ܿͼ� ���ؾ���!! KCH
				alertHolidayAlarm();
			} else {
				//����ð��� ����� �ð��� ���ؼ� ��ġ�ϸ� �˸��߻�
				for (int i : alarmTime){
					if (alarmTime.get(i) == currentTime) {
						alertActivityAlarm();
					}
				}
			}
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
		//�˸��� JOptionPane �̿��϶�� �ϳ� KCH
		JOptionPane.showMessageDialog(null, "���� �ð�, ���� �̸�", "Ȱ�� �˸�", JOptionPane.PLAIN_MESSAGE);
	}
	
	//����ڿ��� ������ ������ ����� �˸� �˾��� ����. 
	public void alertHolidayAlarm() {
		//�˸��� JOptionPane �̿��϶�� �ϳ� KCH
		JOptionPane.showMessageDialog(null, "���� �ð�, ���� �̸�", "Ȱ�� �˸�", JOptionPane.PLAIN_MESSAGE);
	}
	
	//������ ������ ũ�Ѹ��Ͽ� ������ ������ holidayText�� �����Ѵ�. 
	public void crawlingHolidayInf() {
	}
	
	//allSchedule�� getAllStartTime�Լ����� ��ȯ���� ���� alarmTime�� �����Ѵ�. 
	public void updateAlarm() {
	}
}
