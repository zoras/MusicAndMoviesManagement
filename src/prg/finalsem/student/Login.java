package prg.finalsem.student;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;


/**
 *
 * @author Saroj Maharjan
 */
@SuppressWarnings("serial")
public class Login extends JFrame {
	
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/musicandmovies";
    private String userName = "root";
    private String passWord = "a1";
    private Connection con;
    
	public Login() {
		JDialog.setDefaultLookAndFeelDecorated(true);
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setBounds((screenSize.width-300)/2, (screenSize.height-200)/2, 300, 200);
        initComponents();
        try {
        	Class.forName(driver);
        	con = DriverManager.getConnection(url,userName,passWord);
        } catch(ClassNotFoundException ex) {
        	JOptionPane.showMessageDialog(panelLogin, "Class Error: " +  ex.getMessage(),"Class Not Found",JOptionPane.ERROR_MESSAGE);
        } catch(SQLException ex) {
        	JOptionPane.showMessageDialog(panelLogin, "Connection Error: " + ex.getMessage(),"Connection",JOptionPane.ERROR_MESSAGE);
        	System.exit(0);
        }
    }

    private void initComponents() {
    	setTitle("Login");
    	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        panelLogin = new JPanel();
        lblUserName = new JLabel("UserName");
        txtUserName = new JTextField(10);
        lblPassword = new JLabel("Password");
        txtPassword = new JPasswordField(10);
        btnLogin = new JButton("Login");
        btnCancel = new JButton("Cancel");
        getRootPane().setDefaultButton(btnLogin);
        
        btnLogin.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        
        btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnCancelActionPerformed(evt);
			}
        });

        GroupLayout panelLoginLayout = new GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                        .addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLoginLayout.createSequentialGroup()
                        .addGroup(panelLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lblUserName)
                            .addComponent(lblPassword))
                        .addGap(10, 10, 10)
                        .addGroup(panelLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(txtPassword, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(txtUserName, GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLoginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblUserName)
                    .addComponent(txtUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelLoginLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPassword))
                .addGap(12, 12, 12)
                .addGroup(panelLoginLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(btnLogin))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelLogin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelLogin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setVisible(false);
        setResizable(false);
    }

    @SuppressWarnings("deprecation")
	private void btnLoginActionPerformed(ActionEvent evt) {
    	String u = txtUserName.getText().trim();
		String p = txtPassword.getText().trim();
    	String querySQL = "select * from USERS where USER_NAME = '" + u + "'and USER_PASSWORD = '" + p +"'";
        try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(querySQL);
			if(rs.next()) {
				new MDIFrame().setVisible(true);
				setVisible(false);
				con.close();
			} else  {
				JOptionPane.showMessageDialog(panelLogin, "Invalid Username and password....", "Login Error",JOptionPane.ERROR_MESSAGE);
				txtUserName.setText("");
				txtPassword.setText("");
				txtUserName.requestFocus();
			}
		} catch (SQLException ex) {
			System.out.println(ex.getStackTrace());
			JOptionPane.showMessageDialog(panelLogin, "SQL Error: " +  ex.getMessage(),"Login Error",JOptionPane.ERROR_MESSAGE);
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
    }
    
    private void btnCancelActionPerformed(ActionEvent evt) {
    	if(con == null) {
    		JOptionPane.showMessageDialog(panelLogin, "Closing Error: ","Login",JOptionPane.ERROR_MESSAGE);
    	} else  {
    		try {
    			con.close();
    		} catch (SQLException ex) {
    			JOptionPane.showMessageDialog(panelLogin, "Closing Error: " +  ex.getMessage(),"Connection",JOptionPane.ERROR_MESSAGE);
    		}
    		System.exit(0);
    	}
    }
    
    private JButton btnCancel;
    private JButton btnLogin;
    private JLabel lblPassword;
    private JLabel lblUserName;
    private JPanel panelLogin;
    private JPasswordField txtPassword;
    private JTextField txtUserName;
}