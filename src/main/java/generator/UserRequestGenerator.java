package generator;

import dto.UserRequest;
import org.apache.commons.lang3.RandomStringUtils;

public class UserRequestGenerator {

    private static String getRandomString(int stringLength) {
        return RandomStringUtils.randomAlphabetic(stringLength);
    }

    public static UserRequest getNewUserRequest(int nameLength, int passLength) {

        String name = getRandomString(nameLength);
        String password = getRandomString(passLength);

        UserRequest userRequest = new UserRequest();

        userRequest.setName(name);
        userRequest.setEmail(String.format("%s@mail.ru", name.toLowerCase()));
        userRequest.setPassword(password);

        return userRequest;

    }

}
