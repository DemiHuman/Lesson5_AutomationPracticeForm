package utils;

import java.util.Random;

public class RandomUtils {

    Random random = new Random();

    /**
     * Метод генерирует строку из цифр
     *
     * @param value длина строка
     * @return string
     */
    public String getRandomIntString(int value) {
        String string = "";
        for (int i = 0; i < value; i++) {
            string += String.valueOf(random.nextInt(9) + 1);
        }
        return string;
    }

}
