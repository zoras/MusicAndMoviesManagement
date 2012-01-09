package prg.finalsem.student;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.GroupLayout;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

/**
 *
 * @author Saroj Maharjan
 */
@SuppressWarnings("serial")
public class MDIFrame extends JFrame {
	
    public MDIFrame() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void initComponents() {
    	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Music N' Movies Management System");
        
        paneDesktop = new JDesktopPane();
        paneDesktop.setBackground(Color.DARK_GRAY);
//        {
//	        ImageIcon bg = new ImageIcon("images.jpg"); 
//	        public void paintComponent(Graphics g) {
//	             super.paintComponents(g);
//	             g.drawImage(bg.getImage(), 0, 0, paneDesktop );
//	        }
//        };
        mnuBar = new JMenuBar();
        
        mnuMainSetup = new JMenu("Main Setup");
        mnuMusic = new JMenuItem("Music Setup");
        mnuMovies = new JMenuItem("Movies Setup");
        mnuMusicGenre = new JMenuItem("Music Genre");
        mnuMusicType = new JMenuItem("Music Type");
        mnuMoviesType = new JMenuItem("Movies Type");
        mnuMembers = new JMenuItem("Members");
        mnuDisc = new JMenuItem("Disc");
        mnuLanguage = new JMenuItem("Language");
        
        mnuProcess = new JMenu("Process");
        mnuMusicIssue = new JMenuItem("Music Issue N' Return");
        mnuMoviesIssue = new JMenuItem("Movies Issue N' Return");
        mnuMusicSearch = new JMenuItem("Music Search");
        mnuMoviesSearch = new JMenuItem("Movies Search");
        
        mnuHelp = new JMenu("Help");
        mnuAboutUs = new JMenuItem("About Us");
        mnuExit = new JMenuItem("Exit");

        mnuMainSetup.add(mnuMusic);
        mnuMainSetup.add(mnuMusicGenre);
        mnuMainSetup.add(mnuMusicType);
        mnuMainSetup.add(mnuMovies);
        mnuMainSetup.add(mnuMoviesType);
        mnuMainSetup.add(mnuMembers);
        mnuMainSetup.add(mnuDisc);
        mnuMainSetup.add(mnuLanguage);
        mnuBar.add(mnuMainSetup);
        
        mnuProcess.add(mnuMusicIssue);
        mnuProcess.add(mnuMoviesIssue);
        mnuProcess.add(mnuMusicSearch);
        mnuProcess.add(mnuMoviesSearch);
        mnuBar.add(mnuProcess);
 
        mnuHelp.add(mnuAboutUs);
        mnuHelp.add(mnuExit);
        mnuBar.add(mnuHelp);
        setJMenuBar(mnuBar);
        
        mnuMusic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				mnuMusicActionPerformed(evt);
			}
        });
        
        mnuMovies.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				mnuMoviesActionPerformed(evt);
			}
        });
        
        mnuDisc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				mnuMusicDiscActionPerformed(evt);
			}
        });
        
        mnuMusicGenre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				mnuMusicGenreActionPerformed(evt);
			}
        });
        
        mnuMusicType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				mnuMusicTypeActionPerformed(evt);
			}
        });
        
        mnuMoviesType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				mnuMoviesTypeActionPerformed(evt);
			}
        });
        
        mnuLanguage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				mnuLanguageActionPerformed(evt);
			}
        });
        
        mnuMembers.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt) {
				mnuMembersActionPerformed(evt);
			}
        });
        
        mnuMusicIssue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				mnuMusicIssueActionPerformed(evt);
			}
        });
        
        mnuMoviesIssue.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt) {
				mnuMoviesIssueActionPerformed(evt);
			}
        });
        
        mnuMusicSearch.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt) {
				mnuMusicSearchActionPerformed(evt);
			}
        });
        
        mnuMoviesSearch.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt) {
				mnuMoviesSearchActionPerformed(evt);
			}
        });
        
        mnuAboutUs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				mnuAboutUsActionPerformed(evt);
			}
        });
        
        mnuExit.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent evt) {
                mnuExitActionPerformed(evt);
            }
        });
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(paneDesktop, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(paneDesktop, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
        );
        
        setVisible(true);
        pack();
    }                     

    private void mnuMusicActionPerformed(ActionEvent evt) {
    	frameMusic = new MusicSetup();
    	showFrame(frameMusic);
    }
    
    private void mnuMoviesActionPerformed(ActionEvent evt) {
    	frameMovies = new MoviesSetup();
    	showFrame(frameMovies);
    }
    
    private void mnuMusicDiscActionPerformed(ActionEvent evt) {
    	frameDisc = new Disc();
    	showFrame(frameDisc);
    }
    
    private void mnuMusicGenreActionPerformed(ActionEvent evt) {
    	frameMusicGenre = new MusicGenre();
    	showFrame(frameMusicGenre);
    }
    
    private void mnuMusicTypeActionPerformed(ActionEvent evt) {
    	frameMusicType = new MusicType();
    	showFrame(frameMusicType);
    }
    
    private void mnuMoviesTypeActionPerformed(ActionEvent evt) {
    	frameMoviesType = new MoviesType();
    	showFrame(frameMoviesType);
    }
    
    private void mnuLanguageActionPerformed(ActionEvent evt) {
    	frameLanugage = new Language();
    	showFrame(frameLanugage);
    }
    
    private void mnuMembersActionPerformed(ActionEvent evt) {
    	frameMembers = new Members();
    	showFrame(frameMembers);
    }
    
    private void mnuMusicIssueActionPerformed(ActionEvent evt) {
    	frameMusicIssueNReturn = new MusicIssueNReturn();
    	showFrame(frameMusicIssueNReturn);
    }
    
    private void mnuMoviesIssueActionPerformed(ActionEvent evt) {
    	frameMoviesIssueNReturn = new MoviesIssueNReturn();
    	showFrame(frameMoviesIssueNReturn);
    }
    
    private void mnuMusicSearchActionPerformed(ActionEvent evt) {
    	frameMusicSearch = new MusicSearch();
    	showFrame(frameMusicSearch);
    }
    
    private void mnuMoviesSearchActionPerformed(ActionEvent evt) {
    	frameMoviesSearch = new MoviesSearch();
    	showFrame(frameMoviesSearch);
    }
    
    private void mnuAboutUsActionPerformed(ActionEvent evt) {
    	frameAboutUs = new AboutUs();
    	showFrame(frameAboutUs);
    }
    
    private void mnuExitActionPerformed(ActionEvent evt) {                                        
        System.out.println("Exiting....");
        System.exit(0);
    }
    
    private void showFrame(JInternalFrame internalFrame) {
		paneDesktop.add(internalFrame);
		try {
			internalFrame.setSelected(true);
		}
		catch(PropertyVetoException ex) {
			System.out.println(ex.getMessage());
		}
	}
    
    private JMenuItem mnuAboutUs;
    private JMenuBar mnuBar;
    private JMenuItem mnuExit;
    private JMenu mnuHelp;
    private JMenuItem mnuMembers;
    private JMenuItem mnuMovies;
    private JMenuItem mnuDisc;
    private JMenuItem mnuLanguage;
    private JMenuItem mnuMoviesIssue;
    private JMenuItem mnuMoviesSearch;
    private JMenuItem mnuMoviesType;
    private JMenuItem mnuMusic;
    private JMenuItem mnuMusicGenre;
    private JMenuItem mnuMusicIssue;
    private JMenuItem mnuMusicSearch;
    private JMenu mnuMainSetup;
    private JMenuItem mnuMusicType;
    private JMenu mnuProcess;
    private JDesktopPane paneDesktop;   
    private JInternalFrame frameMusic,frameMusicGenre,
    frameDisc,frameMusicType,frameMovies,frameMoviesType,
    frameLanugage,frameMembers,frameMusicIssueNReturn,
    frameMoviesIssueNReturn,frameMusicSearch,frameMoviesSearch,frameAboutUs;
}
