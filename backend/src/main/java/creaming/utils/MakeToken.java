package creaming.utils;

import java.util.Random;

public class MakeToken {

    public static String makeToken() {

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        do {
            int num = random.nextInt(75) + 48;
            if ((num >= 48 && num <= 57)
                    || (num >= 65 && num <= 90)
                    || (num >= 97 && num <= 122)) {
                sb.append((char) num);
            }
        } while (sb.length() < 40);

        return sb.toString();
    }
}
