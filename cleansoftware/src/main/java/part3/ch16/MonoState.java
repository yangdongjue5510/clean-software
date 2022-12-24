package part3.ch16;

public class MonoState {
    private static int itsX = 0;

    public MonoState() {
    }

    public void setX(final int x) {
        itsX = x;
    }

    public int getX() {
       return itsX;
    }
}
