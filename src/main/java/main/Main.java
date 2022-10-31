package main;

import model.User;
import org.postgresql.Driver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import repository.UserRepository;
import repository.jdbc.UserRepositoryJdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {

        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);

        } catch(SQLException e) {
            System.out.println("Failed to load driver class!");
        }

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                Statement statement = connection.createStatement()) {
            

        } catch (SQLException e) {

        }
    }
}
