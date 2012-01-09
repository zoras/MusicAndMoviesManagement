package prg.finalsem.student;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

/**
 *
 * @author Saroj Maharjan
 */
@SuppressWarnings("serial")
public class MusicGenre extends JInternalFrame {

	private String userName = "root";
    private String passWord = "a1";
    private String querySQL = "select * from MUSIC_GENRE";
    private String genreField;
    
    public MusicGenre() {
    	super("Music Genre");
    	JDialog.setDefaultLookAndFeelDecorated(true);
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setBounds((screenSize.width-400)/2, (screenSize.height-300)/2, 400, 300);
	    try {
			initComponents();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private void initComponents() throws Exception {
        panelMusicGenre = new JPanel();
        txtMusicGenre = new JTextField();
        lblMusicGenre = new JLabel("Music Genre");
        musicGenreModel = new MusicMoviesTableModel(userName,passWord,querySQL);
        tblMusicGenre = new JTable(musicGenreModel);
        scrollPaneMusicGenre = new JScrollPane(tblMusicGenre);
        scrollPaneMusicGenre.setViewportView(tblMusicGenre);
        btnInsert = new JButton("Insert");
        btnDelete = new JButton("Delete");
        btnExit = new JButton("Exit");
        
        tblMusicGenre.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tblMusicGenre.getSelectedRow();
				genreField = String.valueOf(tblMusicGenre.getValueAt(i, 0));
				System.out.println(genreField);
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
        
        btnInsert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnInsertActionPerformed(evt);
			}
        });
        
        btnDelete.addActionListener(new ActionListener() {
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

        GroupLayout panelMusicTypeLayout = new GroupLayout(panelMusicGenre);
        panelMusicGenre.setLayout(panelMusicTypeLayout);
        panelMusicTypeLayout.setHorizontalGroup(
            panelMusicTypeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelMusicTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMusicTypeLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPaneMusicGenre, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelMusicTypeLayout.createSequentialGroup()
                        .addComponent(lblMusicGenre)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMusicGenre)))
                .addGap(18, 18, 18)
                .addGroup(panelMusicTypeLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInsert, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMusicTypeLayout.setVerticalGroup(
            panelMusicTypeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelMusicTypeLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(panelMusicTypeLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMusicGenre)
                    .addComponent(txtMusicGenre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInsert))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMusicTypeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneMusicGenre, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelMusicTypeLayout.createSequentialGroup()
                        .addComponent(btnDelete)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExit)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelMusicGenre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelMusicGenre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        setVisible(true);
        pack();
    }
    
    private void btnInsertActionPerformed(ActionEvent evt) {
    	genreField = txtMusicGenre.getText();
    	System.out.println(genreField);
    	if(genreField.equals("")) {
    		JOptionPane.showMessageDialog(panelMusicGenre, "Please type the genre type...","Music Genre", JOptionPane.ERROR_MESSAGE);
    	} else {
    		musicGenreModel.setEdit("insert into MUSIC_GENRE values ('" + genreField + "')");
    		JOptionPane.showMessageDialog(panelMusicGenre, "Successfully inserted...", "Music Genre", JOptionPane.INFORMATION_MESSAGE);
    		txtMusicGenre.setText("");
    		 try {
    			 musicGenreModel.setQuery("select * from MUSIC_GENRE");
    		} catch(SQLException ex) {
    			JOptionPane.showMessageDialog(panelMusicGenre, "SQL Statement Error: " + ex.getMessage());
    		} 
    	}
    }
    
    private void btnDeleteActionPerformed(ActionEvent evt) {
    	try {
    		if(genreField.equals(null)) {
    			JOptionPane.showMessageDialog(panelMusicGenre, "Select the disc to delete from table...","Delete",JOptionPane.ERROR_MESSAGE);
    		} else {
    			String deleteSQL = "delete from MUSIC_GENRE where GENRE='"+ genreField +"'";
    			musicGenreModel.setEdit(deleteSQL);
	    		JOptionPane.showMessageDialog(panelMusicGenre, "Successfully deleted...","Delete",JOptionPane.INFORMATION_MESSAGE);
	    		genreField = null;
	    		musicGenreModel.setQuery(querySQL);
    		}
    	} catch(SQLException ex) {
    		JOptionPane.showMessageDialog(panelMusicGenre, "SQL Statement Error: " + ex.getMessage(),"SQL Statement Error",JOptionPane.ERROR_MESSAGE);
    	} catch(Exception ex) {
    		JOptionPane.showMessageDialog(panelMusicGenre, "Select the genre to delete from table: " + ex.getMessage(),"General Error",JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    private void btnExitActionPerformed(ActionEvent evt) {
    	try {
			setClosed(true);
		} catch (PropertyVetoException e) {
			JOptionPane.showMessageDialog(panelMusicGenre, "Music Genre form error: " + e.getMessage());
		}
    }
    
    private JButton btnDelete;
    private JButton btnExit;
    private JButton btnInsert;
    private JLabel lblMusicGenre;
    private JPanel panelMusicGenre;
    private JScrollPane scrollPaneMusicGenre;
    private JTable tblMusicGenre;
    private JTextField txtMusicGenre;
    private MusicMoviesTableModel musicGenreModel;
}

