package MafiaG;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SignupUI extends JFrame {

    public SignupUI() {
        setTitle("회원가입 페이지");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null); // 화면 중앙

        // 전체 배경
        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(248, 248, 248));
        contentPane.setLayout(new GridBagLayout());
        setContentPane(contentPane);

        // 가운데 박스
        JPanel centerBox = new JPanel();
        centerBox.setPreferredSize(new Dimension(560, 600)); // 전체 크기
        centerBox.setOpaque(false);
        centerBox.setLayout(new BorderLayout());

        // 로고 영역
        JPanel logoPanel = new JPanel();
        logoPanel.setPreferredSize(new Dimension(560, 180));
        logoPanel.setOpaque(false);
        JLabel logoLabel = new JLabel();
        ImageIcon logoIcon = new ImageIcon("D:/KIBWA_Project/untitled/src/com/test/MafiaG_logo.jpg"); // 이미지 경로
        logoLabel.setIcon(new ImageIcon(logoIcon.getImage().getScaledInstance(200, 160, Image.SCALE_SMOOTH)));
        logoPanel.add(logoLabel);

        // 입력 필드 영역
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 1, 0, 10));
        formPanel.setOpaque(false);

        // 각각의 입력 그룹
        formPanel.add(createInputGroup("아이디", JTextField.class));
        formPanel.add(createInputGroup("비밀번호", JPasswordField.class));
        formPanel.add(createInputGroup("확인", JPasswordField.class));
        formPanel.add(createInputGroup("닉네임", JTextField.class));
        formPanel.add(createInputGroup("이메일", JTextField.class));

        // 버튼
        JButton signupButton = new JButton("회원가입");
        signupButton.setPreferredSize(new Dimension(0, 45));
        signupButton.setBackground(new Color(204, 230, 255));
        signupButton.setForeground(new Color(68, 68, 68));
        signupButton.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        signupButton.setFocusPainted(false);
        signupButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        signupButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signupButton.setBackground(new Color(179, 218, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                signupButton.setBackground(new Color(204, 230, 255));
            }
        });

        JPanel formContainer = new JPanel();
        formContainer.setOpaque(false);
        formContainer.setLayout(new BorderLayout(0, 20));
        formContainer.setBorder(new EmptyBorder(20, 20, 20, 20));
        formContainer.add(formPanel, BorderLayout.CENTER);
        formContainer.add(signupButton, BorderLayout.SOUTH);

        // 조립
        centerBox.add(logoPanel, BorderLayout.NORTH);
        centerBox.add(formContainer, BorderLayout.CENTER);

        contentPane.add(centerBox);

        setVisible(true);
    }

    private JPanel createInputGroup(String labelText, Class<? extends JComponent> inputType) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setPreferredSize(new Dimension(80, 40));
        label.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        label.setForeground(new Color(51, 51, 51));
        label.setHorizontalAlignment(SwingConstants.LEFT);

        JComponent input;
        if (inputType == JPasswordField.class) {
            input = new JPasswordField();
        } else {
            input = new JTextField();
        }
        input.setPreferredSize(new Dimension(300, 40));
        input.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        input.setBackground(new Color(227, 232, 236));
        input.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        panel.add(label, BorderLayout.WEST);
        panel.add(input, BorderLayout.CENTER);

        return panel;
    }

}

