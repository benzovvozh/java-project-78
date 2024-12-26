import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

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
    }

    @Test
    void mapSchemaRequiredTest4() {
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        data.put("key2", "value2");
        Validator v = new Validator();
        var schema = v.map();
        schema.required();
        assertEquals(false, schema.sizeof(3).isValid(data));
    }

    @Test
    void mapSchemaShapeTest1() {
        Validator v = new Validator();
        var schema = v.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);
        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertEquals(true, schema.isValid(human1));

    }

    @Test
    void mapSchemaShapeTest2() {
        Validator v = new Validator();
        var schema = v.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);
        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertEquals(false, schema.isValid(human2));

    }

    @Test
    void mapSchemaShapeTest3() {
        Validator v = new Validator();
        var schema = v.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);
        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertEquals(false, schema.isValid(human3));

    }

    @Test
    void stringSchemaCombinedChecksTest1() {
        Validator validator = new Validator();
        var schema = validator.string().required().contains("abc").minLength(5);

        assertEquals(true, schema.isValid("abcde"));
        assertEquals(false, schema.isValid("abcd"));
        assertEquals(false, schema.isValid("xyz"));
        assertEquals(false, schema.isValid(""));
    }

    @Test
    void numberSchemaCombinedChecksTest1() {
        Validator validator = new Validator();
        var schema = validator.number().required().positive().range(10, 20);

        assertEquals(true, schema.isValid(15));
        assertEquals(false, schema.isValid(5));
        assertEquals(false, schema.isValid(25));
        assertEquals(false, schema.isValid(-10));
        assertEquals(false, schema.isValid(null));
    }

    @Test
    public void testMapValidator() {
        var v = new Validator();
        var schema = v.map();

        assertEquals(true, schema.isValid(null));
        assertEquals(true, schema.isValid(new HashMap<>()));

        schema.required();
        assertEquals(false, schema.isValid(null));
        assertEquals(true, schema.isValid(new HashMap<>()));

        schema.sizeof(2);
        assertEquals(false, schema.isValid(new HashMap<>()));
        Map<String, String> actual1 = new HashMap<>();
        actual1.put("key1", "value1");
        assertEquals(false, schema.isValid(actual1));
        actual1.put("key2", "value2");
        assertEquals(true, schema.isValid(actual1));

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required().contains("ya"));
        schemas.put("lastName", v.string().required().contains("ov"));
        schema.shape(schemas);

        Map<String, String> actual2 = new HashMap<>();
        actual2.put("firstName", "Kolya");
        actual2.put("lastName", "Ivanov");
        assertEquals(true, schema.isValid(actual2));

        Map<String, String> actual3 = new HashMap<>();
        actual3.put("firstName", "Maya");
        actual3.put("lastName", "Krasnova");
        assertEquals(true, schema.isValid(actual3));

        Map<String, String> actual4 = new HashMap<>();
        actual4.put("firstName", "John");
        actual4.put("lastName", "Jones");
        assertEquals(false, schema.isValid(actual4));
    }


}
