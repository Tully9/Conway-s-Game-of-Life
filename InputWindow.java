import javax.swing.*;
import java.awt.*;

public class InputWindow {
    // 1. Move the grid and constants to class-level
    private static final int SIZE = 50;
    private int[][] grid = new int[SIZE][SIZE];
    private JFrame frame;

    public InputWindow() {
        createAndShowGUI();
    }

    // 2. The Getter Method
    public int[][] getGrid() {
        return this.grid;
    }

    private void createAndShowGUI() {
        frame = new JFrame("Game of Life: Setup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextArea coordsArea = new JTextArea(10, 20);
        coordsArea.setText("24,25\n25,25\n26,25"); 
        mainPanel.add(new JScrollPane(coordsArea));

        JButton startBtn = new JButton("Load Grid");
        startBtn.addActionListener(e -> {
            // Reset grid to 0s before loading new state
            grid = new int[SIZE][SIZE];
            
            try {
                String[] lines = coordsArea.getText().split("\\n");
                for (String line : lines) {
                    String[] parts = line.trim().split(",");
                    if (parts.length == 2) {
                        int r = Integer.parseInt(parts[0].trim());
                        int c = Integer.parseInt(parts[1].trim());
                        if (r >= 0 && r < SIZE && c >= 0 && c < SIZE) {
                            grid[r][c] = 1;
                        }
                    }
                }
                JOptionPane.showMessageDialog(frame, "Grid Loaded Successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error parsing input.");
            }
        });

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(startBtn);
        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Run the GUI
        new InputWindow();
    }
}