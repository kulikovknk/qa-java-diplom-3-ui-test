package generator;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;

public class UserAccountGenerator {

    private static String getRandomString(int stringLength) {
        return RandomStringUtils.randomAlphabetic(stringLength);
    }

    public static HashMap<String, String> getNewUserAccount(int nameLength, int passLength) {

        HashMap<String, String> userAccount = new HashMap<>();

        String name = getRandomString(nameLength);
        String password = getRandomString(passLength);

        userAccount.put("name", name);
        userAccount.put("email", String.format("%s@mail.ru", name));
        userAccount.put("password", password);

        return userAccount;

    }

    public static HashMap<String, String> getExistingUserAccount() {

        HashMap<String, String> userAccount = new HashMap<>();

        userAccount.put("name", "hgfJGjJHGJ");
        userAccount.put("email", "hgfJGjJHGJ@mail.ru");
        userAccount.put("password", "HGjjGGfjtFJGFJGf");

        return userAccount;
    }

}
