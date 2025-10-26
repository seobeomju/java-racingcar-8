package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Application {
    public static void main(String[] args) {

        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String input = Console.readLine();

        if (!Validator.isnNotBlankInput(input)) {
            throw new IllegalArgumentException("자동차 이름이 비어있습니다");
        }

        List<String> carNames = Arrays.stream(input.split(",", -1))
                .map(String::trim)
                .toList();

        Validator.validateCarNames(carNames);

        System.out.println("시도할 횟수는 몇 회인가요?");
        int count;
        try {
            count = Integer.parseInt(Console.readLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 양의 정수여야 합니다.");
        }
        if (!Validator.isValidTryCount(count)) {
            throw new IllegalArgumentException("시도 횟수는 1 이상이어야 합니다.");
        }

        int[] positions = new int[carNames.size()];

        System.out.println("\n실행 결과");
        for (int round = 0; round < count; round++) {
            for (int i = 0; i < carNames.size(); i++) {
                int random = Randoms.pickNumberInRange(0, 9);
                if (random >= 4) {
                    positions[i]++;
                }
            }

            for (int i = 0; i < positions.length; i++) {
                String bar = "-".repeat(positions[i]);
                System.out.println(carNames.get(i) + " : " + bar);
            }
            System.out.println();
        }

        int max = Arrays.stream(positions).max().orElse(0);
        List<String> winners = new ArrayList<>();
        for (int i = 0; i < carNames.size(); i++) {
            if (positions[i] == max) {
                winners.add(carNames.get(i));
            }
        }

        System.out.println("최종 우승자 : " + String.join(", ", winners));
    }

}

