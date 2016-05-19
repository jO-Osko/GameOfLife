package GameOfLife;


public class Settings {
    public static int lineWidth = 1;
    public static int lineHeight = lineWidth;

    public static int cellWidth = 10;
    public static int cellHeight = cellWidth;

    public static int rowNumber = 20;
    public static int colNumber = 30;

    public static int canvasHeight = -1; // rowNumber * cellHeight + (rowNumber - 1) * lineHeight;
    public static int canvasWidth = -1;  // colNumber * cellWidth  + (colNumber - 1) * lineWidth;

    public static final int menuHeight = 25;

    public static int windowHeight = menuHeight + canvasHeight;
    public static int windowWidth = canvasWidth;

    static {
        recalculateDimensions();
    }

    public static void setDimensions(int newRowNumber, int newColNumber){
        rowNumber = newRowNumber;
        colNumber = newColNumber;

        // recalculate
        recalculateDimensions();

    }


    public static void recalculateDimensions(){
        canvasHeight = (rowNumber - 1) * cellHeight + (rowNumber - 1) * lineHeight;
        canvasWidth = (colNumber - 1) * cellWidth + (colNumber - 1) * lineWidth;
        windowHeight = menuHeight + canvasHeight;
        windowWidth = canvasWidth;
    }

}
