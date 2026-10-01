package hospitality;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnector {
        private DatabaseConnector() {
        }

        public static Connection connect() throws SQLException {
                String url = System.getenv().getOrDefault("HOSPITALITY_DB_URL",
                                "jdbc:mysql://localhost:3306/hospitality_db");
                String user = System.getenv().getOrDefault("HOSPITALITY_DB_USER", "root");
                String password = System.getenv().getOrDefault("HOSPITALITY_DB_PASSWORD", "");
                return DriverManager.getConnection(url, user, password);
        }
}
