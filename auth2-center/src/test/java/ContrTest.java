import org.junit.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ContrTest {
    @Test
    public void test89() {


        // 加密
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encode = encoder.encode("123456".trim());
        System.out.println(encode);


    }


}
