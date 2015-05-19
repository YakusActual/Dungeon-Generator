/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeongenerator;

/**
 *
 * @author YakusActual
 */

public class Sector {

    private int originX, originY, width, height;
    private Room room;

    public Sector(int originX, int originY, int width, int height, boolean genRoom) {
        this.originX = originX;
        this.originY = originY;
        this.width = width;
        this.height = height;
        
        if (genRoom){
            buildRoom();
        }
        
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
    
    public void buildRoom(){

        int minWidth, maxWidth, minHeight, maxHeight;
        int roomWidth, roomHeight;
        int bufferWidth, bufferHeight;
        int roomOriginX, roomOriginY;
        
        minWidth = (int) Math.floor((this.getWidth()/5)*2);
        maxWidth = (int) (this.getWidth()/5)*4;
        minHeight = (int) Math.floor((this.getHeight()/5)*2);
        maxHeight = (int) (this.getHeight()/5)*4;
        roomWidth = RNEngine.rand(minWidth, maxWidth);
        roomHeight = RNEngine.rand(minHeight, maxHeight);
        bufferWidth = (int) Math.floor((this.getWidth() - roomWidth)/2);
        bufferHeight = (int) Math.floor((this.getHeight() - roomHeight)/2);
        roomOriginX = bufferWidth + this.getOriginX();
        roomOriginY = bufferHeight + this.getOriginY();
            
        room = new Room(roomOriginX, roomOriginY, roomWidth, roomHeight);
    }
    
    public Room getRoom(){
        return room;
    }
}
