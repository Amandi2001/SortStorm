
import java.io.*;
import java.util.*;

public class CSVLoader {

    private List<String[]> rows = new ArrayList<>();
    private String[] headers;

    public void loadCSV(File file) throws Exception {
        rows.clear();

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();

        if (line == null) {
            throw new Exception("Empty CSV File");
        }

        headers = line.split(",");

        while ((line = br.readLine()) != null) {
            rows.add(line.split(","));
        }

        br.close();

    }
}
