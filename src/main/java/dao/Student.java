// 1. 声明Student类所在的包：dao
package dao;

// 2. 若Student类需要直接使用DatabaseConnection类，添加以下导入语句
// （若仅作为实体类，无需业务逻辑，可省略此import）
import DatabaseConnection.DatabaseConnection;

/**
 * 学生实体类（放在dao包下）
 * 对应数据库students表的结构
 */
public class Student {
    // 成员属性（与students表字段一一对应）
    private Integer id;          // 学生ID
    private String name;         // 姓名
    private String gender;       // 性别
    private String className;    // 班级（避免与Java关键字class重名）
    private Double mathScore;    // 高数成绩
    private Double javaScore;    // Java成绩

    // 无参构造方法
    public Student() {
    }

    // 全参构造方法
    public Student(Integer id, String name, String gender, String className, Double mathScore, Double javaScore) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.className = className;
        this.mathScore = mathScore;
        this.javaScore = javaScore;
    }

    // 所有set/get方法（保持不变）
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Double getMathScore() {
        return mathScore;
    }

    public void setMathScore(Double mathScore) {
        this.mathScore = mathScore;
    }

    public Double getJavaScore() {
        return javaScore;
    }

    public void setJavaScore(Double javaScore) {
        this.javaScore = javaScore;
    }
}
//被迫营业更改，要求新建branch