package battleships;

public class Grid {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;

    private final Piece[][] grid = new Piece[HEIGHT][WIDTH];
    private int move_count;
    
    /*
     * Initialises grid so that all pieces represent water.
     */
    public Grid() {
    	for (int row = 0; row < grid.length; row++) {
        	for (int column = 0; column < grid[0].length; column++) {
        		alterPieceAtCoord(new Coordinate(row, column), Piece.WATER);
        	}
    	}
    	
    	move_count = 0;
    }
    
    /*
     * Determines if a ship can be placed at a given coordinate
     * either vertically or horizontally.
     */
    public boolean canPlace(Coordinate c, int size, boolean isDown) {
    	boolean can_be_placed, in_grid, water_at_coord;
    	Coordinate current_coord;
    	
    	can_be_placed = true;
    	for (int i = 0; i < size; i++) {
    		if (isDown) {
    			current_coord = new Coordinate(c.getRow() + i, c.getColumn());
    		} else {
    			current_coord = new Coordinate(c.getRow(), c.getColumn() + i);
    		}
    		in_grid = coordInGrid(current_coord);
    		water_at_coord = checkPieceAtCoord(current_coord, Piece.WATER);
    		
    		if (!(in_grid && water_at_coord)) {
    			can_be_placed = false;
    		}
    	}
    	
        return can_be_placed;
    }
    
    public boolean coordInGrid(Coordinate c) {
    	int row, column;
    	row = c.getRow(); column = c.getColumn();
    	boolean in_grid = 0 <= row    && row    < WIDTH 
    	               && 0 <= column && column < HEIGHT;
    	return in_grid;
    }
    
    /*
     * Checks if the specified piece matches with the piece
     * at the given coordinates.
     */
    public boolean checkPieceAtCoord(Coordinate c, Piece p) {
    	boolean matching_piece;
    	if (!coordInGrid(c)) { return false; }
    	matching_piece = grid[c.getRow()][c.getColumn()].equals(p);
    	return matching_piece;
    }
    
    /*
     * Changes the piece at the given coordinates to the specified piece.
     */
    public void alterPieceAtCoord(Coordinate c, Piece p) {
    	grid[c.getRow()][c.getColumn()] = p;
    }

    public void placeShip(Coordinate c, int size, boolean isDown) {
        if (!this.canPlace(c, size, isDown)) { return; }
        Coordinate ship_piece_coord;
        
        for (int i = 0; i < size; i++) {
    		if (isDown) {
    			ship_piece_coord = new Coordinate(c.getRow() + i, 
    					                          c.getColumn());
    		} else {
    			ship_piece_coord = new Coordinate(c.getRow(), 
    					                          c.getColumn() + i);
    		}
    		alterPieceAtCoord(ship_piece_coord, Piece.SHIP);
        }
    }

    public boolean wouldAttackSucceed(Coordinate c) {
        boolean would_damage;
        if (!coordInGrid(c)) {
        	System.out.println("\nInvalid attack coordinates");
        	return false;
        } else if (checkPieceAtCoord(c, Piece.DAMAGED_SHIP)) {
        	System.out.println("\nYou have already damaged a ship there");
        	return false;
        } else if (checkPieceAtCoord(c, Piece.MISS)) {
        	System.out.println("\nYou have already tried attacking there");
        	return false;
        }
        move_count++;
        would_damage = checkPieceAtCoord(c, Piece.SHIP);
        return would_damage;
    }

    public void attackCell(Coordinate c) {
        if (wouldAttackSucceed(c)) {
        	alterPieceAtCoord(c, Piece.DAMAGED_SHIP);
        	System.out.println("\nDirect hit!");
        } else if (checkPieceAtCoord(c, Piece.WATER)) {
        	alterPieceAtCoord(c, Piece.MISS);
        }
    }

    public boolean areAllSunk() {
    	Coordinate current_coord;
    	
    	for (int row = 0; row < grid.length; row++) {
        	for (int column = 0; column < grid[0].length; column++) {
        		current_coord = new Coordinate(row, column);
        		if (checkPieceAtCoord(current_coord, Piece.SHIP)) {
        			return false;
        		}
        	}
    	}
    	
    	return true;
    }
    
    public int getMoveCount() {
    	return move_count;
    }

    /*
     * Returns a string representation of the grid with undamaged ship pieces
     * hidden.
     */
    public String toPlayerString() {
    	String player_view;
    	player_view = toString();
    	player_view = player_view.replaceAll("#", ".");
    	return player_view;    	
    }

    @Override
    public String toString() {
        return renderGrid(grid);
    }

    private static String renderGrid(Piece[][] grid) {
        StringBuilder sb = new StringBuilder();
        sb.append(" 0123456789\n");
        for (int i = 0; i < grid.length; i++) {
            sb.append((char) ('A' + i));
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == null) {
                    return "!";
                }
                switch (grid[i][j]) {
                case SHIP:
                    sb.append('#');
                    break;
                case DAMAGED_SHIP:
                    sb.append('*');
                    break;
                case MISS:
                    sb.append('o');
                    break;
                case WATER:
                    sb.append('.');
                    break;
                }

            }
            sb.append('\n');
        }

        return sb.toString();
    }
}
