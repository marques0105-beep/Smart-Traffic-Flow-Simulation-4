package util;

import java.io.FileWriter;
import java.io.IOException;

public class CsvExporter {

    public static void export(String file, Metrics metrics) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("servedCars,averageWaitingTime\n");
            writer.write(metrics.getServedCars() + "," +
                    metrics.getAverageWaitingTime());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
