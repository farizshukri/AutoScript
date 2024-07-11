import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class GradingSystemGUI extends JFrame {
    private StudentManager studentManager = new StudentManager();
    private JTextArea textArea = new JTextArea();
    private JTextField nameField = new JTextField(20);
    private JTextField classField = new JTextField(10);

    public GradingSystemGUI() {
        setTitle("Grading System");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        panel.add(new JLabel("Student Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Class:"));
        panel.add(classField);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    studentManager.addStudent(new Student(0, nameField.getText(), classField.getText()));
                    JOptionPane.showMessageDialog(null, "Student added!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error adding student.");
                }
            }
        });

        panel.add(addButton);

        JButton displayButton = new JButton("Display Students");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Student> students = studentManager.getAllStudents();
                    textArea.setText("");
                    for (Student student : students) {
                        textArea.append(student.toString() + "\n");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error displaying students.");
                }
            }
        });

        panel.add(displayButton);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GradingSystemGUI().setVisible(true);
            }
        });
    }
}

class Student {
    private int studentID;
    private String name;
    private String className;

    public Student(int studentID, String name, String className) {
        this.studentID = studentID;
        this.name = name;
        this.className = className;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return "ID: " + studentID + ", Name: " + name + ", Class: " + className;
    }
}

class Subject {
    private int subjectID;
    private String subjectName;

    public Subject(int subjectID, String subjectName) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }
}

class Mark {
    private int markID;
    private int studentID;
    private int subjectID;
    private int marksObtained;

    public Mark(int markID, int studentID, int subjectID, int marksObtained) {
        this.markID = markID;
        this.studentID = studentID;
        this.subjectID = subjectID;
        this.marksObtained = marksObtained;
    }

    public int getMarkID() {
        return markID;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public int getMarksObtained() {
        return marksObtained;
    }
}
