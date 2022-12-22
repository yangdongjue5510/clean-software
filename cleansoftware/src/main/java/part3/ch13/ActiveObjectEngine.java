package part3.ch13;

import java.util.LinkedList;

public class ActiveObjectEngine {

    private final LinkedList<Command> commands = new LinkedList<>();

    public void add(Command c) {
        commands.add(c);
    }

    public void run() throws Exception {
        while(!commands.isEmpty()) {
            commands.pop()
                    .execute();
        }
    }
}
