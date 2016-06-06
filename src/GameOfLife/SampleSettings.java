package GameOfLife;


public class SampleSettings implements Cloneable{
    private int numberOfRows;
    private int numberOfColumns;

    private boolean wrapVertical;
    private boolean wrapHorizontal;


    public SampleSettings(int numberOfRows, int numberOfColumns, boolean wrapVertical, boolean wrapHorizontal) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.wrapVertical = wrapVertical;
        this.wrapHorizontal = wrapHorizontal;
    }


    public SampleSettings(int numberOfRows, int numberOfColumns){
        this(numberOfRows, numberOfColumns, false, false);
    }


    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public boolean isWrapVertical() {
        return wrapVertical;
    }

    public void setWrapVertical(boolean wrapVertical) {
        this.wrapVertical = wrapVertical;
    }

    public boolean isWrapHorizontal() {
        return wrapHorizontal;
    }

    public void setWrapHorizontal(boolean wrapHorizontal) {
        this.wrapHorizontal = wrapHorizontal;
    }

    public SampleSettings copy(){
        return new SampleSettings(numberOfRows, numberOfColumns, wrapVertical, wrapHorizontal);
    }

}
