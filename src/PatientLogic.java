import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PatientLogic extends JFrame {
    private JTextField nameField;
    private JTextField lastNameField;
    private JTextField dateOfBirthField;
    private JTextField phoneField;
    private JTextField heightField;
    private JTextField weightField;
    private Doctor doctor;
    private JComboBox<String> patientListComboBox;

    public PatientLogic(Doctor doctor) {
        this.doctor = doctor;
        setTitle("Patient Information");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon(getClass().getResource("medical-cross-hi.png"));
        setIconImage(icon.getImage());

        JLabel Dialog = new JLabel();
        Dialog.setText("Please fill in the following fields to add a new patient\n");
        Dialog.setHorizontalAlignment(SwingConstants.CENTER);
        add(Dialog, BorderLayout.NORTH);

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField();

        JLabel dateOfBirthLabel = new JLabel("Date of Birth:");
        dateOfBirthField = new JTextField();

        JLabel phoneLabel = new JLabel("Phone:");
        phoneField = new JTextField();

        JLabel heightLabel = new JLabel("Height:");
        heightField = new JTextField();

        JLabel weightLabel = new JLabel("Weight:");
        weightField = new JTextField();

        JButton addButton = new JButton("New Patient");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String lastName = lastNameField.getText();
                String dateOfBirth = dateOfBirthField.getText();
                String phone = phoneField.getText();
                String height = heightField.getText();
                String weight = weightField.getText();

                Patient patient = new Patient(name, lastName, dateOfBirth, phone, height, weight);
                doctor.add(patient);

                nameField.setText("");
                lastNameField.setText("");
                dateOfBirthField.setText("");
                phoneField.setText("");
                heightField.setText("");
                weightField.setText("");

                JOptionPane.showMessageDialog(null, "Patient added successfully");
            }
        });

        
        JButton deleteButton = new JButton("Delete Patient");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (patientListComboBox != null) {
                    String selectedPatient = (String) patientListComboBox.getSelectedItem();
                    if (selectedPatient != null) {
                        String[] names = selectedPatient.split(" ");
                        String lastName = names[names.length - 1];
                        if (doctor.delete(lastName)) {
                            JOptionPane.showMessageDialog(null, "Patient deleted successfully");
                        } else {
                            JOptionPane.showMessageDialog(null, "Patient not found", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No patients to delete", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        JButton searchButton = new JButton("Search Patient");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lastName = JOptionPane.showInputDialog("Enter patient's last name:");
                if (lastName != null) {
                    Patient patient = (Patient) doctor.search(lastName);
                    if (patient != null) {
                        JOptionPane.showMessageDialog(null, patient.toString(), "Patient Information", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Patient not found", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        
        JButton showPatientsButton = new JButton("Show Patients");
        showPatientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> patientNames = new ArrayList<>();
                for (Patient patient : doctor.patients) {
                    patientNames.add(patient.name + " " + patient.lastName);
                }

                if (patientNames.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No patients found", "Patients List", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String[] patientArray = patientNames.toArray(new String[0]);
                    patientListComboBox = new JComboBox<>(patientArray);
                    JOptionPane.showMessageDialog(null, patientListComboBox, "Patients List", JOptionPane.PLAIN_MESSAGE);

                    // Add a button to show patient info when a patient is selected
                    JButton showInfoButton = new JButton("Show Info");
                    showInfoButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String selectedPatient = (String) patientListComboBox.getSelectedItem();
                            for (Patient patient : doctor.patients) {
                                if ((patient.name + " " + patient.lastName).equals(selectedPatient)) {
                                    StringBuilder patientInfo = new StringBuilder();
                                    patientInfo.append(patient.toString());
                                    JOptionPane.showMessageDialog(null, patientInfo.toString(), "Patient Information", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        }
                    });

                    // Create panel to hold the show info button
                    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    buttonPanel.add(showInfoButton);

                    // Add the button panel to the dialog box
                    JOptionPane.showMessageDialog(null, buttonPanel, "Patients List", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        JPanel inputPanel = new JPanel(new GridLayout(7, 2));
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(lastNameLabel);
        inputPanel.add(lastNameField);
        inputPanel.add(dateOfBirthLabel);
        inputPanel.add(dateOfBirthField);
        inputPanel.add(phoneLabel);
        inputPanel.add(phoneField);
        inputPanel.add(heightLabel);
        inputPanel.add(heightField);
        inputPanel.add(weightLabel);
        inputPanel.add(weightField);
        inputPanel.add(new JLabel());
        inputPanel.add(addButton);
        
        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(showPatientsButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }
}



class HealthRecord {
    boolean hasHealthRecord;
}

class Patient {
    String name;
    String lastName;
    String dateOfBirth;
    String phone;
    String height;
    String weight;

    public Patient(String name, String lastName, String dateOfBirth, String phone, String height, String weight) {
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.height = height;
        this.weight = weight;
    }

    // Getters and setters omitted for brevity

    @Override
    public String toString() {
        return "Name: " + name + "\nLast Name: " + lastName + "\nDate Of Birth: " + dateOfBirth + "\nPhone: " + phone + "\nHeight: " + height + "\nWeight: " + weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        Patient patient = (Patient) o;
        return name.equals(patient.name) && lastName.equals(patient.lastName);
    }
}

interface PatientList {
    boolean add(Object o);

    Object search(Object o);

    boolean delete(Object o);

    void printLast();
}

class Doctor implements PatientList {
    List<Patient> patients;

    public Doctor() {
        patients = new ArrayList<>();
    }

    @Override
    public boolean add(Object o) {
        if (o instanceof Patient) {
            Patient patient = (Patient) o;
            return patients.add(patient);
        }
        return false;
    }

    @Override
    public Object search(Object o) {
        if (o instanceof String) {
            String lastName = (String) o;
            for (Patient patient : patients) {
                if (patient.lastName.equalsIgnoreCase(lastName)) {
                    return patient;
                }
            }
        }
        return null;
    }

    @Override
    public boolean delete(Object o) {
        if (o instanceof String) {
            String lastName = (String) o;
            for (Patient patient : patients) {
                if (patient.lastName.equalsIgnoreCase(lastName)) {
                    return patients.remove(patient);
                }
            }
        }
        return false;
    }

    @Override
    public void printLast() {
        if (!patients.isEmpty()) {
            Patient lastPatient = patients.get(patients.size() - 1);
            System.out.println("LastPatient:  " + lastPatient.name + " " + lastPatient.lastName);
        } else {
            System.out.println("There are no patients in the list");
        }
    }
}
