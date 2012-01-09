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


@SuppressWarnings("serial")
public class Disc extends JInternalFrame {
	
    private String userName = "root";
    private String passWord = "a1";
    private String querySQL = "select * from DISC";
    private String discField;
    
    public Disc() {
    	super("Disc Setup");
    	JDialog.setDefaultLookAndFeelDecorated(true);
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setBounds((screenSize.width-400)/2, (screenSize.height-300)/2, 400, 300);
        try {
			initComponents();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private void initComponents() throws Exception{
    	panelDisc = new JPanel();
        lblDiscType = new JLabel("Disc Type");
        txtDiscType = new JTextField();
        discModel = new MusicMoviesTableModel(userName,passWord,querySQL);
        tblDiscType = new JTable(discModel);
        scrollPaneDisc = new JScrollPane(tblDiscType);
    	scrollPaneDisc.setViewportView(tblDiscType);
        btnInsert = new JButton("Insert");
        btnDelete = new JButton("Delete");
        btnExit = new JButton("Exit");
        
        tblDiscType.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int i = tblDiscType.getSelectedRow();
				discField = (String) tblDiscType.getValueAt(i, 0);
				System.out.println(discField);
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
        
        btnInsert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				mnuInsertActionPerformed(evt);
			}
        });
        
        btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				mnuDeleteActionPerformed(evt);
			}
        });
        
        btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				mnuExitActionPerformed(evt);
			}
        });
        
        GroupLayout panelMusicDiscLayout = new GroupLayout(panelDisc);
        panelDisc.setLayout(panelMusicDiscLayout);
        panelMusicDiscLayout.setHorizontalGroup(
            panelMusicDiscLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelMusicDiscLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMusicDiscLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(scrollPaneDisc, GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.LEADING, panelMusicDiscLayout.createSequentialGroup()
                        .addComponent(lblDiscType)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiscType, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMusicDiscLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnInsert)
                    .addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelMusicDiscLayout.setVerticalGroup(
            panelMusicDiscLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelMusicDiscLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMusicDiscLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDiscType)
                    .addComponent(txtDiscType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInsert))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMusicDiscLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneDisc, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelMusicDiscLayout.createSequentialGroup()
                        .addComponent(btnDelete)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExit)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelDisc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelDisc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        
        setVisible(true);
        pack();
    }
    
    public void mnuInsertActionPerformed(ActionEvent evt) {
    	String discType = txtDiscType.getText();
    	System.out.println(discType);
    	if(discType.equals("")) {
    		JOptionPane.showMessageDialog(panelDisc, "Please type the disc type...","Disc Type", JOptionPane.ERROR_MESSAGE);
    	} else {
    		discModel.setEdit("insert into DISC values ('" + discType + "')");
    		JOptionPane.showMessageDialog(panelDisc, "Successfully inserted...", "Disc Type", JOptionPane.INFORMATION_MESSAGE);
    		txtDiscType.setText("");
    		 try {
    			 discModel.setQuery(querySQL);
    		} catch(SQLException ex) {
    			JOptionPane.showMessageDialog(panelDisc, "SQL Statement Error: " + ex.getMessage(),"Disc Type",JOptionPane.ERROR_MESSAGE);
    		} 
    	}
    }
    
    private void mnuDeleteActionPerformed(ActionEvent evt) {
    	try {
    		if(discField.equals(null)) {
    			JOptionPane.showMessageDialog(panelDisc, "Select the disc to delete from table...","Disc Type",JOptionPane.ERROR_MESSAGE);
    		} else {
    			String deleteSQL = "delete from DISC where DISC='"+ discField +"'";
    			discModel.setEdit(deleteSQL);
	    		JOptionPane.showMessageDialog(panelDisc, "Successfully deleted...","Disc Type",JOptionPane.INFORMATION_MESSAGE);
	    		discField = null;
	    		discModel.setQuery(querySQL);
    		}
    	} catch(SQLException ex) {
    		JOptionPane.showMessageDialog(panelDisc, "SQL Statement Error: " + ex.getMessage(),"SQL Statement Error",JOptionPane.ERROR_MESSAGE);
    	} catch(Exception ex) {
    		JOptionPane.showMessageDialog(panelDisc, "Select the disc to delete from table: " + ex.getMessage(),"General Error",JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    private void mnuExitActionPerformed(ActionEvent evt) {
    	try {
			setClosed(true);
		} catch (PropertyVetoException e) {
			JOptionPane.showMessageDialog(panelDisc, "Disc Form error: " + e.getMessage());
		}
    }
    
    private JButton btnDelete;
    private JButton btnExit;
    private JButton btnInsert;
    private JLabel lblDiscType;
    private JPanel panelDisc;
    private JScrollPane scrollPaneDisc;
    private JTable tblDiscType;
    private JTextField txtDiscType;
    private MusicMoviesTableModel discModel;
}
