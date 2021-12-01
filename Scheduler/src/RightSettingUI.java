import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.ColorUIResource;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.border.Border;
import javax.swing.JSplitPane;
import javax.swing.JCheckBox;
import java.awt.Font;



public class RightSettingUI extends JPanel {

	private ArrayList<Schedule> allSchedule;
	private LeftTableUI leftUI;
	private ArrayList<DayTimeUI> timeLines;
	private JLabel lineField;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	//알림설정 관련해서 추가한 변수,KCH
	private Alarm alarm;

	public RightSettingUI(ArrayList<Schedule> allSchedule, LeftTableUI leftUI, Alarm alarm) {
		createSettingUI();
	}
	
	//강의일정과 기타일정 체크박스, 알림 스위치, DayTimeUI생성
	//기본적으로 강의 일정이 체크된 상태로 변경
	//selectLecture 호출
	public void createSettingUI() {
		//버튼 클릭시 안에있는 텍스트에 밑줄 생기는것을 없앤다. 
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		defaults.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
		defaults.put("RadioButton.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
		defaults.put("CheckBox.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

		setBorder(new LineBorder(UIManager.getColor ("Button.light"), 2, true));
		this.setSize(390, 520);
		this.setVisible(true);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {30, 30, 30, 30, 30, 30, 30, 30, 5};
		gridBagLayout.rowHeights = new int[] {30, 30, 30, 30, 30,30, 30, 30, 30, 30,30, 30, 30, 30, 30, 5,30};
		
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		
		
		JButton btnNewButton = new JButton("강의추가");
		btnNewButton.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnNewButton.setBorder(new RoundedBorder(5));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 5;
		gbc_btnNewButton.gridy = 1;
		add(btnNewButton, gbc_btnNewButton);
		
		ButtonGroup group = new ButtonGroup();
		
		JRadioButton chckbxNewCheckBox = new JRadioButton("강의일정");
		chckbxNewCheckBox.setBackground(UIManager.getColor ( "Panel.background" ));
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox.anchor = GridBagConstraints.WEST;
		gbc_chckbxNewCheckBox.gridwidth = 4;
		gbc_chckbxNewCheckBox.gridx = 1;
		gbc_chckbxNewCheckBox.gridy = 1;
		add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		group.add(chckbxNewCheckBox);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	removeAll();
	        	createSettingUI();
	        	selectLecture();
	        	revalidate();
	        }
	    });
		
		JRadioButton chckbxNewCheckBox_1 = new JRadioButton("기타일정");
		chckbxNewCheckBox_1.setBackground(UIManager.getColor ( "Panel.background" ));
		GridBagConstraints gbc_chckbxNewCheckBox_1 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxNewCheckBox_1.gridwidth = 4;
		gbc_chckbxNewCheckBox_1.anchor = GridBagConstraints.WEST;
		gbc_chckbxNewCheckBox_1.gridx = 1;
		gbc_chckbxNewCheckBox_1.gridy = 2;
		add(chckbxNewCheckBox_1, gbc_chckbxNewCheckBox_1);
		group.add(chckbxNewCheckBox_1);

    	
		chckbxNewCheckBox_1.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	removeAll();
	        	createSettingUI();
	        	selectOtherSchedule();
	        	revalidate();
	        }
	    });

		lineField = new JLabel("");
		lineField.setBorder(new MatteBorder(0, 0, 1, 0, UIManager.getColor ("Button.light")));
		GridBagConstraints gbc_lineField = new GridBagConstraints();
		gbc_lineField.insets = new Insets(0, 0, 5, 0);
		gbc_lineField.gridwidth = 10;
		gbc_lineField.fill = GridBagConstraints.HORIZONTAL;
		gbc_lineField.gridx = 0;
		gbc_lineField.gridy = 15;
		add(lineField, gbc_lineField);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("알람설정");
		chckbxNewCheckBox_2.setBackground(UIManager.getColor ( "Panel.background" ));
		chckbxNewCheckBox_2.setFont(new Font("굴림", Font.PLAIN, 12));
		GridBagConstraints gbc_chckbxNewCheckBox_2 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_2.gridwidth = 3;
		gbc_chckbxNewCheckBox_2.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxNewCheckBox_2.gridx = 5;
		gbc_chckbxNewCheckBox_2.gridy = 16;
		add(chckbxNewCheckBox_2, gbc_chckbxNewCheckBox_2);
	}
	
	//강의 일정 체크 박스를 체크하면 호출되는 함수
	//기타 일정 체크 박스의 체크상태를 해제
	//강의명, 교수명, 장소, 요일 및 시간을 입력할 수 있는 입력란을 보여준다.
	//요일 및 시간을 삭제할 수 있는 버튼과 
	//요일 및 시간을 추가할 수 있는 버튼을 보여준다.
	public void selectLecture() {
		Component[] components = getComponents();

		//panel에서 모든 컴포넌트 중에 강의일정 검색 후 선택
		for(int i=0; i<components.length;i++) {
			
			if(components[i] instanceof JRadioButton) {
				if(((JRadioButton)components[i]).getText().equals("강의일정")) {
					((JRadioButton)components[i]).setSelected(true);
				}
			}
		}
		
		
		JLabel lblNewLabel = new JLabel("수업명");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 5;
		add(lblNewLabel, gbc_lblNewLabel);
		
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		textField.setBorder(new MatteBorder(0, 0, 1, 0, UIManager.getColor ("Button.light")));
		gbc_textField.gridwidth = 6;
		gbc_textField.insets = new Insets(0, 10, 5, 45);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 5;
		add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("교수명");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 6;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		textField_1.setBorder(new MatteBorder(0, 0, 1, 0, UIManager.getColor ("Button.light")));
		gbc_textField_1.gridwidth = 6;
		gbc_textField_1.insets = new Insets(0, 10, 5, 45);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 6;
		add(textField_1, gbc_textField_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("장소");
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 1;
		gbc_lblNewLabel_1_1.gridy = 7;
		add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBorder(new MatteBorder(0, 0, 1, 0, UIManager.getColor ("Button.light")));
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.gridwidth = 6;
		gbc_textField_2.insets = new Insets(0, 10, 5, 45);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 7;
		add(textField_2, gbc_textField_2);
		
		//DayAndTime UI창
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor ( "Panel.background" ));
		panel.setBorder(BorderFactory.createLineBorder(UIManager.getColor("Panel.background")));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel.add(new DayTimeUI());
		panel.add(new DayTimeUI());
		panel.add(new DayTimeUI());
		panel.add(new DayTimeUI());
		
		JScrollPane jsp = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setBorder(BorderFactory.createLineBorder(UIManager.getColor("Panel.background")));
		GridBagConstraints gbcDayAndTime = new GridBagConstraints();
		gbcDayAndTime.gridwidth = 10;
		gbcDayAndTime.gridheight = 3;
		gbcDayAndTime.insets = new Insets(0, 0, 5, 35);
		gbcDayAndTime.fill = GridBagConstraints.BOTH;
		gbcDayAndTime.gridx = 1;
		gbcDayAndTime.gridy = 9;
		
		add(jsp, gbcDayAndTime);
	}
	
	//기타일정 체크 박스를 선택하면 호출되는 함수이다.
	//강의 일정 체크박스의 체크상태를 해제한다.
	//일정명, 요일및시간 입력란을 보여준다.
	//요일 및 시간을 삭제할 수 있는 버튼과 요일 및 시간을 추가할 수 있는 버튼을 보여준다. 
	public void selectOtherSchedule() {
		Component[] components = getComponents();

		//panel에서 모든 컴포넌트 중에 기타일정 검색 후 선택 
		for(int i=0; i<components.length;i++) {	
			if(components[i] instanceof JRadioButton) {
				if(((JRadioButton)components[i]).getText().equals("기타일정")) {
					((JRadioButton)components[i]).setSelected(true);
				}
			}
		}
		
		JLabel lblNewLabel = new JLabel("일정명");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 5;
		add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		textField.setBorder(new MatteBorder(0, 0, 1, 0, UIManager.getColor ("Button.light")));
		gbc_textField.gridwidth = 6;
		gbc_textField.insets = new Insets(0, 10, 5, 45);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 5;
		add(textField, gbc_textField);
		textField.setColumns(10);
	}
	
	//일정추가 버튼을 클릭하면 호출되는 함수이다.
	//입력되어있는 시간 정보를 가져와서 checkDuplication함수의 인자로 넘긴다.
	//true를 반환 받으면 "시간이 겹칩니다."팝업창을 띄우고 함수를 종료한다.
	//false를 반환 받으면 선택되어 있는 일정 종류를 확인한다.
	//'강의 일정'의 경우 '강의명, 교수명, 장소, 요일 및 시간'을 '기타 일정'의 경우 '일정명, 요일 및 시간'
	//정보를 가져와서 새로운 Schedule객체에 저장한다.
	//서로 생성된 Schedule객체를 allSchedule에 추가한다.
	//leftTableUI의 printSchedule 함수를 호출하여 새로운 일정을 시간표에 추가해 보여 줄 수 있도록 한다.
	public void addScheduleBtnClick() {
		Component[] components = getComponents();
		boolean isLecture = false; //강의일정이 선택되어 있는지 아니면 기타일정이 선택되어있는지 상태를 저장하는 변수

		//panel에서 모든 컴포넌트 중에 "강의일정" 검색 후 선택 
		for(int i=0; i<components.length;i++) {	
			if(components[i] instanceof JRadioButton) {
				if(((JRadioButton)components[i]).getText().equals("강의일정")) {
					
					//현재 강의일정이 선택되어 있다면 
					if(((JRadioButton)components[i]).isSelected()) {
						isLecture = true;
						break;
					}
				}
			}
		}
		
		if(isLecture) {
			for(int i=0; i<components.length;i++) {	
				if(components[i] instanceof JTextField) {
					
				}
			}
		}else {
			
		}
	}
	
	//allSchedule에 저장되어 있는 일정들의 시간과 인자로 받은 시간이 겹친다면 true를 반환한다. 
	public Boolean checkDuplication(String day, int startTime, int endTime) {
		for(int i=0; i < allSchedule.size(); i++) {
			ArrayList<DayAndTime> dayAndTimes = allSchedule.get(i).getDayAndTime();

			for(int j=0; j<dayAndTimes.size(); j++) {
				if(dayAndTimes.get(j).day == day) {
					DayAndTime nowDay = dayAndTimes.get(j);
					if(nowDay.startTime <= startTime &&  startTime < nowDay.endTime) {
						return true;
					}
					else if(nowDay.startTime < endTime &&  endTime <= nowDay.endTime) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	//timeDelete버튼을 클릭하면 호출되는 함수이다.
	//선택된 TimeLine을 삭제해준다. 
	public void timeDeleteBtnClick() {
		
	}

	//Seq(5) 알림설정 KCH
	public void alarmOnOffBtnClick() {
		alarm.changeAlarmState();
	}

}
