package MafiaG;

import javax.swing.*;
import java.awt.*;

public class FindAccountUI extends JFrame {
    public FindAccountUI() {
        setTitle("아이디/비밀번호 찾기");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(248, 248, 248));
        mainPanel.setLayout(new BorderLayout());

        JPanel centerBox = new JPanel();
        centerBox.setOpaque(false);
        centerBox.setLayout(new GridBagLayout());

        JPanel innerBox = new JPanel();
        innerBox.setOpaque(false);
        innerBox.setLayout(new BoxLayout(innerBox, BoxLayout.Y_AXIS));
        innerBox.setPreferredSize(new Dimension(700, 700));

        // 로고 영역
        JPanel logoZone = new JPanel();
        logoZone.setOpaque(false);
        logoZone.setMaximumSize(new Dimension(700, 120)); // 크기 축소
        JLabel logoLabel = new JLabel();

        ImageIcon logoIcon = new ImageIcon("D:/KIBWA_Project/untitled/src/com/test/MafiaG_logo.jpg");
        Image rawImage = logoIcon.getImage();

        // 비율 유지하면서 크기 조정
        int maxWidth = 200;
        int maxHeight = 100;
        double aspectRatio = (double) rawImage.getWidth(null) / rawImage.getHeight(null);

        int width = maxWidth;
        int height = (int)(maxWidth / aspectRatio);
        if (height > maxHeight) {
            height = maxHeight;
            width = (int)(maxHeight * aspectRatio);
        }

        Image scaled = rawImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(scaled));
        logoZone.add(logoLabel);

        // 아이디/비밀번호 찾기 영역
        JPanel findZone = new JPanel();
        findZone.setOpaque(false);
        findZone.setLayout(new GridLayout(1, 2, 40, 0));
        findZone.setMaximumSize(new Dimension(700, 300));

        JPanel findIdBox = createFindBox("아이디 찾기", new String[]{"이메일"});
        JPanel findPwBox = createFindBox("비밀번호 찾기", new String[]{"아이디", "이메일"});

        findZone.add(findIdBox);
        findZone.add(findPwBox);

        // 메시지 영역
        JLabel messageLabel = new JLabel("입력해주신 이메일로 가입하신 아이디는 abc1***입니다.");
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        messageLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // 돌아가기 버튼
        JButton backButton = new JButton("메인 페이지로 돌아가기");
        backButton.setPreferredSize(new Dimension(200, 45));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setBackground(new Color(204, 230, 255));
        backButton.setForeground(new Color(68, 68, 68));
        backButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createLineBorder(new Color(204, 230, 255)));

        // 배치
        innerBox.add(logoZone);
        innerBox.add(Box.createVerticalStrut(10));
        innerBox.add(findZone);
        innerBox.add(Box.createVerticalStrut(10));
        innerBox.add(messageLabel);
        innerBox.add(Box.createVerticalStrut(10));
        innerBox.add(backButton);

        centerBox.add(innerBox);
        mainPanel.add(centerBox, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }

    private JPanel createFindBox(String title, String[] labels) {
        JPanel box = new JPanel();
        box.setBackground(Color.WHITE);
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setForeground(new Color(51, 51, 51));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        box.add(titleLabel);
        box.add(Box.createVerticalStrut(15));

        for (String label : labels) {
            JPanel inputGroup = new JPanel();
            inputGroup.setLayout(new BoxLayout(inputGroup, BoxLayout.X_AXIS));
            inputGroup.setOpaque(false);

            JLabel lbl = new JLabel(label);
            lbl.setPreferredSize(new Dimension(80, 40));
            lbl.setFont(new Font("SansSerif", Font.BOLD, 14));
            lbl.setForeground(new Color(51, 51, 51));

            JTextField input = new JTextField();
            input.setPreferredSize(new Dimension(200, 40));
            input.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
            input.setFont(new Font("SansSerif", Font.PLAIN, 16));
            input.setBackground(new Color(227, 232, 236));
            input.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

            inputGroup.add(lbl);
            inputGroup.add(Box.createHorizontalStrut(10));
            inputGroup.add(input);

            box.add(inputGroup);
            box.add(Box.createVerticalStrut(10));
        }

        JButton button = new JButton(title);
        button.setPreferredSize(new Dimension(200, 45));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(new Color(204, 230, 255));
        button.setForeground(new Color(68, 68, 68));
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(204, 230, 255)));

        box.add(button);

        return box;
    }
}
