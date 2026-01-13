package dao;

import DatabaseConnection.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生信息管理类：实现添加、查询、显示、计算平均分功能
 */
public class StudentManager {

    // 1. 添加学生信息到数据库
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (name, gender, class, math_score, java_score) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // 填充SQL参数（对应Student属性）
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getGender());
            pstmt.setString(3, student.getClassName());
            pstmt.setDouble(4, student.getMathScore());
            pstmt.setDouble(5, student.getJavaScore());
            // 执行插入，返回受影响行数
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. 根据ID查询学生信息
    public Student queryStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // 封装ResultSet为Student对象
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setClassName(rs.getString("class"));
                student.setMathScore(rs.getDouble("math_score"));
                student.setJavaScore(rs.getDouble("java_score"));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // 未找到对应ID的学生
    }

    // 3. 显示所有学生信息
    public List<Student> queryAllStudents() {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setClassName(rs.getString("class"));
                student.setMathScore(rs.getDouble("math_score"));
                student.setJavaScore(rs.getDouble("java_score"));
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    // 4. 计算学生各科目（高数、Java）的平均分数
    public void calculateAverageScore() {
        String sql = "SELECT AVG(math_score) AS avg_math, AVG(java_score) AS avg_java FROM students";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                double avgMath = rs.getDouble("avg_math");
                double avgJava = rs.getDouble("avg_java");
                System.out.println("高数平均分：" + String.format("%.2f", avgMath));
                System.out.println("Java平均分：" + String.format("%.2f", avgJava));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
//被迫更新。因为我不知道要怎么查看之前更新了的提交的文件所以被迫再写一次