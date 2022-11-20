package Modules;

import java.util.Scanner;

public class Level2 {

	static int min;

	public static void main(String[] args) {
		new Level2().process();
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
		min = row * column;
		getMinimum(dungeon, mr - 1, mc - 1, 0);
		int monster = min;
		min = row * column;
		getMinimum(dungeon, ar - 1, ac - 1, 0);
		if (monster < min)
			System.out.println("No possible solution");
		else
			System.out.println(min);
	}

	private void getMinimum(int[][] dungeon, int i, int j, int noOfMoves) {
		int[][] moves = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		int row, column;
		for (int k = 0; k < 4; k++) {
			row = i + moves[k][0];
			column = j + moves[k][1];
			if ((row >= 0 & column >= 0 & row < dungeon.length & column < dungeon[0].length)) {
				if (dungeon[row][column] == 3) {
					noOfMoves += 1;
					if (noOfMoves < min)
						min = noOfMoves;
				} else if (dungeon[row][column] == 0) {
					dungeon[row][column] = 1;
					getMinimum(dungeon, row, column, noOfMoves + 1);
					dungeon[row][column] = 0;
				}
			}
		}
	}

}
