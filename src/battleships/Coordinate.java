package battleships;

public class Coordinate {

    private final int row;
    private final int column;
    
    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }
    
    @Override
    public String toString() {
    	String string_representation;
    	string_representation = String.format("(%s, %s)", row, column);
    	return string_representation;
    }
}
