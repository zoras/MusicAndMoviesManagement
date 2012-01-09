package prg.finalsem.student;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Saroj Maharjan
 */
@SuppressWarnings("serial")
public class AboutUs extends JInternalFrame {

    public AboutUs() {
    	super("About Us");
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setBounds((screenSize.width-350)/2, (screenSize.height-300)/2, 350, 300);
        initComponents();
    }

    private void initComponents() {
        lblTitle = new JLabel("Music N' Movies Management System");
        lblDescription = new JLabel("A Simple Music and Movies Management System");
        lblDevelopedBy = new JLabel("Developed By:");
        lblPurpose = new JLabel("Purpose:");
        applblDevelopedBy = new JLabel("Saroj Maharjan (Msc IT 4th Semseter)");
        applblPurpose = new JLabel("Project Work");
        btnClose = new JButton("Close");
        btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				btnCloseActionPerformed(evt);
			}
        });

        lblTitle.setFont(lblTitle.getFont().deriveFont(lblTitle.getFont().getStyle() | java.awt.Font.BOLD, lblTitle.getFont().getSize()+4));
        lblDevelopedBy.setFont(lblDevelopedBy.getFont().deriveFont(lblDevelopedBy.getFont().getStyle() | java.awt.Font.BOLD));
        lblPurpose.setFont(lblPurpose.getFont().deriveFont(lblPurpose.getFont().getStyle() | java.awt.Font.BOLD));
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescription)
                    .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDevelopedBy)
                            .addComponent(lblPurpose))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(applblPurpose)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnClose))
                            .addComponent(applblDevelopedBy))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDescription)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDevelopedBy)
                    .addComponent(applblDevelopedBy))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPurpose)
                            .addComponent(applblPurpose)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        setVisible(true);
        pack();
    }
    
    private void btnCloseActionPerformed(ActionEvent evt) {
    	try {
			setClosed(true);
		} catch (PropertyVetoException e) {
			JOptionPane.showMessageDialog(null, "About Form error: " + e.getMessage());
		}
    }

    private JButton btnClose;
    private JLabel lblTitle,lblDescription,
    lblDevelopedBy,lblPurpose,applblDevelopedBy,applblPurpose;
}
