package Modules;

import java.util.Scanner;

public class Level3 {

	static int min;
	static String minPath;

	public static void main(String[] args) {
		new Level3().process();
	}

	private void process() {
		System.out.println("Dimension of the dungeon(row x column): ");
		Scanner scan = new Scanner(System.in);
		int row = scan.nextInt();
		int column = scan.nextInt();
		int[][] dungeon = new int[row][column];
		System.out.println("Position of adventure: ");
		int ar = scan.nextInt();
		int ac = scan.nextInt();
		dungeon[ar - 1][ac - 1] = 2;
		System.out.println("Position of monster: ");
		int mr = scan.nextInt();
		int mc = scan.nextInt();
		dungeon[mr - 1][mc - 1] = 4;
		System.out.println("Position of gold: ");
		int gr = scan.nextInt();
		int gc = scan.nextInt();
		scan.close();
		dungeon[gr - 1][gc - 1] = 3;
		String path = "(" + ar + "," + ac + ") ";
		min = row * column;
		getMinimum(dungeon, mr - 1, mc - 1, 0, path);
		int monster = min;
		getMinimum(dungeon, ar - 1, ac - 1, 0, path);
		if (monster < min)
			System.out.println("No possible solution");
		else
			System.out.println(min + "\n" + minPath);
	}

	private void getMinimum(int[][] dungeon, int i, int j, int noOfMoves, String path) {
		int[][] moves = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		String newPath = "";
		int row, column;
		for (int k = 0; k < 4; k++) {
			row = i + moves[k][0];
			column = j + moves[k][1];
			if ((row >= 0 & column >= 0 & row < dungeon.length & column < dungeon[0].length)) {
				if (dungeon[row][column] == 3) {
					newPath = path + " -> (" + (row + 1) + "," + (column + 1) + ") ";
					noOfMoves += 1;
					if (noOfMoves <= min) {
						min = noOfMoves;
						minPath = newPath;
					}
				} else if (dungeon[row][column] == 0) {
					dungeon[row][column] = 1;
					newPath = path + " -> (" + (row + 1) + "," + (column + 1) + ") ";
					getMinimum(dungeon, row, column, noOfMoves + 1, newPath);
					dungeon[row][column] = 0;
				}
			}
		}
	}

}
