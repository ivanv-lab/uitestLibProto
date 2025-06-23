package testlib.utils;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CsvLoader {

    public static List<Map<String,String>> csvRows(String filePath){
        try (InputStream is=CsvLoader.class.getClassLoader().getResourceAsStream(filePath);
             CSVReader reader=new CSVReader(new InputStreamReader(is))){

            List<String[]> allRows=reader.readAll();
            if(allRows.isEmpty()) return List.of();

            String[] headers=allRows.get(0);
            return allRows.stream()
                    .skip(1)
                    .map(row->toFieldMap(headers,row))
                    .collect(Collectors.toList());
        } catch (IOException | CsvException e){
            e.printStackTrace();
            throw new RuntimeException("Ошибка загрузки .csv файла: "+filePath,e);
        }
    }

    private static Map<String,String> toFieldMap(String[] headers,String[] row){
        return IntStream.range(0,Math.min(headers.length, row.length))
                .boxed()
                .collect(Collectors.toMap(
                        i->headers[i],
                        i->row[i],
                        (a,b)->b
                ));
    }
}
