package prg.finalsem.student;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;

import net.sourceforge.jdatepicker.JDatePicker;

/**
 *
 * @author Saroj Maharjan
 */
@SuppressWarnings("serial")
public class MusicIssueNReturn extends JInternalFrame {
	
	private Connection con;
	private String url = "jdbc:mysql://localhost:3306/musicandmovies";
	private String driver = "com.mysql.jdbc.Driver";
    private String userName = "root";
    private String passWord = "a1";
    private String querySQL = "SELECT  I.ISSUE_ID AS ID, I.STATUS,I.ISSUE_DATE AS ISSUEDATE, M.NAME AS TAKENBY, MT.ARTIST, MT.ALBUM from MUSIC_ISSUE I, MEMBERS M, MUSIC MT where I.MEMBER_ID = M.ID and I.MUSIC_ID = MT.ID";
    private String [] status = {"OUT","IN"};
    private int idMusicIssue;
    private String issueDate,musicStatus;
    
    public MusicIssueNReturn() {
    	super("Music Issue N' Return");
    	JDialog.setDefaultLookAndFeelDecorated(true);
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setBounds((screenSize.width-600)/2, (screenSize.height-400)/2, 600, 400);
        try {
			initComponents();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		disableFields();
        btnNew.setEnabled(true);
        btnSave.setEnabled(false);
        fillMusicId();
        fillMemberId();
    }
    
    private void connect() {
    	try {
    		Class.forName(driver);
    		con  = DriverManager.getConnection(url,userName,passWord);
    	} catch(ClassNotFoundException ex) {
    		JOptionPane.showMessageDialog(panelMusicIssue, "Class not found:  " + ex.getMessage(), "Music Issue", JOptionPane.ERROR_MESSAGE);
    	} catch(SQLException ex) {
    		JOptionPane.showMessageDialog(panelMusicIssue, "Error while connecting database:  " + ex.getMessage(), "Music Issue", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    private void fillMusicId() {
    	String sqlQuery = "select * from MUSIC";
    	try {
    		connect();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				cmbMusicId.addItem(rs.getString("ID"));
			}
		} catch(SQLException ex) {
			JOptionPane.showMessageDialog(panelMusicIssue, "Error in SQL statement:  " + ex.getMessage(), "Music Issue", JOptionPane.ERROR_MESSAGE);
		}
    }
    
    private void fillMemberId(){
    	String sqlQuery = "select * from MEMBERS";
    	try {
    		connect();
			Statement st = con.createStatement();
			final ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				cmbMemberId.addItem(rs.getString("ID"));
			}
		} catch(SQLException ex) {
			JOptionPane.showMessageDialog(panelMusicIssue, "Error in SQL statement:  " + ex.getMessage(), "Music Issue", JOptionPane.ERROR_MESSAGE);
		}
    }
    
    private String getCode(String tableName, String targetField, String field, String value) {
    	String value1 = "";
    	try {
    		Class.forName(driver);
			Connection con = DriverManager.getConnection(url,"root","a1");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("Select " + targetField + " from " + tableName + " where " + field + " ='" + value + "'");
			while(rs.next()) {
				if(!rs.equals(null)) {
					value1 = rs.getString(1);
				}
			}
		} catch(SQLException ex) {
			ex.printStackTrace();
		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return value1;
    }

    private void initComponents() throws Exception{
    	panelMusicIssue = new JPanel();
        lblMusicId = new JLabel("Music Id");
        lblMemberId = new JLabel("Member Id");
        cmbMusicId = new JComboBox();
        cmbMemberId = new JComboBox();
        lblMusicAlbum = new JLabel("Music Album will display here...");
        lblMemberName = new JLabel("Member Name will display here...");
        lblIssueDate = new JLabel("Issue Date");
        lblStatus = new JLabel("Status");
        cmbStatus = new JComboBox(status);
        musicIssueModelTable = new MusicMoviesTableModel(userName,passWord,querySQL);
        tblMusicIssue = new JTable(musicIssueModelTable);
        scrollPaneMusicIssue = new JScrollPane(tblMusicIssue);
        scrollPaneMusicIssue.setViewportView(tblMusicIssue);
        datePicker = new JDatePicker();
        btnNew = new JButton("New");
        btnSave = new JButton("Save");
        btnDelete = new JButton("Delete");
        btnExit = new JButton("Exit");
        
        lblMusicAlbum.setFont(new Font("Tahoma", 1, 11)); 
        lblMusicAlbum.setForeground(new Color(51, 51, 255));

        lblMemberName.setFont(new Font("Tahoma", 1, 11)); 
        lblMemberName.setForeground(new Color(51, 51, 255));
        
        cmbMusicId.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				final String cmbValue;
			    cmbValue = cmbMusicId.getSelectedItem().toString();
			    lblMusicAlbum.setText(getCode("MUSIC","ALBUM","ID",cmbValue));
				System.out.println(getCode("MUSIC","ALBUM","ID",cmbValue));
				System.out.println(cmbValue);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
        });
        
        cmbMemberId.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				final String cmbValue;
			    cmbValue = cmbMemberId.getSelectedItem().toString();
				lblMemberName.setText(getCode("MEMBERS","NAME","ID",cmbValue));
				System.out.println(getCode("MEMBERS","NAME","ID",cmbValue));
				System.out.println(cmbValue);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
        });
        
        tblMusicIssue.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int i = tblMusicIssue.getSelectedRow();
				idMusicIssue = Integer.parseInt(tblMusicIssue.getValueAt(i, 0).toString());
				musicStatus = tblMusicIssue.getValueAt(i, 1).toString();
				issueDate = tblMusicIssue.getValueAt(i, 2).toString();
				System.out.println(idMusicIssue + musicStatus + issueDate);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
        });
        
        btnNew.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnNewActionPerformed(evt);
			}
        });
        
        btnSave.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnSaveActionPerformed(evt);
			}
        });
        
        btnDelete.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnDeleteActionPerformed(evt);
			}
        });
        
        btnExit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnExitActionPerformed(evt);
			}
        });
        
        GroupLayout panelMusicIssueLayout = new GroupLayout(panelMusicIssue);
        panelMusicIssue.setLayout(panelMusicIssueLayout);
        panelMusicIssueLayout.setHorizontalGroup(
            panelMusicIssueLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelMusicIssueLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMusicIssueLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneMusicIssue, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                    .addGroup(panelMusicIssueLayout.createSequentialGroup()
                        .addGroup(panelMusicIssueLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lblMusicId)
                            .addComponent(lblMemberId)
                            .addComponent(lblStatus)
                            .addComponent(lblIssueDate))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelMusicIssueLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbMemberId, GroupLayout.Alignment.TRAILING, 0, 91, Short.MAX_VALUE)
                            .addComponent(cmbMusicId, GroupLayout.Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbStatus, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(datePicker))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelMusicIssueLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lblMusicAlbum)
                            .addGroup(panelMusicIssueLayout.createSequentialGroup()
                                .addComponent(btnNew, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete)
                                .addGap(6, 6, 6)
                                .addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblMemberName))))
                .addContainerGap())
        );
        panelMusicIssueLayout.setVerticalGroup(
            panelMusicIssueLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelMusicIssueLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMusicIssueLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMusicId)
                    .addComponent(cmbMusicId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMusicAlbum))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMusicIssueLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMemberId)
                    .addComponent(cmbMemberId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMemberName))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMusicIssueLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStatus)
                    .addComponent(cmbStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNew)
                    .addComponent(btnSave)
                    .addComponent(btnDelete)
                    .addComponent(btnExit))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMusicIssueLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIssueDate)
                    .addComponent(datePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneMusicIssue, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelMusicIssue, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelMusicIssue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        pack();
        setVisible(true);
    }
    
    private void disableFields() {
    	cmbMusicId.setEnabled(false);
    	cmbMemberId.setEnabled(false);
    	cmbStatus.setEnabled(false);
    	lblMemberName.setEnabled(false);
    	lblMusicAlbum.setEnabled(false);
    }
    
    private void enableFields() {
    	cmbMusicId.setEnabled(true);
    	cmbMemberId.setEnabled(true);
    	cmbStatus.setEnabled(true);
    	lblMemberName.setEnabled(true);
    	lblMusicAlbum.setEnabled(true);
    }
    
    private void btnNewActionPerformed(ActionEvent evt){
    	int ans = JOptionPane.showConfirmDialog(
    		    panelMusicIssue,
    		    "Would you like issue and return music?",
    		    "Music Issue N' Return",
    		    JOptionPane.YES_NO_OPTION);
    	if (ans == JOptionPane.YES_OPTION) {
			btnDelete.setEnabled(false);
			btnNew.setEnabled(true);
			btnSave.setEnabled(true);
			enableFields();
		} else if(ans == JOptionPane.NO_OPTION) {
		    btnSave.setEnabled(false);
		    btnDelete.setEnabled(true);
		    btnNew.setEnabled(true);
		    disableFields();
		} else {
			btnNew.setEnabled(true);
			btnSave.setEnabled(false);
			btnDelete.setEnabled(true);
			disableFields();
		}
		btnNew.setEnabled(true);
		btnDelete.setEnabled(true);
    }
    
    private void btnSaveActionPerformed(ActionEvent evt) {
    	String datep = datePicker.toString();
    	System.out.println(datep);
    	if(cmbMusicId.getSelectedItem().equals("") || cmbMemberId.getSelectedItem().equals("") ||
    			cmbStatus.getSelectedItem().equals("")) {
    		JOptionPane.showMessageDialog(panelMusicIssue, "Please fill all the fields" ,"Music Issue N' Return",JOptionPane.WARNING_MESSAGE);
    	} else {
    		musicIssueModelTable.setEdit("insert into MUSIC_ISSUE(MUSIC_ID,MEMBER_ID,ISSUE_DATE,STATUS) values " +
    		"(" + Integer.parseInt(cmbMusicId.getSelectedItem().toString()) + ","+ Integer.parseInt(cmbMemberId.getSelectedItem().toString()) +",'" + datep + "','" +
    		cmbStatus.getSelectedItem() + "')");
    		JOptionPane.showMessageDialog(panelMusicIssue, "Successfully inserted...", "Music Issue N' Return", JOptionPane.INFORMATION_MESSAGE);
    		idMusicIssue = 0;
    		issueDate = null;
    		musicStatus = null;
    		try {
    			musicIssueModelTable.setQuery(querySQL);
    		} catch(SQLException ex) {
    			JOptionPane.showMessageDialog(panelMusicIssue, "Error while inserting data:  " + ex.getMessage(), "SQL Statement Error", JOptionPane.ERROR_MESSAGE);
    		} catch(Exception ex) {
    			JOptionPane.showMessageDialog(panelMusicIssue, "Error while inserting data:  " + ex.getMessage(), "General Error", JOptionPane.ERROR_MESSAGE);
    		}
		    disableFields();
		    btnSave.setEnabled(false);
		    btnNew.setEnabled(true);
    	}
    }
    
    private void btnDeleteActionPerformed(ActionEvent evt) {
    	try {
    		if(musicStatus.equals(null) || issueDate.equals(null)) {
    			JOptionPane.showMessageDialog(panelMusicIssue, "Select the music issue id to delete from table...","Music Issue N' Return",JOptionPane.ERROR_MESSAGE);
    		} else {
    			String deleteSQL = "delete from MUSIC_ISSUE where ISSUE_ID='" + idMusicIssue + "'";
    			musicIssueModelTable.setEdit(deleteSQL);
	    		JOptionPane.showMessageDialog(panelMusicIssue, "Successfully deleted...","Music Issue N' Return",JOptionPane.INFORMATION_MESSAGE);
	    		idMusicIssue = 0;
	    		issueDate = null;
	    		musicStatus = null;
	    		musicIssueModelTable.setQuery(querySQL);
    		}
    	} catch(SQLException ex) {
    		JOptionPane.showMessageDialog(panelMusicIssue, "SQL Statement Error: " + ex.getMessage(),"SQL Statement Error",JOptionPane.ERROR_MESSAGE);
    	} catch(Exception ex) {
    		JOptionPane.showMessageDialog(panelMusicIssue, "Select the member id to delete from table: " + ex.getMessage(),"General Error",JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    private void btnExitActionPerformed(ActionEvent evt) {
    	try {
			setClosed(true);
		} catch (PropertyVetoException e) {
			JOptionPane.showMessageDialog(panelMusicIssue, "Music Issue N' Return form error: " + e.getMessage(), "Music Issue N' Return", JOptionPane.ERROR_MESSAGE);
		}
    }

    private JButton btnDelete;
    private JButton btnExit;
    private JButton btnNew;
    private JButton btnSave;
    private JComboBox cmbMemberId;
    private JComboBox cmbMusicId;
    private JComboBox cmbStatus;
    private JLabel lblIssueDate;
    private JLabel lblMemberId;
    private JLabel lblMemberName;
    private JLabel lblMusicId;
    private JLabel lblMusicAlbum;
    private JLabel lblStatus;
    private JPanel panelMusicIssue;
    private JScrollPane scrollPaneMusicIssue;
    private JTable tblMusicIssue;
    private JDatePicker datePicker;
    private MusicMoviesTableModel musicIssueModelTable;
}

