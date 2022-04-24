
import java.util.ArrayList;
/**
 * @author Mustafa Emre Öztürk
 * 14.04.2021
 */
public
class Assignment02_20180808030 {
    public static
    void main(String[] args) {
    }
}

class Department{
    private String ID;
    private String name;
    private Teacher chair;

    public
    Department(String ID, String name) {
        setID(ID);
        this.name = name;
    }

    public
    String getID() {
       return ID;
    }

    public
    void setID(String ID) {
        if(ID.length()==3 || ID.length()==4){
            this.ID = ID;
        }
        else{
            throw new LengthErrorException(ID.length());
        }

    }

    public
    String getName() {
        return name;
    }

    public
    void setName(String name) {
        this.name = name;
    }

    public
    Teacher getChair() {
        return chair;
    }

    public
    void setChair(Teacher chair) {
        if (chair==null){
            this.chair=null;
        }
        else if (chair.getDepartment()!=this){
            throw new DepartmentMismatchException(this,chair);
        }
        else{
            this.chair = chair;
        }

    }

}
class Course{
    private Department department;
    private Teacher teacher;
    private int number;
    private String title;
    private int AKTS;

    public Course(Department department, int number, String title, int AKTS,
           Teacher teacher) {
        this.department=department;
        setNumber(number);
        this.title = title;
        setAKTS(AKTS);
        this.teacher=teacher;
        if (!teacher.getDepartment().equals(department)){
            throw new DepartmentMismatchException(this,teacher);
        }

    }
    public
    int getNumber() {
        return number;
    }

    public
    void setNumber(int number) {

        if ((100<=number && number<=499) || (5000<=number && number<=5999) || (7000<=number && number<=7999)){
            this.number = number;
        }
        else{
            throw new RuntimeException();
        }
    }
    public
    int getAKTS() {
        return AKTS;
    }

    public
    void setAKTS(int AKTS) {
        if (AKTS > 0){
            this.AKTS = AKTS;
        }
        else{
           throw new RuntimeException();
        }
    }

    public
    Department getDepartment() {
        return department;
    }

    public
    void setDepartment(Department department) {
            this.department = department;
    }

    public
    Teacher getTeacher() {
        return teacher;
    }

    public
    void setTeacher(Teacher teacher) {
        if (!teacher.getDepartment().equals(department)){
            throw new DepartmentMismatchException(this,teacher);
        }
        else{
            this.teacher = teacher;
        }

    }
    public String courseCode(){
        return department.getID()+" "+number;
    }

    @Override
    public
    String toString() {
        return department.getID() + " " + number + " - " + title +
               " (" + AKTS + ")";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
abstract class Person{
    private Department department;
    private String name;
    private String email;
    private long ID;

    public
    Person(String name, String email, long ID,Department department) {
        this.name = name;
        setEmail(email);
        this.ID = ID;
        this.department=department;
    }

    public
    Department getDepartment() {
        return department;
    }

    public
    void setDepartment(Department department) {
        this.department = department;
    }

    public
    String getName() {
        return name;
    }

    public
    void setName(String name) {
        this.name = name;
    }

    public
    String getEmail() {
       return email;
    }

    public
    void setEmail(String email) {
        if (email.contains("@") && email.contains(".")){
            this.email = email;
        }
        else{
           throw new RuntimeException();
        }
    }

    public
    long getID() {
        return ID;
    }

    public
    void setID(long ID) {
        this.ID = ID;
    }

    @Override
    public
    String toString() {
        return name + " (" + ID + ")" +
               " - " + email;
    }
}
class Teacher extends Person{
    private int rank;

    public
    Teacher(String name, String email, long ID, Department department,int rank) {
        super(name, email, ID, department);
        if (rank>=1 && rank<=4){
            this.rank=rank;
        }
        else{
            throw new InvalidRankException(rank);
        }
    }
    public
    int getRank() {
        return rank;
    }

    public void promote(){
        if (rank>=1 && rank<4){
            rank++;
        }
        else{
            throw new InvalidRankException(rank);
        }

    }
    public void demote(){
        if (rank>1 && rank<=4 ){
            rank--;
        }
        else{
           throw new InvalidRankException(rank);
        }

    }

    public
    String getTitle() {
        if(rank==1){
            return "Lecturer";
        }
        else if(rank==2){
            return "Assistant Professor";
        }
        else if (rank==3){
            return "Associate Professor";
        }
        else {
            return "Professor";
        }
    }


    @Override
    public
    void setDepartment(Department department) {
        if (getDepartment().getChair()==this){
            getDepartment().setChair(null);
        }
        super.setDepartment(department);
    }

    @Override
    public
    String toString() {
        return getTitle() + " " + super.toString();
    }
}
class Student extends Person{
    private ArrayList <Double> grade ;
    private ArrayList <Course> course ;

    public
    Student(String name, String email, long ID, Department department) {
        super(name, email, ID, department);
        this.grade = new ArrayList<>();
        this.course = new ArrayList<>();
    }

    public int getAKTS(){
        int totalPassed = 0;
        for (int i = 0; i < course.size(); i++) {
            if (courseResult(course.get(i)).equals("Passed")){
                totalPassed+=course.get(i).getAKTS();
            }
        }
        return totalPassed;
    }
    public int getAttemptedAKTS(){
        int total=0;
        for (int i = 0; i < course.size(); i++) {
            total+=course.get(i).getAKTS();
        }
        return total;
    }
    public void addCourse(Course course,double grade){
        if (grade >= 0 && grade <= 100) {
            if (this.course.contains(course)) {
                this.grade.set(this.course.indexOf(course), grade);

            }
            else {
                this.course.add(course);
                this.grade.add(grade);
            }

        }
        else {
            throw new InvalidGradeException(grade);
        }


    }
    public double courseGPAPoints(Course course){
        if (!this.course.contains(course)){
            throw new CourseNotFoundExcepiton(this,course);
        }else {
            double not=grade.get(this.course.indexOf(course));
            if ( not<= 100 && not >= 88) return 4.0;
            else if (not <= 87 && not >= 81) return 3.5;
            else if (not <= 80 && not >= 74) return 3.0;
            else if (not <= 73 && not >= 67) return 2.5;
            else if (not <= 66 && not >= 60) return 2.0;
            else if (not <= 59 && not >= 53) return 1.5;
            else if (not <= 52 && not >= 46) return 1.0;
            else if (not <= 45 && not >= 35) return 0.5;
            else return 0.0;
        }

    }
    public String courseGradeLetter(Course course){
        if (!this.course.contains(course)){
            throw new CourseNotFoundExcepiton(this,course);
        }else{
        double not=grade.get(this.course.indexOf(course));
        if (not <= 100 && not >= 88) return "AA";
        else if (not <= 87 && not >= 81) return "BA";
        else if (not <= 80 && not >= 74) return "BB";
        else if (not <= 73 && not >= 67) return "CB";
        else if (not <= 66 && not >= 60) return "CC";
        else if (not <= 59 && not >= 53) return "DC";
        else if (not <= 52 && not >= 46) return "DD";
        else if (not <= 45 && not >= 35) return "FD";
        else return "FF";}
    }
    public String courseResult(Course course){
        if (!this.course.contains(course)){
            throw new CourseNotFoundExcepiton(this,course);
        }
        else{
            double not=grade.get(this.course.indexOf(course));
            if (not <= 100 && not >= 88) return "Passed";
            else if (not <= 87 && not >= 81) return "Passed";
            else if (not <= 80 && not >= 74) return "Passed";
            else if (not <= 73 && not >= 67) return "Passed";
            else if (not <= 66 && not >= 60) return "Passed";
            else if (not <= 59 && not >= 53) return "Conditionally Passed";
            else if (not <= 52 && not >= 46) return "Conditionally Passed";
            else if (not <= 45 && not >= 35) return "Failed";
            else return "Failed";
        }


    }
    public double getGPA(){
        double total = 0;
        for (int i = 0; i < this.course.size(); i++) {
            total+=courseGPAPoints(this.course.get(i))*(this.course.get(i).getAKTS());
        }
        double totalGPA=total/getAttemptedAKTS();

        return  totalGPA;
    }

    @Override
    public
    String toString() {
       return super.toString()+"-GPA:"+getGPA();
    }

    public
    ArrayList<Double> getGrade() {
        return grade;
    }

    public
    ArrayList<Course> getCourse() {
        return course;
    }

}
class GradStudent extends Student{
    private String thesis;
    public
    GradStudent(String name, String email, long ID, Department department,
                String thesis) {
        super(name, email, ID, department);
        this.thesis=thesis;

    }

    @Override
    public
    double courseGPAPoints(Course course) {
        if (!getCourse().contains(course)){
            throw new CourseNotFoundExcepiton(this,course);
        }else{
            double not=getGrade().get(getCourse().indexOf(course));
            if ( not<= 100 && not >= 90) return 4.0;
            else if (not <= 89 && not >= 85) return 3.5;
            else if (not <= 84 && not >= 80) return 3.0;
                else if (not <= 79 && not >= 75) return 2.5;
                    else if (not <= 74 && not >= 70) return 2.0;
                        else return 1.5;
        }


    }

    @Override
    public
    String courseGradeLetter(Course course) {
        if (!getCourse().contains(course)){
            throw new CourseNotFoundExcepiton(this,course);
        }else{
            double not=getGrade().get(getCourse().indexOf(course));
            if ( not<= 100 && not >= 90) return "AA";
            else if (not <= 89 && not >= 85) return "BA";
            else if (not <= 84 && not >= 80) return "BB";
                else if (not <= 79 && not >= 75) return "CB";
                    else if (not <= 74 && not >= 70) return "CC";
                        else return "DC";
        }

    }

    @Override
    public
    String courseResult(Course course) {
        if (!getCourse().contains(course)){
            throw new CourseNotFoundExcepiton(this,course);
        }else{
            double not=getGrade().get(getCourse().indexOf(course));
            if ( not<= 100 && not >= 90) return "Passed";
            else if (not <= 89 && not >= 85) return "Passed";
            else if (not <= 84 && not >= 80) return "Passed";
                else if (not <= 79 && not >= 75) return "Passed";
                    else if (not <= 74 && not >= 70) return "Passed";
                        else return "Failed";
        }

    }

    public
    String getThesis() {
        return thesis;
    }

    public
    void setThesis(String thesis) {
        this.thesis = thesis;
    }
}

class CourseNotFoundExcepiton extends RuntimeException {

    private Student student;
    private Course course;

    public CourseNotFoundExcepiton(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    @Override
    public String toString() {
        return "CourseNotFoundExcepiton: " + student.getID()+" has not yet taken "+course.courseCode();
    }
}
class DepartmentMismatchException extends RuntimeException {

    private Department department;
    private Teacher person;
    private Course course;

    public DepartmentMismatchException(Course course,Teacher person) {
        this.course = course;
        this.person = person;
        this.department=null;
    }

    public DepartmentMismatchException(Department department, Teacher person) {
        this.department = department;
        this.person = person;
        this.course=null;
    }

    @Override
    public String toString() {
        if (course == null){
            return "DepartmentMismatchException: " +person.getName()+"("+person.getID()+") cannot be chair of "+
                   department.getID()+" because he/she is currently assigned to "+person.getDepartment().getID();
        }else {
            return "DepartmentMismatchException: " +person.getName()+"("+person.getID()+") cannot teach "+
                   course.courseCode()+" because he/she is currently assigned to "+person.getDepartment().getID();
        }
    }
}
class InvalidGradeException extends RuntimeException {

    private double grade;

    public InvalidGradeException(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "InvalidGradeException: " +grade;
    }
}
class InvalidRankException extends RuntimeException {

    private int rank;

    public InvalidRankException(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "InvalidRankException:" +rank;
    }
}

class LengthErrorException extends RuntimeException{
    private int length;

    public
    LengthErrorException(int length) {
        this.length = length;
    }


    @Override
    public String toString() {
        return "LengthErrorException" +
               "department.ID length"+"invalid value attempted" + length +
               " ID must be exist 3 or 4 characters.";
    }
}
