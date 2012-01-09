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
public class MoviesType extends JInternalFrame {
	
    private String userName = "root";
    private String passWord = "a1";
    private String querySQL = "select * from MOVIES_TYPE";
    private String moviesTypeField;

    public MoviesType() {
    	super("Movies Type");
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
        panelMoviesType = new JPanel();
        txtMoviesType = new JTextField();
        lblMoviesType = new JLabel("Movies Type");
        moviesTypeModel = new MusicMoviesTableModel(userName,passWord,querySQL);
        tblMoviesType = new JTable(moviesTypeModel);
        scrollPaneMoviesType = new JScrollPane(tblMoviesType);
        scrollPaneMoviesType.setViewportView(tblMoviesType);
        btnInsert = new JButton("Insert");
        btnDelete = new JButton("Delete");
        btnExit = new JButton("Exit");
        
        tblMoviesType.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tblMoviesType.getSelectedRow();
				moviesTypeField = (String) tblMoviesType.getValueAt(i, 0);
				System.out.println(moviesTypeField);
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

        GroupLayout panelMusicTypeLayout = new GroupLayout(panelMoviesType);
        panelMoviesType.setLayout(panelMusicTypeLayout);
        panelMusicTypeLayout.setHorizontalGroup(
            panelMusicTypeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelMusicTypeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMusicTypeLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollPaneMoviesType, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelMusicTypeLayout.createSequentialGroup()
                        .addComponent(lblMoviesType)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMoviesType)))
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
                    .addComponent(lblMoviesType)
                    .addComponent(txtMoviesType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInsert))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMusicTypeLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneMoviesType, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
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
            .addComponent(panelMoviesType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelMoviesType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        setVisible(true);
        pack();
    }
    
    private void btnInsertActionPerformed(ActionEvent evt) {
    	String moviesType = txtMoviesType.getText();
    	System.out.println(moviesType);
    	if(moviesType.equals("")) {
    		JOptionPane.showMessageDialog(panelMoviesType, "Please type the movies type...","Movies Type", JOptionPane.ERROR_MESSAGE);
    	} else {
    		moviesTypeModel.setEdit("insert into MOVIES_TYPE values ('" + moviesType + "')");
    		JOptionPane.showMessageDialog(panelMoviesType, "Successfully inserted...", "Movies Type", JOptionPane.INFORMATION_MESSAGE);
    		txtMoviesType.setText("");
    		 try {
    			 moviesTypeModel.setQuery(querySQL);
    		} catch(SQLException ex) {
    			JOptionPane.showMessageDialog(panelMoviesType, "SQL Statement Error: " + ex.getMessage(),"SQL Problem", JOptionPane.ERROR_MESSAGE);
    		} 
    	}
    }
    
    private void btnDeleteActionPerformed(ActionEvent evt) {
    	try {
    		if(moviesTypeField.equals(null)) {
    			JOptionPane.showMessageDialog(panelMoviesType, "Select the movies type to delete from table...","Delete",JOptionPane.ERROR_MESSAGE);
    		} else {
    			String deleteSQL = "delete from MOVIES_TYPE where TYPE='"+ moviesTypeField +"'";
	    		moviesTypeModel.setEdit(deleteSQL);
	    		JOptionPane.showMessageDialog(panelMoviesType, "Successfully deleted...","Delete",JOptionPane.INFORMATION_MESSAGE);
	    		moviesTypeField = null;
	    		moviesTypeModel.setQuery(querySQL);
    		}
    	} catch(SQLException ex) {
    		JOptionPane.showMessageDialog(panelMoviesType, "SQL Statement Error: " + ex.getMessage(),"SQL Statement Error",JOptionPane.ERROR_MESSAGE);
    	} catch(Exception ex) {
    		JOptionPane.showMessageDialog(panelMoviesType, "Select the movies type to delete from table: " + ex.getMessage(),"General Error",JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    private void btnExitActionPerformed(ActionEvent evt) {
    	try {
			setClosed(true);
		} catch (PropertyVetoException e) {
			JOptionPane.showMessageDialog(panelMoviesType, "Movies Type form error: " + e.getMessage());
		}
    }
    
    private JButton btnDelete;
    private JButton btnExit;
    private JButton btnInsert;
    private JLabel lblMoviesType;
    private JPanel panelMoviesType;
    private JScrollPane scrollPaneMoviesType;
    private JTable tblMoviesType;
    private JTextField txtMoviesType;
    private MusicMoviesTableModel moviesTypeModel;
}

