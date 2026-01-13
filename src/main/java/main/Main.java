package main;

import dao.Student;
import dao.StudentManager;
import java.util.List;
import java.util.Scanner;

/**
 * 主类：命令行交互入口，实现学生管理系统的用户操作
 */
public class Main {
    private static final StudentManager studentManager = new StudentManager();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showMenu(); // 显示操作菜单
        while (true) {
            System.out.print("请输入操作编号：");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 消费换行符，避免后续输入异常

            switch (choice) {
                case 1:
                    addStudentAction(); // 1. 添加学生
                    break;
                case 2:
                    queryStudentByIdAction(); // 2. 根据ID查询
                    break;
                case 3:
                    showAllStudentsAction(); // 3. 显示所有学生
                    break;
                case 4:
                    calculateAverageAction(); // 4. 计算科目平均分
                    break;
                case 5:
                    System.out.println("系统已退出");
                    scanner.close();
                    return;
                default:
                    System.out.println("无效的操作编号，请重新输入！");
            }
            System.out.println("------------------------------");
        }
    }

    // 显示操作菜单
    private static void showMenu() {
        System.out.println("===== 学生管理系统 =====");
        System.out.println("1. 添加学生信息");
        System.out.println("2. 根据ID查询学生");
        System.out.println("3. 显示所有学生信息");
        System.out.println("4. 计算科目平均分");
        System.out.println("5. 退出系统");
        System.out.println("=======================");
    }

    // 1. 添加学生的交互逻辑
    private static void addStudentAction() {
        System.out.print("请输入学生姓名：");
        String name = scanner.nextLine();
        System.out.print("请输入学生性别（男/女/其他）：");
        String gender = scanner.nextLine();
        System.out.print("请输入学生班级：");
        String className = scanner.nextLine();
        System.out.print("请输入高数成绩：");
        double mathScore = scanner.nextDouble();
        System.out.print("请输入Java成绩：");
        double javaScore = scanner.nextDouble();
        scanner.nextLine(); // 消费换行符

        // 封装为Student对象
        Student student = new Student(null, name, gender, className, mathScore, javaScore);
        boolean success = studentManager.addStudent(student);
        System.out.println(success ? "添加学生成功！" : "添加学生失败！");
    }

    // 2. 根据ID查询的交互逻辑
    private static void queryStudentByIdAction() {
        System.out.print("请输入要查询的学生ID：");
        int id = scanner.nextInt();
        scanner.nextLine();

        Student student = studentManager.queryStudentById(id);
        if (student != null) {
            System.out.println("查询结果：");
            System.out.println("ID：" + student.getId());
            System.out.println("姓名：" + student.getName());
            System.out.println("性别：" + student.getGender());
            System.out.println("班级：" + student.getClassName());
            System.out.println("高数成绩：" + student.getMathScore());
            System.out.println("Java成绩：" + student.getJavaScore());
        } else {
            System.out.println("未找到ID为" + id + "的学生！");
        }
    }

    // 3. 显示所有学生的交互逻辑
    private static void showAllStudentsAction() {
        List<Student> studentList = studentManager.queryAllStudents();
        if (studentList.isEmpty()) {
            System.out.println("当前无学生信息！");
            return;
        }
        System.out.println("所有学生信息：");
        for (Student s : studentList) {
            System.out.println(s.getId() + "\t" + s.getName() + "\t" + s.getGender() + "\t" + s.getClassName()
                    + "\t" + s.getMathScore() + "\t" + s.getJavaScore());
        }
    }

    // 4. 计算平均分的交互逻辑
    private static void calculateAverageAction() {
        System.out.println("科目平均分统计：");
        studentManager.calculateAverageScore();
    }
}