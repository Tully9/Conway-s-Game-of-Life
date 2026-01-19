// Cell class for Conway's Game of Life

// Any live cell with fewer than two live
// neighbours dies, as if by underpopulation.

// Any live cell with two or three live neighbours
// lives on to the next generation.

// Any live cell with more than three live
// neighbours dies, as if by overpopulation.

// Any dead cell with exactly three live
// neighbours becomes a live cell, as if by
// reproduction.

public class Cell {

    private int posX = 0;
    private int posY = 0;
    private int state = 0; // 1: alive, 0: dead

    // Default constructor for Grid class compatibility
    public Cell() {
        this.posX = 0;
        this.posY = 0;
        this.state = 0;
    }

    // Full constructor with position and state
    public Cell(int x, int y, int liveness) {
        this.posX = x;
        this.posY = y;
        this.state = liveness;
    }

    // Calculate next state based on neighbours (returns new state without modifying)
    public int calculateNextState(Cell[][] grid) {
        int count = 0;
        int mapSize = grid.length;

        // Check all 8 neighbors using coordinate offsets (-1, 0, +1)
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                // Skip the current cell itself
                if (dx == 0 && dy == 0) continue;

                int nx = posX + dx;
                int ny = posY + dy;

                // Check boundaries
                if (nx >= 0 && nx < mapSize && ny >= 0 && ny < mapSize) {
                    if (grid[nx][ny].getState() == 1) {
                        count++;
                    }
                }
            }
        }

        // Apply Game of Life rules
        if (state == 1) { // Current cell is alive
            if (count < 2 || count > 3) {
                return 0; // Dies
            }
            return 1; // Survives
        } else { // Current cell is dead
            if (count == 3) {
                return 1; // Becomes alive
            }
            return 0; // Stays dead
        }
    }

    public int getState() {
        return state;
    }

    public void setState(int mortality) {
        this.state = mortality;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
