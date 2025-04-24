public class Sudoku3D {
    static final int SIZE = 3;
    static final int NUM_VALUES = 9;

    // 3D board (x, y, z)
    static int[][][] board = {
        {   // Layer z = 0
            {1, 0, 0},
            {0, 2, 0},
            {0, 0, 3}
        },
        {   // Layer z = 1
            {0, 4, 0},
            {0, 0, 5},
            {0, 0, 0}
        },
        {   // Layer z = 2
            {0, 0, 6},
            {0, 0, 0},
            {7, 0, 0}
        }
    };

    public static void main(String[] args) {
        if (solve(0, 0, 0)) {
            printBoard();
        } else {
            System.out.println("No solution found.");
        }
    }

    static boolean solve(int x, int y, int z) {
        if (x == SIZE) return true;
        int nextX = (x + 1) % SIZE;
        int nextY = (nextX == 0) ? (y + 1) % SIZE : y;
        int nextZ = (nextX == 0 && nextY == 0) ? z + 1 : z;

        if (z >= SIZE) return true;

        if (board[x][y][z] != 0) {
            return solve(nextX, nextY, nextZ);
        }

        for (int num = 1; num <= NUM_VALUES; num++) {
            if (isSafe(x, y, z, num)) {
                board[x][y][z] = num;
                if (solve(nextX, nextY, nextZ)) return true;
                board[x][y][z] = 0;
            }
        }

        return false;
    }

    static boolean isSafe(int x, int y, int z, int num) {
        // Check row (x-axis)
        for (int i = 0; i < SIZE; i++)
            if (board[i][y][z] == num) return false;

        // Check column (y-axis)
        for (int i = 0; i < SIZE; i++)
            if (board[x][i][z] == num) return false;

        // Check depth (z-axis)
        for (int i = 0; i < SIZE; i++)
            if (board[x][y][i] == num) return false;

        return true;
    }

    static void printBoard() {
        for (int z = 0; z < SIZE; z++) {
            System.out.println("Layer z = " + z + ":");
            for (int y = 0; y < SIZE; y++) {
                for (int x = 0; x < SIZE; x++) {
                    System.out.print(board[x][y][z] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
