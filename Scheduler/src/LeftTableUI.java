import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;


public class LeftTableUI extends JPanel {

	private JPanel gridTable;
	private ArrayList<Schedule> allSchedule;
	private Alarm alarm;

	public LeftTableUI(ArrayList<Schedule> allSchedule, Alarm alarm) {
		this.allSchedule = allSchedule;
		this.alarm = alarm;
		createTable();
	}
	
	//gridTable�� �ִ� ��� ������ ��������
	//������ �о�� �ٽ� �׸���. 
	//���� ���� -> ������, ���, ������
	//��Ÿ ���� -> ������
	//���� ������ ���� �������� ǥ�� 
	public void printSchedule() {		
		removeAll();//gridTable�� �ִ� ��� ������ ����
		
		this.setSize(500, 600);
		this.setLayout(new BorderLayout());

		gridTable = new JPanel();
		gridTable.setSize(550, 600);
		
		GridBagLayout gl = new GridBagLayout();
		gridTable.setLayout(gl);
		
		Map<String, Integer> dayToNum = new HashMap<String, Integer>();
		dayToNum.put("������", 0);
		dayToNum.put("ȭ����", 1);
		dayToNum.put("������", 2);
		dayToNum.put("�����", 3);
		dayToNum.put("�ݿ���", 4);
		dayToNum.put("�����", 5);
		dayToNum.put("�Ͽ���", 6);
		
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.fill = GridBagConstraints.BOTH;
		
		ArrayList<ArrayList<JButton>> btns = new ArrayList<ArrayList<JButton>>();
		
		for(int i=0; i<49 ; i++) {
			btns.add(new ArrayList<JButton>());
			for(int j=0; j<8; j++) {
				JButton btn = new JButton();
				btn.setBackground(UIManager.getColor ("Panel.background"));
				btns.get(i).add(btn);
			}
		}
		
		btns.get(0).set(0,new JButton());
		btns.get(0).set(1,new JButton("��"));
		btns.get(0).set(2,new JButton("ȭ"));
		btns.get(0).set(3,new JButton("��"));
		btns.get(0).set(4,new JButton("��"));
		btns.get(0).set(5,new JButton("��"));
		btns.get(0).set(6,new JButton("��"));
		btns.get(0).set(7,new JButton("��"));

		int time = 0;
		for(int i=1; i<49; i++) {
			if(i%2 != 0) {
				if(time < 10) {
					btns.get(i).set(0, new JButton("0" + String.valueOf(time)));
				}else {
					btns.get(i).set(0, new JButton(String.valueOf(time)));
				}
				time++;
			}else {
				btns.get(i).set(0, new JButton());
			}
		}
	
		//0,0 ��ư border���� 
		btns.get(0).get(0).setBorder(new MatteBorder(0,0,0,0, Color.BLACK));
		
		//�ش� ��ġ ��ư�� ������ grid�� ��ġ, ���� ���� �������ִ� GridBagConstraints���� ���� 
		ArrayList<ArrayList<GridBagConstraints>> gbcs = new ArrayList<ArrayList<GridBagConstraints>>();
		for(int i=0; i<49; i++) {
			gbcs.add(new ArrayList<GridBagConstraints>());
			for(int j=0; j<8; j++) {
				GridBagConstraints gbc = new GridBagConstraints();
				
				gbc.weightx = 1;
				gbc.weighty = 1;
				gbc.fill = GridBagConstraints.BOTH; 
				
				//����ǥ ������ʿ� �ִ� �ð� �� 
				if(j==0) {
					gbc.gridwidth = 1;
					gbc.weightx = 1;
					gbc.gridx = j;
					
					if(i%2==0) {
						gbc.gridheight = 1;
					}else {
						gbc.gridheight = 2;
					}
				}
				else {
					gbc.gridwidth = 2;
					gbc.gridheight = 1;
					gbc.weightx = 2;
					gbc.gridx = j*2 - 1;
				}
				gbc.gridy = i;
				
				gbcs.get(i).add(gbc);
			}
		}
		
		//������ ���� 
		for(int i=0; i < allSchedule.size(); i++) {
			ArrayList<DayAndTime> dayAndTimes = allSchedule.get(i).getDayAndTime();

			for(int j=0; j<dayAndTimes.size(); j++) {
				
				//���� ���۽ð�
				int startTime = dayAndTimes.get(j).startTime;
				
				//���� ����ð�
				int endTime = dayAndTimes.get(j).endTime;
				
				//���� ����, "��"~"��"
				String day = dayAndTimes.get(j).day;
				
				//startTime�� ����11��30���̸� 1130�� ���� ���� ���� �ð��� �����÷��� ������ 100�� �Ѵ�.
				int startHour = startTime/100;
				int endHour = endTime/100;

				//ex) 1130 - 1100
				int startMinute = startTime - startHour*100;
				int endMinute = endTime - endHour*100;
				//�ð��� ���۵Ǵ� row�� ��ġ 
				int initRow = 1;
				
				//���ð��� 2ĭ������ ���ϱ� 2�� �Ѵ�. 
				//���� 0�� �ƴϸ� 30�� �̱� ������ 0�� �̸� 0�� �ƴϸ� 1�� ���Ѵ�
				int startRow = initRow + startHour*2 + (startMinute == 0 ? 0 : 1);
				int endRow = initRow + endHour*2 + (endMinute == 0 ? 0 : 1);
				
				
				//������ ���۵Ǵ� col�� ��ġ
				int initCol = 1;
				
				//�� ����(������~�Ͽ���)�� �°� col���� 
				int col = initCol + dayToNum.get(day);

				GridBagConstraints gbc = gbcs.get(startRow).get(col);
				gbc.gridheight = endRow - startRow;
				
				String btnInformStr = allSchedule.get(i).title;
				 
				//font����
				btns.get(startRow).get(col).setFont(new Font("����", Font.PLAIN, 12));
				//type���� ���� ������ 0, ��Ÿ������ 1
				//�������� �̶��
				if(allSchedule.get(i).type == 0) {
					int height = endRow - startRow;
					if(height == 1) {
						btnInformStr = String.format("<html>%s</html>", allSchedule.get(i).title);
					}else if (height == 2) {
						btnInformStr = String.format("<html>%s<br/>%s</html>", allSchedule.get(i).title,  allSchedule.get(i).getLocation());
					}else {
						btnInformStr = String.format("<html>%s<br/>%s<br/>%s<br/></html>", allSchedule.get(i).title,  allSchedule.get(i).getLocation(), allSchedule.get(i).getProfessor());
					}
				}else if(allSchedule.get(i).type == 1) { //��Ÿ�����̶�� 
					btnInformStr = String.format("<html>%s</html>", allSchedule.get(i).title);
				}
				
				//��ư �ؽ�Ʈ ���� 
				btns.get(startRow).get(col).setText(btnInformStr);
				
				btns.get(startRow).get(col).putClientProperty("scheduleItem", allSchedule.get(i));

				
				btns.get(startRow).get(col).addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JButton btn = (JButton)e.getSource();
						Schedule item = (Schedule)(btn).getClientProperty("scheduleItem");
						String InformStr = String.format("<html>���Ǹ�: %s<br/>���: %s<br/>����: %s<br/></html>", item.title,  item.getLocation(), item.getProfessor());
						ScheduleDeleteDialog dialog = new ScheduleDeleteDialog(InformStr,new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								deleteBtnClick(item);
							}
				        });
					}
		        });			
			}
		}
		
		for(int i=0; i<49; i++) {
			for(int j=0; j<8; j++) {
				btns.get(i).get(j).setBackground(UIManager.getColor ( "Panel.background" ));
				if(j==0) {
					btns.get(i).get(j).setPreferredSize(new Dimension(20, 20));
				}
				else {
					btns.get(i).get(j).setPreferredSize(new Dimension(15, 20));
				}
				gridTable.add(btns.get(i).get(j), gbcs.get(i).get(j));
			}
		}
		
		JScrollPane jsp = new JScrollPane(gridTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(jsp);
		
		revalidate();
		
		alarm.updateAlarm();
	}
	
	//���� ��ư�� Ŭ���ϸ� ȣ��Ǵ� �Լ�.
	//���� ���θ� Ȯ���ϴ� �˾�â�� ����.
	//����ڰ� 'Ȯ��'�� Ŭ���ϸ� ������ �ð�ǥ���� �����ϰ� allschedule ��ü������ ���������� �����Ѵ�.
	//�� �� �˶� ������ ������Ʈ �Ѵ�. 
	public void deleteBtnClick(Schedule scheduleItem) {
		//allSchedule 
		int dialogButton = JOptionPane.YES_NO_OPTION;
		String content = "\""+scheduleItem.title + "\"" + "�� �����Ͻðڽ��ϱ�?";
		int dialogResult = JOptionPane.showConfirmDialog(this, content, "���� ����", dialogButton);
		if(dialogResult == 0) {
			allSchedule.remove(scheduleItem);
			printSchedule();
		}
	}


	//Jpanel�� GridLayout���� �����Ѵ�.
	public void createTable() {
		this.setSize(500, 600);
		this.setLayout(new BorderLayout());

		gridTable = new JPanel();
		gridTable.setSize(550, 600);
		
		GridBagLayout gl = new GridBagLayout();
		gridTable.setLayout(gl);
		
		printSchedule();
	}

}
