import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;

import java.awt.*;

public class TimeTableSystem {

	private final ArrayList<Schedule> allSchedule;

	//Alarm.txt, Schedule.txt
	private final DataLoadSaveManager dataMG;

	private final Alarm alarm;

	public static void main(String[] args) {
		//FlatDarkLaf.setup();
		//FlatDarculaLaf.setup();
		//FlatCarbonIJTheme.setup();
		//FlatArcDarkOrangeIJTheme.setup();
		//FlatLightLaf.setup();
		//FlatCyanLightIJTheme.setup();

		TimeTableSystem system = new TimeTableSystem();
	}
	
	public TimeTableSystem() {
		dataMG = new DataLoadSaveManager();	
		allSchedule = dataMG.loadSchedule();
		//알림 상태 가져오기 - int 0 or 1 로 처리
		int state = dataMG.loadAlarmState();
		//System.out.println(state);	//state 잘 가져오는지 체크
		alarm = new Alarm(state);
		alarm.setAllSchedule(allSchedule);	//알림에 일정 넣기
		//alarm.crawlingHolidayInf("2021");
		
		drawTimeTableSystem();
	}

	public void drawTimeTableSystem() {
		JFrame frame = new JFrame("시간표 기반 일정 관리 어플리케이션");
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
		LeftTableUI leftUI = new LeftTableUI(allSchedule);
        leftUI.setVisible(true);
	    frame.add(leftUI,c);
	    frame.setVisible(true);


	    c.gridx = 1;
	    c.gridy = 0;
	    c.weightx = 1;
        c.weighty = 1;
		RightSettingUI rightUI = new RightSettingUI(allSchedule, leftUI, alarm);
	    frame.add(rightUI, c);
	    frame.setVisible(true);
	    
	    frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
            	closeSystem();
                e.getWindow().dispose();
            }
        });
	}

	public void closeSystem() {
		dataMG.saveSchedule();
		//알림 상태 저장
		dataMG.saveAlarmState(alarm.getAlarmState());
		//terminate System
	}
}