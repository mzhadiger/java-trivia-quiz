package Quiz;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

/*
CSCI 185 Computer Programming II, Spring 2024
Lecture - M05 - Class Nbr 1273
M10: Final Programming Project
Mukhammedali Zhadiger and Jeffrey Wang

Initial date: May 1st, 2024
Finish date: May 9th, 2024
 */

public class QuizGame implements ActionListener {

    // Questions for the Quiz
    String[] questions = {
            "Which of the following is TRUE for constructor?",
            "A statement that is always true for every object of the class is called a ",
            "If an instance variable or a method is marked as protected, then it can be accessed ___",
            "Which of the following methods will be applied late binding by Java?",
            "Which of the following is NOT true for abstract class/abstract method?",
            "What is the purpose of Exception Handling in Java?",
            "Which class is used to create a basic GUI window in Java?",
            "What is the correct way to read from a file in Java?",
            "Which of the following is a checked exception in Java?",
            "Which Java keyword is used to prevent a method from being overridden in a subclass?",
            "What does the keyword 'this' refer to in Java?",

    };

    // Answer Choices of the Questions
    String[][] options = {
            { "Constructor cannot be overloaded", "We can only have 1 constructor per each class",
                    "Constructor has no return type",
                    "The name should be different from the class name" },
            { "class invariant", "class constant", "class model", "class signature" },
            { "in this class itself", "in any derived class", "in any class within the same package",
                    "ALL of the above" },
            { "a method marked as 'final'", "a method marked as 'private'", "a static method",
                    "NONE of the above" },
            { "abstract method has NO method body", "abstract method can be private",
                    "we cannot create an object out of an abstract class",
                    "abstract class can have concrete methods" },
            { "To handle errors that occur during program execution", "To increase program efficiency",
                    "To confuse programmers", "To slow down program execution" },
            { "JFrame", "JPanel", "JButton", "JWindow" },
            { "BufferedReader", "Scanner", "FileReader", "FileInputReader" },
            { "NullPointerException ", "ArrayIndexOutOfBoundsException", "IOException",
                    "IllegalArgumentException" },
            { "override ", "static ", "final", "private" },
            { "Current class instance ", "Parent class instance", "Subclass instance", "Static method" }

    };

    // Answers for the Question
    char[] answers = {
            'C',
            'A',
            'D',
            'D',
            'B',
            'A',
            'A',
            'C',
            'C',
            'C',
            'A'
    };

    char guess;
    char answer;
    int index;
    int correct_guesses = 0;
    int total_questions = questions.length;
    int result;
    int seconds = 10;

    // Timer
    Timer time = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            seconds_left.setText(String.valueOf(seconds));
            seconds--;
            if (seconds == -1) {
                showAnswer();
            }
        }
    });

    JFrame frame = new JFrame();
    JTextField textfield = new JTextField();
    JTextArea textarea = new JTextArea(2, 30);
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();
    JLabel time_label = new JLabel();
    JLabel seconds_left = new JLabel();
    JTextField number_right = new JTextField();
    JTextField percentage = new JTextField();

    JTextField user_experience = new JTextField();
    JTextArea user_response = new JTextArea();

    public QuizGame() {
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(900, 700);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(null);
        frame.setResizable(false);

        textfield.setBounds(0, 0, 900, 50);
        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 30));
        textfield.setBorder(BorderFactory.createBevelBorder(1));
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setEditable(false);

        textarea.setBounds(0, 50, 900, 70);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setBackground(new Color(25, 25, 25));
        textarea.setForeground(new Color(25, 255, 0));
        textarea.setFont(new Font("Oswald", Font.BOLD, 25));
        textarea.setBorder(BorderFactory.createBevelBorder(1));
        textarea.setEditable(false);

        buttonA.setBounds(0, 150, 100, 100);
        buttonA.setFont(new Font("Oswald", Font.BOLD, 35));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(0, 250, 100, 100);
        buttonB.setFont(new Font("Oswald", Font.BOLD, 35));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(0, 350, 100, 100);
        buttonC.setFont(new Font("Oswald", Font.BOLD, 35));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        buttonD.setBounds(0, 450, 100, 100);
        buttonD.setFont(new Font("Oswald", Font.BOLD, 35));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        answer_labelA.setBounds(125, 150, 800, 100);
        answer_labelA.setBackground(new Color(50, 50, 50));
        answer_labelA.setForeground(new Color(25, 255, 0));
        answer_labelA.setFont(new Font("MV Boli", Font.PLAIN, 25));
        answer_labelA.setText("T1");

        answer_labelB.setBounds(125, 250, 800, 100);
        answer_labelB.setBackground(new Color(50, 50, 50));
        answer_labelB.setForeground(new Color(25, 255, 0));
        answer_labelB.setFont(new Font("MV Boli", Font.PLAIN, 25));
        answer_labelB.setText("T2");

        answer_labelC.setBounds(125, 350, 800, 100);
        answer_labelC.setBackground(new Color(50, 50, 50));
        answer_labelC.setForeground(new Color(25, 255, 0));
        answer_labelC.setFont(new Font("MV Boli", Font.PLAIN, 25));
        answer_labelC.setText("T3");

        answer_labelD.setBounds(125, 450, 800, 100);
        answer_labelD.setBackground(new Color(50, 50, 50));
        answer_labelD.setForeground(new Color(25, 255, 0));
        answer_labelD.setFont(new Font("MV Boli", Font.PLAIN, 25));
        answer_labelD.setText("T4");

        seconds_left.setBounds(755, 560, 100, 100);
        seconds_left.setBackground(new Color(25, 25, 25));
        seconds_left.setForeground(new Color(255, 0, 0));
        seconds_left.setFont(new Font("Ink Free", Font.BOLD, 60));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setText(String.valueOf(seconds));

        time_label.setBounds(755, 525, 100, 25);
        time_label.setBackground(new Color(50, 50, 50));
        time_label.setForeground(new Color(255, 0, 0));
        time_label.setFont(new Font("MV Boli", Font.PLAIN, 16));
        time_label.setHorizontalAlignment(JTextField.CENTER);
        time_label.setText("timer:");

        number_right.setBounds(360, 225, 200, 100);
        number_right.setBackground(new Color(25, 25, 25));
        number_right.setForeground(new Color(25, 255, 0));
        number_right.setFont(new Font("Ink Free", Font.BOLD, 50));
        number_right.setBorder(BorderFactory.createBevelBorder(1));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);

        percentage.setBounds(360, 325, 200, 100);
        percentage.setBackground(new Color(25, 25, 25));
        percentage.setForeground(new Color(25, 255, 0));
        percentage.setFont(new Font("Ink Free", Font.BOLD, 50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);

        user_response.setBounds(0, 600, 500, 70);
        user_response.setLineWrap(true);
        user_response.setWrapStyleWord(true);
        user_response.setBackground(new Color(25, 25, 25));
        user_response.setForeground(new Color(25, 255, 0));
        user_response.setFont(new Font("Oswald", Font.BOLD, 25));
        user_response.setBorder(BorderFactory.createBevelBorder(1));
        user_response.setEditable(true);

        user_experience.setBounds(0, 550, 500, 50);
        user_experience.setBackground(new Color(25, 25, 25));
        user_experience.setForeground(new Color(25, 255, 0));
        user_experience.setFont(new Font("Ink Free", Font.BOLD, 30));
        user_experience.setBorder(BorderFactory.createBevelBorder(1));
        user_experience.setHorizontalAlignment(JTextField.CENTER);
        user_experience.setEditable(false);

        frame.add(time_label);

        frame.add(seconds_left);

        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);

        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textfield);
        frame.add(textarea);
        


        frame.setVisible(true);

        Questions();
    }

    // Method for going next problem after answering
    public void Questions() {
        if (index >= total_questions) {
            results();
        } else {
            textfield.setText("Question " + (index + 1));
            textarea.setText(questions[index]);

            // Answer Options
            answer_labelA.setText(options[index][0]);
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);
            time.start();
        }
    }

    // button click
    public void showAnswer() {

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        time.stop();
        if (answers[index] != 'A') {
            answer_labelA.setForeground(new Color(255, 0, 0));
        }
        if (answers[index] != 'B') {
            answer_labelB.setForeground(new Color(255, 0, 0));
        }
        if (answers[index] != 'C') {
            answer_labelC.setForeground(new Color(255, 0, 0));
        }
        if (answers[index] != 'D') {
            answer_labelD.setForeground(new Color(255, 0, 0));
        }

        Timer pauser = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                answer_labelA.setForeground(new Color(20, 255, 0));
                answer_labelB.setForeground(new Color(20, 255, 0));
                answer_labelC.setForeground(new Color(20, 255, 0));
                answer_labelD.setForeground(new Color(20, 255, 0));
                seconds = 10;
                seconds_left.setText(String.valueOf(seconds));
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;
                Questions();
            }
        });
        pauser.setRepeats(false);
        pauser.start();
    }

    // Showing the result of the user's answers
    public void results() {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        buttonA.setVisible(false);
        buttonB.setVisible(false);
        buttonC.setVisible(false);
        buttonD.setVisible(false);


        result = (int) ((correct_guesses / (double) total_questions) * 100);

        textfield.setText("Quiz Result: ");
        textarea.setText("");
        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");

        time_label.setVisible(false);
        seconds_left.setVisible(false);

        number_right.setText(correct_guesses + "/" + total_questions);
        percentage.setText("" + result + "%");

        frame.add(percentage);
        frame.add(number_right);

        user_experience.setText("User Feedback of Quiz Below");
        user_response.setText("Enter:");
        frame.add(user_response);
        frame.add(user_experience);
            user_response.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e){
                    try {
                        if(e.getKeyCode() == KeyEvent.VK_ENTER && user_response.getText().length() > 9 ){
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            System.exit(0); //Exits Program
                        }
                        if (e.getKeyCode() == KeyEvent.VK_ENTER && user_response.getText().length() <= 8) {
                            throw new NotEnoughCharactersException();
                        }
                    } catch (NotEnoughCharactersException e1) {
                        JOptionPane.showMessageDialog(null,"Please enter more characters");
                        results();
                    } catch (Exception e2){
                        JOptionPane.showMessageDialog(null,"Error!");
                        System.out.println("Unknown Error");
                        results();
                    }
                }
            });
    }

    public void actionPerformed(ActionEvent e) {
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if (e.getSource() == buttonA) {
            answer = 'A';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }
        if (e.getSource() == buttonB) {
            answer = 'B';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }
        if (e.getSource() == buttonC) {
            answer = 'C';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }
        if (e.getSource() == buttonD) {
            answer = 'D';
            if (answer == answers[index]) {
                correct_guesses++;
            }
        }
        showAnswer();
    }

    public static void main(String[] args) {
        QuizGame q1 = new QuizGame();
    }
}
