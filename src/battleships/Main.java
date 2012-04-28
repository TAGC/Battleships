package battleships;

import java.util.Scanner;
import java.util.Random;

public class Main {
	
	private static Random gen;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Grid grid = makeInitialGrid();
        String attack_string;
        Coordinate attack_coords;
        gen = new Random();
        
        System.out.println("              Battleships\n\n");
        
        while (!grid.areAllSunk()) {
	        System.out.println("Please enter a coordinate to attack at: ");
	        attack_string = input.next();
	        attack_coords = Util.parseCoordinate(attack_string);
	        System.out.println("Attacking at " + attack_string + "...");
	        grid.attackCell(attack_coords);
	        System.out.println("\n\n" + grid.toPlayerString());
        }
        
        System.out.println("Game complete, # of moves = " 
        		           + grid.getMoveCount());
        
    }

    private static Grid makeInitialGrid() {
        Grid grid = new Grid();

        String[] coords = { "A7", "B1", "B4", "D3", "F7", "H1", "H4" };
        int[] sizes = { 2, 4, 1, 3, 1, 2, 5 };
        boolean[] isDowns = { false, true, true, false, false, true, false };

        for (int i = 0; i < coords.length; i++) {
            Coordinate c = Util.parseCoordinate(coords[i]);
            grid.placeShip(c, sizes[i], isDowns[i]);
        }

        return grid;
    }
    
    /*
     * Incomplete.
     *
    
    public static Grid makeRandomGrid() {
    	Grid grid = new Grid();
    	
    	int[] ships = { 2, 4, 1, 3, 1, 2, 5 };
    	for (int ship : ships) {
    		do {
    			
    		}
    	}
    }
  
    public static Coordinate genRandomCoord(Grid g) {
    	Coordinate coord;
    	String coord_string;
    	int first_letter = Character.getNumericValue('A');
    	do {
    		coord_string = String.format("%s%s", 
    				(char)(first_letter + gen.nextInt(26)),
    				gen.nextInt(10));
    		coord = Util.parseCoordinate(coord_string);
    	} while (!g.coordInGrid(coord));
    	
    	return coord;
    }
    */
}
