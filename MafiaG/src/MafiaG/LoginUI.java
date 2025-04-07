package MafiaG;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class LoginUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("로그인 UI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 800);
            frame.setLocationRelativeTo(null);

            JPanel contentPane = new JPanel();
            contentPane.setBackground(new Color(248, 248, 248));
            contentPane.setLayout(new GridBagLayout());

            RoundedPanel centerPanel = new RoundedPanel(20);
            centerPanel.setPreferredSize(new Dimension(400, 600));
            centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
            centerPanel.setOpaque(false);

            // 로고 영역
            JPanel logoPanel = new JPanel();
            logoPanel.setOpaque(false);
            logoPanel.setPreferredSize(new Dimension(400, 200));
            logoPanel.setMaximumSize(new Dimension(400, 200));
            logoPanel.setLayout(new BorderLayout());

            // ✅ 리사이즈된 이미지 사용
            ImageIcon logoIcon = new ImageIcon("D:/KIBWA_Project/untitled/src/com/test/MafiaG_logo.jpg");
            Image logoImage = logoIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH);  // ★ 크기 조정
            ImageIcon resizedLogoIcon = new ImageIcon(logoImage);

            JLabel logoLabel = new JLabel();
            logoLabel.setIcon(resizedLogoIcon);
            logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
            logoPanel.add(logoLabel, BorderLayout.CENTER);

            // 랭킹 영역
            RoundedPanel rankingPanel = new RoundedPanel(10);
            rankingPanel.setLayout(new BoxLayout(rankingPanel, BoxLayout.Y_AXIS));
            rankingPanel.setBackground(new Color(227, 232, 236));
            rankingPanel.setOpaque(true);
            rankingPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
            rankingPanel.setPreferredSize(new Dimension(600, 150));
            rankingPanel.setMaximumSize(new Dimension(600, 150));
            rankingPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel rankingTitle = new JLabel("🏆 랭킹");
            rankingTitle.setFont(new Font("SansSerif", Font.BOLD, 18));
            rankingTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
            rankingTitle.setForeground(Color.DARK_GRAY);
            rankingPanel.add(rankingTitle);
            rankingPanel.add(Box.createVerticalStrut(10));

            rankingPanel.add(createRankingItem("🥇 유재석", "980점", new Color(110, 168, 226)));
            rankingPanel.add(createRankingItem("🥈 박명수", "920점", new Color(166, 150, 198)));
            rankingPanel.add(createRankingItem("🥉 정준하", "870점", new Color(207, 136, 146)));

            // 로그인 영역
            JPanel loginPanel = new JPanel();
            loginPanel.setOpaque(false);
            loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
            loginPanel.setBorder(new EmptyBorder(10, 20, 0, 20));

            JPanel inputWrapper = new JPanel();
            inputWrapper.setOpaque(false);
            inputWrapper.setLayout(new BorderLayout());
            inputWrapper.setPreferredSize(new Dimension(0, 90));
            inputWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));

            JPanel inputPanel = new JPanel();
            inputPanel.setOpaque(false);
            inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
            inputPanel.setBorder(new EmptyBorder(0, 0, 0, 10));

            JTextField idField = new JTextField();
            idField.setBackground(new Color(227, 232, 236));
            idField.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
            idField.setFont(new Font("SansSerif", Font.PLAIN, 14));
            inputPanel.add(idField);
            inputPanel.add(Box.createVerticalStrut(5));

            JPasswordField pwField = new JPasswordField();
            pwField.setBackground(new Color(227, 232, 236));
            pwField.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
            pwField.setFont(new Font("SansSerif", Font.PLAIN, 14));
            inputPanel.add(pwField);

            JButton loginBtn = new JButton("로그인");
            loginBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
            loginBtn.setBackground(new Color(227, 232, 236));
            loginBtn.setBorder(new EmptyBorder(10, 10, 10, 10));
            loginBtn.setPreferredSize(new Dimension(100, 90));
            loginBtn.setMaximumSize(new Dimension(100, 90));

            inputWrapper.add(inputPanel, BorderLayout.CENTER);
            inputWrapper.add(loginBtn, BorderLayout.EAST);
            loginPanel.add(inputWrapper);

            JLabel errorLabel = new JLabel("※ 아이디 또는 비밀번호를 확인하세요.");
            errorLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
            errorLabel.setForeground(Color.RED);
            errorLabel.setBorder(new EmptyBorder(5, 0, 5, 0));
            loginPanel.add(errorLabel);

            // 회원가입 / 계정 찾기
            JPanel buttonPanel = new JPanel();
            buttonPanel.setOpaque(false);
            buttonPanel.setLayout(new GridLayout(1, 2, 10, 0));
            buttonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

            JButton signupBtn = new JButton("회원가입");
            JButton findBtn = new JButton("계정 찾기");
            signupBtn.setFont(new Font("SansSerif", Font.PLAIN, 12));
            findBtn.setFont(new Font("SansSerif", Font.PLAIN, 12));
            signupBtn.setBackground(Color.WHITE);
            findBtn.setBackground(Color.WHITE);
            signupBtn.setBorder(BorderFactory.createLineBorder(new Color(216, 221, 224)));
            findBtn.setBorder(BorderFactory.createLineBorder(new Color(216, 221, 224)));
            buttonPanel.add(signupBtn);
            buttonPanel.add(findBtn);
            loginPanel.add(buttonPanel);

            // 조립
            centerPanel.add(logoPanel);
            centerPanel.add(rankingPanel);
            centerPanel.add(Box.createVerticalStrut(10));
            centerPanel.add(loginPanel);

            contentPane.add(centerPanel);
            frame.setContentPane(contentPane);
            frame.setVisible(true);
        });
    }

    private static JPanel createRankingItem(String name, String score, Color bgColor) {
        JPanel panel = new JPanel();
        panel.setBackground(bgColor);
        panel.setLayout(new BorderLayout());
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.setBorder(new EmptyBorder(6, 10, 6, 10));

        JLabel nameLabel = new JLabel(name);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JLabel scoreLabel = new JLabel(score);
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        panel.add(nameLabel, BorderLayout.WEST);
        panel.add(scoreLabel, BorderLayout.EAST);
        return panel;
    }

    // 둥근 패널 클래스
    static class RoundedPanel extends JPanel {
        private int radius;

        public RoundedPanel(int radius) {
            super();
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}
