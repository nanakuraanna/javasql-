package DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn != null) {
                System.out.println("数据库连接成功！");
            }
        } catch (SQLException e) {
            System.out.println("数据库连接失败：" + e.getMessage());
            e.printStackTrace(); // 打印完整异常信息
        }
    }
}
