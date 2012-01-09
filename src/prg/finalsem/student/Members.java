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

import com.lamatek.swingextras.JNumericField;

/**
 *
 * @author Saroj Maharjan
 */
@SuppressWarnings("serial")
public class Members extends JInternalFrame {
	
    private String userName = "root";
    private String passWord = "a1";
    private String querySQL = "select * from MEMBERS";
    private String idMembers,name,phone,address;
    
    public Members() {
    	super("Members Setup");
    	JDialog.setDefaultLookAndFeelDecorated(true);
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setBounds((screenSize.width-600)/2, (screenSize.height-400)/2, 600, 400);
        try {
			initComponents();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(panelMembers, "initialComponents problem:  " + ex.getMessage(), "Movies Setup", JOptionPane.ERROR_MESSAGE);
		}
		disableFields();
        btnDelete.setEnabled(true);
        btnNew.setEnabled(true);
        btnSave.setEnabled(false);
        btnUpdate.setEnabled(false);
    }

    private void initComponents() throws Exception{
    	panelMembers = new JPanel();
        lblName = new JLabel("Name");
        txtName = new JTextField();
        lblPhone = new JLabel("Phone");
        lblAddress = new JLabel("Address");
        txtPhone = new JNumericField();
        txtAddress = new JTextField();
        membersTableModel = new MusicMoviesTableModel(userName,passWord,querySQL);
        tblMembers = new JTable(membersTableModel);
        scrollPaneMembers = new JScrollPane(tblMembers);
        scrollPaneMembers.setViewportView(tblMembers);
        btnNew = new JButton("New");
        btnSave = new JButton("Save");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnExit = new JButton("Exit");
        
        tblMembers.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int i = tblMembers.getSelectedRow();
				idMembers = String.valueOf(tblMembers.getValueAt(i, 0));
				name = String.valueOf(tblMembers.getValueAt(i, 1));
				phone = String.valueOf(tblMembers.getValueAt(i, 2));
				address = String.valueOf(tblMembers.getValueAt(i, 3));
				txtName.setText(name);
				txtPhone.setText(phone);
				txtAddress.setText(address);
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
        
        btnUpdate.addActionListener(new ActionListener(){
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
        
        GroupLayout panelMembersLayout = new GroupLayout(panelMembers);
        panelMembers.setLayout(panelMembersLayout);
        panelMembersLayout.setHorizontalGroup(
            panelMembersLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelMembersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMembersLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(panelMembersLayout.createSequentialGroup()
                        .addGroup(panelMembersLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lblAddress)
                            .addComponent(lblName)
                            .addComponent(lblPhone))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelMembersLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(txtAddress, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                            .addComponent(txtName, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                            .addComponent(txtPhone, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelMembersLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnNew, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDelete, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelMembersLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnSave, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate)
                        .addGap(66, 66, 66))
                    .addGroup(panelMembersLayout.createSequentialGroup()
                        .addComponent(scrollPaneMembers, GroupLayout.PREFERRED_SIZE, 519, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        panelMembersLayout.setVerticalGroup(
            panelMembersLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelMembersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMembersLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNew)
                    .addComponent(btnSave)
                    .addComponent(btnUpdate)
                    .addComponent(lblName))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMembersLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete)
                    .addComponent(btnExit)
                    .addComponent(lblPhone))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMembersLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddress)
                    .addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPaneMembers, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelMembers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelMembers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        pack();
        setVisible(true);
    }
    
    private void btnNewActionPerformed(ActionEvent evt) {
    	clearAll();
    	Object[] options = {"Enter New Members", "Update Old Members", "Cancel"};
    	int ans = JOptionPane.showOptionDialog(panelMembers,
    				"Would you like to enter new members or update old members?",
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
    	if(txtName.getText().equals("") || txtPhone.getText().equals("") ||
    	   txtAddress.getText().equals("")) {
  	 	   	JOptionPane.showMessageDialog(panelMembers, "Please fill all the fields" ,"Member Setup",JOptionPane.WARNING_MESSAGE);
  	 	} else {
  	 		membersTableModel.setEdit("insert into MEMBERS(NAME,PHONE,ADDRESS) values " +
  	 									"('" + txtName.getText() + "','" + txtPhone.getInteger() +
  	 									"','" + txtAddress.getText() + "')");
    		JOptionPane.showMessageDialog(panelMembers, "Successfully inserted...", "Member Setup", JOptionPane.INFORMATION_MESSAGE);
    		clearAll();
    		idMembers = null;
    		name = null;
    		phone = null;
    		address = null;
    		try {
    			membersTableModel.setQuery(querySQL);
    		} catch(SQLException ex) {
    			JOptionPane.showMessageDialog(panelMembers, "Error while inserting data:  " + ex.getMessage(), "SQL Statement Error", JOptionPane.ERROR_MESSAGE);
    		} catch(Exception ex) {
    			JOptionPane.showMessageDialog(panelMembers, "Error while inserting data:  " + ex.getMessage(), "General Error", JOptionPane.ERROR_MESSAGE);
    		}
    		clearAll();
    		disableFields();
    		btnSave.setEnabled(false);
    		btnNew.setEnabled(true);
  	 	}
    }
    
    private void btnDeleteActionPerformed(ActionEvent evt) {
    	try {
    		if(idMembers.equals(null) || name.equals(null) || phone.equals(null) ||
    			address.equals(null)) {
    			JOptionPane.showMessageDialog(panelMembers, "Select the member id to delete from table...","Member Setup",JOptionPane.ERROR_MESSAGE);
    		} else {
    			String deleteSQL = "delete from MEMBERS where ID='" + idMembers + "'";
    			membersTableModel.setEdit(deleteSQL);
	    		JOptionPane.showMessageDialog(panelMembers, "Successfully deleted...","Member Setup",JOptionPane.INFORMATION_MESSAGE);
	    		idMembers = null;
	    		name = null;
	    		phone = null;
	    		address = null;
	    		membersTableModel.setQuery(querySQL);
    		}
    	} catch(SQLException ex) {
    		JOptionPane.showMessageDialog(panelMembers, "SQL Statement Error: " + ex.getMessage(),"SQL Statement Error",JOptionPane.ERROR_MESSAGE);
    	} catch(Exception ex) {
    		JOptionPane.showMessageDialog(panelMembers, "Select the member id to delete from table: " + ex.getMessage(),"General Error",JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    private void btnUpdateActionPerformed(ActionEvent evt) {
    	if(txtName.getText().equals("") || txtPhone.getText().equals("") || 
    			txtAddress.getText().equals("")) {
    		JOptionPane.showMessageDialog(panelMembers, "Please fill all the fields" ,"Member Setup",JOptionPane.WARNING_MESSAGE);
    	} else {
    	    try {
    	    	membersTableModel.setEdit("Update " + membersTableModel.getTableName(1) + 
    	    								" Set NAME = '" + txtName.getText() +
    	    		   						"', PHONE = '" + txtPhone.getInteger () + 
    	    		   						"', ADDRESS = '" + txtAddress.getText() +
    	    		   						"' where ID= " + Integer.parseInt(idMembers) + "");
    	    	membersTableModel.setQuery(querySQL);
    		   	JOptionPane.showMessageDialog(panelMembers, "Successfully updated","Member Setup",JOptionPane.INFORMATION_MESSAGE);
    		   	btnUpdate.setEnabled(false);
    		   	btnSave.setEnabled(false);
    		   	btnNew.setEnabled(true);
    		  	idMembers = null;
    			name = null;
    			phone = null;
    			address = null;
    	    	disableFields();
    	    } catch(SQLException ex) {
    	   		JOptionPane.showMessageDialog(panelMembers, "SQL Statement Error: " + ex.getMessage(),"SQL Statement Error",JOptionPane.ERROR_MESSAGE);
    	    } catch(Exception ex) {
    	    	JOptionPane.showMessageDialog(panelMembers, "Please select any id for deleting: " + ex.getMessage(),"General Error",JOptionPane.ERROR_MESSAGE);
    	   	}
    	}
    }
    
    private void btnExitActionPerformed(ActionEvent evt) {
    	try {
			setClosed(true);
		} catch (PropertyVetoException e) {
			JOptionPane.showMessageDialog(panelMembers, "Members Setup form error: " + e.getMessage(), "Member Setup", JOptionPane.ERROR_MESSAGE);
		}
    }
    
    private void clearAll() {
    	txtName.setText("");
    	txtPhone.setText("");
    	txtAddress.setText("");
    }
    
    private void disableFields() {
    	txtName.setEditable(false);
    	txtPhone.setEditable(false);
    	txtAddress.setEditable(false);
    }
    
    private void enableFields() {
    	txtName.setEditable(true);
    	txtPhone.setEditable(true);
    	txtAddress.setEditable(true);
    }

    private JButton btnDelete;
    private JButton btnExit;
    private JButton btnNew;
    private JButton btnSave;
    private JButton btnUpdate;
    private JLabel lblAddress;
    private JLabel lblName;
    private JLabel lblPhone;
    private JScrollPane scrollPaneMembers;
    private JTable tblMembers;
    private JTextField txtAddress;
    private JNumericField txtPhone;
    private JTextField txtName;
    private JPanel panelMembers;
    private MusicMoviesTableModel membersTableModel;

}
