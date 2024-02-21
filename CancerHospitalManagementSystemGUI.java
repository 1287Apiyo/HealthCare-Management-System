import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CancerHospitalManagementSystemGUI {
    public static class Facility {
        private String facilityName;
        private String facilityLevel;
        private String location;
        private int numPatients;
        private int numDeadPatients;

        // Constructor
        public Facility(String facilityName, String facilityLevel, String location) {
            this.facilityName = facilityName;
            this.facilityLevel = facilityLevel;
            this.location = location;
            this.numPatients = 0;
            this.numDeadPatients = 0;
        }

        // Methods
        public void admitPatient(boolean isDead) {
            numPatients++;
            if (isDead) {
                numDeadPatients++;
            }
        }

        public String getFacilityDetails() {
            return "Facility: " + facilityName + "\n" +
                    "Level: " + facilityLevel + "\n" +
                    "Location: " + location + "\n" +
                    "Number of Patients: " + numPatients + "\n" +
                    "Number of Dead Patients: " + numDeadPatients + "\n" +
                    "------------------------------\n";
        }
    }

    public static void main(String[] args) {
        List<Facility> facilities = new ArrayList<>();

        // Sample facilities
        Facility facility1 = new Facility("City Hospital", "Hospital", "Nairobi");
        Facility facility2 = new Facility("Suburb Clinic", "Clinic", "Mombasa");

        facilities.add(facility1);
        facilities.add(facility2);

        // GUI setup
        JFrame frame = new JFrame("Hospital Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea facilityDetailsTextArea = new JTextArea();
        JButton admitPatientButton = new JButton("Admit Patient");

        admitPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admitPatientToFacility(facilities, frame);
                updateFacilityDetails(facilities, facilityDetailsTextArea);
            }
        });

        JScrollPane scrollPane = new JScrollPane(facilityDetailsTextArea);

        frame.setLayout(new BorderLayout());
        frame.add(admitPatientButton, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static void admitPatientToFacility(List<Facility> facilities, JFrame parentFrame) {
        String[] facilityNames = facilities.stream().map(f -> f.facilityName).toArray(String[]::new);
        String selectedFacility = (String) JOptionPane.showInputDialog(
                parentFrame,
                "Select a facility:",
                "Admit Patient",
                JOptionPane.PLAIN_MESSAGE,
                null,
                facilityNames,
                facilityNames[0]
        );

        if (selectedFacility != null) {
            boolean isDead = showDeathConfirmationDialog(parentFrame);

            for (Facility facility : facilities) {
                if (facility.facilityName.equals(selectedFacility)) {
                    facility.admitPatient(isDead);
                    JOptionPane.showMessageDialog(parentFrame, "Patient admitted to " + facility.facilityName);
                    break;
                }
            }
        }
    }

    private static boolean showDeathConfirmationDialog(JFrame parentFrame) {
        int dialogResult = JOptionPane.showConfirmDialog(
                parentFrame,
                "Is the patient dead?",
                "Death Confirmation",
                JOptionPane.YES_NO_OPTION
        );
        return dialogResult == JOptionPane.YES_OPTION;
    }

    private static void updateFacilityDetails(List<Facility> facilities, JTextArea textArea) {
        StringBuilder details = new StringBuilder("Facility Details:\n");
        for (Facility facility : facilities) {
            details.append(facility.getFacilityDetails());
        }
        textArea.setText(details.toString());
    }
}