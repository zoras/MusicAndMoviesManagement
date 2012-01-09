package prg.finalsem.student;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class Splash extends JWindow {
	
	public Splash() {
      try {
          splashInit();
      } catch(Exception exception) {
        exception.printStackTrace();
      }
    }

  private void splashInit() throws Exception {
    loadTimer = new Timer(1000,new ActionListener() {
        public void actionPerformed(ActionEvent e) {
             loadTimer_actionPerformed();
        }
    });
    loadTimer.start();
    this.getContentPane().setLayout(borderLayout1);
    this.setSize(490,300);

    Image.setIcon(new ImageIcon(getClass().getResource("mm.gif")));
    this.getContentPane().add(Image, java.awt.BorderLayout.CENTER);
    frame = new Login();
  }

  private void loadTimer_actionPerformed(){
      if(loadTimerCounter > 1) {
          loadTimer.stop();
          this.setVisible(false);
          frame.setVisible(true);
      } else {
          loadTimerCounter++;
      }
  }

  public static void main(String args[]) {
    JFrame.setDefaultLookAndFeelDecorated(true);
    
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Splash.class.getName()).log(Level.SEVERE, null, ex);
               }catch (ClassNotFoundException e1) {
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
                }
		
    Splash splash = new Splash();
    splash.setSize(621, 378);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    splash.setBounds((screenSize.width-621)/2, (screenSize.height-378)/2, 621, 378);
    splash.setVisible(true);
  }

  private int loadTimerCounter;
  @SuppressWarnings("unused")
private final static Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
  private Timer loadTimer;
  private Login frame;
  private JLabel Image = new JLabel();
  private BorderLayout borderLayout1 = new BorderLayout();
}
