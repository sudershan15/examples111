package adventCode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day12 {

	public static void main(String[] args) {
		Path path = Paths.get("resources/day12.txt");
		try {
			Ship ship = new Ship();
			shipWithWaypoint ship2 = new shipWithWaypoint();
			Files.readAllLines(path).forEach(v -> {
				int units = Integer.parseInt(v.substring(1));
				ship.move(v.charAt(0), units);
				ship2.move(v.charAt(0), units);
			});
			System.out.println("P1: " + ship.manhattenDistance());
			System.out.println("P2: " + ship2.manhattanDistance());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class Ship {
		private int eastWestCounter = 0;
		private int northSouthCounter = 0;
		private int orientation = 90;
		private static final int northDegrees = 0;
		private static final int eastDegrees = 90;
		private static final int westDegrees = 270;
		private static final int southDegrees = 180;

		public int manhattenDistance() {
			return Math.abs(eastWestCounter) + Math.abs(northSouthCounter);
		}

		public void move(char toMove, int value) {
			switch (toMove) {
			case 'N':
				northSouthCounter += value;
				break;
			case 'S':
				northSouthCounter -= value;
				break;
			case 'E':
				eastWestCounter += value;
				break;
			case 'W':
				eastWestCounter -= value;
				break;
			case 'R':
				orientation += value % 360;
				orientation = orientation % 360;
				break;
			case 'L':
				orientation -= value % 360;
				orientation = orientation < 0 ? (360 - -orientation) % 360 : (orientation % 360);
				break;
			case 'F':
				switch (orientation) {
				case northDegrees:
					northSouthCounter += value;
					break;
				case eastDegrees:
					eastWestCounter += value;
					break;
				case southDegrees:
					northSouthCounter -= value;
					break;
				case westDegrees:
					eastWestCounter -= value;
					break;
				}
			}
		}
	}

	private static class shipWithWaypoint {
		private int waypointNorth = 1;
		private int waypointEast = 10;
		private int shipNorth = 0;
		private int shipEast = 0;

		private static final int northDegrees = 0;
		private static final int eastDegrees = 90;
		private static final int westDegrees = 270;
		private static final int southDegrees = 180;

		public void move(char op, int value) {
			int tempNorth = 0;
			int tempEast = 0;
			switch (op) {
			case 'N':
				waypointNorth += value;
				break;
			case 'S':
				waypointNorth -= value;
				break;
			case 'E':
				waypointEast += value;
				break;
			case 'W':
				waypointEast -= value;
				break;
			case 'L':
			case 'R':
				tempNorth = waypointNorth;
				tempEast = waypointEast;
				switch (op == 'L' ? (360 - value) : value) {
				case (eastDegrees):
					waypointNorth = -tempEast;
					waypointEast = tempNorth;
					break;
				case (southDegrees):
					waypointNorth = -tempNorth;
					waypointEast = -tempEast;
					break;
				case (westDegrees):
					waypointNorth = tempEast;
					waypointEast = -tempNorth;
					break;
				case northDegrees:
					tempNorth = 0;
					tempEast = 0;
					break;
				}
				break;
			case 'F':
				shipEast += value * waypointEast;
				shipNorth += value * waypointNorth;
				break;
			}
		}

		public int manhattanDistance() {
			return Math.abs(shipNorth) + Math.abs(shipEast);
		}
	}
}
