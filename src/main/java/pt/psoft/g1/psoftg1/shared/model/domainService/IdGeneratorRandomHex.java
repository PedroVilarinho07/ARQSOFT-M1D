package pt.psoft.g1.psoftg1.shared.model.domainService;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
@Profile("idGeneratorRandomHex")
public class IdGeneratorRandomHex implements IIdGenerator {

    private static final int ID_LENGTH = 24;
    private static final SecureRandom random = new SecureRandom();

    @Override
    public String generateId(String value) {
        byte[] randomBytes = new byte[ID_LENGTH / 2];
        random.nextBytes(randomBytes);

        StringBuilder hexId = new StringBuilder(ID_LENGTH);
        for (byte b : randomBytes) {
            hexId.append(String.format("%02x", b));
        }

        return hexId.toString();
    }
}