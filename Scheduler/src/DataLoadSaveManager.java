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
	
	//紐⑤뱺 �씪�젙 �젙蹂대�� allSchedule蹂��닔�뿉 ���옣
	//�븷�뵆由ъ��씠�뀡�쓣 �떎�뻾�븷 �븣 �궗�슜�옄�쓽 �씪�젙 �젙蹂닿� ���옣�릺�뼱 �엳�뒗 Schedule.txt�뙆�씪�쓣 �뿰�떎.
	//txt�뙆�씪�뿉�뒗 �븳 �뻾留덈떎 �븯�굹�쓽 �씪�젙 �젙蹂닿� ���옣�릺�뼱�엳�떎.
	//媛곹뻾�쓽 �젙蹂대�� Schedule�뿉 ���옣�븳 �썑 allSchedule�뿉 異붽��븳�떎.
	//txt�뙆�씪�쓣 �떎 �씫�뼱�뱾�씠硫� �뙆�씪�쓣 �떕�뒗�떎.
	//紐⑤뱺 �씪�젙 �젙蹂닿� ���옣�맂 allSchedule�쓣 諛섑솚�븳�떎.
	//泥섏쓬 load�븷寃쎌슦 Schedule.txt媛� �뾾�쓣 �닔 �엳�떎. �씠�윺 寃쎌슦 �궡�슜�씠 �뾾�뒗 Schedule.txt瑜� �깮�꽦�븳�떎. 
	public ArrayList<Schedule> loadSchedule(){
		allSchedule = new ArrayList<Schedule>();
		File file = new File("Schedule.txt");
		
		//�꽕怨꾩뿉 �뾾�뒗 肄붾뱶 
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
	
	//�븷�뵆由ъ��씠�뀡�쓣 醫낅즺�븷�븣 �샇異쒕릺�뒗 �븿�닔�씠�떎.
	//紐⑤뱺 �씪�젙�씠 ���옣�릺�뼱 �엳�뒗 allSchedule�쓽 媛믪쓣 Schedule.txt�뙆�씪�뿉 ���옣�븳�떎.
	//�씠�븣 Schedule.txt�뙆�씪�쓽 �븳�뻾�뿉 �븯�굹�쓽 �씪�젙 �젙蹂대�� ���옣�븳�떎. 
	public void saveSchedule() {
		StringBuilder saveStr = new StringBuilder();
		Gson gson = new Gson();
		
		//�썝�옒�뒗 String json = gson.toJson(allSchedule); �씠�젃寃� �빐二쇰㈃�릺�뒗�뜲
		//�븳�씪�씤�뿉 臾댁“嫄� �븳媛�吏� �씪�젙�쓣 �꽔�쑝�씪怨� �빐�꽌 �씠�젃寃� �빀�땲�떎. 
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
		
		
		//(6)醫낅즺 SequenceDiagram -KCH
		//테스를 위해 주석 처리 하였습니다. 
		//int returnValue = alarm.getAlarmState();
	}
	
	//�븷�뵆由ъ��씠�뀡�쓣 醫낅즺�븷 �븣 �샇異쒕릺�뒗 �븿�닔�씠�떎.
	//�궗�슜�옄媛� �꽕�젙�븳 �븣由� �긽�깭(0�삉�뒗1)瑜� Alarm.txt�뙆�씪�뿉 ���옣�븳�떎.
	//�븣由� �긽�깭�뒗 Alarm媛앹껜�쓽 getAlarmState濡� �뼸�뼱�삩�떎.
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
	
	//�븷�뵆由ъ��씠�뀡�쓣 �떆�옉�븷 �븣 �샇異쒕릺�뒗 �븿�닔�씠�떎.
	//Alarm媛앹껜瑜� �깮�꽦�븳�떎.
	//Alarm.txt �뙆�씪�씠 議댁옱�븳�떎硫� Alarm.txt�뙆�씪�뿉�꽌 �븣由� �긽�깭(0�삉�뒗1)瑜� �씫�뼱�삩�떎.
	//Alarm 媛앹껜�쓽 setAlarmState�븿�닔瑜� �궗�슜�빐 �븣由� �긽�깭瑜� ���옣�븳�떎. 洹� �썑 Alarm媛앹껜瑜� 諛섑솚�븳�떎. 
	//泥섏쓬 load�븷寃쎌슦 Alarm.txt媛� �뾾�쓣 �닔 �엳�떎. �씠�윺 寃쎌슦 �궡�슜�씠 �뾾�뒗 Alarm.txt瑜� �깮�꽦�븳�떎. 
	public Alarm loadAlarmState() {
		Gson gson = new Gson();

		String filePath = "Alarm.txt";
		
		//�꽕怨꾩뿉 �뾾�뒗 肄붾뱶 
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
