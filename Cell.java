// Cell

// Any live cell with fewer than two live
// neighbours dies, as if by underpopulation.​

// Any live cell with two or three live neighbours
// lives on to the next generation.​

// Any live cell with more than three live
// neighbours dies, as if by overpopulation.​

// Any dead cell with exactly three live
// neighbours becomes a live cell, as if by
// reproduction.

public class Cell{

    static int mapSize = 0;

    private int posX=0;
    private int posY=0;
    private int state = 0; // 1: alive, 0: dead
    
    public Cell() {
        this.posY = 0;
        this.posX = 0;
        this.state = 0;
        this.mapSize = 0;
    }

    Cell(int x, int y, int liveness, int size){
        this.posX=x;
        this.posY=y;
        this.state=liveness;
        this.mapSize=size;
    }

    public void checkNeighbours(Cell[][] grid) {
        int count = 0;

        // Check all 8 neighbors using coordinate offsets (-1, 0, +1)
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                // Skip the current cell itself
                if (dx == 0 && dy == 0) continue;

                int nx = posX + dx;
                int ny = posY + dy;

                // Single unified check for boundaries
                if (nx >= 0 && nx < mapSize && ny >= 0 && ny < mapSize) {
                    // If neighbour is alive (state 1), increment count
                    if (grid[nx][ny].getState() == 1) {
                        count++;
                    }
                }
            }
        }

        // Apply Game of Life rules based on the count
        if (state == 1) { // Current cell is alive
            if (count < 2 || count > 3) {
                state = 0; // Dies by underpopulation or overpopulation
            }
        } else { // Current cell is dead
            if (count == 3) {
                state = 1; // Becomes alive by reproduction
            }
        }
    }


    public int getState(){
        return state;
    }

    public void setState(int mortality){
        this.state=mortality;
    }
}
