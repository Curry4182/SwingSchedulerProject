import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

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
	
	//gridTable에 있는 모든 일정을 삭제한후
	//일정을 읽어와 다시 그린다. 
	//강의 일정 -> 일정명, 장소, 교수명
	//기타 일정 -> 일정명
	//같은 일정은 같은 배경색으로 표시 
	public void printSchedule() {
		
	}
	
	//삭제 버튼을 클릭하면 호출되는 함수.
	//삭제 여부를 확인하는 팝업창을 띄운다.
	//사용자가 '확인'을 클릭하면 일정을 시간표에서 삭제하고 allschedule 객체에서도 일정정보를 삭제한다.
	//그 후 알람 정보도 업데이트 한다. 
	public void deleteBtnClick() {
		
	}


	//Jpanel을 GridLayout으로 생성한다.
	public void createTable() {
		this.setSize(500, 600);
		this.setLayout(new BorderLayout());

		
		gridTable = new JPanel();
		gridTable.setSize(550, 600);
		
		GridBagLayout gl = new GridBagLayout();
		gridTable.setLayout(gl);
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
		btns.get(0).set(1,new JButton("월"));
		btns.get(0).set(2,new JButton("화"));
		btns.get(0).set(3,new JButton("수"));
		btns.get(0).set(4,new JButton("목"));
		btns.get(0).set(5,new JButton("금"));
		btns.get(0).set(6,new JButton("토"));
		btns.get(0).set(7,new JButton("일"));

		int time = 0;
		for(int i=1; i<49; i++) {
			if(i%2 != 0) {
				if(time < 10) {
					btns.get(i).set(0, new JButton("0" + String.valueOf(time)));
				}else {
					btns.get(i).set(0, new JButton(String.valueOf(time)));
				}
				//btns.get(i).get(0).setBorder(new MatteBorder(1,1,0,1,UIManager.getColor ("TextField.light")));
				time++;
			}else {
				btns.get(i).set(0, new JButton());
				//btns.get(i).get(0).setBorder(new MatteBorder(0,1,1,1,UIManager.getColor ("TextField.light")));
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
	}

}
