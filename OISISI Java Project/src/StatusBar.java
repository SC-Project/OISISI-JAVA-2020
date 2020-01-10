import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

public class StatusBar extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public StatusBar(){
		
		//screen dimensions
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int scrW = (int)screenSize.getWidth() * 3 / 4;
		
		setBorder(new BevelBorder(BevelBorder.LOWERED));
		setPreferredSize(new Dimension(scrW, 30));
		
		setLayout(new BorderLayout());
		
		// Left Side 
		JLabel statusName = new JLabel("Studentska služba");
		statusName.setHorizontalAlignment(SwingConstants.LEFT);
		add(statusName, BorderLayout.WEST);
		
		// Right Side
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.RIGHT));	
		
		// Time
		JLabel statusVreme = new JLabel("");
		ActionListener updateClockAction = new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  String pattern = "HH.mm.ss";
				  SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			      statusVreme.setText(simpleDateFormat.format(new Date())); 
			  }
		};
		p.add(statusVreme);
		Timer t = new Timer(1000, updateClockAction);
		t.start();
		
		
		// Date
		String pattern = "dd.MM.yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		JLabel statusDate = new JLabel(date);
		p.add(statusDate);
		
		add(p, BorderLayout.EAST);
			
	}
}
