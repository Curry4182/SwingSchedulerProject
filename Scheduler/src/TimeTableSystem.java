import java.util.ArrayList;
import java.awt.GridLayout;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.demo.*;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCarbonIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;

import java.awt.*;

public class TimeTableSystem {

	//사용자가 추가한 모든 일정 정보를 관리한다. 
	private ArrayList<Schedule> allSchedule;

	//모든 일정 정보를 텍스트파일에 저장하고, 텍스트 파일에서 불러온다.
	//Alarm.txt, Schedule.txt
	private DataLoadSaveManager dataMG;

	//[그림 2]제안하는 소프트웨어의 구상도에서 왼쪽에 있는 시간표
	private LeftTableUI leftUI;

	//[그림 2]제안하는 소프트웨어의 구상도에서 오른쪽에 있는 설정화면
	private RightSettingUI rightUI;
	
	//TimeTableSystem 객체를 생성하여 호출한다. 
	public static void main(String[] args) {
		//FlatDarkLaf.setup();
		//FlatDarculaLaf.setup();
		//FlatCarbonIJTheme.setup();
		//FlatArcDarkOrangeIJTheme.setup();
		//FlatLightLaf.setup();	
		FlatCyanLightIJTheme.setup();

		TimeTableSystem system = new TimeTableSystem();
	}
	
	public TimeTableSystem() {
		dataMG = new DataLoadSaveManager();	
		allSchedule = dataMG.loadSchedule();
		Alarm alarm = dataMG.loadAlarmState();
		alarm.crawlingHolidayInf();
		
		drawTimeTableSystem();
	}
	
	//leftTableUI에 시간표 격자를 생성하고 시간표에 일정 정보를 출력하고 시간표 격자칸에 색을 표시한다.
	//rightsettingUI에 입력 UI 를 생성하고 알림 여부에 따라 알림스위치를 설정한다. 
	public void drawTimeTableSystem() {
		JFrame frame = new JFrame("I love Swing ");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(1200, 530);
	    frame.setLayout(new GridBagLayout());
	    frame.setLocationRelativeTo(null);

	    GridBagConstraints c = new GridBagConstraints();
	    c.fill = GridBagConstraints.BOTH;

	    c.gridx = 0;
	    c.gridy = 0;
	    c.weightx = 8;
        c.weighty = 1;
	    frame.add(new LeftTableUI(null, null),c);
	    //frame.add(new TestUI(),c);
	    c.gridx = 1;
	    c.gridy = 0;
	    c.weightx = 1;
        c.weighty = 1;
	    frame.add(new RightSettingUI(null, null, null), c);
	    
	    frame.setVisible(true);
	}
	
	//dataMG의 saveSchedule함수를 호출하여 txt파일에 저장한다. 
	//시스템을 종료한다.
	public void closeSystem() {
	
	}
}
