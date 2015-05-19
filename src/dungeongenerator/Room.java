/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeongenerator;

import java.io.PrintWriter;

/**
 *
 * @author YakusActual
 */
public class Room {
   private int originX, originY, width, height;
    private PrintWriter pw;

    public Room(int originX, int originY, int width, int height) {
        this.originX = originX;
        this.originY = originY;
        this.width = width;
        this.height = height;
        pw = new PrintWriter(System.out, true);
    }

    public int getOriginX() {
        return originX;
    }

    public int getOriginY() {
        return originY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getArea() {
        return width * height;
    } 
}
