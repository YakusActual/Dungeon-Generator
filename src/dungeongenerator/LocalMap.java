package dungeongenerator;

/**
 *
 * @author YakusActual
 */
import java.util.ArrayList;

public class LocalMap {

    protected static Point[][] map;
    private final int HEIGHT; // height
    private final int WIDTH; // width
    private ArrayList<Sector> refinedSectors;
    private ArrayList<Sector> rawSectors;
    private ArrayList<Room> orderedRooms;
    private ArrayList<Room> evRooms;
    private ArrayList<Room> odRooms;
    final private int MINIMAL_AREA;
    final private int MIN_WIDTH;
    final private int MIN_HEIGHT;

    //Constructor, getters and setters
    public LocalMap(int width, int height) {
        this.HEIGHT = height;
        this.WIDTH = width;
        this.MINIMAL_AREA = (this.WIDTH * this.HEIGHT) / 7;
        this.MIN_WIDTH = (int) Math.ceil(this.WIDTH / 100.0) * 6;
        System.out.println("Min width: " + MIN_WIDTH);
        this.MIN_HEIGHT = (int) Math.ceil(this.HEIGHT / 100.0) * 10;
        System.out.println("Min height: " + MIN_HEIGHT);
        LocalMap.map = new Point[height][width]; // Arrays take rows>>>columns so height goes first
        refinedSectors = new ArrayList<>();
        rawSectors = new ArrayList<>();
        orderedRooms = new ArrayList<>();
        
        initMap();
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    // Map init function
    private void initMap() {
        System.out.println("W:" + this.WIDTH + ", H:" + this.HEIGHT);

        int insideTileCount = 0;
        // Filling the map insides
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                insertMapPoint(new Point(x, y, Point.BLANK));
                insideTileCount++;
            }
        }

        System.out.println("Inside tiles: " + insideTileCount);

        // Dividing map into sectors
        Sector root = new Sector(0, 0, WIDTH, HEIGHT, false);
        generateSectors(root, ' ');
        System.out.println("Sectors generated: " + rawSectors.size());
        refineSectors();
        System.out.println("Sectors after refining: " + refinedSectors.size());
        // drawAllSectors(rawSectors);
        drawAllRooms();
        
    }

    // Utilities Methods
    protected void insertMapPoint(Point p) {
        map[p.getPointY()][p.getPointX()] = p;
    }

    protected void drawRect(int originX, int originY, int width, int height, char border, char fill) {
        System.out.println("Drawing rectangle: (" + originX + "," + originY + ")" + ": " + width + "x" + height);
        for (int y = originY; y <= originY + height; y++) {
            for (int x = originX; x <= originX + width; x++) {
                if (y == originY) {
                    insertMapPoint(new Point(x, y, border));
                } else if (y == originY + height) {
                    insertMapPoint(new Point(x, y, border));
                } else {
                    insertMapPoint(new Point(x, y, fill));
                }

                if (y > originY && y < originY + height) {
                    if (x == originX) {
                        insertMapPoint(new Point(x, y, border));
                    } else if (x == originX + width) {
                        insertMapPoint(new Point(x, y, border));
                    } else {
                        insertMapPoint(new Point(x, y, fill));
                    }
                }
            }
        }
    }
    
    private void drawLine(Point a, Point b) {        
        Point start, end;
        int distV, distH;
        
        if (a.getPointX() == b.getPointX()){ // Vertical line
            distV = Math.abs(a.getPointY() - b.getPointY());
            if (a.getPointY() < b.getPointY()){ 
                start = a;
                end = b;
            } else {
                start = b;
                end = a;
            }
            
            for (int i = start.getPointY(); i < distV; i++){
                insertMapPoint(new Point(start.getPointX(), i, Point.PATH));
            }
            
        } else if (a.getPointY() == b.getPointY()) { // Horizontal line
            distH = Math.abs(a.getPointX() - b.getPointX());
            if (a.getPointX() < b.getPointX()){
                start = a;
                end = b;
            } else {
                start = b;
                end = a;
            }
            
            for (int i = start.getPointX(); i < distH; i++) {
                insertMapPoint(new Point(i, start.getPointY(), Point.PATH));
            }
        } else {
//            if (a.getPointX() < b.getPointX()){
//                
//                start = a;
//                end = b; 
//            } else {
//                start = b;
//                end = a;
//            }
        }
        
        // drawing code goes here.
    }

    protected void drawAllSectors(ArrayList<Sector> list) {
        for (Sector s : list) {
            drawRect(s.getOriginX(), s.getOriginY(), s.getWidth() - 1, s.getHeight() - 1, Point.LINE, ' ');
        }
    }
    
    protected void drawAllRooms() {
        for (Sector s : refinedSectors) {
            drawRect(s.getRoom().getOriginX(), s.getRoom().getOriginY(), s.getRoom().getWidth(), s.getRoom().getHeight(), Point.LINE, '#');
        }
    }
    
    protected void generateSectors(Sector currSector, char axis) {
        int divPoint;

        if (currSector.getArea() <= MINIMAL_AREA) {
            rawSectors.add(currSector);
        } else {
            if (axis != 'v' && axis != 'h') {
                axis = (RNEngine.rand(0, 1) == 1) ? 'v' : 'h';
            }
            if (axis == 'v') {
                divPoint = RNEngine.rand(currSector.getWidth() / 3, (currSector.getWidth() / 3) * 2);
                System.out.println("Range: " + (currSector.getWidth() / 3) + "-" + ((currSector.getWidth() / 3) * 2) + " -> " + divPoint + " : vertical");
                //insertMapPoint(new Point(divPoint, currSector.getOriginY(), "blank"));
                generateSectors(new Sector(currSector.getOriginX(), currSector.getOriginY(), divPoint, currSector.getHeight(), true), ' ');
                generateSectors(new Sector(divPoint + currSector.getOriginX(), currSector.getOriginY(), currSector.getWidth() - (divPoint), currSector.getHeight(), true), ' ');
            } else if (axis == 'h') {
                divPoint = RNEngine.rand(currSector.getHeight() / 3, (currSector.getHeight() / 3) * 2);
                System.out.println("Range: " + (currSector.getHeight() / 3) + "-" + ((currSector.getHeight() / 3) * 2) + " -> " + divPoint + " : horizontal");
                //insertMapPoint(new Point(currSector.getOriginX(), divPoint, "blank"));
                generateSectors(new Sector(currSector.getOriginX(), currSector.getOriginY(), currSector.getWidth(), divPoint, true), ' ');
                generateSectors(new Sector(currSector.getOriginX(), divPoint + currSector.getOriginY(), currSector.getWidth(), currSector.getHeight() - (divPoint), true), ' ');
            }
        }
    }

    protected void refineSectors() {
        for (Sector s : rawSectors) {
            if (s.getWidth() > s.getHeight()) {
                if (!(s.getHeight() <= MIN_HEIGHT)) {
                    refinedSectors.add(s);
                }
            } else if (s.getWidth() < s.getHeight()) {
                if (!(s.getWidth() <= MIN_WIDTH)) {
                    refinedSectors.add(s);
                }
            } else {
                refinedSectors.add(s);
            }
        }
    }

    // Map printing
    public void printMap() {
        Point currPoint;
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                currPoint = map[y][x];
                System.out.print(currPoint.getPointFace());
            }
            System.out.println();
        }
    }
}
