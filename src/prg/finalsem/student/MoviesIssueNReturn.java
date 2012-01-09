package prg.finalsem.student;

import java.awt.Dimension;
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

@SuppressWarnings("serial")
public class MoviesIssueNReturn extends JInternalFrame {
	
	private Connection con;
	private String url = "jdbc:mysql://localhost:3306/musicandmovies";
	private String driver = "com.mysql.jdbc.Driver";
    private String userName = "root";
    private String passWord = "a1";
    private String querySQL = "SELECT  I.ISSUE_ID AS ID, I.STATUS,I.ISSUE_DATE AS ISSUEDATE, M.NAME AS TAKENBY, MT.DIRECTOR, MT.TITLE from MOVIES_ISSUE I, MEMBERS M, MOVIES MT where I.MEMBER_ID = M.ID and I.MOVIES_ID = MT.ID";
    private String [] status = {"OUT","IN"};
    private int idMoviesIssue;
    private String issueDate,moviesStatus;
	
	public MoviesIssueNReturn() {
		super("Movies Issue N' Return");
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
		fillMovieId();
		fillMemberId();
    }
	
	private void connect() {
    	try {
    		Class.forName(driver);
    		con  = DriverManager.getConnection(url,userName,passWord);
    	} catch(ClassNotFoundException ex) {
    		JOptionPane.showMessageDialog(panelMoviesIssue, "Class not found:  " + ex.getMessage(), "Music Issue", JOptionPane.ERROR_MESSAGE);
    	} catch(SQLException ex) {
    		JOptionPane.showMessageDialog(panelMoviesIssue, "Error while connecting database:  " + ex.getMessage(), "Music Issue", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    private void fillMovieId() {
    	String sqlQuery = "select * from MOVIES";
    	try {
    		connect();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				cmbMoviesId.addItem(rs.getString("ID"));
			}
		} catch(SQLException ex) {
			JOptionPane.showMessageDialog(panelMoviesIssue, "Error in SQL statement:  " + ex.getMessage(), "Movies Issue N' Return", JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(panelMoviesIssue, "Error in SQL statement:  " + ex.getMessage(), "Movies Issue N' Return", JOptionPane.ERROR_MESSAGE);
		}
    }
    
    private String getCode(String tableName, String targetField, String field, String value) {
    	String value1 = "";
    	try {
    		Class.forName(driver);
			Connection con = DriverManager.getConnection(url,"root","");
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
        panelMoviesIssue = new JPanel();
        lblMoviesId = new JLabel("Movies Id");
        lblMemberId = new JLabel("Member Id");
        cmbMoviesId = new JComboBox();
        cmbMemberId = new JComboBox();
        lblMoviesTitle = new JLabel("Movies Title will display here...");
        lblMemberName = new JLabel("Member Name will display here...");
        lblIssueDate = new JLabel("Issue Date");
        datePicker = new JDatePicker();
        lblStatus = new JLabel("Status");
        cmbStatus = new JComboBox(status);
        moviesIssueModelTable = new MusicMoviesTableModel(userName,passWord,querySQL);
        tblMoviesIssue = new JTable(moviesIssueModelTable);
        scrollPaneMoviesIssue = new JScrollPane(tblMoviesIssue);
        scrollPaneMoviesIssue.setViewportView(tblMoviesIssue);
        btnNew = new JButton("New");
        btnSave = new JButton("Save");
        btnDelete = new JButton("Delete");
        btnExit = new JButton("Exit");

        lblMoviesTitle.setFont(new java.awt.Font("Tahoma", 1, 11)); 
        lblMoviesTitle.setForeground(new java.awt.Color(51, 51, 255));

        lblMemberName.setFont(new java.awt.Font("Tahoma", 1, 11));
        lblMemberName.setForeground(new java.awt.Color(51, 51, 255));
        
        cmbMoviesId.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent evt) {
				final String cmbValue;
			    cmbValue = cmbMoviesId.getSelectedItem().toString();
			    lblMoviesTitle.setText(getCode("MOVIES","TITLE","ID",cmbValue));
				System.out.println(getCode("MOVIES","TITLE","ID",cmbValue));
				System.out.println(cmbValue);
			}

			@Override
			public void mouseEntered(MouseEvent evt) {
				
			}

			@Override
			public void mouseExited(MouseEvent evt) {
				
			}

			@Override
			public void mousePressed(MouseEvent evt) {
				
			}

			@Override
			public void mouseReleased(MouseEvent evt) {
				
			}
        });
        
        cmbMemberId.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				final String cmbValue;
			    cmbValue = cmbMemberId.getSelectedItem().toString();
				lblMemberName.setText(getCode("MEMBERS","NAME","ID",cmbValue));
				System.out.println(getCode("MEMBERS","NAME","ID",cmbValue));
				System.out.println(cmbValue);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
        });
        
        tblMoviesIssue.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int i = tblMoviesIssue.getSelectedRow();
				idMoviesIssue = Integer.parseInt(tblMoviesIssue.getValueAt(i, 0).toString());
				moviesStatus = tblMoviesIssue.getValueAt(i, 1).toString();
				issueDate = tblMoviesIssue.getValueAt(i, 2).toString();
				System.out.println(idMoviesIssue + moviesStatus + issueDate);
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
        
        btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnExitActionPerformed(evt);
			}
        });
        
        
        GroupLayout panelMoviesIssueLayout = new GroupLayout(panelMoviesIssue);
        panelMoviesIssue.setLayout(panelMoviesIssueLayout);
        panelMoviesIssueLayout.setHorizontalGroup(
            panelMoviesIssueLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelMoviesIssueLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMoviesIssueLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneMoviesIssue, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                    .addGroup(panelMoviesIssueLayout.createSequentialGroup()
                        .addGroup(panelMoviesIssueLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lblMoviesId)
                            .addComponent(lblMemberId)
                            .addComponent(lblStatus)
                            .addComponent(lblIssueDate))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelMoviesIssueLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbMemberId, GroupLayout.Alignment.TRAILING, 0, 91, Short.MAX_VALUE)
                            .addComponent(cmbMoviesId, GroupLayout.Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbStatus, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(datePicker))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelMoviesIssueLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lblMoviesTitle)
                            .addGroup(panelMoviesIssueLayout.createSequentialGroup()
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
        panelMoviesIssueLayout.setVerticalGroup(
            panelMoviesIssueLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelMoviesIssueLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMoviesIssueLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMoviesId)
                    .addComponent(cmbMoviesId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMoviesTitle))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMoviesIssueLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMemberId)
                    .addComponent(cmbMemberId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMemberName))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMoviesIssueLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStatus)
                    .addComponent(cmbStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNew)
                    .addComponent(btnSave)
                    .addComponent(btnDelete)
                    .addComponent(btnExit))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMoviesIssueLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIssueDate)
                    .addComponent(datePicker, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneMoviesIssue, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelMoviesIssue, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelMoviesIssue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        
        setVisible(true);
        pack();
    }
    
    private void disableFields() {
    	cmbMoviesId.setEnabled(false);
    	cmbMemberId.setEnabled(false);
    	cmbStatus.setEnabled(false);
    	lblMemberName.setEnabled(false);
    	lblMoviesTitle.setEnabled(false);
    }
    
    private void enableFields() {
    	cmbMoviesId.setEnabled(true);
    	cmbMemberId.setEnabled(true);
    	cmbStatus.setEnabled(true);
    	lblMemberName.setEnabled(true);
    	lblMoviesTitle.setEnabled(true);
    }
    
    private void btnNewActionPerformed(ActionEvent evt){
    	int ans = JOptionPane.showConfirmDialog(
    		    panelMoviesIssue,
    		    "Would you like issue and return movies?",
    		    "Movies Issue N' Return",
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
    
    private void btnSaveActionPerformed(ActionEvent ext) {
    	String datep = datePicker.toString();
    	System.out.println(datep);
    	if(cmbMoviesId.getSelectedItem().equals("") || cmbMemberId.getSelectedItem().equals("") ||
    			cmbStatus.getSelectedItem().equals("")) {
    		JOptionPane.showMessageDialog(panelMoviesIssue, "Please fill all the fields" ,"Movies Issue N' Return",JOptionPane.WARNING_MESSAGE);
    	} else {
    		moviesIssueModelTable.setEdit("insert into MOVIES_ISSUE(MOVIES_ID,MEMBER_ID,ISSUE_DATE,STATUS) values " +
    		"(" + Integer.parseInt(cmbMoviesId.getSelectedItem().toString()) + ","+ Integer.parseInt(cmbMemberId.getSelectedItem().toString()) +",'" + datep + "','" +
    		cmbStatus.getSelectedItem() + "')");
    		JOptionPane.showMessageDialog(panelMoviesIssue, "Successfully inserted...", "Movies Issue N' Return", JOptionPane.INFORMATION_MESSAGE);
    		idMoviesIssue = 0;
    		issueDate = null;
    		moviesStatus = null;
    		try {
    			moviesIssueModelTable.setQuery(querySQL);
    		} catch(SQLException ex) {
    			JOptionPane.showMessageDialog(panelMoviesIssue, "Error while inserting data:  " + ex.getMessage(), "SQL Statement Error", JOptionPane.ERROR_MESSAGE);
    		} catch(Exception ex) {
    			JOptionPane.showMessageDialog(panelMoviesIssue, "Error while inserting data:  " + ex.getMessage(), "General Error", JOptionPane.ERROR_MESSAGE);
    		}
		    disableFields();
		    btnSave.setEnabled(false);
		    btnNew.setEnabled(true);
    	}
    }
    
    private void btnDeleteActionPerformed(ActionEvent evt) {
    	try {
    		if(moviesStatus.equals(null) || issueDate.equals(null)) {
    			JOptionPane.showMessageDialog(panelMoviesIssue, "Select the movies issue id to delete from table...","Movies Issue N' Return",JOptionPane.ERROR_MESSAGE);
    		} else {
    			String deleteSQL = "delete from MOVIES_ISSUE where ISSUE_ID='" + idMoviesIssue + "'";
    			moviesIssueModelTable.setEdit(deleteSQL);
	    		JOptionPane.showMessageDialog(panelMoviesIssue, "Successfully deleted...","Movies Issue N' Return",JOptionPane.INFORMATION_MESSAGE);
	    		idMoviesIssue = 0;
	    		issueDate = null;
	    		moviesStatus = null;
	    		moviesIssueModelTable.setQuery(querySQL);
    		}
    	} catch(SQLException ex) {
    		JOptionPane.showMessageDialog(panelMoviesIssue, "SQL Statement Error: " + ex.getMessage(),"SQL Statement Error",JOptionPane.ERROR_MESSAGE);
    	} catch(Exception ex) {
    		JOptionPane.showMessageDialog(panelMoviesIssue, "Select the member id to delete from table: " + ex.getMessage(),"General Error",JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    private void btnExitActionPerformed(ActionEvent evt) {
    	try {
			setClosed(true);
		} catch (PropertyVetoException e) {
			JOptionPane.showMessageDialog(panelMoviesIssue, "Movies Issue N' Return form error: " + e.getMessage(), "Movies Issue N' Return", JOptionPane.ERROR_MESSAGE);
		}
    }

    private JButton btnDelete;
    private JButton btnExit;
    private JButton btnNew;
    private JButton btnSave;
    private JComboBox cmbMemberId;
    private JComboBox cmbMoviesId;
    private JComboBox cmbStatus;
    private JLabel lblIssueDate;
    private JLabel lblMemberId;
    private JLabel lblMemberName;
    private JLabel lblMoviesId;
    private JLabel lblMoviesTitle;
    private JLabel lblStatus;
    private JPanel panelMoviesIssue;
    private JScrollPane scrollPaneMoviesIssue;
    private JTable tblMoviesIssue;
    private JDatePicker datePicker;
    private MusicMoviesTableModel moviesIssueModelTable;

}
