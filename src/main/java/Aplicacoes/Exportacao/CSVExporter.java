package Aplicacoes.Exportacao;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVExporter {

    public <T> void exportarParaCSV(List<T> itens, String filePath, String[] colunas, ItemFormatter<T> formatter) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String coluna : colunas) {
                writer.append(coluna).append(",");
            }
            writer.append("\n");

            for (T item : itens) {
                writer.append(formatter.format(item));
                writer.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FunctionalInterface
    public interface ItemFormatter<T> {
        String format(T item);
    }
}
