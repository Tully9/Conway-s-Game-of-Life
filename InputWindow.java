import javax.swing.*;
import java.awt.*;

public class InputWindow {
    public static void main(String[] args) {
        // Constants for the grid
        final int SIZE = 50;
        final int ALIVE = 1;
        final int DEAD = 0;

        JFrame frame = new JFrame("Game of Life: Integer Setup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 450);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        mainPanel.add(new JLabel("Enter Alive Cells (row, col):"));
        mainPanel.add(Box.createVerticalStrut(10));

        // Text area for coordinate input
        JTextArea coordsArea = new JTextArea(10, 20);
        coordsArea.setText("24,25\n25,25\n26,25"); // Example: Vertical Blinker
        JScrollPane scrollPane = new JScrollPane(coordsArea);
        mainPanel.add(scrollPane);

        mainPanel.add(Box.createVerticalStrut(15));

        JButton startBtn = new JButton("Generate Integer Grid");
        startBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        startBtn.addActionListener(e -> {
            // 1. Initialize 50x50 int array (defaults to 0)
            int[][] grid = new int[SIZE][SIZE];
            
            String[] lines = coordsArea.getText().split("\\n");
            int aliveCount = 0;

            try {
                for (String line : lines) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        String[] parts = line.split(",");
                        int r = Integer.parseInt(parts[0].trim());
                        int c = Integer.parseInt(parts[1].trim());

                        // 2. Set cell to 1 if within bounds
                        if (r >= 0 && r < SIZE && c >= 0 && c < SIZE) {
                            grid[r][c] = ALIVE;
                            aliveCount++;
                        }
                    }
                }
                
                System.out.println("Integer Grid ready with " + aliveCount + " active cells.");
                JOptionPane.showMessageDialog(frame, "Array created! Cells set to 1: " + aliveCount);
                
                // You can now pass this 'grid' to your simulation logic
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: Use 'row,col' format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        mainPanel.add(startBtn);
        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}