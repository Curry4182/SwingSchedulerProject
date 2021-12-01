import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

public class DataLoadSaveManager {
	private ArrayList<Schedule> allSchedule;
	private Alarm alarm;
	
	//��� ���� ������ allSchedule������ ����
	//���ø����̼��� ������ �� ������� ���� ������ ����Ǿ� �ִ� Schedule.txt������ ����.
	//txt���Ͽ��� �� �ึ�� �ϳ��� ���� ������ ����Ǿ��ִ�.
	//������ ������ Schedule�� ������ �� allSchedule�� �߰��Ѵ�.
	//txt������ �� �о���̸� ������ �ݴ´�.
	//��� ���� ������ ����� allSchedule�� ��ȯ�Ѵ�.
	//ó�� load�Ұ�� Schedule.txt�� ���� �� �ִ�. �̷� ��� ������ ���� Schedule.txt�� �����Ѵ�. 
	public ArrayList<Schedule> loadSchedule(){
		allSchedule = new ArrayList<Schedule>();
		File file = new File("Schedule.txt");
		
		//���迡 ���� �ڵ� 
		if(!file.exists()) {
			allSchedule = new ArrayList<Schedule>();
			saveSchedule();
			return allSchedule;
		}
		
		Gson gson = new Gson();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	Schedule schedule = gson.fromJson(line, Schedule.class);
		    	allSchedule.add(schedule);
		    }
		}catch(IOException e){
			 e.printStackTrace();
		}
		
		return allSchedule;
	}
	
	//���ø����̼��� �����Ҷ� ȣ��Ǵ� �Լ��̴�.
	//��� ������ ����Ǿ� �ִ� allSchedule�� ���� Schedule.txt���Ͽ� �����Ѵ�.
	//�̶� Schedule.txt������ ���࿡ �ϳ��� ���� ������ �����Ѵ�. 
	public void saveSchedule() {
		StringBuilder saveStr = new StringBuilder();
		Gson gson = new Gson();
		
		//������ String json = gson.toJson(allSchedule); �̷��� ���ָ�Ǵµ�
		//�Ѷ��ο� ������ �Ѱ��� ������ ������� �ؼ� �̷��� �մϴ�. 
		for(int i=0; i<allSchedule.size(); i++) {
			String line = gson.toJson(allSchedule.get(i));
			saveStr.append(line+"\n");
		}
		
		File file = new File("Schedule.txt");
		try {
		    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		    writer.write(saveStr.toString());
		    writer.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	//���ø����̼��� ������ �� ȣ��Ǵ� �Լ��̴�.
	//����ڰ� ������ �˸� ����(0�Ǵ�1)�� Alarm.txt���Ͽ� �����Ѵ�.
	//�˸� ���´� Alarm��ü�� getAlarmState�� ���´�.
	public void saveAlarmState() {
		Gson gson = new Gson();
		String saveStr="";
		if(alarm != null) {
			saveStr = gson.toJson(alarm);
		}
		
		File file = new File("Alarm.txt");
		try {
		    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		    writer.write(saveStr);
		    writer.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	//���ø����̼��� ������ �� ȣ��Ǵ� �Լ��̴�.
	//Alarm��ü�� �����Ѵ�.
	//Alarm.txt ������ �����Ѵٸ� Alarm.txt���Ͽ��� �˸� ����(0�Ǵ�1)�� �о�´�.
	//Alarm ��ü�� setAlarmState�Լ��� ����� �˸� ���¸� �����Ѵ�. �� �� Alarm��ü�� ��ȯ�Ѵ�. 
	//ó�� load�Ұ�� Alarm.txt�� ���� �� �ִ�. �̷� ��� ������ ���� Alarm.txt�� �����Ѵ�. 
	public Alarm loadAlarmState() {
		Gson gson = new Gson();

		String filePath = "Alarm.txt";
		
		//���迡 ���� �ڵ� 
		File file = new File(filePath);
		if(!file.exists()) {
			alarm = null;
			saveAlarmState();
			return alarm;
		}
		 
        String content = null;
        try {
            content = Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        alarm = gson.fromJson(content, Alarm.class);
		return alarm;
	}
}
