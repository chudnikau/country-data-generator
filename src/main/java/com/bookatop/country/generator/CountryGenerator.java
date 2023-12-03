package com.bookatop.country.generator;

import com.bookatop.country.trans.TransKey;
import com.bookatop.country.world.Country;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CountryGenerator {

    public static final String COUNTRIES_GENERATED_SQL_FILE = "c:/tmp/countries-generated.sql";

    private static final Integer START_PK_ID = 3;

    private static final Integer COUNTRY_NAME_FIELD_SIZE = 100;

    private static final Integer COUNTRY_CODE_FIELD_SIZE = 2;

    private static final Integer COUNTRY_CAPITAL_FIELD_SIZE = 50;

    private static final Integer COUNTRY_REGION_FIELD_SIZE = 10;

    private static final Integer COUNTRY_DIALLING_CODE_FIELD_SIZE = 4;

    private static final Integer COUNTRY_ISO_CODE_FIELD_SIZE = 3;

    private void writeSqlScriptToFile(List<String> lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COUNTRIES_GENERATED_SQL_FILE))) {
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

    public void generate() {

        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("countries.json")) {

            List<String> sqlLines = new ArrayList<>();

            Country[] country = objectMapper.readValue(inputStream, Country[].class);

            int n = START_PK_ID;

            for (Country c : country) {

                if (!(c.getName().equals("Belarus") || c.getName().equals("Russian"))) {

                    String transKey = TransKey.transToKey(c.getName());

                    String r = getString(c, n, transKey);
                    sqlLines.add(r);

                    n++;
                }
            }

            writeSqlScriptToFile(sqlLines);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private String correctStr(String value) {
        return value.replace("'", "''");
    }

    private void validateColumnSize(Integer maxExpectedSize, String value) throws Exception {
        if (value.length() > maxExpectedSize)
            throw new Exception("Incorrect expected max size");
    }

    private String getString(Country c, int n, String transKey) throws Exception {

        validateColumnSize(COUNTRY_NAME_FIELD_SIZE, c.getName());
        validateColumnSize(COUNTRY_CODE_FIELD_SIZE, c.getCode());
        validateColumnSize(COUNTRY_CAPITAL_FIELD_SIZE, c.getCapital());
        validateColumnSize(COUNTRY_REGION_FIELD_SIZE, c.getRegion());
        validateColumnSize(COUNTRY_DIALLING_CODE_FIELD_SIZE, c.getDialling_code());
        validateColumnSize(COUNTRY_ISO_CODE_FIELD_SIZE, c.getIsoCode());

        return "insert into countries select " + n + " id, 'ctr_" + transKey + "' ts_name, '" +
                correctStr(c.getName()) + "' def_name, '" +
                correctStr(c.getCode()) + "' code, '" +
                correctStr(c.getCapital()) + "' capital, '" +
                correctStr(c.getRegion()) + "' region, '" +
                c.getDialling_code() + "' dialling_code, '" +
                c.getIsoCode() + "' iso_code, " +
                "false is_bookable" +
                ";\n";
    }
}
