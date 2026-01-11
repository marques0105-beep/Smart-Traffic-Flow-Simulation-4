package util;

import java.io.FileWriter;
import java.io.IOException;

public class CsvExporter {

    public static void export(Metrics m) {
        try (FileWriter fw = new FileWriter("stats.csv")) {
            fw.write("cars_served,average_wait\n");
            fw.write(m.getCarsServed() + "," + m.getAverageWait());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
