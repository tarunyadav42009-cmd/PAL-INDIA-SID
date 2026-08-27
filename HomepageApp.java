import java.awt.*;
import java.awt.event.*;

public class HomepageApp extends Frame {

    public HomepageApp() {
        // 1. Set the Title of the Window
        setTitle("Management System Homepage");

        // 2. Set Window Dimensions (Width, Height)
        setSize(500, 400);

        // 3. Set a Grid Layout (4 Rows, 1 Column) with spacing
        setLayout(new GridLayout(4, 1, 10, 20));

        // 4. Create Components
        Label welcomeLabel = new Label("Welcome to Management System", Label.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));

        Button adminButton = new Button("Admin Panel");
        Button feesButton = new Button("Fees Structure");
        Button searchButton = new Button("Search Record");

        // 5. Add Custom Styles (Optional)
        adminButton.setBackground(Color.LIGHT_GRAY);
        feesButton.setBackground(Color.LIGHT_GRAY);
        searchButton.setBackground(Color.LIGHT_GRAY);

        // 6. Add Components to the Frame
        add(welcomeLabel);
        add(adminButton);
        add(feesButton);
        add(searchButton);

        // 7. Handle Window Close Event (Allows clicking the X button)
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        // 8. Add Click Listeners to Buttons
        adminButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Admin Button Clicked");
                // Open Admin window code goes here
            }
        });

        feesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Fees Button Clicked");
                // Open Fees window code goes here
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Search Record Button Clicked");
                // Open Search window code goes here
            }
        });

        // 9. Center the window on the screen and make it visible
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        // Run the application
        new HomepageApp();
    }
}
