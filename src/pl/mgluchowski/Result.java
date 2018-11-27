package pl.mgluchowski;

public class Result {

    private Coordinate measurePoint;
    private Coordinate projectPoint1;
    private Coordinate projectPoint2;
    private String deltaXY;
    private String deltaH;

    public Result(Coordinate measurePoint, Coordinate projectPoint1, Coordinate projectPoint2, String deltaXY, String deltaH) {
        this.measurePoint = measurePoint;
        this.projectPoint1 = projectPoint1;
        this.projectPoint2 = projectPoint2;
        this.deltaXY = deltaXY;
        this.deltaH = deltaH;
    }

    public Result(Coordinate measurePoint, Coordinate projectPoint1, Coordinate projectPoint2, String deltaXY) {
        this.measurePoint = measurePoint;
        this.projectPoint1 = projectPoint1;
        this.projectPoint2 = projectPoint2;
        this.deltaXY = deltaXY;
    }

    @Override
    public String toString() {
        return measurePoint + " p1= " + projectPoint1.getNumber() + " p2= " + projectPoint2.getNumber() + " deltaXY= " + deltaXY + " deltaH= " + deltaH;
    }
    
}
