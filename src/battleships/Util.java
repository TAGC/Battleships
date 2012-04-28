package battleships;

import java.util.Arrays;

public class Util {
	
	private static final int first_letter = 'A';
	
    private static int letterToIndex(char letter) {
    	int int_val;
    	int_val = letter - first_letter;
        return int_val;
    }
    
    private static int numberToIndex(char number) {
    	int int_val;
    	int_val = Integer.parseInt(String.format("%s",number));
        return int_val;
    }

    public static Coordinate parseCoordinate(String s) {
    	int row, column;
        Coordinate coords;
        
        row = letterToIndex(s.charAt(0));
        column = numberToIndex(s.charAt(1));
        coords = new Coordinate(row, column);
        return coords;
    }

    public static Piece hideShip(Piece piece) {
    	if (piece.equals(Piece.SHIP)) {
    		return Piece.WATER;
    	}
        return piece;
    }

    public static void hideShips(Piece[][] grid) {
    	Piece current_piece;
    	
        for (int row = 0; row < grid.length; row++) {
        	for (int column = 0; column < grid[0].length; column++) {
        		current_piece = grid[row][column];
        		current_piece  = hideShip(current_piece);
        	}
        	
        }
    }

    public static Piece[][] deepClone(Piece[][] input) {
        Piece[][] output = new Piece[input.length][];
        for (int i = 0; i < input.length; i++) {
            output[i] = Arrays.copyOf(input[i], input[i].length);
        }
        return output;
    }
}
