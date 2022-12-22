package part3.ch13;

public class DelayedTyper implements Command {
    private final ActiveObjectEngine engine;
    private final long itsDelay;
    private final char itsChar;

    public DelayedTyper(final ActiveObjectEngine engine, final long itsDelay, final char itsChar) {
        this.engine = engine;
        this.itsDelay = itsDelay;
        this.itsChar = itsChar;
    }

    @Override
    public void execute() throws Exception {
        System.out.print(itsChar);
        delayAndRepeat();
    }

    private void delayAndRepeat() throws Exception {
        engine.add(new SleepCommand(itsDelay, engine, this));
    }
}
