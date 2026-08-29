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
        txtDate.setText("2026-08-27");

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
                pstmt.setString(5, txtBill.getText().trim());
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

    // --- MODULE 2: ORIGINAL FEES STRUCTURE AND NEW METRIC VIEWS COMBINED ---
    private void openFeesDialog() {
        // Your original functional layout instruction left intact
        JOptionPane.showMessageDialog(this, "Fees Dashboard Operational.", "System Info",
                JOptionPane.INFORMATION_MESSAGE);

        // Your newly requested operational analytics dashboard elements attached below
        JDialog dialog = new JDialog(this, "Fees Analytical Structure Summary", true);
        dialog.setSize(600, 350);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(this);

        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 15, 15));
        mainPanel.setBorder(new EmptyBorder(25, 30, 25, 30));
        mainPanel.setBackground(BACKGROUND_GRAY);

        JPanel totalRevenueCard = new JPanel(new BorderLayout());
        totalRevenueCard.setBackground(Color.WHITE);
        totalRevenueCard.setBorder(BorderFactory.createLineBorder(new Color(220, 224, 230), 1, true));
        JLabel lblRevTitle = new JLabel("  Gross Invoiced Fees Accumulation", JLabel.LEFT);
        lblRevTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblRevTitle.setForeground(PRIMARY_NAVY);
        JLabel lblRevValue = new JLabel("INR 0.00  ", JLabel.RIGHT);
        lblRevValue.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblRevValue.setForeground(EXCEL_GREEN);
        totalRevenueCard.add(lblRevTitle, BorderLayout.WEST);
        totalRevenueCard.add(lblRevValue, BorderLayout.EAST);

        JPanel avgCourseCard = new JPanel(new BorderLayout());
        avgCourseCard.setBackground(Color.WHITE);
        avgCourseCard.setBorder(BorderFactory.createLineBorder(new Color(220, 224, 230), 1, true));
        JLabel lblAvgTitle = new JLabel("  Average Billing Per Scholar", JLabel.LEFT);
        lblAvgTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblAvgTitle.setForeground(PRIMARY_NAVY);
        JLabel lblAvgValue = new JLabel("INR 0.00  ", JLabel.RIGHT);
        lblAvgValue.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblAvgValue.setForeground(ACCENT_BLUE);
        avgCourseCard.add(lblAvgTitle, BorderLayout.WEST);
        avgCourseCard.add(lblAvgValue, BorderLayout.EAST);

        JPanel maxCourseCard = new JPanel(new BorderLayout());
        maxCourseCard.setBackground(Color.WHITE);
        maxCourseCard.setBorder(BorderFactory.createLineBorder(new Color(220, 224, 230), 1, true));
        JLabel lblMaxTitle = new JLabel("  Peak Premium Invoice Registered", JLabel.LEFT);
        lblMaxTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblMaxTitle.setForeground(PRIMARY_NAVY);
        JLabel lblMaxValue = new JLabel("INR 0.00  ", JLabel.RIGHT);
        lblMaxValue.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblMaxValue.setForeground(PRIMARY_NAVY);
        maxCourseCard.add(lblMaxTitle, BorderLayout.WEST);
        maxCourseCard.add(lblMaxValue, BorderLayout.EAST);

        mainPanel.add(totalRevenueCard);
        mainPanel.add(avgCourseCard);
        mainPanel.add(maxCourseCard);

        String query = "SELECT SUM(bill), AVG(bill), MAX(bill) FROM student_records";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                lblRevValue.setText(String.format("₹ %.2f  ", rs.getDouble(1)));
                lblAvgValue.setText(String.format("₹ %.2f  ", rs.getDouble(2)));
                lblMaxValue.setText(String.format("₹ %.2f  ", rs.getDouble(3)));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(dialog, "Metrics Processing Interrupted: " + ex.getMessage(),
                    "SQL Aggregation Fault", JOptionPane.ERROR_MESSAGE);
        }

        dialog.add(mainPanel, BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    // --- MODULE 3: INTERACTIVE RECORD INSPECTION FILTERS (LIVE SEARCH) ---
    private void openSearchDialog() {
        JDialog dialog = new JDialog(this, "Production Database Ledger Audit Window", true);
        dialog.setSize(800, 500);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(this);

        JPanel topBar = new JPanel(new BorderLayout(15, 0));
        topBar.setBorder(new EmptyBorder(15, 20, 15, 20));
        topBar.setBackground(PRIMARY_NAVY);

        JLabel lblSearch = new JLabel("Dynamic Search Filter (Name / Course): ");
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

        DefaultTableModel tableModel = new DefaultTableModel(new String[] { "ID", "Student Name", "Mobile Number",
                "Address", "Course Enrolled", "Fees Invoiced", "Admission Date" }, 0);
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
        SwingUtilities.invokeLater(() -> {
            new ProfessionalManagementSystem().setVisible(true);
        });
    }
}
