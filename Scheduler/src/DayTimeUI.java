import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Font;

public class DayTimeUI extends JPanel{
	private JComboBox day;
	private JComboBox startTime;
	private JComboBox endTime;
	
	
	//����, ���� �ð�, ���� �ð��� ������ �� �ִ� �޺��ڽ��� �����ؼ� panel�� ���δ�.
	public DayTimeUI() {
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);
		setBackground(UIManager.getColor ( "Panel.background" ));
		gridBagLayout.columnWidths = new int[] {40, 10, 40, 10, 40, 5, 25};
		
		String days[]={"������", "ȭ����", "������", "�����", "�ݿ���", "�����", "�Ͽ���"};    
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
		endTime = new JComboBox(times);

		startTime.putClientProperty("pre", startTime.getSelectedItem());
		startTime.putClientProperty("endTime", endTime);
		
		endTime.putClientProperty("pre", endTime.getSelectedItem());
		endTime.putClientProperty("startTime", startTime);
		
		//���۽ð��� ����ð� ���� ũ�ų� ���� ��� �ǵ����� �Լ� �߰�
		startTime.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox startComboBox = (JComboBox) e.getSource();
                int selectedStartTime = Integer.valueOf(startComboBox.getSelectedItem().toString().replace(":", ""));
                
                JComboBox endComboBox = (JComboBox)startComboBox.getClientProperty("endTime");
        		int selectedEndTime = Integer.valueOf(endComboBox.getSelectedItem().toString().replace(":", ""));

        		if(selectedStartTime >= selectedEndTime) {
        			JOptionPane.showMessageDialog(null, "���۽ð��� ����ð� ���� ũ�ų� ���� �� �����ϴ�.", "�����߰� ���� �޼���", JOptionPane.WARNING_MESSAGE);
        			Object pre = startComboBox.getClientProperty("pre");
        			startComboBox.setSelectedItem(pre);
        		}else {
        			Object pre = startComboBox.getSelectedItem();
        			startComboBox.putClientProperty("pre", pre);
        		}
            }
        });
		
		//����ð��� ���۽ð� ���� �۰ų� ���� ��� �ǵ����� �Լ� �߰�
		endTime.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JComboBox endComboBox = (JComboBox) e.getSource();
                int selectedEndTime = Integer.valueOf(endComboBox.getSelectedItem().toString().replace(":", ""));
                
                JComboBox startComboBox = (JComboBox)endComboBox.getClientProperty("startTime");
        		int selectedStartTime = Integer.valueOf(startComboBox.getSelectedItem().toString().replace(":", ""));

        		if(selectedStartTime >= selectedEndTime) {
        			JOptionPane.showMessageDialog(null, "����ð��� ���۽ð� ���� �۰ų� ���� �� �����ϴ�.", "�����߰� ���� �޼���", JOptionPane.WARNING_MESSAGE);
        			Object pre = endComboBox.getClientProperty("pre");
        			endComboBox.setSelectedItem(pre);
        		}else {
        			Object pre = endComboBox.getSelectedItem();
        			endComboBox.putClientProperty("pre", pre);
        		}
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
		
		int selectedStartTime = Integer.valueOf(startTime.getSelectedItem().toString().replace(":", ""));
		int selectedEndTime = Integer.valueOf(endTime.getSelectedItem().toString().replace(":", ""));
		
		return new DayAndTime(day.getSelectedItem().toString(), selectedStartTime, selectedEndTime);
	}
}
