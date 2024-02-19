
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

        public class CancerDataCollectionGUI extends JFrame {
            static class Patient {
                String name;
                int age;
                String cancerType;

                public Patient(String name, int age, String cancerType) {
                    this.name = name;
                    this.age = age;
                    this.cancerType = cancerType;
                }
            }

            private final List<Patient> patientList = new ArrayList<>();
            private final JPanel patientsPanel;

            public CancerDataCollectionGUI() {
                setTitle("Cancer Data Collection System");
                setSize(400, 400);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setLayout(new BorderLayout());
                getContentPane().setBackground(Color.DARK_GRAY); // Set background color to black
                setResizable(false);

                JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
                inputPanel.setBackground(Color.DARK_GRAY); // Set background color to black
                inputPanel.setForeground(Color.WHITE); // Set foreground color to white

                JLabel nameLabel = new JLabel("Name:");
                nameLabel.setForeground(Color.WHITE); // Set foreground color to white
                JTextField nameField = new JTextField();
                inputPanel.add(nameLabel);
                inputPanel.add(nameField);

                JLabel ageLabel = new JLabel("Age:");
                ageLabel.setForeground(Color.WHITE); // Set foreground color to white
                JTextField ageField = new JTextField();
                inputPanel.add(ageLabel);
                inputPanel.add(ageField);

                JLabel cancerTypeLabel = new JLabel("Cancer Type:");
                cancerTypeLabel.setForeground(Color.WHITE); // Set foreground color to white
                JTextField cancerTypeField = new JTextField();
                inputPanel.add(cancerTypeLabel);
                inputPanel.add(cancerTypeField);

                JButton addButton = new JButton("Add Patient Data");
                addButton.setBackground(Color.CYAN); // Set background color to CYAN
                addButton.setForeground(Color.DARK_GRAY); // Set foreground color to black
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String name = nameField.getText();
                        int age = Integer.parseInt(ageField.getText());
                        String cancerType = cancerTypeField.getText();
                        patientList.add(new Patient(name, age, cancerType));
                        nameField.setText("");
                        ageField.setText("");
                        cancerTypeField.setText("");
                        JOptionPane.showMessageDialog(null, "Patient data added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                inputPanel.add(addButton);

                inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding

                add(inputPanel, BorderLayout.NORTH);

                patientsPanel = new JPanel();
                patientsPanel.setLayout(new BoxLayout(patientsPanel, BoxLayout.Y_AXIS));
                JScrollPane scrollPane = new JScrollPane(patientsPanel);
                add(scrollPane, BorderLayout.CENTER);

                JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                buttonPanel.setBackground(Color.BLACK); // Set background color to black

                JButton viewButton = new JButton("View All Patients");
                viewButton.setBackground(Color.CYAN); // Set background color to CYAN
                viewButton.setForeground(Color.BLACK); // Set foreground color to black
                viewButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        updatePatientsPanel();
                    }
                });
                buttonPanel.add(viewButton);
                add(buttonPanel, BorderLayout.SOUTH);

                setVisible(true);
            }

            private void updatePatientsPanel() {
                patientsPanel.removeAll();
                for (int i = 0; i < patientList.size(); i++) {
                    Patient patient = patientList.get(i);
                    JPanel patientPanel = new JPanel(new BorderLayout());
                    patientPanel.setBackground(Color.BLACK); // Set background color to black
                    patientPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE)); // Add border
                    JLabel patientLabel = new JLabel("<html><b>Patient " + (i + 1) + ":</b> " + patient.name + ", " + patient.age + ", " + patient.cancerType + "</html>");
                    patientLabel.setForeground(Color.WHITE); // Set foreground color to white
                    JButton deleteButton = new JButton("Delete");
                    deleteButton.setBackground(Color.CYAN); // Set background color to CYAN
                    deleteButton.setForeground(Color.BLACK); // Set foreground color to black
                    deleteButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            patientList.remove(patient);
                            updatePatientsPanel();
                            JOptionPane.showMessageDialog(null, "Patient deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    });
                    patientPanel.add(patientLabel, BorderLayout.CENTER);
                    patientPanel.add(deleteButton, BorderLayout.EAST);
                    patientsPanel.add(patientPanel);
                }
                patientsPanel.revalidate();
                patientsPanel.repaint();
            }

            public static void main(String[] args) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new CancerDataCollectionGUI();
                    }
                });
            }
        }