import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

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

    @Test
    void numberSchemaUnRequiredTest1() {
        Validator validator = new Validator();
        var schema = validator.number();
        assertEquals(schema.isValid(4), true);
    }

    @Test
    void numberSchemaUnRequiredTest2() {
        Validator validator = new Validator();
        var schema = validator.number();
        assertEquals(schema.isValid(null), true);
    }

    @Test
    void numberSchemaRequiredTest1() {
        Validator v = new Validator();
        var schema = v.number();
        schema.required();
        assertEquals(schema.isValid(null), false);
    }

    @Test
    void numberSchemaRequiredTest2() {
        Validator v = new Validator();
        var schema = v.number();
        schema.required();
        assertEquals(schema.isValid(4), true);
    }

    @Test
    void numberSchemaRequiredPositiveTest() {
        Validator v = new Validator();
        var schema = v.number();
        schema.required();
        assertEquals(true, schema.positive().range(1, 10).isValid(3));
    }

    @Test
    void numberSchemaRequiredUnPositiveTest() {
        Validator v = new Validator();
        var schema = v.number();
        schema.required();
        assertEquals(false, schema.positive().isValid(-1));
    }

    @Test
    void numberSchemaRequiredPositiveZeroTest() {
        Validator v = new Validator();
        var schema = v.number();
        schema.required();
        assertEquals(false, schema.positive().isValid(0));
    }

    @Test
    void numberSchemaRequiredPositiveUnRangeTest() {
        Validator v = new Validator();
        var schema = v.number();
        schema.required();
        assertEquals(false, schema.positive().range(1, 5).isValid(10));
    }

    @Test
    void mapSchemaUnRequiredTest1() {
        Validator v = new Validator();
        var schema = v.map();
        assertEquals(true, schema.isValid(null));
    }

    @Test
    void mapSchemaUnRequiredTest2() {
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        Validator v = new Validator();
        var schema = v.map();
        assertEquals(true, schema.isValid(data));
    }

    @Test
    void mapSchemaRequiredTest1() {
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        Validator v = new Validator();
        var schema = v.map();
        schema.required();
        assertEquals(true, schema.isValid(data));
    }

    @Test
    void mapSchemaRequiredTest2() {
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        Validator v = new Validator();
        var schema = v.map();
        schema.required();
        assertEquals(false, schema.isValid(null));
    }

    @Test
    void mapSchemaRequiredTest3() {
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        Validator v = new Validator();
        var schema = v.map();
        schema.required();
        assertEquals(true, schema.sizeof(2).isValid(data));
    }@Test
    void mapSchemaRequiredTest4() {
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        Validator v = new Validator();
        var schema = v.map();
        schema.required();
        assertEquals(false, schema.sizeof(3).isValid(data));
    }

}
