package Utils;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
    private static final AtomicInteger counter = new AtomicInteger();

    public static int generateId() {
        return counter.incrementAndGet();
    }
}
