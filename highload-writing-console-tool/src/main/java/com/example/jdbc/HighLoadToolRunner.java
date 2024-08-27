package com.example.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class HighLoadToolRunner implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public HighLoadToolRunner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Properties loadProperties(String filePath) throws IOException {
        System.out.println("Loading properties from file: " + filePath);
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(filePath)) {
            System.out.println("Properties loaded" + properties);
            properties.load(input);
        }
        return properties;
    }

    private void createTables(int n, String[] columnTypes, String[] tableNames) {
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            StringBuilder sql = new StringBuilder("CREATE TABLE " + tableNames[i] + " (id INT PRIMARY KEY");

            for (int j = 0; j < columnTypes.length; j++) {
                String columnName = "Column_" + j;
                String columnType = columnTypes[random.nextInt(columnTypes.length)];
                sql.append(", ").append(columnName).append(" ").append(columnType);
            }

            sql.append(")");
            jdbcTemplate.execute(sql.toString());
        }
    }

    private void populateTables(int n, int l, String[] tableNames) {
        ExecutorService executor = Executors.newFixedThreadPool(l);

        for (int i = 0; i < n; i++) {
            final int tableIndex = i;
            executor.submit(() -> {
                Random random = new Random();
                int m = random.nextInt(1000) + 100; // Random number of rows

                for (int j = 0; j < m; j++) {
                    StringBuilder sql = new StringBuilder("INSERT INTO " + tableNames[tableIndex] + " (id");

                    for (int k = 0; k < 3; k++) {
                        sql.append(", Column_").append(k);
                    }

                    sql.append(") VALUES (");

                    sql.append(j);
                    for (int k = 0; k < 3; k++) {
                        sql.append(", ").append(getRandomValue(random, k));
                    }

                    sql.append(")");
                    jdbcTemplate.update(sql.toString());
                }
            });
        }

        executor.shutdown();
    }

    private Object getRandomValue(Random random, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return random.nextInt(1000); // INT
            case 1:
                return "'" + getRandomString(random, 10) + "'"; // VARCHAR
            case 2:
                return "'" + LocalDate.now().minusDays(random.nextInt(1000)) + "'"; // DATE
            default:
                return null;
        }
    }

    private String getRandomString(Random random, int length) {
        return random.ints(97, 123)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length < 1) {
            System.out.println("No properties file specified");
            return;
        }

        Properties properties = loadProperties(args[0]);
        System.out.println("Properties loaded: " + properties);

        int n = Integer.parseInt(properties.getProperty("tables.number"));
        int l = Integer.parseInt(properties.getProperty("concurrent.connections"));
        String[] columnTypes = properties.getProperty("column.types").split(",");
        String[] tableNames = properties.getProperty("table.names").split(",");

        createTables(n, columnTypes, tableNames);
        populateTables(n, l, tableNames);
    }
    
    
}
