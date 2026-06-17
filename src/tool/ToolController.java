package tool;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.security.KeyPair;

public class ToolController {
    private ToolView view;
    private ToolModel model;
    private File selectedKeyFile;
    private KeyPair lastGeneratedKeyPair;

    public ToolController(ToolView view, ToolModel model) {
        this.view = view;
        this.model = model;
        view.btnBrowse.addActionListener(e -> browseKey());
        view.btnSign.addActionListener(e -> doSign());
        view.btnCopy.addActionListener(e -> doCopy());
        view.btnGenerate.addActionListener(e -> generateKey());
        view.btnCopyPrivate.addActionListener(e -> copyText(view.txtPrivateKey.getText(), "Đã sao chép Private Key."));
        view.btnCopyPublic.addActionListener(e -> copyText(view.txtPublicKey.getText(), "Đã sao chép Public Key."));
        view.btnSavePrivate.addActionListener(e -> saveKey(view.txtPrivateKey.getText(), "privateKey", "Private Key"));
        view.btnSavePublic.addActionListener(e -> saveKey(view.txtPublicKey.getText(), "publicKey", "Public Key"));
    }

    public void browseKey() {
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedKeyFile = fc.getSelectedFile();
            view.txtKeyPath.setText(selectedKeyFile.getAbsolutePath());
            view.lblStatus.setText(" ");
        }
    }

    public void doSign() {
        if (selectedKeyFile == null) {
            view.lblStatus.setText("Vui lòng chọn file private key.");
            view.lblStatus.setForeground(Color.RED);
            return;
        }

        String hash = view.txtHash.getText().trim();
        if (hash.isEmpty()) {
            view.lblStatus.setText("Vui lòng nhập mã băm.");
            view.lblStatus.setForeground(Color.RED);
            return;
        }
        try {
            String signature = model.sign(selectedKeyFile, hash);
            view.txtSignature.setText(signature);
            view.btnCopy.setEnabled(true);
            view.lblStatus.setText("Ký thành công.");
            view.lblStatus.setForeground(Color.DARK_GRAY);
        } catch (Exception ex) {
            view.txtSignature.setText("");
            view.btnCopy.setEnabled(false);
            view.lblStatus.setText("Lỗi: " + ex.getMessage());
            view.lblStatus.setForeground(Color.RED);
        }
    }

    private void doCopy() {
        String sig = view.txtSignature.getText();
        if (!sig.isEmpty()) {
            copyText(sig, "Đã sao chép chữ ký.");
            view.lblStatus.setText("Đã sao chép chữ ký.");
        }
    }

    public void generateKey() {
        view.btnGenerate.setEnabled(false);
        view.lblGenStatus.setText("Đang tạo keypair...");
        view.lblGenStatus.setForeground(Color.DARK_GRAY);

        SwingWorker<KeyPair, Void> worker = new SwingWorker<>() {
            @Override
            protected KeyPair doInBackground() throws Exception {
                return model.generateKeyPair();
            }

            @Override
            protected void done() {
                try {
                    lastGeneratedKeyPair = get();
                    view.txtPrivateKey.setText(model.formatPrivateKey(lastGeneratedKeyPair));
                    view.txtPublicKey.setText(model.formatPublicKey(lastGeneratedKeyPair));
                    view.btnCopyPrivate.setEnabled(true);
                    view.btnCopyPublic.setEnabled(true);
                    view.btnSavePrivate.setEnabled(true);
                    view.btnSavePublic.setEnabled(true);
                    view.lblGenStatus.setText("Tạo keypair thành công.");
                    view.lblGenStatus.setForeground(new Color(0, 120, 0));
                } catch (Exception ex) {
                    view.lblGenStatus.setText("Lỗi: " + ex.getCause().getMessage());
                    view.lblGenStatus.setForeground(new Color(180, 0, 0));
                } finally {
                    view.btnGenerate.setEnabled(true);
                }
            }
        };

        worker.execute();
    }

    public void copyText(String text, String successMessage) {
        if (!text.isEmpty()) {
            Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .setContents(new java.awt.datatransfer.StringSelection(text), null);
            view.lblGenStatus.setText(successMessage);
            view.lblGenStatus.setForeground(Color.DARK_GRAY);
        }
    }

    public void saveKey(String content, String defaultFileName, String keyLabel) {
        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(new File(defaultFileName));
        int result = fc.showSaveDialog(view);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            if (!file.getName().endsWith(".key")) {
                file = new File(file.getAbsolutePath() + ".key");
            }
            try {
                Files.writeString(file.toPath(), content);
                view.lblGenStatus.setText("Đã lưu " + keyLabel + ": " + file.getName());
                view.lblGenStatus.setForeground(new Color(0, 120, 0));
            } catch (Exception ex) {
                view.lblGenStatus.setText("Lỗi khi lưu: " + ex.getMessage());
                view.lblGenStatus.setForeground(new Color(180, 0, 0));
            }
        }
    }
}