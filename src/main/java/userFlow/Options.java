package userFlow;

import userFlow.Option.Option;
import java.util.Map;

public interface Options {
    void execute();

    void display();

    Map<Integer, Option> getOptions();
}
