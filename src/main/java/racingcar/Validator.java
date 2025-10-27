package racingcar;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Validator {

    public static boolean isNotBlankInput(String input) {
        return input != null && !input.trim().isEmpty();
    }

    public static boolean isValidTryCount(int count) {
        return !(count <= 0);
    }


    public static void validateCarNames(List<String> cars) {
        requireNonEmptyList(cars);
        ensureNoEmptyToken(cars);
        ensureNamesLength(cars);
        ensureNoInternalWhitespace(cars);
        ensureNoDuplicate(cars);
    }


    private static void requireNonEmptyList(List<String> cars) {
        if (cars == null || cars.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름이 비어있습니다.");
        }
    }

    private static void ensureNoEmptyToken(List<String> cars) {
        for (String car : cars) {
            if (car == null || car.isEmpty()) {
                throw new IllegalArgumentException("자동차 이름에 빈 값이 포함되어 있습니다.");
            }
        }
    }

    private static void ensureNamesLength(List<String> cars) {
        for (String car : cars) {
            if (car.length() > 5) {
                throw new IllegalArgumentException("자동차 이름은 1~5자여야 합니다: " + car);
            }
        }
    }

    private static void ensureNoInternalWhitespace(List<String> cars) {
        for (String car : cars) {
            if (containsWhitespace(car)) {
                throw new IllegalArgumentException("자동차 이름에 공백은 사용할 수 없습니다: " + car);
            }
        }
    }

    private static void ensureNoDuplicate(List<String> names) {
        Set<String> seen = new HashSet<>();
        for (String name : names) {
            if (!seen.add(name)) {
                throw new IllegalArgumentException("자동차 이름이 중복됩니다: " + name);
            }
        }
    }

    private static boolean containsWhitespace(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isWhitespace(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}
