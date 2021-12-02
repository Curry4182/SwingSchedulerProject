import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Font;

public class DayTimeUI extends JPanel{
	private JComboBox day;
	private JComboBox startTime;
	private JComboBox endTime;
	
	
	//요일, 시작 시간, 종료 시간을 선택할 수 있는 콤보박스를 생성해서 panel에 붙인다.
	public DayTimeUI() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		setBackground(UIManager.getColor ( "Panel.background" ));
		gridBagLayout.columnWidths = new int[] {40, 10, 40, 10, 40, 5, 25};
		
		String days[]={"월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"};    
		day = new JComboBox(days);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 0;
		add(day, c);
		
		String times[] = new String[48];
		
		int idx = 0;
		for(int i=0; i<24; i++) {
			if(i<10) { 
				times[idx] = "0" + String.valueOf(i) + ":" + "00";
				times[idx+1] = "0" + String.valueOf(i) + ":" + "30";
				idx+=2;
			}else {
				times[idx] = String.valueOf(i) + ":" + "00";
				times[idx+1] = String.valueOf(i) + ":" + "30";
				idx+=2;
			}
		}
		
		startTime = new JComboBox(times);
		c.gridx = 2;
		c.gridy = 0;
		add(startTime, c);
		
		endTime = new JComboBox(times);
		c.gridx = 4;
		c.gridy = 0;
		add(endTime, c);
		
		
	}
	
	public DayAndTime getDayAndTimeObject() {
		if(startTime.getSelectedIndex() == -1 || endTime.getSelectedIndex() == -1) return null;
		
		int selectedStartTime = Integer.valueOf(startTime.getSelectedItem().toString().replace(":", ""));
		int selectedEndTime = Integer.valueOf(endTime.getSelectedItem().toString().replace(":", ""));
		
		return new DayAndTime(day.getSelectedItem().toString(), selectedStartTime, selectedEndTime);
	}
}
