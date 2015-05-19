package dungeongenerator;

/**
 *
 * @author YakusActual
 */
public class Point {

    private int pointX;
    private int pointY;
    private String type;
    private char pointFace;
    private char[] allowedDirs;
    private char direction;

    static final char BORDER = '#';
    static final char BLANK = ' ';
    static final char DOOR = 'D';
    static final char PATH = 'H';
    static final char POINT_MARKER = 'X';
    static final char SECTOR_ONE = '1';
    static final char SECTOR_TWO = '2';
    static final char SECTOR_THREE = '3';
    static final char SECTOR_FOUR = '4';
    static final char LINE = '+';
    static final char DEFAULT = '0';

    public Point() {
        
    }
    
    public Point(int x, int y) {
        this.pointX = x;
        this.pointY = y;
        this.pointFace = Point.DEFAULT;
    }

    public Point(int x, int y, char type) {
        this.pointX = x;
        this.pointY = y;
        this.pointFace = type;
    }


    public char getPointFace() {
        return pointFace;
    }

    public int getPointX() {
        return pointX;
    }

    public int getPointY() {
        return pointY;
    }

    public char getDirection() {
        return direction;
    }
}
