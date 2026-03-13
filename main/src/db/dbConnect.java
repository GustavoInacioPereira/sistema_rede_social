package db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class dbConnect {
    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            Properties props = loadProperties();
            String url = props.getProperty("dburl");
            try {
                conn = DriverManager.getConnection(url, props);
            } catch (Exception e) {
                throw new dbException(e.getMessage());
            }
        }
        return conn;
    }

    public static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("main\\src\\db\\db.properties")) {
            Properties prop = new Properties();
            prop.load(fs);
            return prop;
        } catch (Exception e) {
            throw new dbException(e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            conn.close();
        } catch (Exception e) {
             throw new dbException(e.getMessage());
        }
    }
}
