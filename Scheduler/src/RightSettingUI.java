import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.ColorUIResource;

import java.awt.Font;



public class RightSettingUI extends JPanel {
	//��� ���� ������ ����ִ� �ʵ� 
	private ArrayList<Schedule> allSchedule;
	
	//
	private LeftTableUI leftUI;
	private ArrayList<DayTimeUI> timeLines;
	//�˸����� �����ؼ� �߰��� ����,KCH
	private Alarm alarm;
	private JPanel dayTimeUIPanel;
	public RightSettingUI(ArrayList<Schedule> allSchedule, LeftTableUI leftUI, Alarm alarm) {
		this.allSchedule = allSchedule;
		this.leftUI = leftUI;
		
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
				addScheduleBtnClick();
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
	        	selectLecture();
	        	revalidate();
	        	repaint();
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
	        	selectOtherSchedule();
	        	revalidate();
	        	repaint();
	        }
	    });
		
		

		JLabel lineField = new JLabel("");
		lineField.setBorder(new MatteBorder(0, 0, 1, 0, UIManager.getColor ("Button.light")));
		GridBagConstraints gbc_lineField = new GridBagConstraints();
		gbc_lineField.insets = new Insets(0, 0, 5, 5);
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
		
		selectLecture();
	}
	
	//���� �� �ð��� �Է¹޴� UI�� �ʱ�ȭ �ϴ� �Լ�
	//selectLecture�� selectOtherSchedule �Լ����� ���δ�. 
	public void dayTimeUIContainerInit(JPanel dayTimeUIContainerPanel) {
		dayTimeUIContainerPanel.setBackground(UIManager.getColor ( "Panel.background" ));
		dayTimeUIContainerPanel.setBorder(BorderFactory.createLineBorder(UIManager.getColor("Panel.background")));
		
		//Y������ �߰��ǵ��� ����
		dayTimeUIContainerPanel.setLayout(new BoxLayout(dayTimeUIContainerPanel, BoxLayout.Y_AXIS));
		
		//DayAndTime UI Panel
		dayTimeUIPanel = new JPanel();
		dayTimeUIPanel.setBackground(UIManager.getColor ( "Panel.background" ));
		dayTimeUIPanel.setBorder(BorderFactory.createLineBorder(UIManager.getColor("Panel.background")));
		
		//Y������ �߰��ǵ��� ����
		dayTimeUIPanel.setLayout(new BoxLayout(dayTimeUIPanel, BoxLayout.Y_AXIS));
		
		//�⺻������ ���� �� �ð��� �Է¹��� �� �ִ� UI���� �߰�
		DayTimeUI newDayTimeUI = new DayTimeUI();
		timeLines.add(newDayTimeUI);
		dayTimeUIPanel.add(newDayTimeUI);
		
		//DayAndTime UI Container�� DayAndTime UI Panel�߰�
		dayTimeUIContainerPanel.add(dayTimeUIPanel);
		
		//+��ư �߰� 
		JButton dayTimePlusBtn = new JButton("+");
		
		//+��ư action�߰� 
		dayTimePlusBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTimeLineBtnClick();
			}
		});
		
		//+��ư �߰� 
		dayTimePlusBtn.setBackground(UIManager.getColor ( "Panel.background" ));
		dayTimePlusBtn.setBorder(null);
		dayTimePlusBtn.setFont(new Font("aria", Font.BOLD, 25));
		dayTimeUIContainerPanel.add(dayTimePlusBtn);
				
		//��ũ�� �߰�
		JScrollPane jsp = new JScrollPane(dayTimeUIContainerPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setBorder(BorderFactory.createLineBorder(UIManager.getColor("Panel.background")));
		GridBagConstraints gbcDayAndTime = new GridBagConstraints();
		gbcDayAndTime.gridwidth = 10;
		gbcDayAndTime.gridheight = 3;
		gbcDayAndTime.insets = new Insets(0, 0, 5, 35);
		gbcDayAndTime.fill = GridBagConstraints.BOTH;
		gbcDayAndTime.gridx = 1;
		gbcDayAndTime.gridy = 9;
		jsp.putClientProperty("isDeleteWhenReSelectRadioBtn", true);
		add(jsp, gbcDayAndTime);
	}
	
	//���� ���� üũ �ڽ��� üũ�ϸ� ȣ��Ǵ� �Լ�
	//��Ÿ ���� üũ �ڽ��� üũ���¸� ����
	//���Ǹ�, ������, ���, ���� �� �ð��� �Է��� �� �ִ� �Է¶��� �����ش�.
	//���� �� �ð��� ������ �� �ִ� ��ư�� 
	//���� �� �ð��� �߰��� �� �ִ� ��ư�� �����ش�.
	public void selectLecture() {
		Component[] components = getComponents();
		this.timeLines = new ArrayList<DayTimeUI>();
		//panel���� ��� ������Ʈ �߿� �������� �˻� �� ����
		for(int i=0; i<components.length;i++) {
			if(components[i] instanceof JRadioButton) {
				//��Ÿ���� Radio button�� select
				JRadioButton radioBtn = (JRadioButton)components[i];
				if(radioBtn.getText().equals("��������")) {
					radioBtn.setSelected(true);
				}
			}
			
			//isDeleteWhenReSelectRadioBtn -> ��������, ��Ÿ������ ���� Radio Button�� ����ɶ� ���� ������ �ִ� �����ؾ��� Component�� �ǹ� 
			//���� ������ ���������� ���õǾ��ִٸ� ���������� ���������� ������ Component���� ����
			if(components[i] instanceof JComponent) {
				JComponent jComponent = (JComponent)components[i];
				if(jComponent.getClientProperty("isDeleteWhenReSelectRadioBtn") != null){
					boolean isDelete = (boolean)jComponent.getClientProperty("isDeleteWhenReSelectRadioBtn");
					remove(components[i]);
				}
			}
		}
		
		//������ label ����
		JLabel lblNewLabel = new JLabel("������");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 5;
		add(lblNewLabel, gbc_lblNewLabel);
		lblNewLabel.putClientProperty("isDeleteWhenReSelectRadioBtn", true);
		
		//������ field ����
		JTextField textField = new JTextField();
		textField.setName("title");// �ʵ� �̸� ����
		GridBagConstraints gbc_textField = new GridBagConstraints();
		textField.setBorder(new MatteBorder(0, 0, 1, 0, UIManager.getColor ("Button.light")));
		gbc_textField.gridwidth = 6;
		gbc_textField.insets = new Insets(0, 10, 5, 45);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 5;
		add(textField, gbc_textField);
		textField.setColumns(10);
		textField.putClientProperty("isDeleteWhenReSelectRadioBtn", true);
		
		
		//������ label ����
		JLabel lblNewLabel_1 = new JLabel("������");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 6;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		lblNewLabel_1.putClientProperty("isDeleteWhenReSelectRadioBtn", true);
		
		//������ field ����
		JTextField textField_1 = new JTextField();
		textField_1.setName("profName");// �ʵ� �̸� ����
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		textField_1.setBorder(new MatteBorder(0, 0, 1, 0, UIManager.getColor ("Button.light")));
		gbc_textField_1.gridwidth = 6;
		gbc_textField_1.insets = new Insets(0, 10, 5, 45);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 6;
		add(textField_1, gbc_textField_1);
		textField_1.putClientProperty("isDeleteWhenReSelectRadioBtn", true);
		
		//��� label ����
		JLabel lblNewLabel_1_1 = new JLabel("���");
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 1;
		gbc_lblNewLabel_1_1.gridy = 7;
		add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		lblNewLabel_1_1.putClientProperty("isDeleteWhenReSelectRadioBtn", true);
		
		//��� field ����
		JTextField textField_2 = new JTextField();
		textField_2.setName("location");// �ʵ� �̸� ����
		textField_2.setColumns(10);
		textField_2.setBorder(new MatteBorder(0, 0, 1, 0, UIManager.getColor ("Button.light")));
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.gridwidth = 6;
		gbc_textField_2.insets = new Insets(0, 10, 5, 45);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 7;
		add(textField_2, gbc_textField_2);
		textField_2.putClientProperty("isDeleteWhenReSelectRadioBtn", true);
		
		//DayAndTime UI Container
		JPanel dayTimeUIContainerPanel = new JPanel();
		
		//DayAndTime UI Container �ʱ�ȭ
		dayTimeUIContainerInit(dayTimeUIContainerPanel);
	}
	
	//��Ÿ���� üũ �ڽ��� �����ϸ� ȣ��Ǵ� �Լ��̴�.
	//���� ���� üũ�ڽ��� üũ���¸� �����Ѵ�.
	//������, ���Ϲ׽ð� �Է¶��� �����ش�.
	//���� �� �ð��� ������ �� �ִ� ��ư�� ���� �� �ð��� �߰��� �� �ִ� ��ư�� �����ش�. 
	public void selectOtherSchedule() {
		Component[] components = getComponents();
		this.timeLines = new ArrayList<DayTimeUI>();
		//panel���� ��� ������Ʈ �߿� ��Ÿ���� �˻� �� ���� 
		for(int i=0; i<components.length;i++) {	
			if(components[i] instanceof JRadioButton) {
				//��Ÿ���� Radio button�� select
				JRadioButton radioBtn = (JRadioButton)components[i];
				if(radioBtn.getText().equals("��Ÿ����")) {
					radioBtn.setSelected(true);
				}
			}
			
			//isDeleteWhenReSelectRadioBtn -> ��������, ��Ÿ������ ���� Radio Button�� ����ɶ� ���� ������ �ִ� �����ؾ��� Component�� �ǹ� 
			//���� ������ ���������� ���õǾ��ִٸ� ���������� ���������� ������ Component���� ����
			if(components[i] instanceof JComponent) {
				JComponent jComponent = (JComponent)components[i];
				if(jComponent.getClientProperty("isDeleteWhenReSelectRadioBtn") != null){
					boolean isDelete = (boolean)jComponent.getClientProperty("isDeleteWhenReSelectRadioBtn");
					remove(components[i]);
				}
			}
		}
		
		//������ label ����
		JLabel lblNewLabel = new JLabel("������");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 5;
		add(lblNewLabel, gbc_lblNewLabel);
		lblNewLabel.putClientProperty("isDeleteWhenReSelectRadioBtn", true);
		
		//������ field ����
		JTextField textField = new JTextField();
		textField.setName("title"); // �ʵ� �̸� ����
		GridBagConstraints gbc_textField = new GridBagConstraints();
		textField.setBorder(new MatteBorder(0, 0, 1, 0, UIManager.getColor ("Button.light")));
		gbc_textField.gridwidth = 6;
		gbc_textField.insets = new Insets(0, 10, 5, 45);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 5;
		add(textField, gbc_textField);
		textField.setColumns(10);
		textField.putClientProperty("isDeleteWhenReSelectRadioBtn", true);
		
		//DayAndTime UI Container
		JPanel dayTimeUIContainerPanel = new JPanel();
		
		//DayAndTime UI Container �ʱ�ȭ
		dayTimeUIContainerInit(dayTimeUIContainerPanel);

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
		
		//���� �߰��ҷ��� �ϴ� �������� ������ ����Ǿ��ִ� ������ ��ġ�°� �ִٸ�
		//�˸� ǥ�� �� �Լ� ���� 
		if(checkDuplication()) {
			JOptionPane.showMessageDialog(null, "�ð��� ��Ĩ�ϴ�.", "�����߰� ���� �޼���", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
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
		
		String title="", profName="", location="";
		//���������� ���õ� ��� 
		if(isLecture) {
			for(int i=0; i<components.length;i++) {	
				if(components[i] instanceof JTextField) {
					JTextField field = (JTextField)components[i];
					String fieldName = field.getName();
					
					if(fieldName.equals("title")) {
						title = field.getText().strip();
					}else if(fieldName.equals("profName")) {
						profName = field.getText().strip();
					}else if(fieldName.equals("location")) {
						location = field.getText().strip();
					}
				}
			}
		}else {//��Ÿ������ ���õ� ��� 
			for(int i=0; i<components.length;i++) {	
				if(components[i] instanceof JTextField) {
					JTextField field = (JTextField)components[i];
					String fieldName = field.getName();
					
					if(fieldName.equals("title")) {
						title = field.getText().strip();
					}
				}
			}
		}
		
		if(isLecture) {
			if(title.equals("")) {
				JOptionPane.showMessageDialog(null, "�������� �Է����ּ���", "�����߰� ���� �޼���", JOptionPane.WARNING_MESSAGE);
				return;
			}else if(profName.equals("")) {
				JOptionPane.showMessageDialog(null, "�������� �Է����ּ���", "�����߰� ���� �޼���", JOptionPane.WARNING_MESSAGE);
				return;
			}else if(location.equals("")) {
				JOptionPane.showMessageDialog(null, "��Ҹ��� �Է����ּ���", "�����߰� ���� �޼���", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}else {
			if(title.equals("")) {
				JOptionPane.showMessageDialog(null, "�������� �Է����ּ���", "�����߰� ���� �޼���", JOptionPane.WARNING_MESSAGE);
				return;
			}
		}
		
		ArrayList<DayAndTime> dayAndTimes = new ArrayList<DayAndTime>();
		for(int i=0; i<timeLines.size(); i++) {
			DayAndTime dayTime = timeLines.get(i).getDayAndTimeObject();
			dayAndTimes.add(dayTime);
		}
		
		Schedule scheldule = new Schedule(0, title, profName, location, dayAndTimes);
		allSchedule.add(scheldule);
		
		
		leftUI.printSchedule();
	}
	
	
	//allSchedule�� ����Ǿ� �ִ� �������� �ð��� ���ڷ� ���� �ð��� ��ģ�ٸ� true�� ��ȯ�Ѵ�. 
	public Boolean checkDuplication() {
		for(int k=0; k<timeLines.size(); k++) {
			DayAndTime dayTime = timeLines.get(k).getDayAndTimeObject();
			String day = dayTime.day;
			int startTime = dayTime.startTime;
			int endTime = dayTime.endTime;
			
			for(int i=0; i < allSchedule.size(); i++) {
				ArrayList<DayAndTime> dayAndTimes = allSchedule.get(i).getDayAndTime();

				for(int j=0; j<dayAndTimes.size(); j++) {
					if(dayAndTimes.get(j).day == day) {
						DayAndTime nowDay = dayAndTimes.get(j); //������ �ִ� ���� 
						
						
						if(nowDay.startTime <= startTime &&  startTime < nowDay.endTime) {
							return true;
						}
						else if(nowDay.startTime < endTime &&  endTime <= nowDay.endTime) {
							return true;
						}else if(startTime <= nowDay.startTime && nowDay.startTime <= endTime) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	public void addTimeLineBtnClick() {
		DayTimeUI newDayTimeUI = new DayTimeUI();
		timeLines.add(newDayTimeUI);
		
		//
		dayTimeUIPanel.add(newDayTimeUI);
		
		//x��ư �߰� 
		JButton dayTimeXBtn = new JButton(" x");
		dayTimeXBtn.setBackground(UIManager.getColor ( "Panel.background" ));
		dayTimeXBtn.setBorder(null);
		dayTimeXBtn.setFont(new Font("aria", Font.BOLD, 15));
		dayTimeXBtn.putClientProperty("DayTimeUIInstance", newDayTimeUI);
		GridBagConstraints gbcDayTimeXBtn = new GridBagConstraints();
		gbcDayTimeXBtn.gridx = 6;
		gbcDayTimeXBtn.gridy = 0;
		newDayTimeUI.add(dayTimeXBtn, gbcDayTimeXBtn);
				
		//x��ư action�߰�
		dayTimeXBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent button = (JComponent)e.getSource();
				DayTimeUI dayTime = (DayTimeUI)button.getClientProperty("DayTimeUIInstance");
				dayTimeUIPanel.remove(dayTime);
				timeLines.remove(dayTime);
				revalidate();
				repaint();
			}
		});
		
		revalidate();
		repaint();
	}
	
	//Seq(5) �˸����� KCH
	public void alarmOnOffBtnClick() {
		alarm.changeAlarmState();
	}

}
