package userflow;

import userflow.option.Option;
import java.util.Map;

public interface Options {

    void display();

    Map<Integer, Option> getOptions();
}
