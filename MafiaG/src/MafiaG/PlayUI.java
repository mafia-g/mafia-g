package MafiaG;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;

public class PlayUI extends JFrame implements ActionListener {
    static Socket sock;
    static BufferedWriter bw = null;
    static BufferedReader br = null;

    private DefaultListModel<String> participantModel;
    private JTextArea rankingArea;
    private JTextField chatInput;
    private JTextPane chatPane;
    private StyledDocument doc;
    private JButton startButton;
    private JComboBox<String> voteChoice;
    private JButton voteBtn;
    private JLabel timerLabel;

    private String myNickname = "사용자";
    private String myColor = "";
    private Map<String, String> nicknameColorMap = new HashMap<>();

    private int participantCount = 0;
    private Timer questionTimer;
    private int questionTimeLeft = 20;

    public PlayUI() {
        setTitle("MafiaG");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setupUI();
        connectToServer();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeConnection();
                dispose();
            }
        });
    }

    private void setupUI() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(238, 238, 238));
        header.setBorder(new EmptyBorder(10, 20, 10, 20));
        header.add(new JLabel(new ImageIcon("src/images/MafiaG_wordlogo.jpg")), BorderLayout.WEST);
        header.add(new JLabel(myNickname + " 님 환영합니다", SwingConstants.RIGHT), BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setPreferredSize(new Dimension(200, 0));
        sidebar.setBackground(new Color(240, 234, 255));

        rankingArea = new JTextArea("랭킹\n", 5, 20);
        rankingArea.setEditable(false);
        JScrollPane rankingScroll = new JScrollPane(rankingArea);
        rankingScroll.setBorder(BorderFactory.createTitledBorder("랭킹"));

        participantModel = new DefaultListModel<>();
        JList<String> participantList = new JList<>(participantModel);
        JScrollPane participantScroll = new JScrollPane(participantList);
        participantScroll.setBorder(BorderFactory.createTitledBorder("참여자 명단"));

        startButton = new JButton("Start");
        startButton.setEnabled(true);
        startButton.setPreferredSize(new Dimension(200, 50));
        startButton.addActionListener(e -> sendToServer("{\"type\":\"start\"}"));

        JPanel sidebarContent = new JPanel(new BorderLayout());
        sidebarContent.add(rankingScroll, BorderLayout.NORTH);
        sidebarContent.add(participantScroll, BorderLayout.CENTER);
        sidebar.add(sidebarContent, BorderLayout.CENTER);
        sidebar.add(startButton, BorderLayout.SOUTH);
        mainPanel.add(sidebar, BorderLayout.WEST);

        JPanel chatContainer = new JPanel(new BorderLayout());

        chatPane = new JTextPane();
        chatPane.setEditable(false);
        doc = chatPane.getStyledDocument();
        JScrollPane chatScroll = new JScrollPane(chatPane);
        chatContainer.add(chatScroll, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        chatInput = new JTextField();
        chatInput.setFont(new Font("\uB9C8\uB871 \uACE0\uB515", Font.PLAIN, 16));
        chatInput.addActionListener(this);
        inputPanel.add(chatInput, BorderLayout.CENTER);
        chatContainer.add(inputPanel, BorderLayout.SOUTH);

        mainPanel.add(chatContainer, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        voteChoice = new JComboBox<>();
        voteBtn = new JButton("투표");
        voteBtn.addActionListener(e -> {
            String selected = (String) voteChoice.getSelectedItem();
            if (selected != null) {
                sendToServer("{\"type\":\"vote\",\"target\":\"" + selected + "\"}");
            }
        });

        timerLabel = new JLabel("남은 시간: 20초");
        bottomPanel.add(new JLabel("투표 대상:"));
        bottomPanel.add(voteChoice);
        bottomPanel.add(voteBtn);
        bottomPanel.add(timerLabel);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        String msg = chatInput.getText().trim();
        if (!msg.isEmpty()) {
            sendToServer(msg);
            appendChat(myNickname, msg, true);
            chatInput.setText("");
        }
    }

    private void appendChat(String sender, String msg, boolean isMine) {
        SimpleAttributeSet attr = new SimpleAttributeSet();
        StyleConstants.setAlignment(attr, isMine ? StyleConstants.ALIGN_RIGHT : StyleConstants.ALIGN_LEFT);
        StyleConstants.setForeground(attr, isMine ? Color.BLACK : Color.BLUE);
        StyleConstants.setFontSize(attr, 16);

        try {
            doc.insertString(doc.getLength(), sender + ": " + msg + "\n", attr);
            doc.setParagraphAttributes(doc.getLength(), 1, attr, false);
            chatPane.setCaretPosition(doc.getLength());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void connectToServer() {
        try {
            sock = new Socket("localhost", 3456);
            bw = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(sock.getInputStream()));

            new Thread(() -> {
                String line;
                try {
                    while ((line = br.readLine()) != null) {
                        System.out.println("서버로부터: " + line);
                        // 메시지 처리 로직 추가 가능
                    }
                } catch (IOException e) {
                    System.out.println("서버 연결 종료됨");
                    closeConnection();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "서버 연결에 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sendToServer(String message) {
        try {
            if (bw != null) {
                bw.write(message + "\n");
                bw.flush();
            }
        } catch (IOException ex) {
            System.out.println("서버로 메시지 전송 실패 (스트림이 닫힘)");
            closeConnection();
        }
    }

    private void closeConnection() {
        try {
            if (br != null) br.close();
            if (bw != null) bw.close();
            if (sock != null) sock.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PlayUI().setVisible(true));
    }
}