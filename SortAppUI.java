import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class SortAppUI extends JFrame {

    private final CSVLoader loader = new CSVLoader();
    private JComboBox<String> columnBox;
    private JTextArea resultArea;

    private Map<String, Long> times = new LinkedHashMap<>();
    private ChartPanel chartPanel;

    public SortAppUI() {

        setTitle("CSV Sorting Performance Visualizer");
        setSize(900, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        getContentPane().setBackground(new Color(245, 248, 255));

        JPanel top = new JPanel();
        top.setBackground(new Color(70, 130, 180));
        top.setBorder(new EmptyBorder(10,10,10,10));

        JButton uploadBtn = new JButton("Upload CSV");
        styleButton(uploadBtn);
        uploadBtn.addActionListener(e -> loadCSV());

        columnBox = new JComboBox<>();
        columnBox.setPreferredSize(new Dimension(200,30));

        JButton sortBtn = new JButton("Run Sorting");
        styleButton(sortBtn);
        sortBtn.addActionListener(e -> runSorting());

        JLabel label = new JLabel("Select Column:");
        label.setForeground(Color.WHITE);

        top.add(uploadBtn);
        top.add(label);
        top.add(columnBox);
        top.add(sortBtn);

        add(top, BorderLayout.NORTH);


        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        resultArea.setBorder(new EmptyBorder(10,10,10,10));

        JScrollPane scroll = new JScrollPane(resultArea);
        scroll.setPreferredSize(new Dimension(300,200));

        chartPanel = new ChartPanel();

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scroll, chartPanel);
        split.setResizeWeight(0.4);

        add(split, BorderLayout.CENTER);

        setVisible(true);
    }

     private void styleButton(JButton btn){
        btn.setBackground(new Color(255, 140, 0));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(5,15,5,15));
    }

    private void loadCSV() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                loader.loadCSV(chooser.getSelectedFile());
                columnBox.removeAllItems();

                for (String h : loader.getHeaders())
                    columnBox.addItem(h);

                resultArea.setText("CSV Loaded Successfully!\nSelect a numeric column.\n");
                times.clear();
                chartPanel.repaint();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }

    private void runSorting() {

        try {
            int index = columnBox.getSelectedIndex();
            Double[] data = loader.getNumericColumn(index);

             times.clear();

            long start;

            start = System.nanoTime();
            SortingAlgorithms.insertionSort(data);
            times.put("Insertion Sort", System.nanoTime() - start);

            start = System.nanoTime();
            SortingAlgorithms.shellSort(data);
            times.put("Shell Sort", System.nanoTime() - start);

            start = System.nanoTime();
            SortingAlgorithms.mergeSort(data);
            times.put("Merge Sort", System.nanoTime() - start);

            start = System.nanoTime();
            SortingAlgorithms.quickSort(data);
            times.put("Quick Sort", System.nanoTime() - start);

            start = System.nanoTime();
            SortingAlgorithms.heapSort(data);
            times.put("Heap Sort", System.nanoTime() - start);


            resultArea.setText("");

            String best = null;
            long bestTime = Long.MAX_VALUE;

            for (String alg : times.keySet()) {
                long t = times.get(alg);
                resultArea.append(String.format("%-15s = %.4f ms\n",
                        alg, t / 1_000_000.0));

                if (t < bestTime) {
                    bestTime = t;
                    best = alg;
                }
            }

            resultArea.append("\nFastest Algorithm: " + best);

            chartPanel.repaint();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    class ChartPanel extends JPanel {

        public ChartPanel(){
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(300,300));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if(times.isEmpty()) return;

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();

            long max = times.values().stream().mapToLong(v -> v).max().getAsLong();

            int x = 60;
            int barWidth = 90;
            int gap = 30;

            int base = height - 60;

            Font font = new Font("Arial", Font.BOLD, 12);
            g2.setFont(font);

            Color[] colors = {
                    new Color(255, 99, 71),
                    new Color(60, 179, 113),
                    new Color(65, 105, 225),
                    new Color(238, 130, 238),
                    new Color(255, 215, 0)
            };

            int i = 0;

            for(String key : times.keySet()){

                long v = times.get(key);

                int barHeight = (int)((double)v / max * (height-120));

                g2.setColor(colors[i % colors.length]);
                g2.fillRoundRect(x, base - barHeight, barWidth, barHeight, 15,15);

                g2.setColor(Color.BLACK);
                g2.drawRoundRect(x, base - barHeight, barWidth, barHeight, 15,15);

                String label = key;

                g2.drawString(label, x, base + 20);

                String timeText = String.format("%.2f ms", v / 1_000_000.0);
                g2.drawString(timeText, x, base - barHeight - 10);

                x += barWidth + gap;
                i++;
            }
        }
    }
}
