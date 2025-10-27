package racingcar;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RacingApp {
    private final InputView input;
    private final OutputView output;

    public RacingApp() {
        this.input = new InputView();
        this.output = new OutputView();
    }

    public void run() {
        List<String> names = getValidatedNames();
        int tryCount = getValidatedTryCount();
        execute(names, tryCount);
    }

    private List<String> getValidatedNames() {
        String rawNames = input.readNames();
        if (!Validator.isNotBlankInput(rawNames)) {
            throw new IllegalArgumentException("자동차 이름이 비어있습니다.");
        }
        List<String> names = Arrays.stream(rawNames.split(",", -1))
                .map(String::trim)
                .collect(Collectors.toList());
        Validator.validateCarNames(names);
        return names;
    }

    private int getValidatedTryCount() {
        String rawTry = input.readTryCount();
        int tryCount;
        try {
            tryCount = Integer.parseInt(rawTry.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 양의 정수여야 합니다.");
        }
        if (!Validator.isValidTryCount(tryCount)) {
            throw new IllegalArgumentException("시도 횟수는 1 이상이어야 합니다.");
        }
        return tryCount;
    }

    private void execute(List<String> names, int tryCount) {
        new RaceRunner(output).run(names, tryCount);
    }
}
