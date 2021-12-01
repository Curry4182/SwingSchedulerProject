import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
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
	
		btns.get(0).get(0).setBorder(new MatteBorder(0,0,0,0, Color.BLACK));
		for(int i=0; i<49; i++) {
			for(int j=0; j<8; j++) {
				btns.get(i).get(j).setBackground(UIManager.getColor ( "Panel.background" ));
				if(j==0) {
					c.gridwidth = 1;
					c.weightx = 1;
					c.gridx = j;
					
					if(i%2==0) {
						c.gridheight = 1;
					}else {
						c.gridheight = 2;
					}
					btns.get(i).get(j).setPreferredSize(new Dimension(20, 20));
				}
				else {
					c.gridwidth = 2;
					c.gridheight = 1;
					c.weightx = 2;
					c.gridx = j*2 - 1;
					btns.get(i).get(j).setPreferredSize(new Dimension(15, 20));
				}
				c.gridy = i;
								
				gridTable.add(btns.get(i).get(j), c);
			}
		}
		
		JScrollPane jsp = new JScrollPane(gridTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(jsp);
		
	
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
				int hour = startTime/100;
				

				//ex) 1130 - 1100
				int m = startTime - hour*100;
				
				//�ð��� ���۵Ǵ� row�� ��ġ 
				int startRow = 1;
				//���ð��� 2ĭ������ ���ϱ� 2�� �Ѵ�. 
				//���� 0�� �ƴϸ� 30�� �̱� ������ 0�� �̸� 0�� �ƴϸ� 1�� ���Ѵ�
				int row = startRow + hour*2 + (m == 0 ? 0 : 1);
				
				//������ ���۵Ǵ� col�� ��ġ
				int startCol = 1;
				
				//�� ���Ϻ��� grid���� 2ĭ�� ���� �ϱ� ������ *2�� �Ѵ�. 
				int col = startCol + dayToNum.get(day)*2;

				System.out.println("c: " + col + "r: " +  row);
			}
		}
	}
	
	//���� ��ư�� Ŭ���ϸ� ȣ��Ǵ� �Լ�.
	//���� ���θ� Ȯ���ϴ� �˾�â�� ����.
	//����ڰ� 'Ȯ��'�� Ŭ���ϸ� ������ �ð�ǥ���� �����ϰ� allschedule ��ü������ ���������� �����Ѵ�.
	//�� �� �˶� ������ ������Ʈ �Ѵ�. 
	public void deleteBtnClick() {
		
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
