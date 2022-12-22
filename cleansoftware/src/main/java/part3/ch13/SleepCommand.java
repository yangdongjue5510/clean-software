package part3.ch13;

public class SleepCommand implements Command {

    private final long sleepMilliSeconds;
    private final ActiveObjectEngine engine;
    private final Command wakeUpCommand;
    private long startedTime = 0;
    private boolean started = false;

    public SleepCommand(final long sleepMilliSeconds, final ActiveObjectEngine engine, final Command wakeUpCommand) {
        this.sleepMilliSeconds = sleepMilliSeconds;
        this.engine = engine;
        this.wakeUpCommand = wakeUpCommand;
    }

    @Override
    public void execute() throws Exception {
        final long currentTime = System.currentTimeMillis();
        if (!started) {
            started = true;
            startedTime = currentTime;
            engine.add(this);
            return;
        }
        if (currentTime - startedTime < sleepMilliSeconds) {
            engine.add(this);
            return;
        }
        engine.add(wakeUpCommand);
    }
}
