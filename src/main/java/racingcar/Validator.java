package racingcar;

import java.util.List;

public class Validator {

    public static boolean stringCheck(String input){
        return input != null && !input.trim().isEmpty();
    }

    public static boolean carNameCheck(List<String> cars) {
        for (String car : cars) {
            if (car.isEmpty() || car.length() > 5) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkCount(int count){
        return !(count <= 0);
    }
}
