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
public class MusicSearch extends JInternalFrame {
	
	private Connection con;
	private ResultSet rs;
	private Statement st;
	private String url = "jdbc:mysql://localhost:3306/musicandmovies";
	private String driver = "com.mysql.jdbc.Driver";
    private String userName = "root";
    private String passWord = "a1";
    private String querySQL = "select * from MUSIC";
	
    public MusicSearch() {
    	super("Music Search");
    	JDialog.setDefaultLookAndFeelDecorated(true);
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setBounds((screenSize.width-900)/2, (screenSize.height-600)/2, 900, 600);
        try {
			initComponents();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		fillMusicGenre();
		fillMusicType();
		fillMusicDisc();
		fillMusicLanguage();
		disableAll();
    }
    
    private void disableAll(){
		txtYear.setEnabled(false);
		txtAlbum.setEnabled(false);
		txtArtist.setEnabled(false);
		txtTitle.setEnabled(false);  
		cmbDisc.setEnabled(false);
		cmbLanguage.setEnabled(false);
		cmbType.setEnabled(false);
		cmbGenre.setEnabled(false);
    }
    
    private void connect() {
    	try {
    		Class.forName(driver);
    		con  = DriverManager.getConnection(url,userName,passWord);
    	} catch(ClassNotFoundException ex) {
    		JOptionPane.showMessageDialog(panelMusicSearch, "Class not found:  " + ex.getMessage(), "Music Issue", JOptionPane.ERROR_MESSAGE);
    	} catch(SQLException ex) {
    		JOptionPane.showMessageDialog(panelMusicSearch, "Error while connecting database:  " + ex.getMessage(), "Music Issue", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    private void fillMusicGenre() {
    	String sqlQuery = "select * from MUSIC_GENRE";
    	try {
    		connect();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				cmbGenre.addItem(rs.getString("GENRE"));
			}
		} catch(SQLException ex) {
			JOptionPane.showMessageDialog(panelMusicSearch, "Error in SQL statement:  " + ex.getMessage(), "Music Search", JOptionPane.ERROR_MESSAGE);
		}
    }
    
    private void fillMusicType() {
    	String sqlQuery = "select * from MUSIC_TYPE";
    	try {
    		connect();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				cmbType.addItem(rs.getString("TYPE"));
			}
		} catch(SQLException ex) {
			JOptionPane.showMessageDialog(panelMusicSearch, "Error in SQL statement:  " + ex.getMessage(), "Music Search", JOptionPane.ERROR_MESSAGE);
		}
    }
    
    private void fillMusicDisc() {
    	String sqlQuery = "select * from DISC";
    	try {
    		connect();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				cmbDisc.addItem(rs.getString("DISC"));
			}
		} catch(SQLException ex) {
			JOptionPane.showMessageDialog(panelMusicSearch, "Error in SQL statement:  " + ex.getMessage(), "Music Search", JOptionPane.ERROR_MESSAGE);
		}
    }
    
    private void fillMusicLanguage() {
    	String sqlQuery = "select * from LANGUAGE";
    	try {
    		connect();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				cmbLanguage.addItem(rs.getString("LANGUAGE"));
			}
		} catch(SQLException ex) {
			JOptionPane.showMessageDialog(panelMusicSearch, "Error in SQL statement:  " + ex.getMessage(), "Music Search", JOptionPane.ERROR_MESSAGE);
		}
    }
    
    private void initComponents() throws Exception{
        panelMusicSearch = new JPanel();
        chkTitle = new JCheckBox("Title");
        chkArtist = new JCheckBox("Artist");
        chkAlbum = new JCheckBox("Album");
        chkGenre = new JCheckBox("Genre");
        txtTitle = new JTextField();
        txtArtist = new JTextField();
        txtAlbum = new JTextField();
        cmbGenre = new JComboBox();
        chkType = new JCheckBox("Type");
        chkDisc = new JCheckBox("Disc");
        chkYear = new JCheckBox("Year");
        chkLanguage = new JCheckBox("Language");
        cmbType = new JComboBox();
        cmbDisc = new JComboBox();
        txtYear = new JNumericField();
        cmbLanguage = new JComboBox();
        btnSearch = new JButton("Search");
        btnExit = new JButton("Exit");
        searchMusicModel = new MusicMoviesTableModel(userName,passWord,querySQL);
        tblMusicSearch = new JTable(searchMusicModel);
        scrollPaneMusicSearch = new JScrollPane(tblMusicSearch);
        scrollPaneMusicSearch.setViewportView(tblMusicSearch);
        
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
        
        chkArtist.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent evt) {
				if(chkArtist.isSelected()) {
					txtArtist.setEnabled(true);
				} else {
					txtArtist.setEnabled(false);
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
        
        chkAlbum.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent evt) {
				if(chkAlbum.isSelected()) {
					txtAlbum.setEnabled(true);
				} else {
					txtAlbum.setEnabled(false);
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
        
        chkGenre.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent evt) {
				if(chkGenre.isSelected()) {
					cmbGenre.setEnabled(true);
				} else {
					cmbGenre.setEnabled(false);
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
				btnSearcActionPerformed(evt);
			}
        });
        
        btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnExitActionPerformed(evt);
			}
        });
        
        GroupLayout panelMusicSearchLayout = new GroupLayout(panelMusicSearch);
        panelMusicSearch.setLayout(panelMusicSearchLayout);
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
                            .addComponent(chkArtist)
                            .addComponent(chkAlbum)
                            .addComponent(chkTitle)
                            .addComponent(chkYear))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelMusicSearchLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(txtYear, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                            .addComponent(txtArtist, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                            .addComponent(txtTitle, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                            .addComponent(txtAlbum, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelMusicSearchLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(chkGenre)
                            .addComponent(chkDisc)
                            .addComponent(chkType)
                            .addComponent(chkLanguage))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelMusicSearchLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(cmbGenre, 0, 278, Short.MAX_VALUE)
                            .addComponent(cmbType, 0, 278, Short.MAX_VALUE)
                            .addComponent(cmbLanguage, 0, 278, Short.MAX_VALUE)
                            .addComponent(cmbDisc, 0, 278, Short.MAX_VALUE))
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
                    .addComponent(chkGenre)
                    .addComponent(btnSearch)
                    .addComponent(cmbGenre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMusicSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(chkArtist)
                    .addComponent(txtArtist, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkType)
                    .addComponent(btnExit)
                    .addComponent(cmbType, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMusicSearchLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(chkAlbum)
                    .addComponent(txtAlbum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
            .addComponent(panelMusicSearch, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelMusicSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        
        setVisible(true);
        pack();
    }
    
    private void btnSearcActionPerformed(ActionEvent evt) {
    	connect();
    	try {
			searchMusicModel.setQuery(querySQL);
		} catch (IllegalStateException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
    	String query = "select * from MUSIC where ID is not null";
    	if(chkTitle.isSelected()) {
    		if(!txtTitle.getText().equals("")){
    			query = query + " and TITLE like '" + txtTitle.getText() + "'";
    			try {
    				st = con.createStatement();
    				rs = st.executeQuery(query);
					if(rs.next() == false) {
						JOptionPane.showMessageDialog(panelMusicSearch,"The title doesn't exists","Music Search", JOptionPane.ERROR_MESSAGE);
					}
					rs.close();
					searchMusicModel.setQuery(query);
					System.out.println(query);
				} catch(SQLException e) {
					e.printStackTrace();
				} catch(IllegalStateException ex) {
					ex.printStackTrace();
				}
    		} else {
    			JOptionPane.showMessageDialog(panelMusicSearch,"Please type the title...","Music Search", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    	if(chkArtist.isSelected()) {
    		if(!txtArtist.getText().equals("")){
    			query = query + " and ARTIST like '" + txtArtist.getText() + "'";
    			try {
    				st = con.createStatement();
    				rs = st.executeQuery(query);
					if(rs.next() == false) {
						JOptionPane.showMessageDialog(panelMusicSearch,"The artist doesn't exists","Music Search", JOptionPane.ERROR_MESSAGE);
					}
					rs.close();
					searchMusicModel.setQuery(query);
					System.out.println(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		} else {
    			JOptionPane.showMessageDialog(panelMusicSearch,"Please type the artist name...","Music Search", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    	if(chkAlbum.isSelected()) {
    		if(!txtAlbum.getText().equals("")){
    			query = query + " and ALBUM like '" + txtAlbum.getText() + "'";
    			try {
    				st = con.createStatement();
    				rs = st.executeQuery(query);
					if(rs.next() == false) {
						JOptionPane.showMessageDialog(panelMusicSearch,"The album doesn't exists","Music Search", JOptionPane.ERROR_MESSAGE);
					}
					rs.close();
					searchMusicModel.setQuery(query);
					System.out.println(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		} else {
    			JOptionPane.showMessageDialog(panelMusicSearch,"Please type the album name...","Music Search", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    	if(chkYear.isSelected()) {
    		if(!txtYear.getText().equals("")){
    			query = query + " and YEAR like '" + txtYear.getInteger() + "'";
    			try {
    				st = con.createStatement();
    				rs = st.executeQuery(query);
					if(rs.next() == false) {
						JOptionPane.showMessageDialog(panelMusicSearch,"The Year doesn't exists","Music Search", JOptionPane.ERROR_MESSAGE);
					}
					rs.close();
					searchMusicModel.setQuery(query);
					System.out.println(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		} else {
    			JOptionPane.showMessageDialog(panelMusicSearch,"Please type the year in number...","Music Search", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    	if(chkGenre.isSelected()) {
    		if(!cmbGenre.getSelectedItem().equals("")){
    			query = query + " and GENRE like '" + cmbGenre.getSelectedItem() + "'";
    			try {
    				st = con.createStatement();
    				rs = st.executeQuery(query);
					if(rs.next() == false) {
						JOptionPane.showMessageDialog(panelMusicSearch,"The genre doesn't exists","Music Search", JOptionPane.ERROR_MESSAGE);
					}
					rs.close();
					searchMusicModel.setQuery(query);
					System.out.println(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		} else {
    			JOptionPane.showMessageDialog(panelMusicSearch,"Please select the genre...","Music Search", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    	if(chkType.isSelected()) {
    		if(!cmbType.getSelectedItem().equals("")){
    			query = query + " and TYPE like '" + cmbType.getSelectedItem() + "'";
    			try {
    				st = con.createStatement();
    				rs = st.executeQuery(query);
					if(rs.next() == false) {
						JOptionPane.showMessageDialog(panelMusicSearch,"The music type doesn't exists","Music Search", JOptionPane.ERROR_MESSAGE);
					}
					rs.close();
					searchMusicModel.setQuery(query);
					System.out.println(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		} else {
    			JOptionPane.showMessageDialog(panelMusicSearch,"Please select the music type...","Music Search", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    	if(chkDisc.isSelected()) {
    		if(!cmbDisc.getSelectedItem().equals("")){
    			query = query + " and DISC like '" + cmbDisc.getSelectedItem() + "'";
    			try {
    				st = con.createStatement();
    				rs = st.executeQuery(query);
					if(rs.next() == false) {
						JOptionPane.showMessageDialog(panelMusicSearch,"The disc doesn't exists","Music Search", JOptionPane.ERROR_MESSAGE);
					}
					rs.close();
					searchMusicModel.setQuery(query);
					System.out.println(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		} else {
    			JOptionPane.showMessageDialog(panelMusicSearch,"Please select the disc type...","Music Search", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    	if(chkLanguage.isSelected()) {
    		if(!cmbLanguage.getSelectedItem().equals("")){
    			query = query + " and LANGUAGE like '" + cmbLanguage.getSelectedItem() + "'";
    			try {
    				st = con.createStatement();
    				rs = st.executeQuery(query);
					if(rs.next() == false) {
						JOptionPane.showMessageDialog(panelMusicSearch,"The language doesn't exists","Music Search", JOptionPane.ERROR_MESSAGE);
					}
					rs.close();
					searchMusicModel.setQuery(query);
					System.out.println(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		} else {
    			JOptionPane.showMessageDialog(panelMusicSearch,"Please select the language...","Music Search", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    }
    
    private void btnExitActionPerformed(ActionEvent evt) {
    	try {
			setClosed(true);
		} catch (PropertyVetoException e) {
			JOptionPane.showMessageDialog(panelMusicSearch, "Music Search form error: " + e.getMessage(), "Music Issue N' Return", JOptionPane.ERROR_MESSAGE);
		}
    }

    private JButton btnExit;
    private JButton btnSearch;
    private JCheckBox chkAlbum;
    private JCheckBox chkArtist;
    private JCheckBox chkDisc;
    private JCheckBox chkGenre;
    private JCheckBox chkLanguage;
    private JCheckBox chkTitle;
    private JCheckBox chkType;
    private JCheckBox chkYear;
    private JComboBox cmbDisc;
    private JComboBox cmbLanguage;
    private JComboBox cmbType;
    private JComboBox cmbGenre;
    private JNumericField txtYear;
    private JTextField txtAlbum;
    private JTextField txtArtist;
    private JTextField txtTitle;
    private JTable tblMusicSearch;
    private JScrollPane scrollPaneMusicSearch;
    private JPanel panelMusicSearch;
    private MusicMoviesTableModel searchMusicModel;
}

