import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;


public class LeftTableUI extends JPanel {

	private JPanel gridTable;
	private final ArrayList<Schedule> allSchedule;

	public LeftTableUI(ArrayList<Schedule> allSchedule) {
		this.allSchedule = allSchedule;
		createTable();
	}
	
	//gridTable에 있는 모든 일정을 삭제한후
	//일정을 읽어와 다시 그린다. 
	//강의 일정 -> 일정명, 장소, 교수명
	//기타 일정 -> 일정명
	//같은 일정은 같은 배경색으로 표시 
	public void printSchedule() {		
		removeAll();//gridTable에 있는 모든 일정을 삭제
		
		this.setSize(500, 600);
		this.setLayout(new BorderLayout());

		gridTable = new JPanel();
		gridTable.setSize(550, 600);
		
		GridBagLayout gl = new GridBagLayout();
		gridTable.setLayout(gl);
		
		Map<String, Integer> dayToNum = new HashMap<>();
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
		c.fill = GridBagConstraints.BOTH;
		
		ArrayList<ArrayList<JButton>> btns = new ArrayList<>();
		
		for(int i=0; i<49 ; i++) {
			btns.add(new ArrayList<>());
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
					btns.get(i).set(0, new JButton("0" + time));
				}else {
					btns.get(i).set(0, new JButton(String.valueOf(time)));
				}
				time++;
			}else {
				btns.get(i).set(0, new JButton());
			}
		}
	
		//0,0 버튼 border 제거
		btns.get(0).get(0).setBorder(new MatteBorder(0,0,0,0, Color.BLACK));
		
		//해당 위치 버튼을 넣을때 grid 의 위치, 범위 등을 설정해주는 GridBagConstraints 들을 설정
		ArrayList<ArrayList<GridBagConstraints>> gbcs = new ArrayList<>();
		for(int i=0; i<49; i++) {
			gbcs.add(new ArrayList<>());
			for(int j=0; j<8; j++) {
				GridBagConstraints gbc = new GridBagConstraints();
				
				gbc.weightx = 1;
				gbc.weighty = 1;
				gbc.fill = GridBagConstraints.BOTH; 
				
				//일정표 가장왼쪽에 있는 시간 열 
				if(j==0) {
					gbc.gridwidth = 1;
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
		HashSet<String> colorSet = new HashSet<String>();
		int r=0,g=0,b=0;
		Color newColor;

		//스케줄 설정 
		for (Schedule schedule : allSchedule) {
			ArrayList<DayAndTime> dayAndTimes = schedule.getDayAndTime();
			
			//rgb가 집합에 넣을 수 있을때까지 랜덤으로 r,g,b를 재설정
			do {
				r = (int) (Math.random() * 256);
				g = (int) (Math.random() * 256);
				b = (int) (Math.random() * 256);
				newColor = new Color(r, g, b); //일정에 표시되는 color
			}while(!colorSet.add(newColor.toString()));
			
			for (DayAndTime dayAndTime : dayAndTimes) {

				//일정 시작시간
				int startTime = dayAndTime.startTime;

				//일정 종료시간
				int endTime = dayAndTime.endTime;

				//일정 요일, "월"~"일"
				String day = dayAndTime.day;

				//startTime 은 오전11시30분이면 1130과 같이 저장 따라서 시간을 가져올려면 나누기 100을 한다.
				int startHour = startTime / 100;
				int endHour = endTime / 100;

				//ex) 1130 - 1100
				int startMinute = startTime - startHour * 100;
				int endMinute = endTime - endHour * 100;
				//시간이 시작되는 row 의 위치
				int initRow = 1;

				//각시간당 2칸임으로 곱하기 2를 한다. 
				//분은 0분 아니면 30분 이기 문에 0분 이면 0을 아니면 1을 더한다
				int startRow = initRow + startHour * 2 + (startMinute == 0 ? 0 : 1);
				int endRow = initRow + endHour * 2 + (endMinute == 0 ? 0 : 1);


				//요일이 시작되는 col 의 위치
				int initCol = 1;

				//각 요일(월요일~일요일)에 맞게 col 변경
				int col = initCol + dayToNum.get(day);

				GridBagConstraints gbc = gbcs.get(startRow).get(col);
				gbc.gridheight = endRow - startRow;

				String btnInformStr = schedule.title;

				//font 수정
				btns.get(startRow).get(col).setFont(new Font("굴림", Font.PLAIN, 12));
				//type 에서 강의 일정은 0, 기타일정은 1
				//강의일정 이라면
				if (schedule.type == 0) {
					int height = endRow - startRow;
					if (height == 1) {
						btnInformStr = String.format("<html>%s</html>", schedule.title);
					} else if (height == 2) {
						btnInformStr = String.format("<html>%s<br/>%s</html>", schedule.title, schedule.getLocation());
					} else {
						btnInformStr = String.format("<html>%s<br/>%s<br/>%s<br/></html>", schedule.title, schedule.getLocation(), schedule.getProfessor());
					}
				} else if (schedule.type == 1) { //기타일정이라면 
					btnInformStr = String.format("<html>%s</html>", schedule.title);
				}

				//버튼 텍스트 수정 
				btns.get(startRow).get(col).setText(btnInformStr);
				btns.get(startRow).get(col).putClientProperty("scheduleItem", schedule);
				
				Color darkColor = newColor.darker();
				Color lightColor = newColor.brighter();

				btns.get(startRow).get(col).setBorder(new LineBorder(darkColor));
				btns.get(startRow).get(col).setBackground(lightColor);


				//버튼 클릭시 상세정보 표시 함수 실행
				btns.get(startRow).get(col).addActionListener(e -> {
					JButton btn = (JButton) e.getSource();
					Schedule item = (Schedule) (btn).getClientProperty("scheduleItem");
					
					String InformStr="";
					if(item.type == 0) {//강의일정
						InformStr = String.format("<html>강의명: %s<br/>장소: %s<br/>교수: %s<br/></html>", item.title, item.getLocation(), item.getProfessor());
					}else if(item.type == 1){ //기타일정
						InformStr = String.format("<html>일정명: %s</html>", item.title);
					}
					ScheduleDeleteDialog dialog = new ScheduleDeleteDialog(InformStr, e1 -> deleteBtnClick(item));
				});
			}
		}
		
		for(int i=0; i<49; i++) {
			for(int j=0; j<8; j++) {
				//btns.get(i).get(j).setBackground(UIManager.getColor ( "Panel.background" ));
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
	}
	
	//삭제 버튼을 클릭하면 호출되는 함수.
	//삭제 여부를 확인하는 팝업창을 띄운다.
	//사용자가 '확인' 을 클릭하면 일정을 시간표에서 삭제하고 allSchedule 객체에서도 일정정보를 삭제한다.
	//그 후 알람 정보도 업데이트 한다. 
	public void deleteBtnClick(Schedule scheduleItem) {
		//allSchedule 
		int dialogButton = JOptionPane.YES_NO_OPTION;
		String content = "\""+scheduleItem.title + "\"" + "를 삭제하시겠습니까?";
		int dialogResult = JOptionPane.showConfirmDialog(this, content, "일정 삭제", dialogButton);
		if(dialogResult == 0) {
			allSchedule.remove(scheduleItem);
			printSchedule();
		}
	}


	//JPanel 을 GridLayout 으로 생성한다.
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