package i.tile;

import i.enums.TileType;

import java.io.Serializable;

public abstract class Tile implements Serializable {
    private static final long serialVersionUID = 1169497781180693820L;

    private int xPos;
    private int yPos;
//    protected Command onLand;
//    protected Command onPass;

    public Tile(int x, int y) {
        setX(x);
        setY(y);
    }

    public int getX() {
        return xPos;
    }

    public int getY() {
        return yPos;
    }

    public void setX(int x) {
        xPos = x;
    }

    public void setY(int y) {
        yPos = y;
    }

    abstract public TileType getTileType();

//    public void setOnLandCommand(Command command) {
//        onLand = command;
//    }
//
//    public void setOnPassCommand(Command command) {
//        onPass = command;
//    }
//
//    public void onPass(Player movingPlayer) {
//        onPass.execute(movingPlayer);
//    }
//
//    public void onLand(Player movingPlayer) {
//        onLand.execute(movingPlayer);
//    }
}
