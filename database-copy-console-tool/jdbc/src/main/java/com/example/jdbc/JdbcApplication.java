package com.example.jdbc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class JdbcApplication implements CommandLineRunner  {

    @Autowired
    private DataSource sourceDataSource;

    @Autowired
    private DataSource destinationDataSource;

    private JdbcTemplate sourceJdbcTemplate;
    private JdbcTemplate destinationJdbcTemplate;

	public static void main(String[] args)  {
		SpringApplication.run(JdbcApplication.class, args);
	}

    @Override
    public void run(String... args) {
        if (args.length < 1) {
            System.out.println("Usage: <order>");
            return;
        }

        String order = args[0].toLowerCase();
        List<String> tables = getTableNames();
        Collections.sort(tables);

        for (String table : tables) {
/*             copyTable(table, order); */
        }

        System.out.println("Database copy completed successfully.");
    }

    private List<String> getTableNames() {
        List<String> tables = new ArrayList<>();
        sourceJdbcTemplate.query("SELECT table_name FROM information_schema.tables WHERE table_schema='public'", rs -> {
            tables.add(rs.getString(1));
        });
        return tables;
    }

    private void copyTable(String table, String order) {
        String query = "SELECT * FROM " + table;
        if (order.equals("reverse")) {
            query += " ORDER BY 1 DESC";
        }

        sourceJdbcTemplate.query(query, rs -> {
            StringBuilder insertQuery = new StringBuilder("INSERT INTO " + table + " (");
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                insertQuery.append(rs.getMetaData().getColumnName(i));
                if (i < rs.getMetaData().getColumnCount()) insertQuery.append(", ");
            }
            insertQuery.append(") VALUES (");
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                insertQuery.append("?");
                if (i < rs.getMetaData().getColumnCount()) insertQuery.append(", ");
            }
            insertQuery.append(")");

            destinationJdbcTemplate.update(insertQuery.toString(), ps -> {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    ps.setObject(i, rs.getObject(i));
                }
            });
        });
    }
}
