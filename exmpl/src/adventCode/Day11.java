package adventCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static java.util.Arrays.deepToString;
import static java.util.Arrays.sort;
import static java.util.Arrays.stream;

import java.awt.*;

public class Day11 {
	
	static final char L = 'L';
	static final char OCCUPIED = '#';
	
	public static void main(String[] args) {
		Path path = Paths.get("resources/day11.txt");
		try {
			List<String> inputs = Files.readAllLines(path);
			char[][] seating = new char[inputs.size() + 1][inputs.get(0).length() + 1];
			for (int i = 0; i < inputs.size(); i++) {
				int j = 0;
				for (char c: inputs.get(i).toCharArray()) {
					seating[i][j++] = c;
				}
			}
			
			char[][] nseating = getSeating(seating);
			System.out.println();
			getSeating(nseating);
			System.out.println(part1(seating));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static char[][] getSeating(char[][] seating) {
		char[][] nseating = new char[seating.length][seating[0].length];
		for (int i = 0; i < seating.length; i ++) {
			for (int j = 0; j < seating[i].length; j++) {
				if (seating[i][j] == L && countOfAdjacentSeats(seating, i, j) == 0) {
					nseating[i][j] = OCCUPIED;
				} else if (seating[i][j] == OCCUPIED && countOfAdjacentSeats(seating, i, j) >= 4) {
					nseating[i][j] = L;
				} else {
					nseating[i][j] = seating[i][j];
				}
				System.out.print(nseating[i][j]);
			}
			System.out.println();
		}
		return nseating;
	}
	static int countOfAdjacentSeats(char[][] seating, int i, int j) {
		int noOfAdjSeatsOccupied = 0;
		if (i-1 > 0) {
			noOfAdjSeatsOccupied += seating[i-1][j] == OCCUPIED ? 1 : 0;
			if (j-1 > 0) {
				noOfAdjSeatsOccupied += seating[i-1][j-1] == OCCUPIED ? 1 : 0;
			}
		}
		if (j-1 > 0) {
			noOfAdjSeatsOccupied += seating[i][j-1] == OCCUPIED ? 1 : 0;
			if (i+1 < seating.length) {
				noOfAdjSeatsOccupied += seating[i+1][j-1] == OCCUPIED ? 1 : 0;
			}
		}
		if (i+1 < seating.length) {
			noOfAdjSeatsOccupied += seating[i+1][j] == OCCUPIED ? 1 : 0;
		}
		if (j+1 < seating[i].length) {
			noOfAdjSeatsOccupied += seating[i][j+1] == OCCUPIED ? 1 : 0;
			if (i+1 < seating.length) {
				noOfAdjSeatsOccupied += seating[i+1][j+1] == OCCUPIED ? 1 : 0;
			}
			if (i-1 > 0) {
				noOfAdjSeatsOccupied += seating[i-1][j+1] == OCCUPIED ? 1 : 0;
			}
		}
		return noOfAdjSeatsOccupied;
	}

		
		static long part1(char[][] input)  {
			int changes;
			do{
				changes = 0;
				char[][] newGrid = new char[input.length][input[0].length];
				for(int i = 0; i<input.length; i++){
					for(int j = 0; j<input[0].length; j++){
						char[][] finalInput = input;
						long occupied = countOfAdjacentSeats(finalInput, i, j);
						changes = getChanges(input, changes, newGrid, i, j, occupied, 4L);
					}
				}
				input = newGrid;
				printMatrix(input);
				System.out.println("\n");
			} while(changes > 0);
			return getCount(input, '#');
		}

		static void printMatrix(char[][] seating) {
			for (int i = 0; i < seating.length; i ++) {
				for (int j = 0; j < seating[i].length; j++) {
					System.out.print(seating[i][j]);
				}
				System.out.println();
			}
		}
		
		private static int getChanges(char[][] input, int changes, char[][] newGrid, int i, int j, long occupied, long numOccupied) {
			if(occupied == 0L && input[i][j] == 'L'){
				newGrid[i][j] = '#';
				changes++;
			} else if(occupied >= numOccupied && input[i][j] == '#'){
				newGrid[i][j] = 'L';
				changes++;
			} else {
				newGrid[i][j] = input[i][j];
			}
			return changes;
		}

		private static long getCount(char[][] input, char ch) {
			return stream(input).flatMapToInt(c -> new String(c).chars()).filter(c -> c == ch).count();
		}

		public Object part2()  {
			char[][] input = dayGrid();
			int changes;
			do{
				changes = 0;
				char[][] newGrid = new char[input.length][input[0].length];
				for(int i = 0; i<input.length; i++){
					for(int j = 0; j<input[0].length; j++){
						int occupied = countOccupied(input, i, j);
						changes = getChanges(input, changes, newGrid, i, j, occupied, 5L);
					}
				}
				input = newGrid;
			} while (changes > 0);
			return getCount(input, '#');
		}

		private int countOccupied(char[][] input, int i, int j) {
			int occupied = 0;
			Direction[] dirs = Direction.values();
			for(Direction dir : dirs){
				Point p = new Point(i, j);
				p = dir.move(p, 1);
				while(p.x>=0 && p.x< input.length && p.y>=0 && p.y< input[0].length && input[p.x][p.y] != 'L'){
					if(input[p.x][p.y] == '#'){
						occupied++;
						break;
					}
					p = dir.move(p, 1);
				}
			}
			return occupied;
		}

}
