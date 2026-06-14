package tool;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ToolController {
    private ToolView view;
    private ToolModel model;
    private File selectedKeyFile;

    public ToolController(ToolView view, ToolModel model) {
        this.view = view;
        this.model = model;
        view.btnBrowse.addActionListener(e -> browseKey());
        view.btnSign.addActionListener(e -> doSign());
        view.btnCopy.addActionListener(e -> doCopy());
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
            view.lblStatus.setForeground(new Color(180, 0, 0));
            return;
        }

        String hash = view.txtHash.getText().trim();
        if (hash.isEmpty()) {
            view.lblStatus.setText("Vui lòng nhập mã băm.");
            view.lblStatus.setForeground(new Color(180, 0, 0));
            return;
        }
        try {
            String signature = model.sign(selectedKeyFile, hash);
            view.txtSignature.setText(signature);
            view.btnCopy.setEnabled(true);
            view.lblStatus.setText("Ký thành công.");
        } catch (Exception ex) {
            view.txtSignature.setText("");
            view.btnCopy.setEnabled(false);
            view.lblStatus.setText("Lỗi: " + ex.getMessage());
        }
    }

    private void doCopy() {
        String sig = view.txtSignature.getText();
        if (!sig.isEmpty()) {
            Toolkit.getDefaultToolkit()
                    .getSystemClipboard()
                    .setContents(new java.awt.datatransfer.StringSelection(sig), null);
            view.lblStatus.setText("Đã sao chép chữ ký.");
        }
    }
}
