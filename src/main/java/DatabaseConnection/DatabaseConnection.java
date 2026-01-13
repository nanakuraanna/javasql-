package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库连接管理类：封装连接获取、资源关闭等操作
 */
public class DatabaseConnection {
    // 数据库连接配置（建议从配置文件读取，这里简化为常量）
    private static final String URL = "jdbc:mysql://localhost:3306/student_management?useSSL=false&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // 静态代码块：加载MySQL驱动（MySQL 8.0+ 可省略，但建议保留）
    static {
        try {
            // 替换为MySQL 5.x的驱动类名
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL驱动加载失败", e);
        }
    }
    /**
     * 获取数据库连接
     * @return Connection对象
     * @throws SQLException 连接异常
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * 关闭资源（ResultSet、PreparedStatement、Connection）
     * @param rs 结果集
     * @param pstmt 预编译语句
     * @param conn 连接对象
     */
    public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 重载：关闭Connection（无ResultSet和PreparedStatement时）
    public static void close(Connection conn) {
        close(null, null, conn);
    }
}