package base;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Action extends Base {

    public void print(String print) {
        log.info(print);
    }

}
