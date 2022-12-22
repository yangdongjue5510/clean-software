package part3.ch13;

public class Application {
    private static final ActiveObjectEngine ENGINE = new ActiveObjectEngine();

    public static void main(String[] args) throws Exception {
        ENGINE.add(new DelayedTyper(ENGINE, 100, '1'));
        ENGINE.add(new DelayedTyper(ENGINE, 300, '3'));
        ENGINE.add(new DelayedTyper(ENGINE, 500, '5'));
        ENGINE.add(new DelayedTyper(ENGINE, 700, '7'));
        ENGINE.add(new SleepCommand(20000, ENGINE, () -> System.exit(0)));
        ENGINE.run();
    }
}
