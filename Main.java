import javax.swing.*;
import java.util.Arrays;

public class Main {
    private static final int SIZE = 50;
    private static final int DELAY_MS = 200; // Delay between generations

    public static void main(String[] args) {
        // Step 1: Show InputWindow and wait for user to set up initial state
        InputWindow inputWindow = new InputWindow();
        inputWindow.waitForStart();

        // Step 2: Get the grid from InputWindow
        int[][] userInput = inputWindow.getGrid();
        inputWindow.close();

        // Step 3: Create Cell grid and initialize with user input
        Cell[][] grid = new Cell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = new Cell(i, j, userInput[i][j]);
            }
        }

        // Step 4: Create the visual Grid display
        Grid display = new Grid(grid);

        // Step 5: Run the game loop
        int generation = 0;
        int[][] previousState = null;

        while (true) {
            generation++;

            // Store current state before updating
            int[][] currentState = captureState(grid);

            // Check if state is same as previous (game over condition)
            if (previousState != null && statesEqual(currentState, previousState)) {
                showGameOver(display, generation - 1);
                break;
            }

            // Calculate next states for all cells (don't apply yet)
            int[][] nextStates = new int[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    nextStates[i][j] = grid[i][j].calculateNextState(grid);
                }
            }

            // Apply all next states simultaneously
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    grid[i][j].setState(nextStates[i][j]);
                }
            }

            // Update the display
            display.updateGrid();

            // Store current state for next comparison
            previousState = currentState;

            // Delay for visibility
            try {
                Thread.sleep(DELAY_MS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    // Capture current grid state as 2D int array
    private static int[][] captureState(Cell[][] grid) {
        int[][] state = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                state[i][j] = grid[i][j].getState();
            }
        }
        return state;
    }

    // Compare two states for equality
    private static boolean statesEqual(int[][] state1, int[][] state2) {
        for (int i = 0; i < SIZE; i++) {
            if (!Arrays.equals(state1[i], state2[i])) {
                return false;
            }
        }
        return true;
    }

    // Show game over dialog with score
    private static void showGameOver(Grid display, int generations) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(
                display,
                "Game Over!\n\nYour pattern survived for " + generations + " generations.",
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE
            );
        });
    }
}
