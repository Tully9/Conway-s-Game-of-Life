import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CountDownLatch;

public class InputWindow {
    private static final int SIZE = 50;
    private int[][] grid = new int[SIZE][SIZE];
    private JFrame frame;
    private CountDownLatch latch = new CountDownLatch(1);
    private boolean gridLoaded = false;

    public InputWindow() {
        createAndShowGUI();
    }

    public int[][] getGrid() {
        return this.grid;
    }

    public int getSize() {
        return SIZE;
    }

    // Blocks until user clicks "Start Game"
    public void waitForStart() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Close the input window
    public void close() {
        if (frame != null) {
            frame.dispose();
        }
    }

    private void createAndShowGUI() {
        frame = new JFrame("Game of Life: Setup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel instructions = new JLabel("<html>Enter coordinates (row,col) for live cells:<br>One per line, e.g. 24,25</html>");
        instructions.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(instructions);
        mainPanel.add(Box.createVerticalStrut(10));

        JTextArea coordsArea = new JTextArea(12, 20);
        coordsArea.setText("24,25\n25,25\n26,25");
        JScrollPane scrollPane = new JScrollPane(coordsArea);
        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(scrollPane);

        JButton loadBtn = new JButton("Load Grid");
        loadBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        loadBtn.addActionListener(e -> {
            grid = new int[SIZE][SIZE];

            try {
                String[] lines = coordsArea.getText().split("\\n");
                int count = 0;
                for (String line : lines) {
                    String[] parts = line.trim().split(",");
                    if (parts.length == 2) {
                        int r = Integer.parseInt(parts[0].trim());
                        int c = Integer.parseInt(parts[1].trim());
                        if (r >= 0 && r < SIZE && c >= 0 && c < SIZE) {
                            grid[r][c] = 1;
                            count++;
                        }
                    }
                }
                gridLoaded = true;
                JOptionPane.showMessageDialog(frame, "Loaded " + count + " live cells!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error parsing input: " + ex.getMessage());
            }
        });

        JButton startBtn = new JButton("Start Game");
        startBtn.setAlignmentX(Component.LEFT_ALIGNMENT);
        startBtn.addActionListener(e -> {
            if (!gridLoaded) {
                JOptionPane.showMessageDialog(frame, "Please load the grid first!");
                return;
            }
            latch.countDown(); // Release the waiting thread
        });

        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(loadBtn);
        mainPanel.add(Box.createVerticalStrut(5));
        mainPanel.add(startBtn);

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
