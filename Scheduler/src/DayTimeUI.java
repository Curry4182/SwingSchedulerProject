import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Objects;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class DayTimeUI extends JPanel{
	private final JComboBox day;
	private final JComboBox startTime;
	private final JComboBox endTime;

	//요일, 시작 시간, 종료 시간을 선택할 수 있는 콤보박스를 생성해서 panel에 붙인다.
	public DayTimeUI() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		setBackground(UIManager.getColor ( "Panel.background" ));
		gridBagLayout.columnWidths = new int[] {40, 10, 40, 10, 40, 5, 25};
		
		String[] days ={"월요일", "화요일", "수요일", "목요일", "금요일", "토요일", "일요일"};
		day = new JComboBox(days);
		
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 0;
		c.gridy = 0;
		add(day, c);
		
		String[] times = new String[48];
		
		int idx = 0;
		for(int i=0; i<24; i++) {
			if(i<10) { 
				times[idx] = "0" + i + ":" + "00";
				times[idx+1] = "0" + i + ":" + "30";
				idx+=2;
			}else {
				times[idx] = i + ":" + "00";
				times[idx+1] = i + ":" + "30";
				idx+=2;
			}
		}
		
		startTime = new JComboBox(times);
		endTime = new JComboBox(times);

		startTime.putClientProperty("pre", startTime.getSelectedItem());
		startTime.putClientProperty("endTime", endTime);
		
		endTime.putClientProperty("pre", endTime.getSelectedItem());
		endTime.putClientProperty("startTime", startTime);
		//00:30분 부터 시작되도록 설정
		endTime.setSelectedIndex(1);
		
		//시작시간이 종료시간 보다 크거나 같을 경우 되돌리는 함수 추가
		startTime.addActionListener(e -> {
			JComboBox startComboBox = (JComboBox) e.getSource();
			int selectedStartTime = Integer.parseInt(Objects.requireNonNull(startComboBox.getSelectedItem()).toString().replace(":", ""));

			JComboBox endComboBox = (JComboBox)startComboBox.getClientProperty("endTime");
			int selectedEndTime = Integer.parseInt(Objects.requireNonNull(endComboBox.getSelectedItem()).toString().replace(":", ""));

			if(selectedStartTime >= selectedEndTime) {
				JOptionPane.showMessageDialog(null, "시작시간이 종료시간 보다 크거나 같을 수 없습니다.", "일정추가 에러 메세지", JOptionPane.WARNING_MESSAGE);
				Object pre = startComboBox.getClientProperty("pre");
				startComboBox.setSelectedItem(pre);
			}else {
				Object pre = startComboBox.getSelectedItem();
				startComboBox.putClientProperty("pre", pre);
			}
		});
		
		//종료시간이 시작시간 보다 작거나 같을 경우 되돌리는 함수 추가
		endTime.addActionListener(e -> {
			JComboBox endComboBox = (JComboBox) e.getSource();
			int selectedEndTime = Integer.parseInt(Objects.requireNonNull(endComboBox.getSelectedItem()).toString().replace(":", ""));

			JComboBox startComboBox = (JComboBox)endComboBox.getClientProperty("startTime");
			int selectedStartTime = Integer.parseInt(Objects.requireNonNull(startComboBox.getSelectedItem()).toString().replace(":", ""));

			if(selectedStartTime >= selectedEndTime) {
				JOptionPane.showMessageDialog(null, "종료시간이 시작시간 보다 작거나 같을 수 없습니다.", "일정추가 에러 메세지", JOptionPane.WARNING_MESSAGE);
				Object pre = endComboBox.getClientProperty("pre");
				endComboBox.setSelectedItem(pre);
			}else {
				Object pre = endComboBox.getSelectedItem();
				endComboBox.putClientProperty("pre", pre);
			}
		});
		
		c.gridx = 2;
		c.gridy = 0;
		add(startTime, c);
		
		c.gridx = 4;
		c.gridy = 0;
		add(endTime, c);
	}
	
	public DayAndTime getDayAndTimeObject() {
		if(startTime.getSelectedIndex() == -1 || endTime.getSelectedIndex() == -1) return null;
		
		int selectedStartTime = Integer.parseInt(Objects.requireNonNull(startTime.getSelectedItem()).toString().replace(":", ""));
		int selectedEndTime = Integer.parseInt(Objects.requireNonNull(endTime.getSelectedItem()).toString().replace(":", ""));
		
		return new DayAndTime(Objects.requireNonNull(day.getSelectedItem()).toString(), selectedStartTime, selectedEndTime);
	}
}