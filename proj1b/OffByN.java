public class OffByN implements CharacterComparator {
    private static final char DIFF = 'a' - 'A';
    private int N;

    public OffByN(int N) {
        this.N = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (x >= 'a' && x <= 'z') {
            x = (char) (x - DIFF);
        }
        if (y >= 'a' && y <= 'z') {
            y = (char) (y - DIFF);
        }
        int delta = x - y;
        return delta == N || delta == -N;
    }

}
