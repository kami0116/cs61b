public class OffByOne implements CharacterComparator {
    private static final char DIFF = 'a' - 'A';

    @Override
    public boolean equalChars(char x, char y) {
        if (x >= 'a' && x <= 'z') {
            x = (char) (x - DIFF);
        }
        if (y >= 'a' && y <= 'z') {
            y = (char) (y - DIFF);
        }
        int delta = x - y;
        return delta == 1 || delta == -1;
    }
}
