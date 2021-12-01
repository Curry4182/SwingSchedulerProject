import java.util.ArrayList;

public class CommonSchedule {
	protected int type; //������ ������ �����Ѵ�.(��������(0), ��Ÿ����(1))
	protected String title; //�������� �����Ѵ�.
	protected ArrayList<DayAndTime> dayAndTime; //������ ���۽ð�, ����ð�, ������ ��� �����Ѵ�.
	
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
	
	//type�� ��ȯ�Ѵ�. 0�� ���� ������ , 1�� ��Ÿ������ �ǹ��Ѵ�
	public int getType() {
		return type;
	}
}
