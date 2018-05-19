package guiElements;
import javax.swing.JPanel;
import javax.swing.Timer;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class GUITimerCode extends JPanel
	{
		JLabel lblTime;
		Timer timer;
		Integer timeLeft;

		/**
		 * Create the panel.
		 */
		public GUITimerCode(int heroNum)
			{
				setLayout(new MigLayout("", "[][]", "[]"));
				
				
				ActionListener countDown = new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						timeLeft--;
//						SimpleDateFormat df = new SimpleDateFormat("ss");
						lblTime.setText("Time left: " + timeLeft);
						if (timeLeft <= 0) {
							timer.stop();
						}
					}
				};
				
				timer = new Timer(1000, countDown);
				
				JButton btnNewButton = new JButton("New button");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
//						Timer timer = new Timer(5, this);
						timer.stop();
						timeLeft = 5;
						lblTime.setText("Time left: 5");
						timer.start();
					}
				});
				add(btnNewButton, "cell 0 0");
				
				lblTime = new JLabel("Time left:");
				add(lblTime, "cell 1 0");
			}

	}
