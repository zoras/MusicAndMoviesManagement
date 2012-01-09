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
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import com.lamatek.swingextras.JNumericField;

/**
 *
 * @author Saroj Maharjan
 */
@SuppressWarnings("serial")
public class MoviesSetup extends JInternalFrame {
	
	private Connection con;
	private String url = "jdbc:mysql://localhost:3306/musicandmovies";
	private String driver = "com.mysql.jdbc.Driver";
    private String userName = "root";
    private String passWord = "a1";
    private String querySQL = "select * from MOVIES";
    private String idMovies,title,actor,actress,year,director,language,type,disc;
    
    public MoviesSetup() {
    	super("Movies Setup");
    	JDialog.setDefaultLookAndFeelDecorated(true);
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setBounds((screenSize.width-800)/2, (screenSize.height-600)/2, 800, 600);
        try {
			initComponents();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(moviesPanel, "initialComponents problem:  " + ex.getMessage(), "Movies Setup", JOptionPane.ERROR_MESSAGE);
		}
		disableFields();
        btnDelete.setEnabled(true);
        btnNew.setEnabled(true);
        btnSave.setEnabled(false);
        btnUpdate.setEnabled(false);
        fillMoviesDiscComboBox();
        fillMoviesTypeComboBox();
        fillMoviesLanguageComboBox();
    }
    
    private void connect() {
    	try {
    		Class.forName(driver);
    		con  = DriverManager.getConnection(url,userName,passWord);
    	} catch(ClassNotFoundException ex) {
    		JOptionPane.showMessageDialog(moviesPanel, "Class not found:  " + ex.getMessage(), "Movies Setup", JOptionPane.ERROR_MESSAGE);
    	} catch(SQLException ex) {
    		JOptionPane.showMessageDialog(moviesPanel, "Error while connecting database:  " + ex.getMessage(), "Movies Setup", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    private void fillMoviesDiscComboBox() {
    	String sqlQuery = "select * from DISC";
    	try {
    		connect();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				cmbDisc.addItem(rs.getString("DISC"));
			}
		} catch(SQLException ex) {
			JOptionPane.showMessageDialog(moviesPanel, "Error in SQL statement:  " + ex.getMessage(), "Movies Setup", JOptionPane.ERROR_MESSAGE);
		}
    }
    
    private void fillMoviesTypeComboBox() {
    	String sqlQuery = "select * from MOVIES_TYPE";
    	try {
    		connect();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				cmbType.addItem(rs.getString("TYPE"));
			}
		} catch(SQLException ex) {
			JOptionPane.showMessageDialog(moviesPanel, "Error in SQL statement:  " + ex.getMessage(), "Movies Setup", JOptionPane.ERROR_MESSAGE);
		}
    }
    
    private void fillMoviesLanguageComboBox() {
    	String sqlQuery = "select * from LANGUAGE";
    	try {
    		connect();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				cmbLanguage.addItem(rs.getString("LANGUAGE"));
			}
		} catch(SQLException ex) {
			JOptionPane.showMessageDialog(moviesPanel, "Error in SQL statement:  " + ex.getMessage(), "Movies Setup", JOptionPane.ERROR_MESSAGE);
		}
    }

    private void initComponents() throws Exception {
        moviesPanel = new JPanel();
        lblTitle = new JLabel("Title");
        txtTitle = new JTextField();
        lblActor = new JLabel("Actor");
        txtActor = new JTextField();
        lblActress = new JLabel("Actress");
        txtActress = new JTextField();
        lblDirector = new JLabel("Director");
        txtDirector = new JTextField();
        lblYear = new JLabel("Year");
        txtYear = new JNumericField();
        lblLanguage = new JLabel("Lanuage");
        cmbLanguage = new JComboBox();
        lblType = new JLabel("Type");
        cmbType = new JComboBox();
        lblDisc = new JLabel("Disc");
        cmbDisc = new JComboBox();
        moviesSetupModel = new MusicMoviesTableModel(userName,passWord,querySQL);
        tblMovies = new JTable(moviesSetupModel);
        scrollPaneMovies = new JScrollPane(tblMovies);
        scrollPaneMovies.setViewportView(tblMovies);
        btnNew = new JButton("New");
        btnSave = new JButton("Save");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnRefresh = new JButton("Refresh");
        btnExit = new JButton("Exit");
        
        tblMovies.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int i = tblMovies.getSelectedRow();
				idMovies = String.valueOf(tblMovies.getValueAt(i, 0));
				title = String.valueOf(tblMovies.getValueAt(i, 1));
				actor = String.valueOf(tblMovies.getValueAt(i, 2));
				actress = String.valueOf(tblMovies.getValueAt(i, 3));
				type = String.valueOf(tblMovies.getValueAt(i, 4));
				director = String.valueOf(tblMovies.getValueAt(i, 5));
				language = String.valueOf(tblMovies.getValueAt(i, 6));
				year = String.valueOf(tblMovies.getValueAt(i, 7));
				disc = String.valueOf(tblMovies.getValueAt(i, 8));
				txtTitle.setText(title);
				txtActor.setText(actor);
				txtActress.setText(actress);
				txtYear.setText(year);
				txtDirector.setText(director);
				cmbLanguage.setSelectedItem(language);
				cmbType.setSelectedItem(type);
				cmbDisc.setSelectedItem(disc);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
			}
        });
        
        btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnNewActionPerformed(evt);
			}
        });
        
        btnSave.addActionListener(new ActionListener() {
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
        
        btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnUpdateActionPerformed(evt);
			}
        });
        
        btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnExitActionPerformed(evt);
			}
        });
        
        GroupLayout moviesPanelLayout = new GroupLayout(moviesPanel);
        moviesPanel.setLayout(moviesPanelLayout);
        moviesPanelLayout.setHorizontalGroup(
            moviesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(moviesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(moviesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneMovies, GroupLayout.DEFAULT_SIZE, 825, Short.MAX_VALUE)
                    .addGroup(moviesPanelLayout.createSequentialGroup()
                        .addGroup(moviesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitle)
                            .addComponent(lblActor)
                            .addComponent(lblActress)
                            .addComponent(lblDirector)
                            .addComponent(lblYear)
                            .addComponent(lblLanguage)
                            .addComponent(lblType)
                            .addComponent(lblDisc))
                        .addGap(21, 21, 21)
                        .addGroup(moviesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtYear)
                            .addComponent(txtActress, GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbDisc, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbType, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbLanguage, GroupLayout.Alignment.TRAILING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDirector)
                            .addComponent(txtActor)
                            .addComponent(txtTitle, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnNew)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(moviesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(moviesPanelLayout.createSequentialGroup()
                                .addComponent(btnSave)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate))
                            .addGroup(moviesPanelLayout.createSequentialGroup()
                                .addComponent(btnRefresh)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 256, Short.MAX_VALUE)))
                .addContainerGap())
        );
        moviesPanelLayout.setVerticalGroup(
            moviesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(moviesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(moviesPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle)
                    .addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnNew))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(moviesPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txtActor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblActor)
                    .addComponent(btnRefresh)
                    .addComponent(btnExit))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(moviesPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblActress)
                    .addComponent(txtActress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(moviesPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDirector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDirector))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(moviesPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblYear)
                    .addComponent(txtYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(moviesPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbLanguage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLanguage))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(moviesPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblType))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(moviesPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbDisc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDisc))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneMovies, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(moviesPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(moviesPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setVisible(true);
    }
    
    private void clearAll() {
    	txtTitle.setText("");
    	txtActor.setText("");
    	txtActress.setText("");
    	txtDirector.setText("");
    	txtYear.setText("");
    	cmbDisc.setSelectedItem("");
    	cmbLanguage.setSelectedItem("");;
    	cmbType.setSelectedItem("");
    }
    
    private void disableFields() {
    	txtTitle.setEditable(false);
    	txtActor.setEditable(false);
    	txtActress.setEditable(false);
    	txtYear.setEditable(false);
    	txtDirector.setEditable(false);
    	cmbDisc.setEnabled(false);
    	cmbLanguage.setEnabled(false);
    	cmbType.setEnabled(false);
    }
    
    private void enableFields() {
    	txtTitle.setEditable(true);
    	txtActor.setEditable(true);
    	txtActress.setEditable(true);
    	txtYear.setEditable(true);
    	txtDirector.setEditable(true);
    	cmbDisc.setEnabled(true);
    	cmbLanguage.setEnabled(true);
    	cmbType.setEnabled(true);
    }
    
    private void btnNewActionPerformed(ActionEvent evt) {
    	clearAll();
    	Object[] options = {"Enter New Movies", "Update Old Movies", "Cancel"};
    	int ans = JOptionPane.showOptionDialog(moviesPanel,
    				"Would you like to enter new movies or update old movies?",
    				"Save and Update",
    				JOptionPane.YES_NO_CANCEL_OPTION,
    				JOptionPane.QUESTION_MESSAGE,
    				null,
    				options,
    				options[2]);
		if (ans == JOptionPane.YES_OPTION) {
			btnUpdate.setEnabled(false);
			btnDelete.setEnabled(false);
			btnNew.setEnabled(false);
			btnSave.setEnabled(true);
			enableFields();
		} else if(ans == JOptionPane.NO_OPTION) {
		    btnSave.setEnabled(false);
		    btnUpdate.setEnabled(true);
		    btnDelete.setEnabled(false);
		    btnNew.setEnabled(false);
		    enableFields();
		} else {
			btnNew.setEnabled(true);
			btnSave.setEnabled(false);
			btnUpdate.setEnabled(false);
			btnDelete.setEnabled(true);
			disableFields();
		}
		btnNew.setEnabled(true);
		btnDelete.setEnabled(true);
    }
    
    private void btnSaveActionPerformed(ActionEvent evt) {
    	if(txtTitle.getText().equals("") || txtActor.getText().equals("") ||
 	       txtActress.getText().equals("") || txtYear.getText().equals("") ||
 	       cmbDisc.getSelectedItem().equals("")  || txtDirector.equals("") ||
 	       cmbLanguage.getSelectedItem().equals("")  || cmbType.getSelectedItem().equals("")) {
 	       JOptionPane.showMessageDialog(moviesPanel, "Please fill all the fields" ,"Movies Setup",JOptionPane.WARNING_MESSAGE);
    	} else {
	 		moviesSetupModel.setEdit("insert into MOVIES(TITLE,ACTOR,ACTRESS,TYPE,DIRECTOR,LANGUAGE,YEAR,DISC) values " +
	 		"('" + txtTitle.getText() + "','"+ txtActor.getText() +"','" + txtActress.getText() + "','" +
	 		cmbType.getSelectedItem() + "','" + txtDirector.getText() + "','" + cmbLanguage.getSelectedItem() + "','" +
	 		txtYear.getInteger() + "','" + cmbDisc.getSelectedItem() + "')");
	 		JOptionPane.showMessageDialog(moviesPanel, "Successfully inserted...", "Movies Setup", JOptionPane.INFORMATION_MESSAGE);
	 		clearAll();
	 		idMovies = null;
	 		title = null;
	 		actor = null;
	 		actress = null;
	 		year = null;
	 		director = null;
	 		type = null;
	 		language = null;
	 		try {
	 			moviesSetupModel.setQuery(querySQL);
	 		} catch(SQLException ex) {
	 			JOptionPane.showMessageDialog(moviesPanel, "Error while inserting data:  " + ex.getMessage(), "SQL Statement Error", JOptionPane.ERROR_MESSAGE);
	 		} catch(Exception ex) {
	 			JOptionPane.showMessageDialog(moviesPanel, "Error while inserting data:  " + ex.getMessage(), "General Error", JOptionPane.ERROR_MESSAGE);
		    }
		    clearAll();
		    disableFields();
		    btnSave.setEnabled(false);
		    btnNew.setEnabled(true);
    	}
    }
    
    private void btnDeleteActionPerformed(ActionEvent evt) {
    	try {
    		if(idMovies.equals(null) || title.equals(null) || actor.equals(null) ||
    			actress.equals(null) || year.equals(null) || director.equals(null) ||
    			type.equals(null) || language.equals(null)) {
    			JOptionPane.showMessageDialog(moviesPanel, "Select the movies id to delete from table...","Movies Setup",JOptionPane.ERROR_MESSAGE);
    		} else {
    			String deleteSQL = "delete from MOVIES where ID='" + idMovies + "'";
    			moviesSetupModel.setEdit(deleteSQL);
	    		JOptionPane.showMessageDialog(moviesPanel, "Successfully deleted...","Movies Setup",JOptionPane.INFORMATION_MESSAGE);
	    		idMovies = null;
	    		title = null;
	    		actor = null;
	    		actress = null;
	    		year = null;
	    		director = null;
	    		type = null;
	    		language = null;
	    		moviesSetupModel.setQuery(querySQL);
    		}
    	} catch(SQLException ex) {
    		JOptionPane.showMessageDialog(moviesPanel, "SQL Statement Error: " + ex.getMessage(),"SQL Statement Error",JOptionPane.ERROR_MESSAGE);
    	} catch(Exception ex) {
    		JOptionPane.showMessageDialog(moviesPanel, "Select the music id to delete from table: " + ex.getMessage(),"General Error",JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    private void btnUpdateActionPerformed(ActionEvent evt) {
    	if(txtTitle.getText().equals("") || txtActor.getText().equals("") ||
    	   txtActress.getText().equals("") || txtYear.getText().equals("") ||
    	   cmbDisc.getSelectedItem().equals("")  || txtDirector.getText().equals("") ||
    	   cmbLanguage.getSelectedItem().equals("")  || cmbType.getSelectedItem().equals("")) {
    	   JOptionPane.showMessageDialog(moviesPanel, "Please fill all the fields" ,"Movies Setup",JOptionPane.WARNING_MESSAGE);
    	} else {
    		try {
    			moviesSetupModel.setEdit("Update " + moviesSetupModel.getTableName(1) + 
    								" Set TITLE = '" + txtTitle.getText() +
    		   						"', ACTOR = '" + txtActor.getText () + 
    		   						"', ACTRESS = '" + txtActress.getText() +
    		   						"', TYPE = '" + txtYear.getInteger() +
    		   						"', DIRECTOR = '" + txtDirector.getText() +
    		   						"', LANGUAGE = '" + cmbLanguage.getSelectedItem() +
    		   						"', YEAR = '" + cmbType.getSelectedItem() +
    		   						"', DISC = '" + cmbDisc.getSelectedItem() + 
    		   						"' where ID= " + Integer.parseInt(idMovies) + "");
    		
    		   	moviesSetupModel.setQuery(querySQL);
    		   	JOptionPane.showMessageDialog(moviesPanel, "Successfully updated","Movies Setup",JOptionPane.INFORMATION_MESSAGE);
    		   	btnUpdate.setEnabled(false);
    		   	btnSave.setEnabled(false);
    		   	btnNew.setEnabled(true);
    		  	idMovies = null;
    			title = null;
    			actor = null;
    			actress = null;
    			year = null;
    			director = null;
    	  		type = null;
    	   		language = null;
    	    	disableFields();
    	    } catch(SQLException ex) {
    	   		JOptionPane.showMessageDialog(moviesPanel, "SQL Statement Error: " + ex.getMessage(),"SQL Statement Error",JOptionPane.ERROR_MESSAGE);
    	    } catch(Exception ex) {
    	    	JOptionPane.showMessageDialog(moviesPanel, "Please select any id for deleting: " + ex.getMessage(),"General Error",JOptionPane.ERROR_MESSAGE);
    	   	}
    	}
    }
    
    private void btnExitActionPerformed(ActionEvent evt) {
    	try {
			setClosed(true);
		} catch (PropertyVetoException e) {
			JOptionPane.showMessageDialog(moviesPanel, "Movies Setup form error: " + e.getMessage(), "Movies Setup", JOptionPane.ERROR_MESSAGE);
		}
    }

    private JButton btnDelete;
    private JButton btnExit;
    private JButton btnNew;
    private JButton btnRefresh;
    private JButton btnSave;
    private JButton btnUpdate;
    private JComboBox cmbDisc;
    private JComboBox cmbLanguage;
    private JComboBox cmbType;
    private JLabel lblActor;
    private JLabel lblActress;
    private JLabel lblDirector;
    private JLabel lblDisc;
    private JLabel lblLanguage;
    private JLabel lblTitle;
    private JLabel lblType;
    private JLabel lblYear;
    private JPanel moviesPanel;
    private JScrollPane scrollPaneMovies;
    private JTable tblMovies;
    private JTextField txtActor;
    private JTextField txtActress;
    private JTextField txtDirector;
    private JTextField txtTitle;
    private JNumericField txtYear;
    private MusicMoviesTableModel moviesSetupModel;

}
