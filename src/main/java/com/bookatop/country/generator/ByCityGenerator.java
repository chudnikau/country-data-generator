package com.bookatop.country.generator;

import com.bookatop.country.trans.TransKey;
import com.bookatop.country.trans.TransValue;
import com.bookatop.country.by.ByCity;
import com.bookatop.country.by.ByCountry;
import com.bookatop.country.by.ByRegion;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ByCityGenerator {

    public static final Integer START_PK_ID = 9;

    public static final Integer CITY_FK_ID = 1;

    public static final String CITIES_GENERATED_SQL_FILE = "c:/tmp/cities-generated.sql";

    public static final String CITIES_GENERATED_IMPORT_JSON_FILE = "c:/tmp/cities-generated-import.json";

    public void generate() {
        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("by/by-cities.json")) {

            ByCountry belCountry =
                    objectMapper.readValue(
                            inputStream, ByCountry.class);

            int n = START_PK_ID;

            Map<String, String> properties = new HashMap<>();
            List<String> sqlLines = new ArrayList<>();

            for (ByRegion region : belCountry.getRegions()) {
                for (ByCity city : region.getCities()) {
                    String transKey = TransKey.transToKey(city.getName());
                    String transValue = TransValue.transToValue(transKey);

                    String tmpKey = "cty_" + transKey;
                    if (!(tmpKey.equals("cty_minsk") ||
                            tmpKey.equals("cty_mogilev") ||
                            tmpKey.equals("cty_vitebsk") ||
                            tmpKey.equals("cty_brest") ||
                            tmpKey.equals("cty_gomel") ||
                            tmpKey.equals("cty_grodno"))) {
                        String resWord = "insert into cities select " + n + ", " + CITY_FK_ID + ", 'cty_" + transKey + "';" + "\n";

                        sqlLines.add(resWord);
                        n++;
                    }

                    properties.put("cty_" + transKey, transValue);
                }
            }

            writeSqlScriptToFile(sqlLines);
            writeJsonImportToFile(properties);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void writeSqlScriptToFile(List<String> lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CITIES_GENERATED_SQL_FILE))) {
            lines.forEach(s -> {
                try {
                    writer.append(s);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            writer.flush();
        }
    }

    private void writeJsonImportToFile(Map<String, String> properties) throws IOException {
        ObjectMapper objectMapperOut = new ObjectMapper();
        String jsonImport = objectMapperOut.writeValueAsString(properties);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CITIES_GENERATED_IMPORT_JSON_FILE))) {
            writer.write(jsonImport);
            writer.flush();
        }
    }

}
