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

	//����ڰ� �߰��� ��� ���� ������ �����Ѵ�. 
	private ArrayList<Schedule> allSchedule;

	//��� ���� ������ �ؽ�Ʈ���Ͽ� �����ϰ�, �ؽ�Ʈ ���Ͽ��� �ҷ��´�.
	//Alarm.txt, Schedule.txt
	private DataLoadSaveManager dataMG;

	//[�׸� 2]�����ϴ� ����Ʈ������ ���󵵿��� ���ʿ� �ִ� �ð�ǥ
	private LeftTableUI leftUI;

	//[�׸� 2]�����ϴ� ����Ʈ������ ���󵵿��� �����ʿ� �ִ� ����ȭ��
	private RightSettingUI rightUI;
	
	//TimeTableSystem ��ü�� �����Ͽ� ȣ���Ѵ�. 
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
	
	//leftTableUI�� �ð�ǥ ���ڸ� �����ϰ� �ð�ǥ�� ���� ������ ����ϰ� �ð�ǥ ����ĭ�� ���� ǥ���Ѵ�.
	//rightsettingUI�� �Է� UI �� �����ϰ� �˸� ���ο� ���� �˸�����ġ�� �����Ѵ�. 
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
	
	//dataMG�� saveSchedule�Լ��� ȣ���Ͽ� txt���Ͽ� �����Ѵ�. 
	//�ý����� �����Ѵ�.
	public void closeSystem() {
	
	}
}
