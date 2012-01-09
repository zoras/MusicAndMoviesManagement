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
public class MusicSetup extends JInternalFrame {
	
	private Connection con;
	private String url = "jdbc:mysql://localhost:3306/musicandmovies";
	private String driver = "com.mysql.jdbc.Driver";
    private String userName = "root";
    private String passWord = "a1";
    private String querySQL = "select * from MUSIC";
    private String idMusic,title,artist,album,year,genre,language,type,disc;

    public MusicSetup() {
    	super("Music Setup");
    	JDialog.setDefaultLookAndFeelDecorated(true);
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setBounds((screenSize.width-800)/2, (screenSize.height-600)/2, 800, 600);
        try {
			initComponents();
		} catch (Exception e) {
			e.printStackTrace();
		}
        disableFields();
        btnDelete.setEnabled(true);
        btnNew.setEnabled(true);
        btnSave.setEnabled(false);
        btnUpdate.setEnabled(false);
        fillMusicDiscComboBox();
        fillMusicTypeComboBox();
        fillMusicGenreComboxBox();
        fillMusicLanguageBoxComboBox();
    }
    
    private void connect() {
    	try {
    		Class.forName(driver);
    		con  = DriverManager.getConnection(url,userName,passWord);
    	} catch(ClassNotFoundException ex) {
    		JOptionPane.showMessageDialog(musicPanel, "Class not found:  " + ex.getMessage(), "Music Setup", JOptionPane.ERROR_MESSAGE);
    	} catch(SQLException ex) {
    		JOptionPane.showMessageDialog(musicPanel, "Error while connecting database:  " + ex.getMessage(), "Music Setup", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    private void fillMusicDiscComboBox() {
    	String sqlQuery = "select * from DISC";
    	try {
    		connect();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				cmbDisc.addItem(rs.getString("DISC"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
    }
    
    private void fillMusicGenreComboxBox() {
    	String sqlQuery = "select * from MUSIC_GENRE";
    	try {
    		connect();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				cmbGenre.addItem(rs.getString("GENRE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    private void fillMusicTypeComboBox() {
    	String sqlQuery = "select * from MUSIC_TYPE";
    	try {
    		connect();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				cmbType.addItem(rs.getString("TYPE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    private void fillMusicLanguageBoxComboBox() {
    	String sqlQuery = "select * from LANGUAGE";
    	try {
    		connect();
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlQuery);
			while(rs.next()) {
				cmbLanguage.addItem(rs.getString("LANGUAGE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    private void initComponents() throws Exception {
        musicPanel = new JPanel();
        lblTitle = new JLabel("Title");
        txtTitle = new JTextField();
        lblArtist = new JLabel("Artist");
        txtArtist = new JTextField();
        lblAlbum = new JLabel("Album");
        txtAlbum = new JTextField();
        lblYear = new JLabel("Year");
        txtYear = new JNumericField();
        lblGenre = new JLabel("Genre");
        cmbGenre = new JComboBox();
        lblLanguage = new JLabel("Language");
        cmbLanguage = new JComboBox();
        lblType = new JLabel("Type");
        cmbType = new JComboBox();
        lblDisc = new JLabel("Disc");
        cmbDisc = new JComboBox();
        musicSetupModel = new MusicMoviesTableModel(userName,passWord,querySQL);
        tblMusic = new JTable(musicSetupModel);
        scrollPaneMusic = new JScrollPane(tblMusic);
        scrollPaneMusic.setViewportView(tblMusic);
        btnNew = new JButton("New");
        btnSave = new JButton("Save");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnRefresh = new JButton("Refresh");
        btnExit = new JButton("Exit");
        
        tblMusic.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int i = tblMusic.getSelectedRow();
				idMusic = String.valueOf(tblMusic.getValueAt(i, 0));
				title = String.valueOf(tblMusic.getValueAt(i, 1));
				artist = String.valueOf(tblMusic.getValueAt(i, 2));
				album = String.valueOf(tblMusic.getValueAt(i, 3));
				year = String.valueOf(tblMusic.getValueAt(i, 4));
				genre = String.valueOf(tblMusic.getValueAt(i, 5));
				language = String.valueOf(tblMusic.getValueAt(i, 6));
				type = String.valueOf(tblMusic.getValueAt(i, 7));
				disc = String.valueOf(tblMusic.getValueAt(i, 8));
				txtTitle.setText(title);
				txtArtist.setText(artist);
				txtAlbum.setText(album);
				txtYear.setText(year);
				cmbGenre.setSelectedItem(genre);
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
        
        btnDelete.addActionListener(new ActionListener() {
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
        
        btnRefresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnRefreshActionPerformed(evt);
			}
        });
        
        btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnExitActionPerformed(evt);
			}
        });

        GroupLayout musicPanelLayout = new GroupLayout(musicPanel);
        musicPanel.setLayout(musicPanelLayout);
        musicPanelLayout.setHorizontalGroup(
            musicPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(musicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(musicPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneMusic, GroupLayout.DEFAULT_SIZE, 825, Short.MAX_VALUE)
                    .addGroup(musicPanelLayout.createSequentialGroup()
                        .addGroup(musicPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitle)
                            .addComponent(lblArtist)
                            .addComponent(lblAlbum)
                            .addComponent(lblYear)
                            .addComponent(lblGenre)
                            .addComponent(lblLanguage)
                            .addComponent(lblType)
                            .addComponent(lblDisc))
                        .addGap(21, 21, 21)
                        .addGroup(musicPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtAlbum)
                            .addComponent(cmbDisc, GroupLayout.Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbType, GroupLayout.Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbLanguage, GroupLayout.Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbGenre, GroupLayout.Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtYear, GroupLayout.Alignment.LEADING)
                            .addComponent(txtArtist, GroupLayout.Alignment.LEADING)
                            .addComponent(txtTitle, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btnNew)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(musicPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addGroup(musicPanelLayout.createSequentialGroup()
                                .addComponent(btnSave)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate))
                            .addGroup(musicPanelLayout.createSequentialGroup()
                                .addComponent(btnRefresh)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 256, Short.MAX_VALUE)))
                .addContainerGap())
        );
        musicPanelLayout.setVerticalGroup(
            musicPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(musicPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(musicPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle)
                    .addComponent(txtTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnNew))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(musicPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txtArtist, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblArtist)
                    .addComponent(btnRefresh)
                    .addComponent(btnExit))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(musicPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAlbum)
                    .addComponent(txtAlbum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(musicPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txtYear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblYear))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(musicPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbGenre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGenre))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(musicPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbLanguage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLanguage))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(musicPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblType))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(musicPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbDisc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDisc))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneMusic, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(musicPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(musicPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        
        pack();
        setVisible(true);
    }
    
    private void disableFields() {
    	txtTitle.setEditable(false);
    	txtArtist.setEditable(false);
    	txtAlbum.setEditable(false);
    	txtYear.setEditable(false);
    	cmbDisc.setEnabled(false);
    	cmbGenre.setEnabled(false);
    	cmbLanguage.setEnabled(false);
    	cmbType.setEnabled(false);
    }
    
    private void enableFields() {
    	txtTitle.setEditable(true);
    	txtArtist.setEditable(true);
    	txtAlbum.setEditable(true);
    	txtYear.setEditable(true);
    	cmbDisc.setEnabled(true);
    	cmbGenre.setEnabled(true);
    	cmbLanguage.setEnabled(true);
    	cmbType.setEnabled(true);
    }
    
    private void clearAll() {
    	txtTitle.setText("");
    	txtArtist.setText("");
    	txtAlbum.setText("");
    	txtYear.setText("");
    	cmbDisc.setSelectedItem("");
    	cmbGenre.setSelectedItem("");
    	cmbLanguage.setSelectedItem("");;
    	cmbType.setSelectedItem("");
    }
    
    private void btnNewActionPerformed(ActionEvent evt) {
    	clearAll();
    	Object[] options = {"Enter New Music", "Update Old Music",
                "Cancel"};
		int ans = JOptionPane.showOptionDialog(musicPanel,
				"Would you like to enter new music or update old music?",
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
    
    private void btnSaveActionPerformed(ActionEvent evt){
    	if(txtTitle.getText().equals("") || txtArtist.getText().equals("") ||
    	           txtAlbum.getText().equals("") || txtYear.getText().equals("") ||
    	           cmbDisc.getSelectedItem().equals("")  || cmbGenre.getSelectedItem().equals("") ||
    	           cmbLanguage.getSelectedItem().equals("")  || cmbType.getSelectedItem().equals("")) {
    	        	JOptionPane.showMessageDialog(musicPanel, "Please fill all the fields" ,"Music Setup",JOptionPane.WARNING_MESSAGE);
    	} else {
    		musicSetupModel.setEdit("insert into MUSIC(TITLE,ARTIST,ALBUM,YEAR,GENRE,LANGUAGE,TYPE,DISC) values " +
    		"('" + txtTitle.getText() + "','"+ txtArtist.getText() +"','" + txtAlbum.getText() + "','" +
    		txtYear.getInteger() + "','" + cmbGenre.getSelectedItem() + "','" + cmbLanguage.getSelectedItem() + "','" +
    		cmbType.getSelectedItem() + "','" + cmbDisc.getSelectedItem() + "')");
    		JOptionPane.showMessageDialog(musicPanel, "Successfully inserted...", "Music Setup", JOptionPane.INFORMATION_MESSAGE);
    		clearAll();
    		idMusic = null;
    		title = null;
    		artist = null;
    		album = null;
    		year = null;
    		genre = null;
    		type = null;
    		language = null;
    		 try {
    			 musicSetupModel.setQuery(querySQL);
    		 } catch(SQLException ex) {
	    		JOptionPane.showMessageDialog(musicPanel, "Error while inserting data:  " + ex.getMessage(), "SQL Statement Error", JOptionPane.ERROR_MESSAGE);
    		 } catch(Exception ex) {
	    		JOptionPane.showMessageDialog(musicPanel, "Error while inserting data:  " + ex.getMessage(), "General Error", JOptionPane.ERROR_MESSAGE);
	    	 }
	    	clearAll();
	    	disableFields();
	    	btnSave.setEnabled(false);
	    	btnNew.setEnabled(true);
        }
    }
    
    private void btnDeleteActionPerformed(ActionEvent evt) {
    	try {
    		if(idMusic.equals(null) || title.equals(null) || artist.equals(null) ||
    			album.equals(null) || year.equals(null) || genre.equals(null) ||
    			type.equals(null) || language.equals(null)) {
    			JOptionPane.showMessageDialog(musicPanel, "Select the music id to delete from table...","Delete",JOptionPane.ERROR_MESSAGE);
    		} else {
    			String deleteSQL = "delete from MUSIC where ID='" + idMusic + "'";
    			musicSetupModel.setEdit(deleteSQL);
	    		JOptionPane.showMessageDialog(musicPanel, "Successfully deleted...","Music Setup",JOptionPane.INFORMATION_MESSAGE);
	    		idMusic = null;
	    		title = null;
	    		artist = null;
	    		album = null;
	    		year = null;
	    		genre = null;
	    		type = null;
	    		language = null;
	    		musicSetupModel.setQuery(querySQL);
    		}
    	} catch(SQLException ex) {
    		JOptionPane.showMessageDialog(musicPanel, "SQL Statement Error: " + ex.getMessage(),"SQL Statement Error",JOptionPane.ERROR_MESSAGE);
    	} catch(Exception ex) {
    		JOptionPane.showMessageDialog(musicPanel, "Select the music id to delete from table: " + ex.getMessage(),"General Error",JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    private void btnUpdateActionPerformed(ActionEvent evt) {
    	if(txtTitle.getText().equals("") || txtArtist.getText().equals("") ||
    	   txtAlbum.getText().equals("") || txtYear.getText().equals("") ||
    	   cmbDisc.getSelectedItem().equals("")  || cmbGenre.getSelectedItem().equals("") ||
    	   cmbLanguage.getSelectedItem().equals("")  || cmbType.getSelectedItem().equals("")) {
    	   JOptionPane.showMessageDialog(musicPanel, "Please fill all the fields" ,"Music Setup",JOptionPane.WARNING_MESSAGE);
    	} else {
    		try {
    			musicSetupModel.setEdit("Update " + musicSetupModel.getTableName(1) + 
		    						" Set TITLE = '" + txtTitle.getText() +
		    						"', ARTIST = '" + txtArtist.getText () + 
		    						"', ALBUM = '" + txtAlbum.getText() +
		    						"', YEAR = '" + txtYear.getInteger() +
		    						"', GENRE = '" + cmbGenre.getSelectedItem() +
		    						"', LANGUAGE = '" + cmbLanguage.getSelectedItem() +
		    						"', TYPE = '" + cmbType.getSelectedItem() +
		    						"', DISC = '" + cmbDisc.getSelectedItem() + 
		    						"' where ID= " + Integer.parseInt(idMusic) + "");
		    
		    	musicSetupModel.setQuery(querySQL);
		    	JOptionPane.showMessageDialog(musicPanel, "Successfully updated","Music Setup",JOptionPane.INFORMATION_MESSAGE);
		    	btnUpdate.setEnabled(false);
		    	btnSave.setEnabled(false);
		    	btnNew.setEnabled(true);
		    	idMusic = null;
	    		title = null;
	    		artist = null;
	    		album = null;
	    		year = null;
	    		genre = null;
	    		type = null;
	    		language = null;
		    	disableFields();
		    } catch(SQLException ex) {
	    		JOptionPane.showMessageDialog(musicPanel, "SQL Statement Error: " + ex.getMessage(),"SQL Statement Error",JOptionPane.ERROR_MESSAGE);
		    } catch(Exception ex) {
		    	JOptionPane.showMessageDialog(musicPanel, "Please select any id for deleting: " + ex.getMessage(),"General Error",JOptionPane.ERROR_MESSAGE);
	    	}
    	}
    }
    
	private void btnRefreshActionPerformed(ActionEvent evt) {
    	try {
			musicSetupModel.setQuery(querySQL);
		} catch (IllegalStateException ex) {
			JOptionPane.showMessageDialog(musicPanel, "IllegalState Error at refresh Button:" + ex.getMessage(),"Music Setup", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(musicPanel, "SQL Error at refresh Button:" + ex.getMessage(),"Music Setup", JOptionPane.ERROR_MESSAGE);
		}
	}
    private void btnExitActionPerformed(ActionEvent evt) {
    	try {
			setClosed(true);
		} catch (PropertyVetoException e) {
			JOptionPane.showMessageDialog(musicPanel, "Music Setup form error: " + e.getMessage(), "Music Setup", JOptionPane.ERROR_MESSAGE);
		}
    }

    private JButton btnDelete;
    private JButton btnExit;
    private JButton btnNew;
    private JButton btnRefresh;
    private JButton btnSave;
    private JButton btnUpdate;
    private JComboBox cmbDisc;
    private JComboBox cmbGenre;
    private JComboBox cmbLanguage;
    private JComboBox cmbType;
    private JLabel lblAlbum;
    private JLabel lblArtist;
    private JLabel lblDisc;
    private JLabel lblGenre;
    private JLabel lblLanguage;
    private JLabel lblTitle;
    private JLabel lblType;
    private JLabel lblYear;
    private JPanel musicPanel;
    private JScrollPane scrollPaneMusic;
    private JTable tblMusic;
    private JTextField txtAlbum;
    private JTextField txtArtist;
    private JTextField txtTitle;
    private JNumericField txtYear;
    private MusicMoviesTableModel musicSetupModel;
}

