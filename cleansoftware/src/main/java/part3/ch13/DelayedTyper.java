package part3.ch13;

public class DelayedTyper implements Command {
    private static final ActiveObjectEngine ENGINE = new ActiveObjectEngine();
    private static boolean stop = false;

    private final long itsDelay;
    private final char itsChar;

    public DelayedTyper(final long itsDelay, final char itsChar) {
        this.itsDelay = itsDelay;
        this.itsChar = itsChar;
    }

    public static void main(String[] args) throws Exception {
        ENGINE.add(new DelayedTyper(100, '1'));
        ENGINE.add(new DelayedTyper(300, '3'));
        ENGINE.add(new DelayedTyper(500, '5'));
        ENGINE.add(new DelayedTyper(700, '7'));
        Command stopCommand = () -> stop = true;
        ENGINE.add(new SleepCommand(20000, ENGINE, stopCommand));
        ENGINE.run();
    }

    @Override
    public void execute() throws Exception {
        System.out.print(itsChar);
        if (!stop) {
            delayAndRepeat();
        }
    }

    private void delayAndRepeat() throws Exception {
        ENGINE.add(new SleepCommand(itsDelay, ENGINE, this));
    }
}
