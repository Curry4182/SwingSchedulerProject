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
	
	//gridTable에 있는 모든 일정을 삭제한후
	//일정을 읽어와 다시 그린다. 
	//강의 일정 -> 일정명, 장소, 교수명
	//기타 일정 -> 일정명
	//같은 일정은 같은 배경색으로 표시 
	public void printSchedule() {
		Map<String, Integer> dayToNum = new HashMap<String, Integer>();
		dayToNum.put("월요일", 0);
		dayToNum.put("화요일", 1);
		dayToNum.put("수요일", 2);
		dayToNum.put("목요일", 3);
		dayToNum.put("금요일", 4);
		dayToNum.put("토요일", 5);
		dayToNum.put("일요일", 6);
		
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
				//일정 시작시간
				int startTime = dayAndTimes.get(j).startTime;
				
				//일정 종료시간
				int endTime = dayAndTimes.get(j).endTime;
				
				//일정 요일, "월"~"일"
				String day = dayAndTimes.get(j).day;
				
				//startTime은 오전11시30분이면 1130과 같이 저장 따라서 시간을 가져올려면 나누기 100을 한다.
				int hour = startTime/100;
				

				//ex) 1130 - 1100
				int m = startTime - hour*100;
				
				//시간이 시작되는 row의 위치 
				int startRow = 1;
				//각시간당 2칸임으로 곱하기 2를 한다. 
				//분은 0분 아니면 30분 이기 떄문에 0분 이면 0을 아니면 1을 더한다
				int row = startRow + hour*2 + (m == 0 ? 0 : 1);
				
				//요일이 시작되는 col의 위치
				int startCol = 1;
				
				//각 요일별로 grid에서 2칸을 차기 하기 떄문에 *2를 한다. 
				int col = startCol + dayToNum.get(day)*2;

				System.out.println("c: " + col + "r: " +  row);
			}
		}
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
		
		printSchedule();
	}

}
