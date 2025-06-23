package testlib.utils;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.qameta.allure.util.ResultsUtils.bytesToHex;

public class CsvLoader {

    public static List<Map<String,String>> csvRows(String filePath){
        try (InputStream is=CsvLoader.class.getClassLoader().getResourceAsStream(filePath);
             InputStreamReader reader=new InputStreamReader(is, StandardCharsets.UTF_8)){

            CSVReader csvReader=new CSVReaderBuilder(reader)
                    .withCSVParser(new CSVParserBuilder()
                            .withSeparator(';')
                            .withQuoteChar('"')
                            .build())
                    .build();

            List<String[]> allRows=csvReader.readAll();
            if(allRows.isEmpty()) return List.of();

            System.out.println("Raw headers: " + Arrays.toString(allRows.get(0)));
            System.out.println("Hex dump of first header: "
                    + bytesToHex(allRows.get(0)[0].getBytes()));

            return allRows.stream()
                    .skip(1)
                    .map(row -> convertToMap(allRows.get(0), row))
                    .peek(map -> System.out.println("Generated map: " + map))
                    .collect(Collectors.toList());
//
//            String[] headers=allRows.get(0);
//            return allRows.stream()
//                    .skip(1)
//                    .map(row->toFieldMap(headers,row))
//                    .collect(Collectors.toList());
        } catch (IOException | CsvException e){
            e.printStackTrace();
            throw new RuntimeException("Ошибка загрузки .csv файла: "+filePath,e);
        }
    }

//    private static Map<String,String> toFieldMap(String[] headers,String[] row){
//        Map<String,String> map=new LinkedHashMap<>();
//        for(int i=0;i<Math.min(headers.length,row.length);i++){
//            map.put(headers[i].trim(),row[i].trim());
//        }
//
//        return map;
//    }

    private static Map<String,String> convertToMap(String[] headers, String[] row){
        Map<String,String> map=new LinkedHashMap<>();
        for(int i=0;i<Math.min(headers.length, row.length);i++){
            String header=headers[i]
                    .replace("\uFEFF", "")
                    .trim()
                    .replaceAll("\\p{C}", "");
            map.put(header,row[i].trim());
        }
        return map;
    }
}
