import javax.swing.*;
import java.awt.*;

public class Grid extends JFrame {
    private static final int CELL_SIZE = 20;
    private static final int GRID_WIDTH = 50;
    private static final int GRID_HEIGHT = 50;
    
    private Cell[][] cells; // Assuming this is your 2D array of cells
    private JPanel[][] cellPanels; // UI representation
    
    public Grid() {
        // Initialize the cell matrix (you can pass this as parameter too)
        cells = new Cell[GRID_HEIGHT][GRID_WIDTH];
        initializeCells(); // You'll need to implement this
        
        // Set up the JFrame
        setTitle("Conway's Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        setMinimumSize(new Dimension(
            GRID_WIDTH * CELL_SIZE, 
            GRID_HEIGHT * CELL_SIZE
        ));
        
        // Create and set up the grid
        setupGrid();
        
        pack();
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }
    
    private void initializeCells() {
        // Initialize your cells here
        // This is just a placeholder - you should implement your own logic
        for (int row = 0; row < GRID_HEIGHT; row++) {
            for (int col = 0; col < GRID_WIDTH; col++) {
                cells[row][col] = new Cell(); // Assuming Cell has a default constructor
                // Random initialization or pattern as needed
            }
        }
    }
    
    private void setupGrid() {
        // Use GridLayout for the cell grid
        setLayout(new GridLayout(GRID_HEIGHT, GRID_WIDTH, 1, 1));
        
        cellPanels = new JPanel[GRID_HEIGHT][GRID_WIDTH];
        
        // Create a panel for each cell
        for (int row = 0; row < GRID_HEIGHT; row++) {
            for (int col = 0; col < GRID_WIDTH; col++) {
                JPanel cellPanel = new JPanel();
                cellPanel.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                
                // Set color based on cell state
                updateCellColor(cellPanel, cells[row][col].getState());
                
                add(cellPanel);
                cellPanels[row][col] = cellPanel;
            }
        }
    }
    
    private void updateCellColor(JPanel cellPanel, int state) {
        switch (state) {
            case 1:
                cellPanel.setBackground(Color.WHITE);
                break;
            case 0:
                cellPanel.setBackground(Color.BLACK);
                break;
            default:
                cellPanel.setBackground(Color.LIGHT_GRAY);
        }
    }
    
    // Public method to update the entire grid display
    public void updateGrid() {
        for (int row = 0; row < GRID_HEIGHT; row++) {
            for (int col = 0; col < GRID_WIDTH; col++) {
                updateCellColor(cellPanels[row][col], cells[row][col].getState());
            }
        }
        repaint();
    }
    
    // Method to update a specific cell
    public void updateCell(int row, int col) {
        updateCellColor(cellPanels[row][col], cells[row][col].getState());
    }
    
    // Getters for cell manipulation
    public Cell getCell(int row, int col) {
        return cells[row][col];
    }
    
    public void setCell(int row, int col, Cell cell) {
        cells[row][col] = cell;
        updateCell(row, col);
    }
    
    // Main method to launch the application (can be in separate class)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Grid();
        });
    }
}
