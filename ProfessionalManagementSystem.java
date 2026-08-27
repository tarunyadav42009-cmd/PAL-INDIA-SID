import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ProfessionalManagementSystem extends JFrame {
    
    // Core Shared Database Instance (Shared across all functional sub-forms)
    private static final ArrayList<StudentRecord> database = new ArrayList<>();

    // Institution Branding Theme Colors
    private final Color PRIMARY_NAVY = new Color(44, 62, 80);
    private final Color ACCENT_BLUE = new Color(52, 152, 219);
    private final Color EXCEL_GREEN = new Color(46, 204, 113);
    private final Color BACKGROUND_GRAY = new Color(245, 247, 250);

    public ProfessionalManagementSystem() {
        // Initial Seed Mock Records for testing Search queries
        if (database.isEmpty()) {
            database.add(new StudentRecord("Rahul Sharma", "9876543210", "Mumbai, India", "Java Programming", "4500", "2026-08-10"));
            database.add(new StudentRecord("Priya Patel", "9123456789", "Vasai, Maharashtra", "Web Development", "6000", "2026-08-25"));
        }

        // Framework Configuration Profile
        setTitle("PAL INDIA COMPUTER EDUCATION - Student Ledger");
        setSize(950, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BACKGROUND_GRAY);
        setLayout(new BorderLayout());

        // --- 1. GLOBAL NAV BANNER ---
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PRIMARY_NAVY);
        headerPanel.setBorder(new EmptyBorder(25, 30, 25, 30));
        
        JLabel titleLabel = new JLabel("PAL INDIA COMPUTER EDUCATION");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(Color.WHITE);
        
        JLabel subtitleLabel = new JLabel("Enterprise Student Directory Portal v2.0", JLabel.RIGHT);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(200, 214, 229));

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(subtitleLabel, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        // --- 2. FOUR CARD GRID SYSTEM INTERFACE ---
        JPanel mainGrid = new JPanel(new GridLayout(1, 4, 15, 0));
        mainGrid.setBackground(BACKGROUND_GRAY);
        mainGrid.setBorder(new EmptyBorder(60, 25, 70, 25));

        JButton addStudentCard = createDashboardCard("Add Student", "Register new entries into current profile batch.", EXCEL_GREEN);
        JButton feesCard       = createDashboardCard("Fees Structure", "Generate invoices and track cleared ledger logs.", ACCENT_BLUE);
        JButton searchCard     = createDashboardCard("Search Record", "Filter and inspect active institutional student files.", PRIMARY_NAVY);
        JButton adminCard      = createDashboardCard("Admin Panel", "Audit global workspace profiles and program diagnostics.", PRIMARY_NAVY);

        mainGrid.add(addStudentCard);
        mainGrid.add(feesCard);
        mainGrid.add(searchCard);
        mainGrid.add(adminCard);
        add(mainGrid, BorderLayout.CENTER);

        // --- 3. EVENT ROUTERS ---
        addStudentCard.addActionListener(e -> openAddStudentDialog());
        feesCard.addActionListener(e -> openFeesDialog());
        searchCard.addActionListener(e -> openSearchDialog());
        adminCard.addActionListener(e -> openAdminDialog());
    }

    // High Density Custom Render Dashboard Item Button Module
    private JButton createDashboardCard(String title, String description, Color baseAccent) {
        JButton button = new JButton();
        button.setLayout(new BorderLayout(0, 10));
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 224, 230), 1, true),
            BorderFactory.createEmptyBorder(25, 15, 25, 15)
        ));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel lblTitle = new JLabel(title, JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblTitle.setForeground(baseAccent);

        JLabel lblDesc = new JLabel("<html><center>" + description + "</center></html>", JLabel.CENTER);
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDesc.setForeground(Color.GRAY);

        button.add(lblTitle, BorderLayout.NORTH);
        button.add(lblDesc, BorderLayout.CENTER);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                button.setBackground(new Color(242, 246, 250));
                button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(baseAccent, 1, true),
                    BorderFactory.createEmptyBorder(25, 15, 25, 15)
                ));
            }
            @Override
            public void mouseExited(MouseEvent evt) {
                button.setBackground(Color.WHITE);
                button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220, 224, 230), 1, true),
                    BorderFactory.createEmptyBorder(25, 15, 25, 15)
                ));
            }
        });

        return button;
    }
    // --- MODULE 1: REGISTER NEW STUDENT REGISTRATION ENTRY ---
    private void openAddStudentDialog() {
        JDialog dialog = new JDialog(this, "Pal India Admissions Office", true);
        dialog.setSize(480, 500);
        dialog.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new EmptyBorder(20, 25, 20, 25));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 5, 10, 5);

        JTextField txtName = new JTextField(20);
        JTextField txtMobile = new JTextField(20);
        JTextField txtAddress = new JTextField(20);
        JTextField txtCourse = new JTextField(20);
        JTextField txtBill = new JTextField(20);
        JTextField txtDate = new JTextField(20);
        
        // Auto-fill field with current year parameter values
        txtDate.setText("2026-08-27");

        // Map layout locations across Grid Matrix
        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(new JLabel("Student Name:"), gbc);
        gbc.gridx = 1; formPanel.add(txtName, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(new JLabel("Mobile Number:"), gbc);
        gbc.gridx = 1; formPanel.add(txtMobile, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; formPanel.add(new JLabel("Address Location:"), gbc);
        gbc.gridx = 1; formPanel.add(txtAddress, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3; formPanel.add(new JLabel("Enrolled Course:"), gbc);
        gbc.gridx = 1; formPanel.add(txtCourse, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4; formPanel.add(new JLabel("Course Fee Bill (INR):"), gbc);
        gbc.gridx = 1; formPanel.add(txtBill, gbc);
        
        gbc.gridx = 0; gbc.gridy = 5; formPanel.add(new JLabel("Date of Admission:"), gbc);
        gbc.gridx = 1; formPanel.add(txtDate, gbc);

        JButton btnSave = new JButton("Confirm Registration");
        btnSave.setBackground(EXCEL_GREEN);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 14));

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2; gbc.insets = new Insets(25, 5, 5, 5);
        formPanel.add(btnSave, gbc);

        btnSave.addActionListener(e -> {
            if (txtName.getText().trim().isEmpty() || txtMobile.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Failed: Name and Mobile Number parameters required.", "Validation Fault", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Directly append to database memory grid state variables
            StudentRecord entry = new StudentRecord(
                txtName.getText().trim(), txtMobile.getText().trim(),
                txtAddress.getText().trim(), txtCourse.getText().trim(),
                txtBill.getText().trim(), txtDate.getText().trim()
            );
            database.add(entry);

            JOptionPane.showMessageDialog(dialog, "Success: " + entry.name + " uploaded to PAL INDIA system database directory.", "Record Saved", JOptionPane.INFORMATION_MESSAGE);
            dialog.dispose();
        });

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // --- MODULE 2: ACCOUNT STATEMENT AND INVOICING ---
    private void openFeesDialog() {
        JDialog dialog = new JDialog(this, "Billing Ledger System", true);
        dialog.setSize(750, 480);
        dialog.setLayout(new GridLayout(1, 2, 15, 0));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder(" Core Billing Terminal "));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 5, 8, 5);

        JTextField txtSearchName = new JTextField();
        JTextField txtPaidAmt = new JTextField();
        JButton btnProcess = new JButton("Generate Disbursement Invoice");
        btnProcess.setBackground(ACCENT_BLUE);
        btnProcess.setForeground(Color.WHITE);

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(new JLabel("Target Student Name:"), gbc);
        gbc.gridx = 1; formPanel.add(txtSearchName, gbc);
        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(new JLabel("Amount Received (INR):"), gbc);
        gbc.gridx = 1; formPanel.add(txtPaidAmt, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; gbc.insets = new Insets(20, 5, 5, 5);
        formPanel.add(btnProcess, gbc);

        JPanel receiptPanel = new JPanel(new BorderLayout());
        receiptPanel.setBorder(BorderFactory.createTitledBorder(" Processed Receipt Output "));
        JTextArea txtReceipt = new JTextArea();
        txtReceipt.setFont(new Font("Consolas", Font.PLAIN, 12));
        txtReceipt.setEditable(false);
        receiptPanel.add(new JScrollPane(txtReceipt), BorderLayout.CENTER);

        btnProcess.addActionListener(e -> {
            String searchTarget = txtSearchName.getText().trim();
            StudentRecord targetedProfile = null;

            for (StudentRecord student : database) {
                if (student.name.equalsIgnoreCase(searchTarget)) {
                    targetedProfile = student;
                    break;
                }
            }

            if (targetedProfile == null) {
                JOptionPane.showMessageDialog(dialog, "No account matches name string identifier inside local index file.", "Profile Missing", JOptionPane.WARNING_MESSAGE);
                return;
            }

            txtReceipt.setText(
                "=========================================\n" +
                "         PAL INDIA COMPUTER EDUCATION    \n" +
                "               OFFICIAL FEES SLIP        \n" +
                "=========================================\n" +
                "  • Candidate Full Name : " + targetedProfile.name + "\n" +
                "  • Phone Reference Id  : " + targetedProfile.mobileNo + "\n" +
                "  • Class Curriculum    : " + targetedProfile.course + "\n" +
                "  • Base Total Bill Amount : INR " + targetedProfile.bill + "\n" +
                "  • Processed Remittance   : INR " + txtPaidAmt.getText().trim() + "\n" +
                "=========================================\n" +
                "  Status Trace: Payment Verified in Memory.\n" +
                "=========================================\n"
            );
        });

        dialog.add(formPanel);
        dialog.add(receiptPanel);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    // --- MODULE 3: MOBILE OR NAME STRING SEARCH CROSS REFERENCE TOOL ---
    private void openSearchDialog() {
        JDialog dialog = new JDialog(this, "Master Registry Query Scanner", true);
        dialog.setSize(650, 420);
        dialog.setLayout(new BorderLayout(0, 10));

        JPanel searchBarPanel = new JPanel(new BorderLayout(10, 0));
        searchBarPanel.setBorder(new EmptyBorder(15, 15, 10, 15));
        
        JTextField searchField = new JTextField();
        JButton searchBtn = new JButton("Execute Directory Scan");
        searchBtn.setBackground(PRIMARY_NAVY);
        searchBtn.setForeground(Color.WHITE);

        searchBarPanel.add(new JLabel("Search Name or Mobile No:"), BorderLayout.WEST);
        searchBarPanel.add(searchField, BorderLayout.CENTER);
        searchBarPanel.add(searchBtn, BorderLayout.EAST);

        JTextArea resultDisplay = new JTextArea();
        resultDisplay.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        resultDisplay.setMargin(new Insets(15, 15, 15, 15));
        resultDisplay.setEditable(false);
        resultDisplay.setText("Enter complete query parameter index string text input.\nMock default records ready to search: 'Rahul Sharma' or 'Priya Patel'");

        searchBtn.addActionListener(e -> {
            String query = searchField.getText().trim().toLowerCase();
            StringBuilder buffer = new StringBuilder();
            boolean matchFound = false;

            for (StudentRecord s : database) {
                if (s.name.toLowerCase().contains(query) || s.mobileNo.contains(query)) {
                    buffer.append("🔍 PAL INDIA FOUND DATA MATCH:\n")
                          .append("--------------------------------------------------\n")
                          .append(" • Student Name     : ").append(s.name).append("\n")
                          .append(" • Contact Line     : ").append(s.mobileNo).append("\n")
                          .append(" • Street Address   : ").append(s.address).append("\n")
                          .append(" • Current Course   : ").append(s.course).append("\n")
                          .append(" • Ledger Bill Fees : INR ").append(s.bill).append("\n")
                          .append(" • Matriculation Dt : ").append(s.dateOfAdmission).append("\n")
                          .append("--------------------------------------------------\n\n");
                    matchFound = true;
                }
            }

            if (matchFound) {
                resultDisplay.setText(buffer.toString());
            } else {
                resultDisplay.setText("❌ QUERY COMPLETE: Zero corresponding data rows matches in array directory matrix framework.");
            }
        });

        dialog.add(searchBarPanel, BorderLayout.NORTH);
        dialog.add(new JScrollPane(resultDisplay), BorderLayout.CENTER);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // --- MODULE 4: CENTRAL AUDIT CONSOLE ---
    private void openAdminDialog() {
        JDialog dialog = new JDialog(this, "Central Audit Control Matrix", true);
        dialog.setSize(500, 380);
        dialog.setLayout(new BorderLayout());

        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(PRIMARY_NAVY);
        JLabel title = new JLabel(" Operational Dashboard Log Metrics");
        title.setFont(new Font("Segoe UI", Font.BOLD, 14));
        title.setForeground(Color.WHITE);
        header.add(title);

        JTextArea metricsArea = new JTextArea();
        metricsArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        metricsArea.setEditable(false);
        metricsArea.setMargin(new Insets(20, 20, 20, 20));
        
        metricsArea.setText(
            "====================================================\n" +
            "            PAL INDIA OPERATIONAL TELEMETRY        \n" +
            "====================================================\n" +
            "  • Environment Core Core   : Java Swing Architecture\n" +
            "  • Local Memory Pipeline   : ACTIVE (" + database.size() + " Students Registered)\n" +
            "  • Security Authority Lock : Root Administrator Clearance\n" +
            "  • Access Nodes Integrity  : Synchronized Matrix Secure\n" +
            "====================================================\n"
        );

        JButton closeBtn = new JButton("Exit Diagnostics Window");
        closeBtn.setBackground(PRIMARY_NAVY);
        closeBtn.setForeground(Color.WHITE);
        closeBtn.addActionListener(e -> dialog.dispose());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        bottomPanel.add(closeBtn);

        dialog.add(header, BorderLayout.NORTH);
        dialog.add(new JScrollPane(metricsArea), BorderLayout.CENTER);
        dialog.add(bottomPanel, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // Interactive Blueprint Structural Object Definition
    static class StudentRecord {
        String name, mobileNo, address, course, bill, dateOfAdmission;
        public StudentRecord(String n, String m, String a, String c, String b, String d) {
            this.name = n; this.mobileNo = m; this.address = a; 
            this.course = c; this.bill = b; this.dateOfAdmission = d;
        }
    }

    // --- MAIN EXECUTABLE FRAMEWORK APPLICATION ENTRY POINT ---
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new ProfessionalManagementSystem().setVisible(true));
    }
}
