package pl.mgluchowski;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Calculate {

    static void calculate(List<Coordinate> coorProjList, List<Coordinate> coorMeasList) throws IOException {
        List<Result> results = new ArrayList<>();
        int lenght = coorMeasList.size();
        for (int i = 0; i < lenght; i++) {
            results.add(count(coorMeasList.get(i), coorProjList));
        }

        OutputData.exportTxt(results);

    }

    static Result count(Coordinate pointMeasured, List<Coordinate> coorProjList) {
        int lenghtList = coorProjList.size();
        double[] countedDeltaXY = null;
        Coordinate PPoint1 = null;
        Coordinate PPoint2 = null;
        for (int i = 0; i < lenghtList - 1; i++) {
            PPoint1 = coorProjList.get(i);
            PPoint2 = coorProjList.get(i + 1);
            countedDeltaXY = countDelta(pointMeasured, PPoint1, PPoint2);
            if (countedDeltaXY != null) {
                break;
            }
        }
        double deltaXY = countedDeltaXY[0];
        StringBuilder deltaXYString1 = new StringBuilder(Double.toString(deltaXY));
        String roundedDeltaXYMM = roundMM(deltaXYString1, false);
        StringBuilder deltaXYString2 = new StringBuilder(roundedDeltaXYMM);
        String roundedDeltaXYCM = roundCM(deltaXYString2, false);

        double pH1 = Double.parseDouble(PPoint1.getCoordH());
        double pH2 = Double.parseDouble(PPoint2.getCoordH());

        double dpH = pH2 - pH1;
        double projH = pH1 + ((countedDeltaXY[2] * dpH) / countedDeltaXY[1]);
        StringBuilder projHString = new StringBuilder(Double.toString(projH));
        String projHStringMM = roundMM(projHString, false);
        double deltaH = Double.parseDouble(pointMeasured.getCoordH()) - Double.parseDouble(projHStringMM);
        StringBuilder deltaHString = new StringBuilder(Double.toString(deltaH));
        String roundedDeltaHCM = roundCM(deltaHString, false);

        Result result = new Result(pointMeasured, PPoint1, PPoint2, roundedDeltaXYCM, roundedDeltaHCM);
        return result;
    }

    static double[] countDelta(Coordinate MPoint, Coordinate PPoint1, Coordinate PPoint2) {
        double A;
        double B;
        double L;
        double a; // delta
        double c1;
        double b1;
        double c2;
        double b2;
        boolean isUnderLine = false;

        double Mx = Double.parseDouble(MPoint.getCoordX());
        double My = Double.parseDouble(MPoint.getCoordY());

        double P1x = Double.parseDouble(PPoint1.getCoordX());
        double P1y = Double.parseDouble(PPoint1.getCoordY());

        double P2x = Double.parseDouble(PPoint2.getCoordX());
        double P2y = Double.parseDouble(PPoint2.getCoordY());

        A = (P1y - P2y) / (P1x - P2x);
        B = P1y - (A * P1x);
        L = lenght(PPoint1, PPoint2);

        a = modulus((A * Mx) + (-1 * My) + B) / Math.sqrt((A * A) + ((-1) * (-1)));

        c1 = lenght(PPoint1, MPoint);
        b1 = Math.sqrt((c1 * c1) - (a * a));

        c2 = lenght(PPoint2, MPoint);
        b2 = Math.sqrt((c2 * c2) - (a * a));

        if (((A * Mx) + B - My) > 0) {
            isUnderLine = true;
        }

        if ((L + 0.0001) < (b1 + b2)) {
            return null;
        } else {
            if (isUnderLine) {
                return new double[]{a, L, b1, b2};
            } else {
                return new double[]{-a, L, b1, b2};
            }
        }
    }

    static double lenght(Coordinate Point1, Coordinate Point2) {
        double L;
        double P1x = Double.parseDouble(Point1.getCoordX());
        double P1y = Double.parseDouble(Point1.getCoordY());

        double P2x = Double.parseDouble(Point2.getCoordX());
        double P2y = Double.parseDouble(Point2.getCoordY());

        L = Math.sqrt(((P2x - P1x) * (P2x - P1x)) + ((P2y - P1y) * (P2y - P1y)));

        return L;
    }

    static double modulus(double a) {
        return Math.sqrt(a * a);
    }

    private static String roundMM(StringBuilder number, boolean isH) {
        int roundPlaces = 3;
        int dotPosition = number.indexOf(".");
        int lenght = number.length();
        boolean isKB = true;

        if (isH && number.toString().equals("0")) {
            return "";
        }

        if (dotPosition == -1 && roundPlaces == 0) {
            return number.toString();
        }
        if (dotPosition == -1) {
            number.append('.');
            for (int i = 0; i < roundPlaces; i++) {
                number.append('0');
            }
            return number.toString();
        }

        int digitsAfterDot = lenght - (dotPosition + 1);
        if (digitsAfterDot == roundPlaces) {
            return number.toString();
        }
        if (digitsAfterDot < roundPlaces) {
            for (int i = 0; i < (roundPlaces - digitsAfterDot); i++) {
                number.append('0');
            }
            return number.toString();
        }
        if (digitsAfterDot > roundPlaces) {
            return round(number, roundPlaces, isKB);
        }
        return "";
    }

    private static String roundCM(StringBuilder number, boolean isH) {
        int roundPlaces = 2;
        int dotPosition = number.indexOf(".");
        int lenght = number.length();
        boolean isKB = true;

        if (isH && number.toString().equals("0")) {
            return "";
        }

        if (dotPosition == -1 && roundPlaces == 0) {
            return number.toString();
        }
        if (dotPosition == -1) {
            number.append('.');
            for (int i = 0; i < roundPlaces; i++) {
                number.append('0');
            }
            return number.toString();
        }

        int digitsAfterDot = lenght - (dotPosition + 1);
        if (digitsAfterDot == roundPlaces) {
            return number.toString();
        }
        if (digitsAfterDot < roundPlaces) {
            for (int i = 0; i < (roundPlaces - digitsAfterDot); i++) {
                number.append('0');
            }
            return number.toString();
        }
        if (digitsAfterDot > roundPlaces) {
            return round(number, roundPlaces, isKB);
        }
        return "";
    }

    public static String round(StringBuilder value, int places, boolean isKB) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value.toString());
        if (isKB) {
            bd = bd.setScale(places, RoundingMode.HALF_EVEN);
        } else {
            bd = bd.setScale(places, RoundingMode.HALF_UP);
        }
        return bd.toString();
    }

}
