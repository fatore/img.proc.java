
package br.usp.pi.core;

/**
 *
 * @author fm
 */
public abstract class Image {

    protected int columns;
    protected int rows;
    protected int columnBorder;
    protected int rowBorder;

    public Image(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        this.columnBorder = this.rowBorder = 0;
    }

    public Image() {
    }

    public int getColumns() {return columns;}
    public int getRows() {return rows;}
    public int getColumnBorder() {return columnBorder;}
    public int getRowBorder() {return rowBorder;}


    public void setColumnBorder(int xBorderWidth) {this.columnBorder = xBorderWidth;}
    public void setRowBorder(int yBorderWidth) {this.rowBorder = yBorderWidth;}

}