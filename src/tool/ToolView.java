package tool;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ToolView extends JFrame {

    JTextField txtKeyPath;
    JTextArea txtHash;
    JTextArea txtSignature;
    JButton btnBrowse;
    JButton btnSign;
    JButton btnCopy;
    JLabel lblStatus;

    public ToolView() {
        setTitle("Tool Chữ ký số");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 520);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBorder(new EmptyBorder(16, 16, 16, 16));
//
//        JLabel t = new JLabel("Tool Chữ ký số");
//        t.setFont(new Font("SansSerif", Font.BOLD, 16));
//        t.setBorder(new EmptyBorder(0, 0, 20, 0));
//        root.add(t);

        JLabel title = new JLabel("Tool Chữ ký số");
        title.setFont(new Font("Monospaced", Font.BOLD, 16));
        title.setAlignmentX(LEFT_ALIGNMENT);
        title.setBorder(new EmptyBorder(0, 0, 14, 0));
        root.add(title);

        JLabel lblKey = new JLabel("Private Key File:");
        lblKey.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblKey.setAlignmentX(LEFT_ALIGNMENT);
        root.add(lblKey);
        root.add(Box.createVerticalStrut(4));

        txtKeyPath = new JTextField();
        txtKeyPath.setEditable(false);
        txtKeyPath.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtKeyPath.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        btnBrowse = new JButton("Duyệt...");
        JPanel keyRow = new JPanel(new BorderLayout(6, 0));
        keyRow.setAlignmentX(LEFT_ALIGNMENT);
        keyRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        keyRow.add(txtKeyPath, BorderLayout.CENTER);
        keyRow.add(btnBrowse, BorderLayout.EAST);
        root.add(keyRow);
        root.add(Box.createVerticalStrut(10));
        JLabel lblHash = new JLabel("Mã băm:");
        lblHash.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblHash.setAlignmentX(LEFT_ALIGNMENT);
        root.add(lblHash);
        root.add(Box.createVerticalStrut(4));
        txtHash = new JTextArea(4, 0);
        txtHash.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtHash.setLineWrap(true);
        txtHash.setWrapStyleWord(true);
        JScrollPane spHash = new JScrollPane(txtHash);
        spHash.setAlignmentX(LEFT_ALIGNMENT);
        spHash.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        root.add(spHash);
        root.add(Box.createVerticalStrut(8));

        btnSign = new JButton("▶ Chữ ký");
        btnSign.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnSign.setAlignmentX(LEFT_ALIGNMENT);
        root.add(btnSign);
        root.add(Box.createVerticalStrut(10));

        JLabel lblSig = new JLabel("Chữ ký:");
        lblSig.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblSig.setAlignmentX(LEFT_ALIGNMENT);
        root.add(lblSig);
        root.add(Box.createVerticalStrut(4));
        txtSignature = new JTextArea(6, 0);
        txtSignature.setFont(new Font("Monospaced", Font.PLAIN, 11));
        txtSignature.setLineWrap(true);
        txtSignature.setEditable(false);

        JScrollPane spSig = new JScrollPane(txtSignature);
        spSig.setAlignmentX(LEFT_ALIGNMENT);
        spSig.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        root.add(spSig);
        root.add(Box.createVerticalStrut(8));

        btnCopy = new JButton("Sao chép");
        btnCopy.setEnabled(false);
        btnCopy.setAlignmentX(LEFT_ALIGNMENT);
        root.add(btnCopy);
        lblStatus = new JLabel(" ");
        lblStatus.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblStatus.setForeground(Color.DARK_GRAY);
        lblStatus.setAlignmentX(LEFT_ALIGNMENT);
        lblStatus.setBorder(new EmptyBorder(10, 0, 0, 0));
        root.add(Box.createVerticalGlue());
        root.add(lblStatus);

        setContentPane(root);
        setVisible(true);
    }
}