import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SchoolManagementGUI extends JFrame {
    private UserManager userManager = new UserManager();

    public SchoolManagementGUI() {
        setTitle("School Management System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        // Admin Panel
        JPanel adminPanel = new JPanel();
        adminPanel.setLayout(new BorderLayout());
        JButton manageUsersButton = new JButton("Manage Users");
        manageUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUserManagementDialog();
            }
        });
        adminPanel.add(manageUsersButton, BorderLayout.CENTER);

        tabbedPane.addTab("Admin", adminPanel);

        // Other Panels for Students, Teachers, and Staff could be added similarly
        // ...

        add(tabbedPane, BorderLayout.CENTER);
    }

    private void showUserManagementDialog() {
        JDialog dialog = new JDialog(this, "Manage Users", true);
        dialog.setSize(600, 400);
        dialog.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField roleField = new JTextField();
        JTextField userIDField = new JTextField();

        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Role:"));
        panel.add(roleField);
        panel.add(new JLabel("User ID (for Update/Delete):"));
        panel.add(userIDField);

        dialog.add(panel, BorderLayout.CENTER);

        JButton addButton = new JButton("Add User");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    userManager.addUser(usernameField.getText(), new String(passwordField.getPassword()), roleField.getText());
                    JOptionPane.showMessageDialog(dialog, "User added successfully");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(dialog, "Error adding user");
                }
            }
        });

        JButton updateButton = new JButton("Update User");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int userID = Integer.parseInt(userIDField.getText());
                    userManager.updateUser(userID, usernameField.getText(), new String(passwordField.getPassword()), roleField.getText());
                    JOptionPane.showMessageDialog(dialog, "User updated successfully");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(dialog, "Error updating user");
                }
            }
        });

        JButton deleteButton = new JButton("Delete User");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int userID = Integer.parseInt(userIDField.getText());
                    userManager.deleteUser(userID);
                    JOptionPane.showMessageDialog(dialog, "User deleted successfully");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(dialog, "Error deleting user");
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SchoolManagementGUI().setVisible(true);
            }
        });
    }
}
