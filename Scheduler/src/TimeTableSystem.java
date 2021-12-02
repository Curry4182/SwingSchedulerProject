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

	//�궗�슜�옄媛� 異붽��븳 紐⑤뱺 �씪�젙 �젙蹂대�� 愿�由ы븳�떎. 
	private ArrayList<Schedule> allSchedule;

	//紐⑤뱺 �씪�젙 �젙蹂대�� �뀓�뒪�듃�뙆�씪�뿉 ���옣�븯怨�, �뀓�뒪�듃 �뙆�씪�뿉�꽌 遺덈윭�삩�떎.
	//Alarm.txt, Schedule.txt
	private DataLoadSaveManager dataMG;

	//[洹몃┝ 2]�젣�븞�븯�뒗 �냼�봽�듃�썾�뼱�쓽 援ъ긽�룄�뿉�꽌 �쇊履쎌뿉 �엳�뒗 �떆媛꾪몴
	private LeftTableUI leftUI;

	//[洹몃┝ 2]�젣�븞�븯�뒗 �냼�봽�듃�썾�뼱�쓽 援ъ긽�룄�뿉�꽌 �삤瑜몄そ�뿉 �엳�뒗 �꽕�젙�솕硫�
	private RightSettingUI rightUI;
	
	//TimeTableSystem 媛앹껜瑜� �깮�꽦�븯�뿬 �샇異쒗븳�떎. 
	public static void main(String[] args) {
		FlatDarkLaf.setup();
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
		Alarm alarm = dataMG.loadAlarmState();
		//alarm.crawlingHolidayInf();
		
		drawTimeTableSystem();
	}
	
	//leftTableUI�뿉 �떆媛꾪몴 寃⑹옄瑜� �깮�꽦�븯怨� �떆媛꾪몴�뿉 �씪�젙 �젙蹂대�� 異쒕젰�븯怨� �떆媛꾪몴 寃⑹옄移몄뿉 �깋�쓣 �몴�떆�븳�떎.
	//rightsettingUI�뿉 �엯�젰 UI 瑜� �깮�꽦�븯怨� �븣由� �뿬遺��뿉 �뵲�씪 �븣由쇱뒪�쐞移섎�� �꽕�젙�븳�떎. 
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
        leftUI = new LeftTableUI(allSchedule, null);
	    frame.add(leftUI,c);

	    c.gridx = 1;
	    c.gridy = 0;
	    c.weightx = 1;
        c.weighty = 1;
        rightUI = new RightSettingUI(allSchedule, leftUI, null);
	    frame.add(rightUI, c);
	    
	    frame.setVisible(true);
	}
	
	//dataMG�쓽 saveSchedule�븿�닔瑜� �샇異쒗븯�뿬 txt�뙆�씪�뿉 ���옣�븳�떎. 
	//�떆�뒪�뀥�쓣 醫낅즺�븳�떎.
	public void closeSystem() {
	//(6)醫낅즺 SequenceDiagram -KCH
		dataMG.saveSchedule();
		dataMG.saveAlarmState();
		//terminate System
	}
}
