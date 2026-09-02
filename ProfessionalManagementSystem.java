import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Vector;

public class ProfessionalManagementSystem extends JFrame {

    // Database Connection Parameters
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
        setTitle("PAL INDIA COMPUTER EDUCATION - Enterprise ERP Ledger");
        setSize(1000, 650);
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

        JButton addStudentCard = createDashboardCard("Admissions Hub",
                "Register new profiles and configure active institutional course pipelines.", EXCEL_GREEN);
        JButton feesCard = createDashboardCard("Fees Structure",
                "Process monthly installments, review ledger records, and spool dynamic invoices.", ACCENT_BLUE);
        JButton searchCard = createDashboardCard("Search & Modify",
                "Filter database registries, view historical balances, and update profiles live.", PRIMARY_NAVY);
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
        dialog.setSize(480, 520);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(this);

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
            double billVal = 0.0;
            try {
                if (!txtBill.getText().trim().isEmpty()) billVal = Double.parseDouble(txtBill.getText().trim());
            } catch (NumberFormatException nfe) { /* Fallback to 0.0 */ }

            String query = "INSERT INTO student_records (name, mobile_no, address, course, bill, pending_balance, date_of_admission) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, txtName.getText().trim());
                pstmt.setString(2, txtMobile.getText().trim());
                pstmt.setString(3, txtAddress.getText().trim());
                pstmt.setString(4, txtCourse.getText().trim());
                pstmt.setDouble(5, billVal);
                pstmt.setDouble(6, billVal); // Initialize outstanding balance identical to gross bill
                pstmt.setString(7, txtDate.getText().trim());

                if (pstmt.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(dialog, "Student Registered Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(dialog, "Database Error: " + ex.getMessage(), "Database Fault", JOptionPane.ERROR_MESSAGE);
            }
        });
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.setVisible(true);
    }

        // --- MODULE 2: LIVE SQL FEES TRANSACTION ENGINE LOGICS ---
    private void openFeesDialog() {
        JDialog dialog = new JDialog(this, "Enterprise Monthly Installment Terminal", true);
        dialog.setSize(1100, 680);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(this);

        // Left Workspace Panel Splitter
        DefaultTableModel smallModel = new DefaultTableModel(new String[]{"ID", "Scholar Name", "Course Assigned"}, 0);
        JTable smallTable = new JTable(smallModel);
        smallTable.setRowHeight(22);
        JScrollPane leftScroll = new JScrollPane(smallTable);
        leftScroll.setPreferredSize(new Dimension(300, 0));
        leftScroll.setBorder(BorderFactory.createTitledBorder(" Select Student Record "));

        // Right Workspace Panel Splitter (Top Preview Canvas, Bottom Payment Collector Form)
        JEditorPane receiptPane = new JEditorPane();
        receiptPane.setEditable(false);
        receiptPane.setContentType("text/html");
        JScrollPane rightScroll = new JScrollPane(receiptPane);
        rightScroll.setBorder(BorderFactory.createTitledBorder(" Real-Time Document Ledger Canvas "));

        JPanel transactionInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        transactionInputPanel.setBorder(BorderFactory.createTitledBorder(" Record New Monthly Installment Fee Payment "));
        JTextField txtPayAmount = new JTextField(10);
        JTextField txtPayRemarks = new JTextField(15);
        JButton btnProcessPayment = new JButton("Post Payment Transaction");
        btnProcessPayment.setBackground(EXCEL_GREEN);
        btnProcessPayment.setForeground(Color.WHITE);
        btnProcessPayment.setEnabled(false);
        
        transactionInputPanel.add(new JLabel("Amount Paid:"));
        transactionInputPanel.add(txtPayAmount);
        transactionInputPanel.add(new JLabel("Month / Remarks:"));
        transactionInputPanel.add(txtPayRemarks);
        transactionInputPanel.add(btnProcessPayment);

        JPanel rightWrapperPanel = new JPanel(new BorderLayout());
        rightWrapperPanel.add(rightScroll, BorderLayout.CENTER);
        rightWrapperPanel.add(transactionInputPanel, BorderLayout.SOUTH);

        JSplitPane horizontalSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftScroll, rightWrapperPanel);
        horizontalSplit.setDividerLocation(320);
        dialog.add(horizontalSplit, BorderLayout.CENTER);

        JPanel controlBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
        controlBar.setBackground(Color.WHITE);
        JButton btnPrint = new JButton("Execute System Print Pipeline");
        btnPrint.setBackground(ACCENT_BLUE);
        btnPrint.setForeground(Color.WHITE);
        btnPrint.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnPrint.setEnabled(false);
        controlBar.add(btnPrint);
        dialog.add(controlBar, BorderLayout.SOUTH);

            // Synchronous runtime list reloader
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
        smallTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && smallTable.getSelectedRow() != -1) {
                int studentId = (int) smallTable.getValueAt(smallTable.getSelectedRow(), 0);
                btnProcessPayment.setEnabled(true);

                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                     PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM student_records WHERE id = ?")) {
                    pstmt.setInt(1, studentId);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        if (rs.next()) {
                            String name = rs.getString("name");
                            String mobile = rs.getString("mobile_no");
                            String address = rs.getString("address");
                            String course = rs.getString("course");
                            double bill = rs.getDouble("bill");
                            double pendingBalance = rs.getDouble("pending_balance");
                            String notes = rs.getString("dynamic_notes") == null ? "No transaction records posted" : rs.getString("dynamic_notes");

                            // Pull recent individual monthly transactional history lines dynamically
                            StringBuilder historyRows = new StringBuilder();
                            try (PreparedStatement txP = conn.prepareStatement("SELECT amount_paid, payment_date, remarks FROM fee_transactions WHERE student_id=? ORDER BY transaction_id ASC")) {
                                txP.setInt(1, studentId);
                                try (ResultSet txRs = txP.executeQuery()) {
                                    while (txRs.next()) {
                                        historyRows.append(String.format("<tr><td>Installment Entry (%s) - %s</td><td align='right'>₹ %.2f</td></tr>",
                                            txRs.getTimestamp("payment_date").toString().substring(0, 16), txRs.getString("remarks"), txRs.getDouble("amount_paid")));
                                    }
                                }
                            }

                            String invoiceHtml = "<html><head><style>" +
                                "body { font-family: 'Segoe UI', sans-serif; padding: 10px; color: #2c3e50; }" +
                                ".receipt-card { border: 2px solid #2c3e50; padding: 15px; }" +
                                ".item-table { width: 100%; border-collapse: collapse; margin-top: 10px; }" +
                                ".item-table th { background-color: #2c3e50; color: #ffffff; padding: 6px; text-align: left; }" +
                                ".item-table td { padding: 6px; border-bottom: 1px solid #dcdde1; }" +
                                ".total-row { font-size: 13px; font-weight: bold; background-color: #f8f9fa; }" +
                                "</style></head><body><div class='receipt-card'>" +
                                "<h2>PAL INDIA COMPUTER EDUCATION</h2>" +
                                "<p><b>Student Profile:</b> " + name + " | <b>Course:</b> " + course + " | <b>Contact:</b> " + mobile + "</p>" +
                                "<table class='item-table'><thead><tr><th>Transaction Milestone Trace</th><th align='right'>Amount (INR)</th></tr></thead>" +
                                "<tbody><tr><td><b>Gross Assigned Base Contract Fees</b></td><td align='right'>₹ " + String.format("%.2f", bill) + "</td></tr>" +
                                historyRows.toString() +
                                "<tr class='total-row'><td align='right'>Current Net Remaining Balance:</td><td align='right' style='color:#e74c3c;'>₹ " + String.format("%.2f", pendingBalance) + "</td></tr>" +
                                "</tbody></table></div></body></html>";

                            receiptPane.setText(invoiceHtml);
                            btnPrint.setEnabled(true);
                        }
                    }
                } catch (SQLException ex) { ex.printStackTrace(); }
            }
        });

        // Click handler to post money updates and instantly deduct balance metrics
        btnProcessPayment.addActionListener(evt -> {
            int targetRow = smallTable.getSelectedRow();
            if (targetRow == -1) return;
            int studentId = (int) smallTable.getValueAt(targetRow, 0);

            try {
                double paymentAmount = Double.parseDouble(txtPayAmount.getText().trim());
                String remarks = txtPayRemarks.getText().trim();

                try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                    conn.setAutoCommit(false);
                    try {
                        // 1. Post dynamic record entry line inside history logger
                        try (PreparedStatement txStmt = conn.prepareStatement("INSERT INTO fee_transactions (student_id, amount_paid, remarks) VALUES (?, ?, ?)")) {
                            txStmt.setInt(1, studentId);
                            txStmt.setDouble(2, paymentAmount);
                            txStmt.setString(3, remarks);
                            txStmt.executeUpdate();
                        }
                        // 2. Perform background mathematical deduction metrics
                        try (PreparedStatement updateBal = conn.prepareStatement("UPDATE student_records SET pending_balance = pending_balance - ? WHERE id = ?")) {
                            updateBal.setDouble(1, paymentAmount);
                            updateBal.setInt(2, studentId);
                            updateBal.executeUpdate();
                        }
                        conn.commit();
                        JOptionPane.showMessageDialog(dialog, "Installment Fee Logged and Subtracted Cleanly!", "Ledger Balanced", JOptionPane.INFORMATION_MESSAGE);
                        txtPayAmount.setText(""); txtPayRemarks.setText("");
                        smallTable.getSelectionModel().setSelectionInterval(targetRow, targetRow); // Force-refresh visual UI pane
                    } catch (SQLException se) { conn.rollback(); throw se; }
                }
            } catch (Exception ex) { JOptionPane.showMessageDialog(dialog, "Invalid Entry Parameters: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
        });

        btnPrint.addActionListener(e -> {
            try {
                if (receiptPane.print(null, null, true, null, null, true)) {
                    JOptionPane.showMessageDialog(dialog, "Document routed to system print spooler successfully.", "Print Completed", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) { JOptionPane.showMessageDialog(dialog, "Printing Subsystem Error: " + ex.getMessage(), "Printing Fault", JOptionPane.ERROR_MESSAGE); }
        });

        populateStudentList.run();
        dialog.setVisible(true);
    }
    // --- MODULE 3: INTERACTIVE RECORD INSPECTION & MANAGEMENT CORE ---
    private void openSearchDialog() {
        JDialog dialog = new JDialog(this, "Production Database Ledger Audit Window", true);
        dialog.setSize(950, 550);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(this);

        JPanel topBar = new JPanel(new BorderLayout(15, 0));
        topBar.setBorder(new EmptyBorder(15, 20, 15, 20));
        topBar.setBackground(PRIMARY_NAVY);

        JLabel lblSearch = new JLabel("Dynamic Search Filter (Scholar / Program / Address): ");
        lblSearch.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblSearch.setForeground(Color.WHITE);
        JTextField txtSearch = new JTextField();
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JButton btnFilter = new JButton("Run Ledger Query");
        btnFilter.setBackground(ACCENT_BLUE);
        btnFilter.setForeground(Color.WHITE);

        topBar.add(lblSearch, BorderLayout.WEST);
        topBar.add(txtSearch, BorderLayout.CENTER);
        topBar.add(btnFilter, BorderLayout.EAST);

        DefaultTableModel tableModel = new DefaultTableModel(new String[]{
            "ID", "Scholar Name", "Mobile Number", "Address Location", "Active Program", "Gross Base Cost", "Net Outstanding Due Balance", "Profile Notes"
        }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        JTable recordsTable = new JTable(tableModel);
        recordsTable.setRowHeight(25);
        JScrollPane tableScrollPane = new JScrollPane(recordsTable);
        tableScrollPane.setBorder(new EmptyBorder(10, 20, 20, 20));

        Runnable loadFilteredRecords = () -> {
            tableModel.setRowCount(0);
            String filterText = "%" + txtSearch.getText().trim() + "%";
            String selectQuery = "SELECT id, name, mobile_no, address, course, bill, pending_balance, dynamic_notes FROM student_records WHERE name LIKE ? OR course LIKE ? OR address LIKE ?";

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement(selectQuery)) {
                pstmt.setString(1, filterText);
                pstmt.setString(2, filterText);
                pstmt.setString(3, filterText);

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        Vector<Object> row = new Vector<>();
                        row.add(rs.getInt("id"));
                        row.add(rs.getString("name"));
                        row.add(rs.getString("mobile_no"));
                        row.add(rs.getString("address"));
                        row.add(rs.getString("course"));
                        row.add(rs.getDouble("bill"));
                        row.add(rs.getDouble("pending_balance"));
                        row.add(rs.getString("dynamic_notes"));
                        tableModel.addRow(row);
                    }
                }
            } catch (SQLException ex) { ex.printStackTrace(); }
        };

        recordsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && recordsTable.getSelectedRow() != -1) {
                    int targetRow = recordsTable.getSelectedRow();
                    int targetId = (int) tableModel.getValueAt(targetRow, 0);
                    
                    JDialog upDialog = new JDialog(dialog, "Modify Institutional Information Profiler", true);
                    upDialog.setSize(450, 480);
                    upDialog.setLayout(new GridBagLayout());
                    upDialog.setLocationRelativeTo(dialog);
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.insets = new Insets(8, 10, 8, 10);

                    JTextField editName = new JTextField(tableModel.getValueAt(targetRow, 1).toString(), 20);
                    JTextField editMobile = new JTextField(tableModel.getValueAt(targetRow, 2).toString(), 20);
                    JTextField editAddress = new JTextField(tableModel.getValueAt(targetRow, 3).toString(), 20);
                    JTextField editCourse = new JTextField(tableModel.getValueAt(targetRow, 4).toString(), 20);
                    JTextField editBill = new JTextField(tableModel.getValueAt(targetRow, 5).toString(), 20);
                    JTextArea editNotes = new JTextArea(tableModel.getValueAt(targetRow, 7) == null ? "" : tableModel.getValueAt(targetRow, 7).toString(), 3, 20);
                    editNotes.setLineWrap(true); editNotes.setWrapStyleWord(true);
                    editNotes.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

                    gbc.gridx = 0; gbc.gridy = 0; upDialog.add(new JLabel("Scholar Name:"), gbc);
                    gbc.gridx = 1; upDialog.add(editName, gbc);
                    gbc.gridx = 0; gbc.gridy = 1; upDialog.add(new JLabel("Mobile Number:"), gbc);
                    gbc.gridx = 1; upDialog.add(editMobile, gbc);
                    gbc.gridx = 0; gbc.gridy = 2; upDialog.add(new JLabel("Address Location:"), gbc);
                    gbc.gridx = 1; upDialog.add(editAddress, gbc);
                    gbc.gridx = 0; gbc.gridy = 3; upDialog.add(new JLabel("Update Course:"), gbc);
                    gbc.gridx = 1; upDialog.add(editCourse, gbc);
                    gbc.gridx = 0; gbc.gridy = 4; upDialog.add(new JLabel("Gross Tuition (INR):"), gbc);
                    gbc.gridx = 1; upDialog.add(editBill, gbc);
                    gbc.gridx = 0; gbc.gridy = 5; upDialog.add(new JLabel("Profile Notes:"), gbc);
                    gbc.gridx = 1; upDialog.add(new JScrollPane(editNotes), gbc);

                    JButton btnUpdate = new JButton("Save Global Updates");
                    btnUpdate.setBackground(EXCEL_GREEN); btnUpdate.setForeground(Color.WHITE);
                    gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2; gbc.insets = new Insets(20, 10, 10, 10);
                    upDialog.add(btnUpdate, gbc);

                    btnUpdate.addActionListener(commitEvt -> {
                        try {
                            double oldBill = (double) tableModel.getValueAt(targetRow, 5);
                            double newBill = Double.parseDouble(editBill.getText().trim());
                            double billDiff = newBill - oldBill;

                            String upSQL = "UPDATE student_records SET name=?, mobile_no=?, address=?, course=?, bill=?, pending_balance = pending_balance + ?, dynamic_notes=? WHERE id=?";
                            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                                 PreparedStatement pstmt = conn.prepareStatement(upSQL)) {
                                pstmt.setString(1, editName.getText().trim());
                                pstmt.setString(2, editMobile.getText().trim());
                                pstmt.setString(3, editAddress.getText().trim());
                                pstmt.setString(4, editCourse.getText().trim());
                                pstmt.setDouble(5, newBill);
                                pstmt.setDouble(6, billDiff); // Add/Subtract variance directly into ongoing metrics balance
                                pstmt.setString(7, editNotes.getText().trim());
                                pstmt.setInt(8, targetId);

                                pstmt.executeUpdate();
                                JOptionPane.showMessageDialog(upDialog, "Institutional entry logs modified successfully.", "System Updated", JOptionPane.INFORMATION_MESSAGE);
                                upDialog.dispose();
                                loadFilteredRecords.run();
                            }
                        } catch (Exception ex) { JOptionPane.showMessageDialog(upDialog, "Error saving updates: " + ex.getMessage(), "Fault", JOptionPane.ERROR_MESSAGE); }
                    });
                    upDialog.setVisible(true);
                }
            }
        });

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
                ex.printStackTrace();
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

