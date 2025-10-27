package racingcar;

import java.util.List;

public class OutputView {

    public void printHeader() {
        System.out.println("\n실행 결과");
    }

    public void printRound(List<Car> cars) {
        for (Car car : cars) {
            String bar = "-".repeat(car.getPosition());
            System.out.println(car.getName() + " : " + bar);
        }
        System.out.println();
    }

    public void printWinners(List<String> winners) {
        System.out.println("최종 우승자 : " + String.join(", ", winners));
    }

    public void printError(String message) {
        System.out.println(message);
    }
}
