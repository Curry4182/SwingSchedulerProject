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
	//�˸����� �����ؼ� �߰��� ����,KCH
	private Alarm alarm;

	public RightSettingUI(ArrayList<Schedule> allSchedule, LeftTableUI leftUI, Alarm alarm) {
		createSettingUI();
	}
	
	//���������� ��Ÿ���� üũ�ڽ�, �˸� ����ġ, DayTimeUI����
	//�⺻������ ���� ������ üũ�� ���·� ����
	//selectLecture ȣ��
	public void createSettingUI() {
		//��ư Ŭ���� �ȿ��ִ� �ؽ�Ʈ�� ���� ����°��� ���ش�. 
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
		
		
		
		JButton btnNewButton = new JButton("�����߰�");
		btnNewButton.setFont(new Font("���� ���", Font.BOLD, 12));
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
		
		JRadioButton chckbxNewCheckBox = new JRadioButton("��������");
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
		
		JRadioButton chckbxNewCheckBox_1 = new JRadioButton("��Ÿ����");
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
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("�˶�����");
		chckbxNewCheckBox_2.setBackground(UIManager.getColor ( "Panel.background" ));
		chckbxNewCheckBox_2.setFont(new Font("����", Font.PLAIN, 12));
		GridBagConstraints gbc_chckbxNewCheckBox_2 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_2.gridwidth = 3;
		gbc_chckbxNewCheckBox_2.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxNewCheckBox_2.gridx = 5;
		gbc_chckbxNewCheckBox_2.gridy = 16;
		add(chckbxNewCheckBox_2, gbc_chckbxNewCheckBox_2);
	}
	
	//���� ���� üũ �ڽ��� üũ�ϸ� ȣ��Ǵ� �Լ�
	//��Ÿ ���� üũ �ڽ��� üũ���¸� ����
	//���Ǹ�, ������, ���, ���� �� �ð��� �Է��� �� �ִ� �Է¶��� �����ش�.
	//���� �� �ð��� ������ �� �ִ� ��ư�� 
	//���� �� �ð��� �߰��� �� �ִ� ��ư�� �����ش�.
	public void selectLecture() {
		Component[] components = getComponents();

		//panel���� ��� ������Ʈ �߿� �������� �˻� �� ����
		for(int i=0; i<components.length;i++) {
			
			if(components[i] instanceof JRadioButton) {
				if(((JRadioButton)components[i]).getText().equals("��������")) {
					((JRadioButton)components[i]).setSelected(true);
				}
			}
		}
		
		
		JLabel lblNewLabel = new JLabel("������");
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
		
		JLabel lblNewLabel_1 = new JLabel("������");
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
		
		JLabel lblNewLabel_1_1 = new JLabel("���");
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
		
		//DayAndTime UIâ
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
	
	//��Ÿ���� üũ �ڽ��� �����ϸ� ȣ��Ǵ� �Լ��̴�.
	//���� ���� üũ�ڽ��� üũ���¸� �����Ѵ�.
	//������, ���Ϲ׽ð� �Է¶��� �����ش�.
	//���� �� �ð��� ������ �� �ִ� ��ư�� ���� �� �ð��� �߰��� �� �ִ� ��ư�� �����ش�. 
	public void selectOtherSchedule() {
		Component[] components = getComponents();

		//panel���� ��� ������Ʈ �߿� ��Ÿ���� �˻� �� ���� 
		for(int i=0; i<components.length;i++) {	
			if(components[i] instanceof JRadioButton) {
				if(((JRadioButton)components[i]).getText().equals("��Ÿ����")) {
					((JRadioButton)components[i]).setSelected(true);
				}
			}
		}
		
		JLabel lblNewLabel = new JLabel("������");
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
	
	//�����߰� ��ư�� Ŭ���ϸ� ȣ��Ǵ� �Լ��̴�.
	//�ԷµǾ��ִ� �ð� ������ �����ͼ� checkDuplication�Լ��� ���ڷ� �ѱ��.
	//true�� ��ȯ ������ "�ð��� ��Ĩ�ϴ�."�˾�â�� ���� �Լ��� �����Ѵ�.
	//false�� ��ȯ ������ ���õǾ� �ִ� ���� ������ Ȯ���Ѵ�.
	//'���� ����'�� ��� '���Ǹ�, ������, ���, ���� �� �ð�'�� '��Ÿ ����'�� ��� '������, ���� �� �ð�'
	//������ �����ͼ� ���ο� Schedule��ü�� �����Ѵ�.
	//���� ������ Schedule��ü�� allSchedule�� �߰��Ѵ�.
	//leftTableUI�� printSchedule �Լ��� ȣ���Ͽ� ���ο� ������ �ð�ǥ�� �߰��� ���� �� �� �ֵ��� �Ѵ�.
	public void addScheduleBtnClick() {
		Component[] components = getComponents();
		boolean isLecture = false; //���������� ���õǾ� �ִ��� �ƴϸ� ��Ÿ������ ���õǾ��ִ��� ���¸� �����ϴ� ����

		//panel���� ��� ������Ʈ �߿� "��������" �˻� �� ���� 
		for(int i=0; i<components.length;i++) {	
			if(components[i] instanceof JRadioButton) {
				if(((JRadioButton)components[i]).getText().equals("��������")) {
					
					//���� ���������� ���õǾ� �ִٸ� 
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
	
	//allSchedule�� ����Ǿ� �ִ� �������� �ð��� ���ڷ� ���� �ð��� ��ģ�ٸ� true�� ��ȯ�Ѵ�. 
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
	
	//timeDelete��ư�� Ŭ���ϸ� ȣ��Ǵ� �Լ��̴�.
	//���õ� TimeLine�� �������ش�. 
	public void timeDeleteBtnClick() {
		
	}

	//Seq(5) �˸����� KCH
	public void alarmOnOffBtnClick() {
		alarm.changeAlarmState();
	}

}
