import javax.swing.*;


import java.awt.*;
import java.awt.event.*;

public class WindowMain extends JFrame {
    private JPanel mainPanel;
    private JFrame loginFrame;
    private Doctor doctor;

    public WindowMain() {
    	//setting size and dimentions of the window
    	doctor = new Doctor();
        setTitle("Medical App");
        setSize(700, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //medical cross icon
        ImageIcon icon = new ImageIcon(getClass().getResource("medical-cross-hi.png"));
        setIconImage(icon.getImage());
        //show the login window when the program starts
        showLoginWindow();
    }

    


    private void showLoginWindow() {
        if (loginFrame == null) {
            // Create the login window if it doesn't exist
            loginFrame = new JFrame("Login");
            loginFrame.setSize(300, 200);
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            loginFrame.setLayout(new BorderLayout());
            ImageIcon icon = new ImageIcon(getClass().getResource("medical-cross-hi.png"));
            loginFrame.setIconImage(icon.getImage());
            //creating title for the window
            JLabel titleLabel = new JLabel("Medical App Login");
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            loginFrame.add(titleLabel, BorderLayout.NORTH);

            //Fields for the user input
            JPanel inputPanel = new JPanel(new GridLayout(4, 2));
            JLabel usernameLabel = new JLabel("Username:");
            JTextField usernameField = new JTextField();
            JLabel passwordLabel = new JLabel("Password:");
            JPasswordField passwordField = new JPasswordField();
            JButton loginButton = new JButton("Login");
            JButton registerButton = new JButton("Register");

            inputPanel.add(usernameLabel);
            inputPanel.add(usernameField);
            inputPanel.add(passwordLabel);
            inputPanel.add(passwordField);
            inputPanel.add(new JLabel());
            inputPanel.add(loginButton);
            inputPanel.add(new JLabel());
            inputPanel.add(registerButton);

            loginFrame.add(inputPanel, BorderLayout.CENTER);

            //If login button is pressed, then check if the user had valid credentials
            loginButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    char[] password = passwordField.getPassword();
                    if (isValidCredentials(username, password)) {
                        JOptionPane.showMessageDialog(null, "Login successful!");
                        loginFrame.dispose(); // Close the login window
                        showMainWindow(username); // Display the main window after successful login
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
                    }
                }
            });

      
            registerButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Open registration window
                    openRegistrationWindow();
                }
            });
        } else {
            // Clear text fields if login frame is being reused
            Container contentPane = loginFrame.getContentPane();
            if (contentPane instanceof JPanel) {
                for (Component component : ((JPanel) contentPane).getComponents()) {
                    if (component instanceof JTextField) {
                        ((JTextField) component).setText("");
                    }
                }
            }
        }

        loginFrame.setVisible(true);
    }


    // Registration Window, class is called when user registers 
    private void openRegistrationWindow() {
        RegistrationWindow registrationWindow = new RegistrationWindow();
        registrationWindow.setVisible(true);
    }

    private boolean isValidCredentials(String username, char[] password) {
        //No actual logic for this method yet, so it always returns true
        return true;
    }
    
    
    //This is the main window where we will see all of the tiles
    private void showMainWindow(String username) {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add some padding
        JLabel welcomeLabel = new JLabel("Welcome, " + username + "!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        //Displays hello + username 
        
        
        //This will create all of the tiles for the program
        JPanel gridPanel = new JPanel(new GridLayout(3, 3)); // 3x3 grid layout for tiles

        // Tile 1: Patient Information
        JPanel patientInfoPanel = new JPanel(new BorderLayout());
        JButton patientInfoButton = new JButton("Patient Information");
        patientInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	        PatientLogic patientInfoWindow = new PatientLogic(doctor);
            	        // Set the visibility of the new window to true
            	        patientInfoWindow.setVisible(true);
            	

                }
            });
              
                // Logic to handle when the patient information button is clicked
            
      
        gridPanel.add(patientInfoPanel);
        patientInfoPanel.add(patientInfoButton, BorderLayout.CENTER);


        JPanel healthHistoryPanel = new JPanel(new BorderLayout());
        JButton healthHistory = new JButton("Patient Health History");
		healthHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  mainPanel.removeAll();
	                mainPanel.revalidate();
	                mainPanel.repaint();
	                JLabel backLabel2 = new JLabel("Back");
	                backLabel2.setForeground(Color.BLUE);
	                backLabel2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	                backLabel2.addMouseListener(new MouseAdapter() {
	                    @Override
	                    public void mouseClicked(MouseEvent e) {
	                        // Logic to handle going back to the previous page
	                        showMainWindow(username);
	                    }
	                    
		});
	                mainPanel.add(backLabel2, BorderLayout.NORTH);
	                            }
			});
		        gridPanel.add(healthHistoryPanel);
		        healthHistoryPanel.add(healthHistory, BorderLayout.CENTER);

        JPanel FamilyTrackingPanel = new JPanel(new BorderLayout());
        JButton FamilyTracking = new JButton("Patient Family Tracking");
                FamilyTracking.addActionListener(new ActionListener() {
                	                        public void actionPerformed(ActionEvent e) {
                	                        	  mainPanel.removeAll();
                	                              mainPanel.revalidate();
                	                              mainPanel.repaint();                        }
                });
                gridPanel.add(FamilyTrackingPanel);
                FamilyTrackingPanel.add(FamilyTracking, BorderLayout.CENTER);
                
                JPanel AppointmentScheduling = new JPanel(new BorderLayout()); 
                JButton AppointmentSchedulingButton = new JButton("Appointment Scheduling");
                AppointmentSchedulingButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	  mainPanel.removeAll();
                          mainPanel.revalidate();
                          mainPanel.repaint();   
      	                JLabel backLabel3 = new JLabel("Back");
    	                backLabel3.setForeground(Color.BLUE);
    	                backLabel3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    	                backLabel3.addMouseListener(new MouseAdapter() {
    	                    @Override
    	                    public void mouseClicked(MouseEvent e) {
    	                        // Logic to handle going back to the previous page
    	                        showMainWindow(username);
    	                    }
    	                    
    		});
    	                mainPanel.add(backLabel3, BorderLayout.NORTH);
    	                            }
    			});
                          
                
                AppointmentScheduling.add(AppointmentSchedulingButton, BorderLayout.CENTER);
                gridPanel.add(AppointmentScheduling); 

       
        JPanel reminderPanel = new JPanel(new BorderLayout());
        JButton reminderButton = new JButton("Reminders & Notifications");
		reminderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				  mainPanel.removeAll();
	                mainPanel.revalidate();
	                mainPanel.repaint();			
  	                JLabel backLabel4 = new JLabel("Back");
	                backLabel4.setForeground(Color.BLUE);
	                backLabel4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	                backLabel4.addMouseListener(new MouseAdapter() {
	                    @Override
	                    public void mouseClicked(MouseEvent e) {
	                        // Logic to handle going back to the previous page
	                        showMainWindow(username);
	                    }
	                    
		});
	                mainPanel.add(backLabel4, BorderLayout.NORTH);
	                            }
			});
                      
            
		        reminderPanel.add(reminderButton, BorderLayout.CENTER);
		        gridPanel.add(reminderPanel);

        JPanel medicalDiagnosisPanel = new JPanel(new BorderLayout());
        JButton medicalDiagnosisButton = new JButton("Medical Diagnosis");
                medicalDiagnosisButton.addActionListener(new ActionListener() {
                	                        public void actionPerformed(ActionEvent e) {
                	                        	  mainPanel.removeAll();
                	                              mainPanel.revalidate();
                	                              mainPanel.repaint();             
                	            	                JLabel backLabel5 = new JLabel("Back");
                	            	                backLabel5.setForeground(Color.BLUE);
                	            	                backLabel5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                	            	                backLabel5.addMouseListener(new MouseAdapter() {
                	            	                    @Override
                	            	                    public void mouseClicked(MouseEvent e) {
                	            	                        // Logic to handle going back to the previous page
                	            	                        showMainWindow(username);
                	            	                    }
                	            	                    
                	            		});
                	            	                mainPanel.add(backLabel5, BorderLayout.NORTH);
                	            	                            }
                	            			});
                	                                  
                	                        
                medicalDiagnosisPanel.add(medicalDiagnosisButton, BorderLayout.CENTER);
                gridPanel.add(medicalDiagnosisPanel);
                

        JPanel labTest = new JPanel(new BorderLayout());
		JButton labTestButton = new JButton("Lab Test, X-Ray's, MRI's");
		labTestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  mainPanel.removeAll();
	                mainPanel.revalidate();
	                mainPanel.repaint();	
  	                JLabel backLabel6 = new JLabel("Back");
	                backLabel6.setForeground(Color.BLUE);
	                backLabel6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	                backLabel6.addMouseListener(new MouseAdapter() {
	                    @Override
	                    public void mouseClicked(MouseEvent e) {
	                        // Logic to handle going back to the previous page
	                        showMainWindow(username);
	                    }
	                    
		});
	                mainPanel.add(backLabel6, BorderLayout.NORTH);
	                            }
			});
                      
            
		labTest.add(labTestButton, BorderLayout.CENTER);
		gridPanel.add(labTest);

       
        JPanel prescriptionTracking = new JPanel(new BorderLayout());
        JButton prescriptionTrackingButton = new JButton("Prescription Tracking");
		prescriptionTrackingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  mainPanel.removeAll();
	                mainPanel.revalidate();
	                mainPanel.repaint();	
  	                JLabel backLabel7 = new JLabel("Back");
	                backLabel7.setForeground(Color.BLUE);
	                backLabel7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	                backLabel7.addMouseListener(new MouseAdapter() {
	                    @Override
	                    public void mouseClicked(MouseEvent e) {
	                        // Logic to handle going back to the previous page
	                        showMainWindow(username);
	                    }
	                    
		});
	                mainPanel.add(backLabel7, BorderLayout.NORTH);
	                            }
			});
                      
            
		prescriptionTracking.add(prescriptionTrackingButton, BorderLayout.CENTER);
		gridPanel.add(prescriptionTracking);
		

        JPanel analysisReport = new JPanel(new BorderLayout());
        JButton analysisReportButton = new JButton("Analytical Report");
                analysisReportButton.addActionListener(new ActionListener() {
                	                        public void actionPerformed(ActionEvent e) {
                	                        	  mainPanel.removeAll();
                	                              mainPanel.revalidate();
                	                              mainPanel.repaint();                        }
                });
                analysisReport.add(analysisReportButton, BorderLayout.CENTER);
                gridPanel.add(analysisReport);
                
                

        mainPanel.add(gridPanel, BorderLayout.CENTER);


        //Create a menu bar

      
        JMenuBar menuBar = new JMenuBar();

      
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        JMenu editMenu = new JMenu("Edit");
        JMenuItem cutMenuItem = new JMenuItem("Cut");
        JMenuItem copyMenuItem = new JMenuItem("Copy");
        JMenuItem pasteMenuItem = new JMenuItem("Paste");

        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("About");

        helpMenu.add(aboutMenuItem);

    
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        
        
    
       

     
        JMenuItem signOutMenuItem = new JMenuItem("Sign Out");
        signOutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                setVisible(false);
                showMainWindow(username);

                showLoginWindow();
            }
            
            
         
            
        });

     // Creates a search field for the program
        JTextField searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(150, 25)); 

        // Create the search button with an icon
        ImageIcon searchIcon = new ImageIcon(getClass().getResource("search-icon.png"));
        JButton searchButton = new JButton(searchIcon);
        searchButton.setIcon(searchIcon);
        searchButton.setPreferredSize(new Dimension(25, 25)); 

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                // Implement your search logic here
                JOptionPane.showMessageDialog(null, "Performing search for: " + searchText);
            }
        });

        // Add the search field and button to the menu bar
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(searchField);
        menuBar.add(searchButton);



        //The username is bold red is a drop down menu as well to sign out
        JMenu userMenu = new JMenu("<html><b><font color='red'>" + username + "</font></b></html>");
        userMenu.add(signOutMenuItem);
        
        menuBar.add(Box.createHorizontalGlue());
          menuBar.add(userMenu);

        // If exit is pressed, program closes
        exitMenuItem.addActionListener(e -> System.exit(0));
        aboutMenuItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Medical App v1.0, Created by Team 4 Sac State"));

        setJMenuBar(menuBar);

       
        
        
        
        
        add(mainPanel);
        revalidate();
        repaint();
    }




    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WindowMain().setVisible(true);
            }
        });
    }
}
