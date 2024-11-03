package pt.psoft.g1.psoftg1.shared.model.domainService;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

@Component
@Profile("idGeneratorHash")
public class IdGeneratorHash implements IIdGenerator {
    @Override
    public String generateId(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(value.getBytes(StandardCharsets.UTF_8));

            String base36Hash = new BigInteger(1, hashBytes).toString(36);

            return base36Hash.length() > 20 ? base36Hash.substring(0, 20) : String.format("%-20s", base36Hash).replace(' ', '0');

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash: " + e.getMessage());
        }
    }
}
