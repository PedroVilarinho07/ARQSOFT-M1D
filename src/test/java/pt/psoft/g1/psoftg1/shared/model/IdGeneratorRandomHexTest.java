package pt.psoft.g1.psoftg1.shared.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.psoft.g1.psoftg1.shared.model.domainService.IdGeneratorRandomHex;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class IdGeneratorRandomHexTest {

    private IdGeneratorRandomHex idGenerator;

    @BeforeEach
    void setUp() {
        idGenerator = new IdGeneratorRandomHex();
    }

    @Test
    void ensureGenerateIdWithRegularInput() {
        String input = "testInput";
        String generatedId = idGenerator.generateId(input);

        assertNotNull(generatedId);
        assertEquals(24, generatedId.length());
        assertTrue(generatedId.matches("[0-9a-fA-F]+"), "Generated ID should be in hexadecimal format");
    }

    @Test
    void ensureGenerateIdWithEmptyString() {
        String input = "";
        String generatedId = idGenerator.generateId(input);

        assertNotNull(generatedId);
        assertEquals(24, generatedId.length());
        assertTrue(generatedId.matches("[0-9a-fA-F]+"), "Generated ID should be in hexadecimal format");
    }

    @Test
    void ensureDifferentIdsForSameInput() {
        String input = "sameInput";
        String generatedId1 = idGenerator.generateId(input);
        String generatedId2 = idGenerator.generateId(input);

        assertNotEquals(generatedId1, generatedId2, "Each ID should be unique, even for the same input");
    }

    @Test
    void testGenerateIdWithLongString() {
        String input = "ThisIsAVeryLongStringToTestRandomIdGeneration";
        String generatedId = idGenerator.generateId(input);

        assertNotNull(generatedId);
        assertEquals(24, generatedId.length());
        assertTrue(generatedId.matches("[0-9a-fA-F]+"), "Generated ID should be in hexadecimal format");
    }

    @Test
    void testGenerateIdForSpecialCharacters() {
        String input = "!@#SpecialInput$%^";
        String generatedId = idGenerator.generateId(input);

        assertNotNull(generatedId);
        assertEquals(24, generatedId.length());
        assertTrue(generatedId.matches("[0-9a-fA-F]+"), "Generated ID should be in hexadecimal format");
    }

    @Test
    void ensureIdLengthConsistency() {
        for (int i = 0; i < 100; i++) {
            String generatedId = idGenerator.generateId("testInput" + i);
            assertEquals(24, generatedId.length(), "Generated ID should consistently be 24 characters long");
        }
    }

    @Test
    void ensureNoDuplicateIdsAcrossMultipleGenerations() {
        // Generate multiple IDs and store in a set to check for uniqueness
        Set<String> generatedIds = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            String generatedId = idGenerator.generateId("uniqueTestInput" + i);
            assertTrue(generatedIds.add(generatedId), "Generated ID should be unique across multiple generations");
        }
    }

    @Test
    void ensureIdContainsOnlyHexCharacters() {
        String input = "testInputHex";
        String generatedId = idGenerator.generateId(input);

        assertTrue(generatedId.matches("^[0-9a-fA-F]+$"), "Generated ID should only contain hexadecimal characters");
    }

    @Test
    void testGenerateIdWithNullInput() {
        // Although the method doesn’t use the input, ensure it can handle null without exceptions
        assertDoesNotThrow(() -> idGenerator.generateId(null), "Method should handle null input without throwing an exception");
    }

    @Test
    void testPerformanceOfIdGeneration() {
        // Test that the method can generate IDs quickly (within 100ms for 1000 IDs)
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            idGenerator.generateId("performanceTest" + i);
        }
        long endTime = System.currentTimeMillis();
        assertTrue((endTime - startTime) < 100, "ID generation should be performant, generating 1000 IDs in less than 100ms");
    }
}
