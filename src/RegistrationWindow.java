import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegistrationWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeComboBox;
    private ImageIcon icon = new ImageIcon(getClass().getResource("medical-cross-hi.png"));


    public RegistrationWindow() {
        setTitle("Registration");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
       setIconImage(icon.getImage());
        
        

        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JLabel userTypeLabel = new JLabel("User Type:");
        String[] userTypes = {"Doctor", "Nurse", "Physician", "Nurse Practitioner", "Patient"};
        userTypeComboBox = new JComboBox<>(userTypes);
        JButton registerButton = new JButton("Register");

        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(userTypeLabel);
        formPanel.add(userTypeComboBox);
        formPanel.add(new JLabel());
        formPanel.add(registerButton);

        add(formPanel, BorderLayout.CENTER);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String userType = (String) userTypeComboBox.getSelectedItem();
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                } else {
                    // Call a method to handle registration
                    registerNewUser(username, password, userType);
                }
            }
        });
    }

    private void registerNewUser(String username, String password, String userType) {
        // No actual registration logic is implemented 
        // Displaying the info for now
        JOptionPane.showMessageDialog(null, "Registration Successful\nUsername: " + username + "\nPassword: " + password + "\nUser Type: " + userType);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RegistrationWindow().setVisible(true);
            }
        });
    }
}
