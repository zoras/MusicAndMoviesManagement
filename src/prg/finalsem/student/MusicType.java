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
public class MusicType extends JInternalFrame {
	
    private String userName = "root";
    private String passWord = "a1";
    private String querySQL = "select * from MUSIC_TYPE";
    private String musicTypeField;

    public MusicType() {
    	super("Music Type");
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
        panelMusicType = new JPanel();
        txtMusicType = new JTextField();
        lblMusicType = new JLabel("Music Type");
        musicTypeModel = new MusicMoviesTableModel(userName,passWord,querySQL);
        tblMusicType = new JTable(musicTypeModel);
        scrollPaneMusicType = new JScrollPane(tblMusicType);
        scrollPaneMusicType.setViewportView(tblMusicType);
        btnInsert = new JButton("Insert");
        btnDelete = new JButton("Delete");
        btnExit = new JButton("Exit");
        
        tblMusicType.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tblMusicType.getSelectedRow();
				musicTypeField = (String) tblMusicType.getValueAt(i, 0);
				System.out.println(musicTypeField);
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

        GroupLayout panelMusicTypeLayout = new GroupLayout(panelMusicType);
        panelMusicType.setLayout(panelMusicTypeLayout);
        panelMusicTypeLayout.setHorizontalGroup(
            panelMusicTypeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelMusicTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMusicTypeLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPaneMusicType, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelMusicTypeLayout.createSequentialGroup()
                        .addComponent(lblMusicType)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMusicType)))
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
                    .addComponent(lblMusicType)
                    .addComponent(txtMusicType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInsert))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMusicTypeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneMusicType, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
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
            .addComponent(panelMusicType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelMusicType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        setVisible(true);
        pack();
    }
    
    private void btnInsertActionPerformed(ActionEvent evt) {
    	String musicType = txtMusicType.getText();
    	System.out.println(musicType);
    	if(musicType.equals("")) {
    		JOptionPane.showMessageDialog(panelMusicType, "Please type the music type...","Music Type", JOptionPane.ERROR_MESSAGE);
    	} else {
    		musicTypeModel.setEdit("insert into MUSIC_TYPE values ('" + musicType + "')");
    		JOptionPane.showMessageDialog(panelMusicType, "Successfully inserted...", "Music Type", JOptionPane.INFORMATION_MESSAGE);
    		txtMusicType.setText("");
    		 try {
        		 musicTypeModel.setQuery(querySQL);
    		} catch(SQLException ex) {
    			JOptionPane.showMessageDialog(panelMusicType, "SQL Statement Error: " + ex.getMessage(),"SQL Problem", JOptionPane.ERROR_MESSAGE);
    		} 
    	}
    }
    
    private void btnDeleteActionPerformed(ActionEvent evt) {
    	try {
    		if(musicTypeField.equals(null)) {
    			JOptionPane.showMessageDialog(panelMusicType, "Select the music type to delete from table...","Delete",JOptionPane.ERROR_MESSAGE);
    		} else {
    			String deleteSQL = "delete from MUSIC_TYPE where TYPE='"+ musicTypeField +"'";
	    		musicTypeModel.setEdit(deleteSQL);
	    		JOptionPane.showMessageDialog(panelMusicType, "Successfully deleted...","Delete",JOptionPane.INFORMATION_MESSAGE);
	    		musicTypeField = null;
	    		musicTypeModel.setQuery(querySQL);
    		}
    	} catch(SQLException ex) {
    		JOptionPane.showMessageDialog(panelMusicType, "SQL Statement Error: " + ex.getMessage(),"SQL Statement Error",JOptionPane.ERROR_MESSAGE);
    	} catch(Exception ex) {
    		JOptionPane.showMessageDialog(panelMusicType, "Select the music type to delete from table: " + ex.getMessage(),"General Error",JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    private void btnExitActionPerformed(ActionEvent evt) {
    	try {
			setClosed(true);
		} catch (PropertyVetoException e) {
			JOptionPane.showMessageDialog(panelMusicType, "Music Type form error: " + e.getMessage());
		}
    }
    
    private JButton btnDelete;
    private JButton btnExit;
    private JButton btnInsert;
    private JLabel lblMusicType;
    private JPanel panelMusicType;
    private JScrollPane scrollPaneMusicType;
    private JTable tblMusicType;
    private JTextField txtMusicType;
    private MusicMoviesTableModel musicTypeModel;
}
