package tool;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class ToolModel {

    public String sign(File keyFile, String hashString) throws Exception {
        byte[] keyBytes = Files.readAllBytes(keyFile.toPath());
        String s = new String(keyBytes)
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decoded = Base64.getDecoder().decode(s);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
        KeyFactory kf = KeyFactory.getInstance("DSA");
        PrivateKey privateKey = kf.generatePrivate(spec);

        byte[] hashBytes = hashString.trim().getBytes(StandardCharsets.UTF_8);

        Signature sig = Signature.getInstance("SHA256withDSA");
        sig.initSign(privateKey);
        sig.update(hashBytes);
        byte[] signed = sig.sign();

        return Base64.getEncoder().encodeToString(signed);
    }
}
