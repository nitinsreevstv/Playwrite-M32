package M32.TestCases;
import java.util.Random;

public class Helper {
    public String generateRandomPhone(int digits) {
        Random rand = new Random();
        StringBuilder phone = new StringBuilder();
        for (int i = 0; i < digits; i++) {
            phone.append(rand.nextInt(10));
        }
        return phone.toString();
    }
}
