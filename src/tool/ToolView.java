package tool;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ToolView extends JFrame {

    JTabbedPane tabbedPane;

    JTextField txtKeyPath;
    JTextArea txtHash;
    JTextArea txtSignature;
    JButton btnBrowse;
    JButton btnSign;
    JButton btnCopy;
    JLabel lblStatus;

    JButton btnGenerate;
    JTextArea txtPrivateKey;
    JTextArea txtPublicKey;
    JButton btnSavePrivate;
    JButton btnSavePublic;
    JButton btnCopyPrivate;
    JButton btnCopyPublic;
    JLabel lblGenStatus;

    public ToolView() {
        setTitle("Tool Chữ ký số");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 580);
        setLocationRelativeTo(null);
        setResizable(false);
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Ký dữ liệu", buildSignPanel());
        tabbedPane.addTab("Tạo Keypair", buildKeygenPanel());
        setContentPane(tabbedPane);
        setVisible(true);
    }

    public JPanel buildSignPanel() {
        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBorder(new EmptyBorder(16, 16, 16, 16));
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
        btnSign = new JButton("Chữ ký");
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

        return root;
    }

    public JPanel buildKeygenPanel() {
        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBorder(new EmptyBorder(16, 16, 16, 16));

        JLabel title = new JLabel("Tạo cặp khóa DSA 2048-bit");
        title.setFont(new Font("Monospaced", Font.BOLD, 16));
        title.setAlignmentX(LEFT_ALIGNMENT);
        title.setBorder(new EmptyBorder(0, 0, 14, 0));
        root.add(title);
        btnGenerate = new JButton("Tạo Keypair");
        btnGenerate.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnGenerate.setAlignmentX(LEFT_ALIGNMENT);
        root.add(btnGenerate);
        root.add(Box.createVerticalStrut(12));
        JLabel lblPriv = new JLabel("Private Key:");
        lblPriv.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblPriv.setAlignmentX(LEFT_ALIGNMENT);
        root.add(lblPriv);
        root.add(Box.createVerticalStrut(4));

        txtPrivateKey = new JTextArea(5, 0);
        txtPrivateKey.setFont(new Font("Monospaced", Font.PLAIN, 11));
        txtPrivateKey.setLineWrap(true);
        txtPrivateKey.setEditable(false);
        JScrollPane spPriv = new JScrollPane(txtPrivateKey);
        spPriv.setAlignmentX(LEFT_ALIGNMENT);
        spPriv.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
        root.add(spPriv);
        root.add(Box.createVerticalStrut(4));
        JPanel privBtnRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        privBtnRow.setAlignmentX(LEFT_ALIGNMENT);
        btnCopyPrivate = new JButton("Sao chép Private Key");
        btnCopyPrivate.setEnabled(false);
        btnSavePrivate = new JButton("Lưu Private Key...");
        btnSavePrivate.setEnabled(false);
        privBtnRow.add(btnCopyPrivate);
        privBtnRow.add(btnSavePrivate);
        root.add(privBtnRow);
        root.add(Box.createVerticalStrut(10));
        JLabel lblPub = new JLabel("Public Key:");
        lblPub.setFont(new Font("SansSerif", Font.PLAIN, 12));
        lblPub.setAlignmentX(LEFT_ALIGNMENT);
        root.add(lblPub);
        root.add(Box.createVerticalStrut(4));
        txtPublicKey = new JTextArea(5, 0);
        txtPublicKey.setFont(new Font("Monospaced", Font.PLAIN, 11));
        txtPublicKey.setLineWrap(true);
        txtPublicKey.setEditable(false);
        JScrollPane spPub = new JScrollPane(txtPublicKey);
        spPub.setAlignmentX(LEFT_ALIGNMENT);
        spPub.setMaximumSize(new Dimension(Integer.MAX_VALUE, 110));
        root.add(spPub);
        root.add(Box.createVerticalStrut(4));

        JPanel pubBtnRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        pubBtnRow.setAlignmentX(LEFT_ALIGNMENT);
        pubBtnRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        btnCopyPublic = new JButton("Sao chép Public Key");
        btnCopyPublic.setEnabled(false);
        btnSavePublic = new JButton("Lưu Public Key...");
        btnSavePublic.setEnabled(false);
        pubBtnRow.add(btnCopyPublic);
        pubBtnRow.add(btnSavePublic);
        root.add(pubBtnRow);

        lblGenStatus = new JLabel(" ");
        lblGenStatus.setFont(new Font("SansSerif", Font.PLAIN, 11));
        lblGenStatus.setForeground(Color.DARK_GRAY);
        lblGenStatus.setAlignmentX(LEFT_ALIGNMENT);
        lblGenStatus.setBorder(new EmptyBorder(10, 0, 0, 0));
        root.add(Box.createVerticalGlue());
        root.add(lblGenStatus);

        return root;
    }
}