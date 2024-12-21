import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void stringSchemaUnRequiredTest1() throws Exception {
        Validator validator = new Validator();
        var schema = validator.string();
        assertEquals(schema.isValid(""), true);
    }

    @Test
    void stringSchemaRequiredTes1() throws Exception {
        Validator validator = new Validator();
        var schema = validator.string();
        schema.required();
        assertEquals(schema.isValid(null), false);
    }

    @Test
    void stringSchemaRequiredTest2() throws Exception {
        Validator validator = new Validator();
        var schema = validator.string();
        schema.required();
        assertEquals(schema.isValid(""), false);
    }

    @Test
    void stringSchemaRequiredTest3() throws Exception {
        Validator validator = new Validator();
        var schema = validator.string();
        schema.required();
        assertEquals(schema.isValid("text"), true);
    }

    @Test
    void stringSchemaRequiredAndOtherTest1() throws Exception {
        Validator validator = new Validator();
        var schema = validator.string().required().contains("ex").minLength(1);
        assertEquals(schema.isValid("text"), true);
    }

    @Test
    void stringSchemaRequiredAndOtherTest2() throws Exception {
        Validator validator = new Validator();
        var schema = validator.string().required().contains("some").minLength(1);
        assertEquals(schema.isValid("text"), false);
    }
}
