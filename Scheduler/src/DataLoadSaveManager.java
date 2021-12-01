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
	
	//모든 일정 정보를 allSchedule변수에 저장
	//애플리케이션을 실행할 때 사용자의 일정 정보가 저장되어 있는 Schedule.txt파일을 연다.
	//txt파일에는 한 행마다 하나의 일정 정보가 저장되어있다.
	//각행의 정보를 Schedule에 저장한 후 allSchedule에 추가한다.
	//txt파일을 다 읽어들이면 파일을 닫는다.
	//모든 일정 정보가 저장된 allSchedule을 반환한다.
	//처음 load할경우 Schedule.txt가 없을 수 있다. 이럴 경우 내용이 없는 Schedule.txt를 생성한다. 
	public ArrayList<Schedule> loadSchedule(){
		allSchedule = new ArrayList<Schedule>();
		File file = new File("Schedule.txt");
		
		//설계에 없는 코드 
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
	
	//애플리케이션을 종료할때 호출되는 함수이다.
	//모든 일정이 저장되어 있는 allSchedule의 값을 Schedule.txt파일에 저장한다.
	//이때 Schedule.txt파일의 한행에 하나의 일정 정보를 저장한다. 
	public void saveSchedule() {
		StringBuilder saveStr = new StringBuilder();
		Gson gson = new Gson();
		
		//원래는 String json = gson.toJson(allSchedule); 이렇게 해주면되는데
		//한라인에 무조건 한가지 일정을 넣으라고 해서 이렇게 합니다. 
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
	
	//애플리케이션을 종료할 때 호출되는 함수이다.
	//사용자가 설정한 알림 상태(0또는1)를 Alarm.txt파일에 저장한다.
	//알림 상태는 Alarm객체의 getAlarmState로 얻어온다.
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
	
	//애플리케이션을 시작할 때 호출되는 함수이다.
	//Alarm객체를 생성한다.
	//Alarm.txt 파일이 존재한다면 Alarm.txt파일에서 알림 상태(0또는1)를 읽어온다.
	//Alarm 객체의 setAlarmState함수를 사용해 알림 상태를 저장한다. 그 후 Alarm객체를 반환한다. 
	//처음 load할경우 Alarm.txt가 없을 수 있다. 이럴 경우 내용이 없는 Alarm.txt를 생성한다. 
	public Alarm loadAlarmState() {
		Gson gson = new Gson();

		String filePath = "Alarm.txt";
		
		//설계에 없는 코드 
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
