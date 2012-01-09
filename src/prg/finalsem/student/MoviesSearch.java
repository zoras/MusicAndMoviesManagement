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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
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
public class MoviesSearch extends JInternalFrame {
	
	private Connection con;
	private ResultSet rs;
	private Statement st;
	private String url = "jdbc:mysql://localhost:3306/musicandmovies";
	private String driver = "com.mysql.jdbc.Driver";
    private String userName = "root";
    private String passWord = "a1";
    private String querySQL = "select * from MOVIES";
	
    public MoviesSearch() {
    	super("Movies Search");
    	JDialog.setDefaultLookAndFeelDecorated(true);
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setBounds((screenSize.width-900)/2, (screenSize.height-600)/2, 900, 600);
        try {
			initComponents();
		} catch (Exception e) {
			e.printStackTrace();
		}
		fillMoviesType();
		fillMoviesDiscType();
		fillMoviesLanguage();
        disableAll();
    }
    
    private void disableAll(){
		txtYear.setEnabled(false);
		txtActor.setEnabled(false);
		txtActress.setEnabled(false);
		txtTitle.setEnabled(false);  
		cmbDisc.setEnabled(false);
		cmbLanguage.setEnabled(false);
		cmbType.setEnabled(false);
		txtDirector.setEnabled(false);
    }
    
    private void connect() {
    	try {
    		Class.forName(driver);
    		con  = DriverManager.getConnection(url,userName,passWord);
    	} catch(ClassNotFoundException ex) {
    		JOptionPane.showMessageDialog(panelMoviesSearch, "Class not found:  " + ex.getMessage(), "Movies Search", JOptionPane.ERROR_MESSAGE);
    	} catch(SQLException ex) {
    		JOptionPane.showMessageDialog(panelMoviesSearch, "Error while connecting database:  " + ex.getMessage(), "Movies Search", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    private void fillMoviesType() {
    	String sqlQuery = "select * from MOVIES_TYPE";
    	try {
    		connect();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				cmbType.addItem(rs.getString("TYPE"));
			}
		} catch(SQLException ex) {
			JOptionPane.showMessageDialog(panelMoviesSearch, "Error in SQL statement:  " + ex.getMessage(), "Music Search", JOptionPane.ERROR_MESSAGE);
		}
    }
    
    private void fillMoviesDiscType() {
    	String sqlQuery = "select * from DISC";
    	try {
    		connect();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				cmbDisc.addItem(rs.getString("DISC"));
			}
		} catch(SQLException ex) {
			JOptionPane.showMessageDialog(panelMoviesSearch, "Error in SQL statement:  " + ex.getMessage(), "Music Search", JOptionPane.ERROR_MESSAGE);
		}
    }
    
    private void fillMoviesLanguage() {
    	String sqlQuery = "select * from LANGUAGE";
    	try {
    		connect();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				cmbLanguage.addItem(rs.getString("LANGUAGE"));
			}
		} catch(SQLException ex) {
			JOptionPane.showMessageDialog(panelMoviesSearch, "Error in SQL statement:  " + ex.getMessage(), "Music Search", JOptionPane.ERROR_MESSAGE);
		}
    }

    private void initComponents() throws Exception {
    	panelMoviesSearch = new JPanel();
        chkTitle = new JCheckBox("Title");
        chkActor = new JCheckBox("Actor");
        chkActress = new JCheckBox("Actress");
        chkYear = new JCheckBox("Year");
        txtTitle = new JTextField();
        txtActor = new JTextField();
        txtActress = new JTextField();
        chkDirector = new JCheckBox("Director");
        chkType = new JCheckBox("Type");
        chkDisc = new JCheckBox("Disc");
        chkLanguage = new JCheckBox("Language");
        cmbType = new JComboBox();
        cmbDisc = new JComboBox();
        cmbLanguage = new JComboBox();
        btnSearch = new JButton("Search");
        btnExit = new JButton("Exit");
        searchMoviesModel = new MusicMoviesTableModel(userName,passWord,querySQL);
        tblMusicSearch = new JTable(searchMoviesModel);
        scrollPaneMusicSearch = new JScrollPane(tblMusicSearch);
        scrollPaneMusicSearch.setViewportView(tblMusicSearch);
        txtYear = new JNumericField();
        txtDirector = new JTextField();
        
        chkTitle.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent evt) {
				if(chkTitle.isSelected()) {
					txtTitle.setEnabled(true);
				} else {
					txtTitle.setEnabled(false);
				}
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
        
        chkActor.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent evt) {
				if(chkActor.isSelected()) {
					txtActor.setEnabled(true);
				} else {
					txtActor.setEnabled(false);
				}
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
        
        chkActress.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent evt) {
				if(chkActress.isSelected()) {
					txtActress.setEnabled(true);
				} else {
					txtActress.setEnabled(false);
				}
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
        
        chkYear.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent evt) {
				if(chkYear.isSelected()) {
					txtYear.setEnabled(true);
				} else {
					txtYear.setEnabled(false);
				}
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
        
        chkDirector.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent evt) {
				if(chkDirector.isSelected()) {
					txtDirector.setEnabled(true);
				} else {
					txtDirector.setEnabled(false);
				}
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
        
        chkType.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent evt) {
				if(chkType.isSelected()) {
					cmbType.setEnabled(true);
				} else {
					cmbType.setEnabled(false);
				}
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
        
        chkDisc.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent evt) {
				if(chkDisc.isSelected()) {
					cmbDisc.setEnabled(true);
				} else {
					cmbDisc.setEnabled(false);
				}
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
        
        chkLanguage.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent evt) {
				if(chkLanguage.isSelected()) {
					cmbLanguage.setEnabled(true);
				} else {
					cmbLanguage.setEnabled(false);
				}
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
        
        btnSearch.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnSearchActionPerformed(evt);
			}
        });
        
        btnExit.addActionListener(new ActionListener() {
        	@Override
			public void actionPerformed(ActionEvent evt) {
				btnExitActionPerformed(evt);
			}
        });

        GroupLayout panelMusicSearchLayout = new GroupLayout(panelMoviesSearch);
        panelMoviesSearch.setLayout(panelMusicSearchLayout);
        panelMusicSearchLayout.setHorizontalGroup(
            panelMusicSearchLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelMusicSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMusicSearchLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panelMusicSearchLayout.createSequentialGroup()
                        .addComponent(scrollPaneMusicSearch, GroupLayout.DEFAULT_SIZE, 903, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(panelMusicSearchLayout.createSequentialGroup()
                        .addGroup(panelMusicSearchLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(chkActor)
                            .addComponent(chkActress)
                            .addComponent(chkTitle)
                            .addComponent(chkYear))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelMusicSearchLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(txtYear, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                            .addComponent(txtActor, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                            .addComponent(txtTitle, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                            .addComponent(txtActress, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelMusicSearchLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(chkDirector)
                            .addComponent(chkDisc)
                            .addComponent(chkType)
                            .addComponent(chkLanguage))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelMusicSearchLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(cmbType, 0, 275, Short.MAX_VALUE)
                            .addComponent(cmbLanguage, 0, 275, Short.MAX_VALUE)
                            .addComponent(cmbDisc, 0, 275, Short.MAX_VALUE)
                            .addComponent(txtDirector, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelMusicSearchLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnExit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(45, 45, 45))))
        );
        panelMusicSearchLayout.setVerticalGroup(
            panelMusicSearchLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelMusicSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMusicSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(chkTitle)
                    .addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkDirector)
                    .addComponent(btnSearch)
                    .addComponent(txtDirector, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMusicSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(chkActor)
                    .addComponent(txtActor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkType)
                    .addComponent(btnExit)
                    .addComponent(cmbType, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMusicSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(chkActress)
                    .addComponent(txtActress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkDisc)
                    .addComponent(cmbDisc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMusicSearchLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(chkYear)
                    .addGroup(panelMusicSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(chkLanguage)
                        .addComponent(cmbLanguage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneMusicSearch, GroupLayout.PREFERRED_SIZE, 333, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelMoviesSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelMoviesSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        
        pack();
        setVisible(true);
    }
    
    private void btnSearchActionPerformed(ActionEvent evt) {
    	connect();
    	try {
    		searchMoviesModel.setQuery(querySQL);
		} catch (IllegalStateException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
    	String query = "select * from MOVIES where ID is not null";
    	if(chkTitle.isSelected()) {
    		if(!txtTitle.getText().equals("")){
    			query = query + " and TITLE like '" + txtTitle.getText() + "'";
    			try {
    				st = con.createStatement();
    				rs = st.executeQuery(query);
					if(rs.next() == false) {
						JOptionPane.showMessageDialog(panelMoviesSearch,"The title doesn't exists","Movies Search", JOptionPane.ERROR_MESSAGE);
					}
					rs.close();
					searchMoviesModel.setQuery(query);
					System.out.println(query);
				} catch(SQLException e) {
					e.printStackTrace();
				} catch(IllegalStateException ex) {
					ex.printStackTrace();
				}
    		} else {
    			JOptionPane.showMessageDialog(panelMoviesSearch,"Please type the title...","Movies Search", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    	if(chkActor.isSelected()) {
    		if(!txtActor.getText().equals("")){
    			query = query + " and ACTOR like '" + txtActor.getText() + "'";
    			try {
    				st = con.createStatement();
    				rs = st.executeQuery(query);
					if(rs.next() == false) {
						JOptionPane.showMessageDialog(panelMoviesSearch,"The actor name doesn't exists","Movies Search", JOptionPane.ERROR_MESSAGE);
					}
					rs.close();
					searchMoviesModel.setQuery(query);
					System.out.println(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		} else {
    			JOptionPane.showMessageDialog(panelMoviesSearch,"Please type the actor name...","Movies Search", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    	if(chkActress.isSelected()) {
    		if(!txtActress.getText().equals("")){
    			query = query + " and ACTRESS like '" + txtActress.getText() + "'";
    			try {
    				st = con.createStatement();
    				rs = st.executeQuery(query);
					if(rs.next() == false) {
						JOptionPane.showMessageDialog(panelMoviesSearch,"The actress name doesn't exists","Movies Search", JOptionPane.ERROR_MESSAGE);
					}
					rs.close();
					searchMoviesModel.setQuery(query);
					System.out.println(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		} else {
    			JOptionPane.showMessageDialog(panelMoviesSearch,"Please type the actress name...","Movies Search", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    	if(chkYear.isSelected()) {
    		if(!txtYear.getText().equals("")){
    			query = query + " and YEAR like '" + txtYear.getInteger() + "'";
    			try {
    				st = con.createStatement();
    				rs = st.executeQuery(query);
					if(rs.next() == false) {
						JOptionPane.showMessageDialog(panelMoviesSearch,"The Year doesn't exists","Movies Search", JOptionPane.ERROR_MESSAGE);
					}
					rs.close();
					searchMoviesModel.setQuery(query);
					System.out.println(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		} else {
    			JOptionPane.showMessageDialog(panelMoviesSearch,"Please type the year in number...","Movies Search", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    	if(chkDirector.isSelected()) {
    		if(!txtDirector.getText().equals("")){
    			query = query + " and DIRECTOR like '" + txtDirector.getText() + "'";
    			try {
    				st = con.createStatement();
    				rs = st.executeQuery(query);
					if(rs.next() == false) {
						JOptionPane.showMessageDialog(panelMoviesSearch,"The director doesn't exists","Movies Search", JOptionPane.ERROR_MESSAGE);
					}
					rs.close();
					searchMoviesModel.setQuery(query);
					System.out.println(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		} else {
    			JOptionPane.showMessageDialog(panelMoviesSearch,"Please type the director name...","Movies Search", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    	if(chkType.isSelected()) {
    		if(!cmbType.getSelectedItem().equals("")){
    			query = query + " and TYPE like '" + cmbType.getSelectedItem() + "'";
    			try {
    				st = con.createStatement();
    				rs = st.executeQuery(query);
					if(rs.next() == false) {
						JOptionPane.showMessageDialog(panelMoviesSearch,"The movies type doesn't exists","Movies Search", JOptionPane.ERROR_MESSAGE);
					}
					rs.close();
					searchMoviesModel.setQuery(query);
					System.out.println(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		} else {
    			JOptionPane.showMessageDialog(panelMoviesSearch,"Please select the movies type...","Movies Search", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    	if(chkDisc.isSelected()) {
    		if(!cmbDisc.getSelectedItem().equals("")){
    			query = query + " and DISC like '" + cmbDisc.getSelectedItem() + "'";
    			try {
    				st = con.createStatement();
    				rs = st.executeQuery(query);
					if(rs.next() == false) {
						JOptionPane.showMessageDialog(panelMoviesSearch,"The disc doesn't exists","Movies Search", JOptionPane.ERROR_MESSAGE);
					}
					rs.close();
					searchMoviesModel.setQuery(query);
					System.out.println(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		} else {
    			JOptionPane.showMessageDialog(panelMoviesSearch,"Please select the disc type...","Movies Search", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    	if(chkLanguage.isSelected()) {
    		if(!cmbLanguage.getSelectedItem().equals("")){
    			query = query + " and LANGUAGE like '" + cmbLanguage.getSelectedItem() + "'";
    			try {
    				st = con.createStatement();
    				rs = st.executeQuery(query);
					if(rs.next() == false) {
						JOptionPane.showMessageDialog(panelMoviesSearch,"The language doesn't exists","Music Search", JOptionPane.ERROR_MESSAGE);
					}
					rs.close();
					searchMoviesModel.setQuery(query);
					System.out.println(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		} else {
    			JOptionPane.showMessageDialog(panelMoviesSearch,"Please select the language...","Music Search", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    }
    
    private void btnExitActionPerformed(ActionEvent evt) {
    	try {
			setClosed(true);
		} catch (PropertyVetoException e) {
			JOptionPane.showMessageDialog(panelMoviesSearch, "Movies Search form error: " + e.getMessage(), "Music Issue N' Return", JOptionPane.ERROR_MESSAGE);
		}
    }

    private JButton btnExit;
    private JButton btnSearch;
    private JCheckBox chkActor;
    private JCheckBox chkActress;
    private JCheckBox chkDirector;
    private JCheckBox chkDisc;
    private JCheckBox chkLanguage;
    private JCheckBox chkTitle;
    private JCheckBox chkType;
    private JCheckBox chkYear;
    private JComboBox cmbDisc;
    private JComboBox cmbLanguage;
    private JComboBox cmbType;
    private JPanel panelMoviesSearch;
    private JScrollPane scrollPaneMusicSearch;
    private JTable tblMusicSearch;
    private JTextField txtActor;
    private JTextField txtActress;
    private JTextField txtDirector;
    private JTextField txtTitle;
    private JNumericField txtYear;
    private MusicMoviesTableModel searchMoviesModel;

}
