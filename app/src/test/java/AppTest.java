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
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
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
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
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
        Map<String, BaseSchema<?>> schemas = new HashMap<>();
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
    void mapSchemaShapeCombinedChecksTest1() {
        Validator validator = new Validator();
        var schema = validator.map();

        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("name", validator.string().required().contains("Ivan").minLength(5));
        schemas.put("age",  validator.number().required().positive().range(18, 100));

        schema.shape(schemas);

        Map<String, Object> validData = Map.of(
                "name", "Ivan Ivanov",
                "age", 30
        );
        Map<String, Object> invalidData1 = Map.of(
                "name", "Ivan",
                "age", 30
        );
        Map<String, Object> invalidData2 = Map.of(
                "name", "Ivan Ivanov",
                "age", 15
        );

        assertEquals(true, schema.isValid(validData));        // Все проверки проходят
        assertEquals(false, schema.isValid(invalidData1));    // Имя не удовлетворяет minLength
        assertEquals(false, schema.isValid(invalidData2));    // Возраст не попадает в диапазон
    }

    @Test
    void mapSchemaShapeCombinedChecksTest2() {
        Validator validator = new Validator();
        var schema = validator.map();

        Map<String, BaseSchema<?>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));
        schemas.put("age", validator.number().positive().range(18, 99));

        schema.shape(schemas);

        Map<String, Object> data = Map.of(
                "firstName", "Alice",
                "lastName", "Smith",
                "age", 20
        );

        assertEquals(true, schema.isValid(data)); // Валидная структура

        Map<String, Object> invalidData = Map.of(
                "firstName", "Alice",
                "lastName", "S", // Не соответствует minLength
                "age", 20
        );

        assertEquals(false, schema.isValid(invalidData)); // Не валидно из-за lastName
    }
    @Test
    void ValidatorTest(){
        Validator v = new Validator();
        var schema = v.string().required();
    }



}
