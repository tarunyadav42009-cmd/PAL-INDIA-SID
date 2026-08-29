import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Vector;

public class ProfessionalManagementSystem extends JFrame {

    // Database Connection Parameters (Modify user/password to match your local
    // MySQL configuration)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pal_india_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

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

        JButton addStudentCard = createDashboardCard("Add Student",
                "Register new entries directly into production database tables.", EXCEL_GREEN);
        JButton feesCard = createDashboardCard("Fees Structure",
                "Generate invoices and extract live payment accounts profiles.", ACCENT_BLUE);
        JButton searchCard = createDashboardCard("Search Record",
                "Filter and inspect active institutional student storage logs.", PRIMARY_NAVY);
        JButton adminCard = createDashboardCard("Admin Panel",
                "Audit global workspace counts and database connection health.", PRIMARY_NAVY);

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
                BorderFactory.createEmptyBorder(25, 15, 25, 15)));
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
                        BorderFactory.createEmptyBorder(25, 15, 25, 15)));
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                button.setBackground(Color.WHITE);
                button.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(220, 224, 230), 1, true),
                        BorderFactory.createEmptyBorder(25, 15, 25, 15)));
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
        txtDate.setText("2026-08-29");

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Student Name:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Mobile Number:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtMobile, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Address Location:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtAddress, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Enrolled Course:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtCourse, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(new JLabel("Course Fee Bill (INR):"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtBill, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(new JLabel("Date of Admission:"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtDate, gbc);

        JButton btnSave = new JButton("Confirm SQL Insert");
        btnSave.setBackground(EXCEL_GREEN);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 14));

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(25, 5, 5, 5);
        formPanel.add(btnSave, gbc);

        btnSave.addActionListener(e -> {
            if (txtName.getText().trim().isEmpty() || txtMobile.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Validation Error: Name and Mobile Number are required.",
                        "Validation Fault", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String query = "INSERT INTO student_records (name, mobile_no, address, course, bill, date_of_admission) VALUES (?, ?, ?, ?, ?, ?)";

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                    PreparedStatement pstmt = conn.prepareStatement(query)) {

                pstmt.setString(1, txtName.getText().trim());
                pstmt.setString(2, txtMobile.getText().trim());
                pstmt.setString(3, txtAddress.getText().trim());
                pstmt.setString(4, txtCourse.getText().trim());

                double billAmount = 0.0;
                try {
                    billAmount = Double.parseDouble(txtBill.getText().trim());
                } catch (NumberFormatException nfe) {
                    // Fallback to 0.0 if empty
                }
                pstmt.setDouble(5, billAmount);
                pstmt.setString(6, txtDate.getText().trim());

                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(dialog, "Student Added Successfully!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(dialog, "Database Error: " + ex.getMessage(), "Database Fault",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    // --- MODULE 2: INTERACTIVE ENTERPRISE FEES RECEIPT CONSOLE ---
    private void openFeesDialog() {
        JDialog dialog = new JDialog(this, "Enterprise Invoice Terminal", true);
        dialog.setSize(1000, 650);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(this);

        // Left Frame: Quick Scholar Selection Ledger
        DefaultTableModel smallModel = new DefaultTableModel(new String[]{"ID", "Scholar Name", "Course Assigned"}, 0);
        JTable smallTable = new JTable(smallModel);
        smallTable.setRowHeight(22);
        JScrollPane leftScroll = new JScrollPane(smallTable);
        leftScroll.setPreferredSize(new Dimension(300, 0));
        leftScroll.setBorder(BorderFactory.createTitledBorder(" Select Student Record "));

        // Right Frame: Professional Printing Workspace Canvas Render View
        JEditorPane receiptPane = new JEditorPane();
        receiptPane.setEditable(false);
        receiptPane.setContentType("text/html");
        JScrollPane rightScroll = new JScrollPane(receiptPane);
        rightScroll.setBorder(BorderFactory.createTitledBorder(" Live Document Preview Canvas "));

        JSplitPane horizontalSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftScroll, rightScroll);
        horizontalSplit.setDividerLocation(320);
        dialog.add(horizontalSplit, BorderLayout.CENTER);

        // Action Toolbar Control Panel footer elements placement rules
        JPanel controlBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        controlBar.setBackground(Color.WHITE);
        JButton btnPrint = new JButton("Execute System Print Pipeline");
        btnPrint.setBackground(ACCENT_BLUE);
        btnPrint.setForeground(Color.WHITE);
        btnPrint.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnPrint.setEnabled(false);
        controlBar.add(btnPrint);
        dialog.add(controlBar, BorderLayout.SOUTH);

        // Synchronous runtime data mapping route invocation
        Runnable populateStudentList = () -> {
            smallModel.setRowCount(0);
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT id, name, course FROM student_records ORDER BY id DESC")) {
                while (rs.next()) {
                    smallModel.addRow(new Object[]{rs.getInt("id"), rs.getString("name"), rs.getString("course")});
                }
            } catch (SQLException ex) {
                receiptPane.setText("<html><body><p style='color:red;'>System data retrieval failure: " + ex.getMessage() + "</p></body></html>");
            }
        };

        // Selection mapping tracking routine monitoring dynamic clicks
        smallTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && smallTable.getSelectedRow() != -1) {
                int studentId = (int) smallTable.getValueAt(smallTable.getSelectedRow(), 0);
                
                String query = "SELECT * FROM student_records WHERE id = ?";
                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                     PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setInt(1, studentId);
                    
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            String name = rs.getString("name");
                            String mobile = rs.getString("mobile_no");
                            String address = rs.getString("address");
                            String course = rs.getString("course");
                            double bill = rs.getDouble("bill");
                            String dateStr = rs.getString("date_of_admission");

                            // Dynamic Generation of Professional HTML/CSS Invoice Element
                            String invoiceHtml = "<html>" +
                                "<head><style>" +
                                "body { font-family: 'Segoe UI', Tahoma, sans-serif; padding: 20px; color: #2c3e50; background-color:#ffffff; }" +
                                ".receipt-card { border: 2px solid #34495e; padding: 25px; background: #ffffff; }" +
                                ".header-table { width: 100%; border-bottom: 3px double #34495e; padding-bottom: 15px; }" +
                                ".title { font-size: 22px; font-weight: bold; color: #2c3e50; }" +
                                ".meta-details { width: 100%; margin-top: 15px; margin-bottom: 25px; font-size: 12px; }" +
                                ".item-table { width: 100%; border-collapse: collapse; margin-top: 15px; }" +
                                ".item-table th { background-color: #2c3e50; color: #ffffff; padding: 8px; font-size: 13px; text-align: left; }" +
                                ".item-table td { padding: 10px; border-bottom: 1px solid #dcdde1; font-size: 13px; }" +
                                ".total-row { font-size: 15px; font-weight: bold; background-color: #f5f6fa; }" +
                                ".footer-signature { width: 100%; margin-top: 50px; font-size: 12px; }" +
                                "</style></head>" +
                                "<body>" +
                                "<div class='receipt-card'>" +
                                "  <table class='header-table'>" +
                                "    <tr>" +
                                "      <td><span class='title'>PAL INDIA COMPUTER EDUCATION</span><br/>" +
                                "      <small style='color:#7f8c8d;'>Premium Quality Technical Learning Matrix</small></td>" +
                                "      <td align='right' valign='top'><strong>OFFICIAL FEES RECEIPT</strong><br/>" +
                                "      <span style='color:#e74c3c;'>Inv No: #PIN/" + dateStr.replace("-","") + "/" + studentId + "</span></td>" +
                                "    </tr>" +
                                "  </table>" +
                                "  <table class='meta-details'>" +
                                "    <tr>" +
                                "      <td><strong>Billed To:</strong><br/>" + name + "<br/>Contact: " + mobile + "<br/>Loc: " + address + "</td>" +
                                "      <td align='right' valign='top'><strong>Transaction Metadata:</strong><br/>Date: " + dateStr + "<br/>Mode: Direct Core Cash Ledger<br/>Status: <b>PAID Verified</b></td>" +
                                "    </tr>" +
                                "  </table>" +
                                "  <table class='item-table'>" +
                                "    <thead>" +
                                "      <tr>" +
                                "        <th>Description of Institutional Modules Enrolled</th>" +
                                "        <th align='right' style='text-align: right; width: 120px;'>Amount (INR)</th>" +
                                "      </tr>" +
                                "    </thead>" +
                                "    <tbody>" +
                                "      <tr>" +
                                "        <td>Program Tuition & Resource License Fee for: <b>" + course + "</b></td>" +
                                "        <td align='right' style='text-align: right;'>₹ " + String.format("%.2f", bill) + "</td>" +
                                "      </tr>" +
                                "      <tr>" +
                                "        <td>Workspace Technology infrastructure Levies</td>" +
                                "        <td align='right' style='text-align: right;'>₹ 0.00</td>" +
                                "      </tr>" +
                                "      <tr class='total-row'>" +
                                "        <td align='right'>Cumulative Net Sum Settled:</td>" +
                                "        <td align='right' style='text-align: right; color:#27ae60;'>₹ " + String.format("%.2f", bill) + "</td>" +
                                "      </tr>" +
                                "    </tbody>" +
                                "  </table>" +
                                "  <table class='footer-signature'>" +
                                "    <tr>" +
                                "      <td><small>This document acts as an explicit structural verification tracking transaction validation metrics.</small></td>" +
                                "      <td align='right' valign='bottom'><br/><br/>----------------------------------------<br/>Authorized Registrar Seal Signature</td>" +
                                "    </tr>" +
                                "  </table>" +
                                "</div>" +
                                "</body>" +
                                "</html>";
                            
                            receiptPane.setText(invoiceHtml);
                            btnPrint.setEnabled(true);
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(dialog, "Query pipeline failure: " + ex.getMessage());
                }
            }
        });

        // Native System Printing Dialog Link Pipeline Action
        btnPrint.addActionListener(e -> {
            try {
                boolean finished = receiptPane.print(null, null, true, null, null, true);
                if (finished) {
                    JOptionPane.showMessageDialog(dialog, "Document routed to system spooler successfully.", "Print Completed", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
JOptionPane.showMessageDialog(dialog, "Print engine failure interface exception: " + ex.getMessage(), 
"Printing Fault", JOptionPane.ERROR_MESSAGE);}});
populateStudentList.run();dialog.setVisible(true);}
    // --- MODULE 3: INTERACTIVE RECORD INSPECTION FILTERS (LIVE SEARCH) ---
    private void openSearchDialog() {
        JDialog dialog = new JDialog(this, "Production Database Ledger Audit Window", true);
        dialog.setSize(800, 500);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(this);

        JPanel topBar = new JPanel(new BorderLayout(15, 0));
        topBar.setBorder(new EmptyBorder(15, 20, 15, 20));
        topBar.setBackground(PRIMARY_NAVY);

        JLabel lblSearch = new JLabel("Dynamic Search Filter (Scholar / Program Name): ");
        lblSearch.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblSearch.setForeground(Color.WHITE);
        JTextField txtSearch = new JTextField();
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JButton btnFilter = new JButton("Run Ledger Query");
        btnFilter.setBackground(ACCENT_BLUE);
        btnFilter.setForeground(Color.WHITE);
        btnFilter.setFocusPainted(false);

        topBar.add(lblSearch, BorderLayout.WEST);
        topBar.add(txtSearch, BorderLayout.CENTER);
        topBar.add(btnFilter, BorderLayout.EAST);

        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"ID", "Scholar Name", "Mobile Number",
                "Address Location", "Active Program", "Gross Invoiced Balance", "Admission Date"}, 0);
        JTable recordsTable = new JTable(tableModel);
        recordsTable.setRowHeight(25);
        recordsTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        JScrollPane tableScrollPane = new JScrollPane(recordsTable);
        tableScrollPane.setBorder(new EmptyBorder(10, 20, 20, 20));

        Runnable loadFilteredRecords = () -> {
            tableModel.setRowCount(0);
            String searchFilter = txtSearch.getText().trim();
            String selectQuery = "SELECT * FROM student_records WHERE name LIKE ? OR course LIKE ?";

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(selectQuery)) {

                pstmt.setString(1, "%" + searchFilter + "%");
                pstmt.setString(2, "%" + searchFilter + "%");

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Vector<Object> row = new Vector<>();
                        row.add(rs.getInt("id"));
                        row.add(rs.getString("name"));
                        row.add(rs.getString("mobile_no"));
                        row.add(rs.getString("address"));
                        row.add(rs.getString("course"));
                        row.add(rs.getDouble("bill"));
                        row.add(rs.getDate("date_of_admission"));
                        tableModel.addRow(row);
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        };

        btnFilter.addActionListener(e -> loadFilteredRecords.run());
        txtSearch.addActionListener(e -> loadFilteredRecords.run());

        loadFilteredRecords.run();

        dialog.add(topBar, BorderLayout.NORTH);
        dialog.add(tableScrollPane, BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    // --- MODULE 4: ENTERPRISE ADMIN DIAGNOSTIC SUITE & SYSTEM HEALTH ---
    private void openAdminDialog() {
        JDialog dialog = new JDialog(this, "System Administration & Infrastructure Health", true);
        dialog.setSize(550, 420);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(this);
        dialog.getContentPane().setBackground(BACKGROUND_GRAY);

        JPanel mainPanel = new JPanel(new GridLayout(4, 1, 15, 15));
        mainPanel.setBorder(new EmptyBorder(25, 30, 25, 30));
        mainPanel.setBackground(BACKGROUND_GRAY);

        JPanel statusCard = new JPanel(new BorderLayout());
        statusCard.setBackground(Color.WHITE);
        statusCard.setBorder(BorderFactory.createLineBorder(new Color(220, 224, 230), 1, true));
        JLabel lblStatusTitle = new JLabel("  Database Link Server Status", JLabel.LEFT);
        lblStatusTitle.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblStatusTitle.setForeground(PRIMARY_NAVY);
        JLabel lblStatusValue = new JLabel("CHECKING...  ", JLabel.RIGHT);
        lblStatusValue.setFont(new Font("Segoe UI", Font.BOLD, 15));
        statusCard.add(lblStatusTitle, BorderLayout.WEST);
        statusCard.add(lblStatusValue, BorderLayout.EAST);

        JPanel totalScholarsCard = new JPanel(new BorderLayout());
        totalScholarsCard.setBackground(Color.WHITE);
        totalScholarsCard.setBorder(BorderFactory.createLineBorder(new Color(220, 224, 230), 1, true));
        JLabel lblScholarsTitle = new JLabel("  Total Enrolled Scholars Count", JLabel.LEFT);
        lblScholarsTitle.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblScholarsTitle.setForeground(PRIMARY_NAVY);
        JLabel lblScholarsValue = new JLabel("0  ", JLabel.RIGHT);
        lblScholarsValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblScholarsValue.setForeground(ACCENT_BLUE);
        totalScholarsCard.add(lblScholarsTitle, BorderLayout.WEST);
        totalScholarsCard.add(lblScholarsValue, BorderLayout.EAST);

        JPanel uniqueCoursesCard = new JPanel(new BorderLayout());
        uniqueCoursesCard.setBackground(Color.WHITE);
        uniqueCoursesCard.setBorder(BorderFactory.createLineBorder(new Color(220, 224, 230), 1, true));
        JLabel lblCoursesTitle = new JLabel("  Active Programs Offered", JLabel.LEFT);
        lblCoursesTitle.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblCoursesTitle.setForeground(PRIMARY_NAVY);
        JLabel lblCoursesValue = new JLabel("0  ", JLabel.RIGHT);
        lblCoursesValue.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblCoursesValue.setForeground(PRIMARY_NAVY);
        uniqueCoursesCard.add(lblCoursesTitle, BorderLayout.WEST);
        uniqueCoursesCard.add(lblCoursesValue, BorderLayout.EAST);

        mainPanel.add(statusCard);
        mainPanel.add(totalScholarsCard);
        mainPanel.add(uniqueCoursesCard);

        JButton btnPing = new JButton("Run Live Infrastructure Audit Diagnostics");
        btnPing.setBackground(PRIMARY_NAVY);
        btnPing.setForeground(Color.WHITE);
        btnPing.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnPing.setFocusPainted(false);
        dialog.add(btnPing, BorderLayout.SOUTH);

        Runnable runSystemAudit = () -> {
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                if (!conn.isClosed()) {
                    lblStatusValue.setText("CONNECTED ONLINE  ");
                    lblStatusValue.setForeground(EXCEL_GREEN);
                }

                String countScholarsSQL = "SELECT COUNT(*), COUNT(DISTINCT course) FROM student_records";
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery(countScholarsSQL)) {
                    if (rs.next()) {
                        lblScholarsValue.setText(rs.getInt(1) + "  ");
                        lblCoursesValue.setText(rs.getInt(2) + "  ");
                    }
                }
            } catch (SQLException ex) {
                lblStatusValue.setText("OFFLINE / LINK ERROR  ");
                lblStatusValue.setForeground(Color.RED);
                lblScholarsValue.setText("ERR  ");
                lblCoursesValue.setText("ERR  ");
            }
        };

        btnPing.addActionListener(e -> runSystemAudit.run());
        runSystemAudit.run();

        dialog.add(mainPanel, BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            new ProfessionalManagementSystem().setVisible(true);
        });
    }
}
