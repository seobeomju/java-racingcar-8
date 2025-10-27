package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class RaceRunner {

    private static final int MIN_RAND = 0;
    private static final int MAX_RAND = 9;

    private final OutputView output;

    public RaceRunner(OutputView output) {
        this.output = output;
    }

    public void run(List<String> names, int tryCount) {
        Race race = new Race(names);
        output.printHeader();

        for (int r = 0; r < tryCount; r++) {
            race.playOneRound(() -> Randoms.pickNumberInRange(MIN_RAND, MAX_RAND));
            output.printRound(race.getCars());
        }

        output.printWinners(getWinners(race.getCars()));
    }

    private List<String> getWinners(List<Car> cars) {
        int max = cars.stream().mapToInt(Car::getPosition).max().orElse(0);
        List<String> winners = new ArrayList<>();
        for (Car car : cars) {
            if (car.getPosition() == max) {
                winners.add(car.getName());
            }
        }
        return winners;
    }
}
