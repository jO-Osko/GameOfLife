package GameOfLife;

/**
 * Created by Filip on 5.6.2016.
 */
public class SampleSettings implements Cloneable{
    private int numberOfRows;
    private int numberOfColumns;


    public SampleSettings(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
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

    public SampleSettings copy(){
        return new SampleSettings(numberOfRows, numberOfColumns);
    }

}
