import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class SortingAlgorithms {

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

}
