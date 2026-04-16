package util;

public class IdGenerator {

    public static String generate(int userId, int clock) {
        return userId + "-" + clock;
    }
}