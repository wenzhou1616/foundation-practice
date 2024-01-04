import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.herry.code.demo.week01.Animal;
import com.herry.code.practice.week08.rpc.common.Message;

import java.io.IOException;

/**
 * @author herry
 */
public class Test {
    @org.junit.Test
    public void test01() throws IOException {
        Animal animal = new Animal();
        ObjectMapper objectMapper = new ObjectMapper();
        // 序列化
        String json = objectMapper.writeValueAsString(animal);
        System.out.println(json);

        // 反序列化
        Animal animal1 = objectMapper.readValue(json, Animal.class);
        System.out.println(animal1);


    }
}
