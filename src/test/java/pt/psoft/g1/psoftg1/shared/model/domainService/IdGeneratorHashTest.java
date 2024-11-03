package pt.psoft.g1.psoftg1.shared.model.domainService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IdGeneratorHashTest {
    private IdGeneratorHash idGenerator;

    @BeforeEach
    void setUp() {
        idGenerator = new IdGeneratorHash();
    }

    @Test
    void ensureGenerateIdWithRegularInput() {
        String input = "testInput";
        String generatedId = idGenerator.generateId(input);

        assertNotNull(generatedId);
        assertEquals(20, generatedId.length());
    }

    @Test
    void ensureSameInputSameIdTest() {
        String input = "testInput";
        String generatedId1 = idGenerator.generateId(input);
        String generatedId2 = idGenerator.generateId(input);
        assertEquals(generatedId1, generatedId2);
    }

    @Test
    void ensureGenerateIdWithEmptyString() {
        String input = "";
        String generatedId = idGenerator.generateId(input);

        assertNotNull(generatedId);
        assertEquals(20, generatedId.length());
    }

    @Test
    void testGenerateIdWithLongString() {
        String input = "ThisIsAVeryLongStringToTestTheHashFunctionAndEnsureItHandlesLongStringsProperly";
        String generatedId = idGenerator.generateId(input);

        assertNotNull(generatedId);
        assertEquals(20, generatedId.length());
    }

    @Test
    void testGenerateIdForDifferentInputs() {
        String input1 = "inputOne";
        String input2 = "inputTwo";

        String id1 = idGenerator.generateId(input1);
        String id2 = idGenerator.generateId(input2);

        assertNotEquals(id1, id2);
    }

    @Test
    void testGenerateIdForSpecialCharacters() {
        String input = "!@#SpecialInput$%^";
        String generatedId = idGenerator.generateId(input);

        assertNotNull(generatedId);
        assertEquals(20, generatedId.length());
    }
}
