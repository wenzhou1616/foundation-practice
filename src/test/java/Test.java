import com.google.gson.Gson;
import com.herry.code.practice.week08.rpc.common.Message;

/**
 * @author herry
 */
public class Test {
    @org.junit.Test
    public void test01() {
        Message message = new Message();
        message.setMethodName("123");
        Gson gson = new Gson();
        String s = gson.toJson(message);
        System.out.println(s);

        Message message1 = gson.fromJson(s, Message.class);
        System.out.println(message1);
    }
}
