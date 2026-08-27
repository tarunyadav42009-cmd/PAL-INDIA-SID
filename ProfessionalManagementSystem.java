import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class ProfessionalManagementSystem extends JFrame {
    
    // Database Connection Parameters (Modify user/password to match your local MySQL configuration)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pal_india_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Removed "root", left empty
 
    // UI Color Theme Palette
    private final Color PRIMARY_NAVY = new Color(44, 62, 80);
    private final Color ACCENT_BLUE = new Color(52, 152, 219);
    private final Color EXCEL_GREEN = new Color(46, 204, 113);
    private final Color BACKGROUND_GRAY = new Color(245, 247, 250);

    public ProfessionalManagementSystem() {
        // Core Layout Management Configurations
        setTitle("PAL INDIA COMPUTER EDUCATION - Live SQL Ledger");
        setSize(950, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BACKGROUND_GRAY);
        setLayout(new BorderLayout());

        // --- 1. NAVIGATION TOP DISPLAY HEADER ---
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PRIMARY_NAVY);
        headerPanel.setBorder(new EmptyBorder(25, 30, 25, 30));
        
        JLabel titleLabel = new JLabel("PAL INDIA COMPUTER EDUCATION");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setForeground(Color.WHITE);
        
        JLabel subtitleLabel = new JLabel("Enterprise Portal Connected to Live MySQL Core", JLabel.RIGHT);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitleLabel.setForeground(new Color(200, 214, 229));

        headerPanel.add(titleLabel, BorderLayout.WEST);
        headerPanel.add(subtitleLabel, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        // --- 2. MULTI-CARD PANEL SYSTEM INTERFACE ---
        JPanel mainGrid = new JPanel(new GridLayout(1, 4, 15, 0));
        mainGrid.setBackground(BACKGROUND_GRAY);
        mainGrid.setBorder(new EmptyBorder(60, 25, 70, 25));

        JButton addStudentCard = createDashboardCard("Add Student", "Register new entries directly into production database tables.", EXCEL_GREEN);
        JButton feesCard       = createDashboardCard("Fees Structure", "Generate invoices and extract live payment accounts profiles.", ACCENT_BLUE);
        JButton searchCard     = createDashboardCard("Search Record", "Filter and inspect active institutional student storage logs.", PRIMARY_NAVY);
        JButton adminCard      = createDashboardCard("Admin Panel", "Audit global workspace counts and database connection health.", PRIMARY_NAVY);

        mainGrid.add(addStudentCard);
        mainGrid.add(feesCard);
        mainGrid.add(searchCard);
        mainGrid.add(adminCard);
        add(mainGrid, BorderLayout.CENTER);

        // --- 3. CORE LOGIC ROUTERS MAPPING ---
        addStudentCard.addActionListener(e -> openAddStudentDialog());
        feesCard.addActionListener(e -> openFeesDialog());
        searchCard.addActionListener(e -> openSearchDialog());
        adminCard.addActionListener(e -> openAdminDialog());
    }

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
    // --- MODULE 1: INTERACTIVE STUDENT LIVE REGISTRATION OVER SQL ---
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
        txtDate.setText("2026-08-27"); // Auto-fill current date parameter

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

        JButton btnSave = new JButton("Confirm SQL Insert");
        btnSave.setBackground(EXCEL_GREEN);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 14));

        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2; gbc.insets = new Insets(25, 5, 5, 5);
        formPanel.add(btnSave, gbc);

        btnSave.addActionListener(e -> {
            if (txtName.getText().trim().isEmpty() || txtMobile.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Validation Error: Name and Mobile Number are required.", "Validation Fault", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Secure Parameterized Statement Insert Engine
            String query = "INSERT INTO student_records (name, mobile_no, address, course, bill, date_of_admission) VALUES (?, ?, ?, ?, ?, ?)";
            
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                
                pstmt.setString(1, txtName.getText().trim());
                pstmt.setString(2, txtMobile.getText().trim());
                pstmt.setString(3, txtAddress.getText().trim());
                pstmt.setString(4, txtCourse.getText().trim());
                pstmt.setDouble(5, Double.parseDouble(txtBill.getText().trim().isEmpty() ? "0" : txtBill.getText().trim()));
                pstmt.setString(6, txtDate.getText().trim());

                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(dialog, "Success: Record inserted directly into MySQL Database schema.", "Database Update Complete", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(dialog, "JDBC Error: Connection failed.\nDetails: " + ex.getMessage(), "Database Connection Fault", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Error: Bill value numeric mapping failed.", "Type Parsing Fault", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // --- MODULE 2: SQL CONNECTED ACCOUNT STATEMENT AND RECEIPT GENERATOR ---
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
        JButton btnProcess = new JButton("Fetch & Generate Receipt");
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
            String selectQuery = "SELECT * FROM student_records WHERE name = ? LIMIT 1";

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(selectQuery)) {
                
                pstmt.setString(1, searchTarget);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    txtReceipt.setText(
                        "=========================================\n" +
                        "         PAL INDIA COMPUTER EDUCATION    \n" +
                        "               OFFICIAL FEES SLIP        \n" +
                        "=========================================\n" +
                        "  • Candidate Name    : " + rs.getString("name") + "\n" +
                        "  • Phone Contact ID  : " + rs.getString("mobile_no") + "\n" +
                        "  • Class Curriculum  : " + rs.getString("course") + "\n" +
                        "  • Total base Bill   : INR " + rs.getDouble("bill") + "\n" +
                        "  • Remittance Flow   : INR " + txtPaidAmt.getText().trim() + "\n" +
                        "=========================================\n" +
                        "  Status Trace: Pulled from MySQL server rows.\n" +
                        "=========================================\n"
                    );
                } else {
                    JOptionPane.showMessageDialog(dialog, "No candidate match located inside MySQL row queries matching search text.", "Profile Not Found", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(dialog, "Query Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.add(formPanel);
        dialog.add(receiptPanel);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    // --- MODULE 3: INTERCONNECTED SCANNER VIA SQL PARAMETER EXTRACTIONS ---
    private void openSearchDialog() {
        JDialog dialog = new JDialog(this, "Master Registry Query Scanner", true);
        dialog.setSize(650, 420);
        dialog.setLayout(new BorderLayout(0, 10));

        JPanel searchBarPanel = new JPanel(new BorderLayout(10, 0));
        searchBarPanel.setBorder(new EmptyBorder(15, 15, 10, 15));
        
        JTextField searchField = new JTextField();
        JButton searchBtn = new JButton("Run Live Database Query");
        searchBtn.setBackground(PRIMARY_NAVY);
        searchBtn.setForeground(Color.WHITE);

        searchBarPanel.add(new JLabel("Search Name or Mobile No:"), BorderLayout.WEST);
        searchBarPanel.add(searchField, BorderLayout.CENTER);
        searchBarPanel.add(searchBtn, BorderLayout.EAST);

        JTextArea resultDisplay = new JTextArea();
        resultDisplay.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        resultDisplay.setMargin(new Insets(15, 15, 15, 15));
        resultDisplay.setEditable(false);
        resultDisplay.setText("Enter complete query parameter index string text input inside SQL database rows.");

        searchBtn.addActionListener(e -> {
            String queryPattern = "%" + searchField.getText().trim() + "%";
            String searchQuery = "SELECT * FROM student_records WHERE name LIKE ? OR mobile_no LIKE ?";
            StringBuilder buffer = new StringBuilder();
            boolean matchFound = false;

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(searchQuery)) {
                
                pstmt.setString(1, queryPattern);
                pstmt.setString(2, queryPattern);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    buffer.append("🔍 PAL INDIA FOUND DATA MATCH (Live SQL Database):\n")
                          .append("--------------------------------------------------\n")
                          .append(" • Student Name     : ").append(rs.getString("name")).append("\n")
                          .append(" • Contact Line     : ").append(rs.getString("mobile_no")).append("\n")
                          .append(" • Street Address   : ").append(rs.getString("address")).append("\n")
                          .append(" • Current Course   : ").append(rs.getString("course")).append("\n")
                          .append(" • Ledger Bill Fees : INR ").append(rs.getDouble("bill")).append("\n")
                          .append(" • Matriculation Dt : ").append(rs.getDate("date_of_admission")).append("\n")
                          .append("--------------------------------------------------\n\n");
                    matchFound = true;
                }

                if (matchFound) {
                    resultDisplay.setText(buffer.toString());
                } else {
                    resultDisplay.setText("❌ QUERY COMPLETE: Zero matching data records found inside MySQL storage tables.");
                }
            } catch (SQLException ex) {
                resultDisplay.setText("SQL Query Failure: " + ex.getMessage());
            }
        });

        dialog.add(searchBarPanel, BorderLayout.NORTH);
        dialog.add(new JScrollPane(resultDisplay), BorderLayout.CENTER);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // --- MODULE 4: REAL-TIME RELATIONAL DIAGNOSTICS VIEW ---
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

        int totalCount = 0;
        String statusMessage = "Online / Secured";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS row_count FROM student_records")) {
            if (rs.next()) {
                totalCount = rs.getInt("row_count");
            }
        } catch (SQLException ex) {
            statusMessage = "OFFLINE - CONNECTION FAULT";
        }

        metricsArea.setText(
            "====================================================\n" +
            "            PAL INDIA OPERATIONAL TELEMETRY        \n" +
            "====================================================\n" +
            "  • Environment Core Core   : Java Swing UI Core\n" +
            "  • Pipeline Architecture   : MySQL Dynamic JDBC Connection\n" +
            "  • Database Records Count  : " + totalCount + " Records Live in Tables\n" +
            "  • Network Pipeline State  : " + statusMessage + "\n" +
            "  • Security Authority Lock : Root Administrator Clearance\n" +
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

    // --- APPLICATION STARTING RUNNER PORT ---
    public static void main(String[] args) {
        try {
            // FORCES JAVA RUNTIME SYSTEM TO INITIALIZE DYNAMIC JDBC MYSQL DRIVER CLASSPATH ENGINES
            Class.forName("com.mysql.cj.jdbc.Driver");
            
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
