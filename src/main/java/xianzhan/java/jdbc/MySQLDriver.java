package xianzhan.java.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * 加载 MySQL 驱动
 *
 * @author xinzhan
 * @date 2021-03-21
 */
public class MySQLDriver {

    private static final String URL = "jdbc:mysql://192.168.137.28:3306/april?useSSL=false&characterEncoding=utf8";

    public static void main(String[] args) throws Exception {

        InputStream resourceAsStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("jdbc.properties");
        Properties p = new Properties();
        p.load(resourceAsStream);

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, p);

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from users");

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            System.out.printf("id: %d%n", id);
            String username = resultSet.getString("username");
            System.out.printf("username: %s%n", username);
            int age = resultSet.getInt("age");
            System.out.printf("age: %d%n", age);
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
