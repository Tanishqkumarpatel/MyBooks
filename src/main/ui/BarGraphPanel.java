package ui;

import java.util.Map;

import javax.swing.*;
import java.awt.*;

// represent the bar graph
public class BarGraphPanel extends JPanel {

    private Map<String, Integer> data;

    // MODIFIES: this
    // EFFECTS: initiates the bar graph
    public BarGraphPanel(Map<String, Integer> data) {
        this.data = data;
        this.setPreferredSize(new Dimension(200, 400));
    }

    // MODIFIES: this
    // EFFECTS: paint the % of component in the bar
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBarGraph(g);
    }

    // MODIFIES: this
    // EFFECTS: produce the bar graph
    private void drawBarGraph(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        int barWidth = width / (data.size() * 2);
        int maxHeight = height - 50;
        int x = 50;
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int maxValue = data.values().stream().max(Integer::compareTo).orElse(1);

        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            int barHeight = (int) ((entry.getValue() / (double) maxValue) * maxHeight);
            g2d.setColor(getColor(entry.getKey()));
            g2d.fillRect(x, height - barHeight - 30, barWidth, barHeight);
            g2d.setColor(Color.BLACK);
            g2d.drawRect(x, height - barHeight - 30, barWidth, barHeight);
            g2d.drawString(entry.getKey(), x, height - 10);
            x += barWidth * 2;
        }
    }

    
    // EFFECTS: get the color for given key
    private Color getColor(String key) {
        return "Read".equals(key) ? Color.GREEN : Color.RED;
    }
}
