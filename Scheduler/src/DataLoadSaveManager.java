import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;

public class DataLoadSaveManager {
	private ArrayList<Schedule> allSchedule;

	public ArrayList<Schedule> loadSchedule(){
		allSchedule = new ArrayList<>();
		File file = new File("Schedule.txt");

		if(!file.exists()) {
			allSchedule = new ArrayList<>();
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

	public void saveSchedule() {
		StringBuilder saveStr = new StringBuilder();
		Gson gson = new Gson();

		for (Schedule schedule : allSchedule) {
			String line = gson.toJson(schedule);
			saveStr.append(line).append("\n");
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
	
	//알림 상태 저장, 0 혹은 1로 저장
	public void saveAlarmState(int status) {
		String saveStr;
		saveStr = ""+status;

		File file = new File("Alarm.txt");
		try {
		    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		    writer.write(saveStr);
		    writer.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public int loadAlarmState() {
		String filePath = "Alarm.txt";

		File file = new File(filePath);
		int alarmState;
		if(!file.exists()) {	//파일이 존재하지 않으면, 0 상태로 파일 생성, 저장
			alarmState = 0;
			saveAlarmState(0);
			return alarmState;
		}
		 
        String content = null;
        try {
            content = Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
		assert content != null;
		//alarm.txt 가 이미 존재하고, ""일 때
		if (content.equals("")) {
			alarmState = 0;
			saveAlarmState(0);	//0으로 초기 세팅
			return alarmState;
		}
        alarmState = Integer.parseInt(content);
		return alarmState;
	}
}
