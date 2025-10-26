package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {

    // (선택) 매직 넘버 상수화
    private static final int MIN_RAND = 0;
    private static final int MAX_RAND = 9;
    private static final int MOVE_THRESHOLD = 4;

    public static void main(String[] args) {

        List<String> carNames = readAndValidateNames();
        int tryCount = readAndValidateTryCount();

        int[] positions = new int[carNames.size()];

        System.out.println("\n실행 결과");
        for (int round = 0; round < tryCount; round++) {
            playRound(positions, carNames.size());
            printRound(carNames, positions);
        }

        printWinners(carNames, positions);
    }

    private static List<String> readAndValidateNames() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String input = Console.readLine();

        if (!Validator.isnNotBlankInput(input)) {
            throw new IllegalArgumentException("자동차 이름이 비어있습니다.");
        }

        List<String> names = Arrays.stream(input.split(",", -1))
                .map(String::trim)
                .toList();

        Validator.validateCarNames(names);
        return names;
    }

    private static int readAndValidateTryCount() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        String raw = Console.readLine();

        int count;
        try {
            count = Integer.parseInt(raw.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 양의 정수여야 합니다.");
        }

        if (!Validator.isValidTryCount(count)) {
            throw new IllegalArgumentException("시도 횟수는 1 이상이어야 합니다.");
        }
        return count;
    }

    private static void playRound(int[] positions, int carCount) {
        for (int i = 0; i < carCount; i++) {
            int random = Randoms.pickNumberInRange(MIN_RAND, MAX_RAND);
            if (random >= MOVE_THRESHOLD) {
                positions[i]++;
            }
        }
    }

    private static void printRound(List<String> names, int[] positions) {
        for (int i = 0; i < names.size(); i++) {
            String bar = "-".repeat(positions[i]);
            System.out.println(names.get(i) + " : " + bar);
        }
        System.out.println();
    }

    private static void printWinners(List<String> names, int[] positions) {
        int max = Arrays.stream(positions).max().orElse(0);

        List<String> winners = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            if (positions[i] == max) {
                winners.add(names.get(i));
            }
        }
        System.out.println("최종 우승자 : " + String.join(", ", winners));
    }
}
