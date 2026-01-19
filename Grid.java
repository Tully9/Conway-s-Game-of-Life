import javax.swing.*;
import java.awt.*;

public class Grid extends JFrame {
    private static final int CELL_SIZE = 20;
    private int gridWidth;
    private int gridHeight;

    private Cell[][] cells;
    private JPanel[][] cellPanels;

    // Constructor that accepts a Cell[][] from the game loop
    public Grid(Cell[][] cells) {
        this.cells = cells;
        this.gridHeight = cells.length;
        this.gridWidth = cells[0].length;

        setTitle("Conway's Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        setMinimumSize(new Dimension(
            gridWidth * CELL_SIZE,
            gridHeight * CELL_SIZE
        ));

        setupGrid();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setupGrid() {
        setLayout(new GridLayout(gridHeight, gridWidth, 1, 1));

        cellPanels = new JPanel[gridHeight][gridWidth];

        for (int row = 0; row < gridHeight; row++) {
            for (int col = 0; col < gridWidth; col++) {
                JPanel cellPanel = new JPanel();
                cellPanel.setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                updateCellColor(cellPanel, cells[row][col].getState());
                add(cellPanel);
                cellPanels[row][col] = cellPanel;
            }
        }
    }

    private void updateCellColor(JPanel cellPanel, int state) {
        if (state == 1) {
            cellPanel.setBackground(Color.WHITE);
        } else {
            cellPanel.setBackground(Color.BLACK);
        }
    }

    // Update the display to reflect current cell states
    public void updateGrid() {
        for (int row = 0; row < gridHeight; row++) {
            for (int col = 0; col < gridWidth; col++) {
                updateCellColor(cellPanels[row][col], cells[row][col].getState());
            }
        }
        repaint();
    }

    // Get the cells array
    public Cell[][] getCells() {
        return cells;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public int getGridHeight() {
        return gridHeight;
    }
}
