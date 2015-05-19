package dungeongenerator;

import java.util.Scanner;

/**
 *
 * @author YakusActual
 */
public class DungeonGenerator {

    LocalMap mapInstance;

    public DungeonGenerator() {
	  // Scanner input = new Scanner(System.in);
        // System.out.println("Please enter the size of the map in format: 'x y' or input '0' to load defaults");
        // String userInput = input.nextLine();
        // if (userInput.equals("0")) {
        // 	this.mapInstance = new Map(75, 45);
        // } else {
        //  int[] size = getSizeDimensions(userInput);
        // this.mapInstance = new Map(size[1], size[0]);
        // }
        this.mapInstance = new LocalMap(200, 45);
    }

    private int[] getSizeDimensions(String s) {
        String[] temp = s.split(" ");
        int values[] = new int[2];
        values[0] = Integer.parseInt(temp[0]);
        values[1] = Integer.parseInt(temp[1]);
        return values;
    }

    public static void main(String args[]) {
        DungeonGenerator app = new DungeonGenerator();
        app.mapInstance.printMap();
    }
}
