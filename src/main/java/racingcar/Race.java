package racingcar;

import java.util.List;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;

public class Race {
    private final List<Car> cars;
    private static final int MOVE_THRESHOLD = 4;

    public Race(List<String> names) {
        this.cars = names.stream().map(Car::new).collect(Collectors.toList());
    }

    public void playOneRound(IntSupplier rng) {
        for (Car car : cars) {
            int n = rng.getAsInt();
            if (n >= MOVE_THRESHOLD) {
                car.move();
            }
        }
    }

    public List<Car> getCars() {
        return cars;
    }
}
