package pl.mgluchowski;

public class Coordinate {

    private String number;
    private String coordX;
    private String coordY;
    private String coordH;

    public Coordinate(String number, String coordX, String coordY, String coordH) {
        this.number = number;
        this.coordX = coordX;
        this.coordY = coordY;
        this.coordH = coordH;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCoordX() {
        return coordX;
    }

    public void setCoordX(String coordX) {
        this.coordX = coordX;
    }

    public String getCoordY() {
        return coordY;
    }

    public void setCoordY(String coordY) {
        this.coordY = coordY;
    }

    public String getCoordH() {
        return coordH;
    }

    public void setCoordH(String coordH) {
        this.coordH = coordH;
    }

    @Override
    public String toString() {
            return number + " " + coordX + " " + coordY + " " + coordH;
    }

}
