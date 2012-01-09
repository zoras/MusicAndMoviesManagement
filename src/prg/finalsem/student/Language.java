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
public class Language extends JInternalFrame {
	
    private String userName = "root";
    private String passWord = "a1";
    private String querySQL = "select * from LANGUAGE";
    private String languageField;
    
    public Language() {
    	super("Language Setup");
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
    	panelLanguage = new JPanel();
    	lblLanguageType = new JLabel("Language");
        txtLanguageType = new JTextField();
        languageModel = new MusicMoviesTableModel(userName,passWord,querySQL);
        tblLanguageType = new JTable(languageModel);
        scrollPaneLanguage = new JScrollPane(tblLanguageType);
        scrollPaneLanguage.setViewportView(tblLanguageType);
        btnInsert = new JButton("Insert");
        btnDelete = new JButton("Delete");
        btnExit = new JButton("Exit");
        
        tblLanguageType.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int i = tblLanguageType.getSelectedRow();
				languageField = (String) tblLanguageType.getValueAt(i, 0);
				System.out.println(languageField);
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
        
        GroupLayout panelMusicDiscLayout = new GroupLayout(panelLanguage);
        panelLanguage.setLayout(panelMusicDiscLayout);
        panelMusicDiscLayout.setHorizontalGroup(
            panelMusicDiscLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelMusicDiscLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMusicDiscLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(scrollPaneLanguage, GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addGroup(GroupLayout.Alignment.LEADING, panelMusicDiscLayout.createSequentialGroup()
                        .addComponent(lblLanguageType)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLanguageType, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(lblLanguageType)
                    .addComponent(txtLanguageType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInsert))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMusicDiscLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneLanguage, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
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
            .addComponent(panelLanguage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelLanguage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        
        setVisible(true);
        pack();
    }
    
    public void mnuInsertActionPerformed(ActionEvent evt) {
    	String languageType = txtLanguageType.getText();
    	System.out.println(languageType);
    	if(languageType.equals("")) {
    		JOptionPane.showMessageDialog(panelLanguage, "Please type the language...","Language Setup", JOptionPane.ERROR_MESSAGE);
    	} else {
    		languageModel.setEdit("insert into LANGUAGE values ('" + languageType + "')");
    		JOptionPane.showMessageDialog(panelLanguage, "Successfully inserted...", "Language Setup", JOptionPane.INFORMATION_MESSAGE);
    		txtLanguageType.setText("");
    		 try {
    			 languageModel.setQuery(querySQL);
    		} catch(SQLException ex) {
    			JOptionPane.showMessageDialog(panelLanguage, "SQL Statement Error: " + ex.getMessage(),"Language Setup",JOptionPane.ERROR_MESSAGE);
    		} 
    	}
    }
    
    private void mnuDeleteActionPerformed(ActionEvent evt) {
    	try {
    		if(languageField.equals(null)) {
    			JOptionPane.showMessageDialog(panelLanguage, "Select the language to delete from table...","Language Setup",JOptionPane.ERROR_MESSAGE);
    		} else {
    			String deleteSQL = "delete from LANGUAGE where LANGUAGE='"+ languageField +"'";
    			languageModel.setEdit(deleteSQL);
	    		JOptionPane.showMessageDialog(panelLanguage, "Successfully deleted...","Language Setup",JOptionPane.INFORMATION_MESSAGE);
	    		languageField = null;
	    		languageModel.setQuery(querySQL);
    		}
    	} catch(SQLException ex) {
    		JOptionPane.showMessageDialog(panelLanguage, "SQL Statement Error: " + ex.getMessage(),"SQL Statement Error",JOptionPane.ERROR_MESSAGE);
    	} catch(Exception ex) {
    		JOptionPane.showMessageDialog(panelLanguage, "Select the disc to delete from table: " + ex.getMessage(),"General Error",JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    private void mnuExitActionPerformed(ActionEvent evt) {
    	try {
			setClosed(true);
		} catch (PropertyVetoException e) {
			JOptionPane.showMessageDialog(panelLanguage, "Disc Form error: " + e.getMessage());
		}
    }
    
    private JButton btnDelete;
    private JButton btnExit;
    private JButton btnInsert;
    private JLabel lblLanguageType;
    private JPanel panelLanguage;
    private JScrollPane scrollPaneLanguage;
    private JTable tblLanguageType;
    private JTextField txtLanguageType;
    private MusicMoviesTableModel languageModel;
}
