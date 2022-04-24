import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author Mustafa Emre Öztürk
 * 25.05.2021
 */
public class Assignment04_20180808030 {
    public static void main(String[] args) {
        Semester s1= new Semester(1,2020);
        Semester s2= new Semester(2,2021);
        Semester s3= new Semester(3,2021);
        Semester s4= new Semester(1,2022);
        Semester s5= new Semester(2,2023);
        Semester s6= new Semester(3,2023);

        Department d = new Department("CSE", "Computer Engineering");
        Department d2 = new Department("FİZ", "Fizik Fakultesi");
        Department d3 = new Department("MAT", "Matematik Fakultesi");
        Department d4 = new Department("TDB", "Edebiyat Fakultesi");

        Teacher t = new Teacher("Joseph Ledet", "josephledet@akdeniz.edu.tr", 123L, d, 2);
        Teacher t2 = new Teacher("Deniz Kaya", "denizkaya@akdeniz.edu.tr", 124L, d2, 3);
        Teacher t3 = new Teacher("Mehmet Galip Zorba", "mehmetgalipzorba@akdeniz.edu.tr", 125L, d, 2);
        Teacher t4 = new Teacher("Melih Günay", "melihgunay@akdeniz.edu.tr", 126L, d, 4);
        Teacher t5 = new Teacher("Ayşe Yılmaz Ceylan", "ayseyılmazceylan@akdeniz.edu.tr", 127L, d3, 3);
        Teacher t6 = new Teacher("Taha Yiğit Alkan", "tahayigitalkan@akdeniz.edu.tr", 128L, d, 1);
        Teacher t7 = new Teacher("Taner Danışman", "tanerdanisman@akdeniz.edu.tr", 128L, d, 2);
        Teacher t8 = new Teacher("Türk Dili Hocası", "tdhocası@akdeniz.edu.tr", 129L, d4, 2);


        Course c1 = new Course(d, 101, "Programming 1", 4, t);
        Course c2 = new Course(d, 105, "Introduction Computer Science", 2, t7);
        Course c3 = new Course(d, 102, "Programming Lab", 2, t6);
        Course c4 = new Course(d2, 107, "Physics", 5, t2);
        Course c5 = new Course(d3, 163, "Mathematics", 6, t5);
        Course c6 = new Course(d, 181, "Natural Science", 5, t4);
        Course c7 = new Course(d, 183, "English Writing Skills", 4, t3);
        Course c8 = new Course(d4, 101, "Türk Dili Edebiyatı", 2, t8);

        Student s = new Student("Ali Çolak", "alicolak@akdeniz.edu.tr", 456L, d);

        s.addCourse(c1,s6,60);
        s.addCourse(c2,s6,50);
        s.addCourse(c3,s1,60);
        s.addCourse(c4,s5,70);
        s.addCourse(c5,s3,70);
        s.addCourse(c6,s1,90);
        s.addCourse(c7,s3,30);
        s.addCourse(c3,s2,70);
        s.addCourse(c3,s3,80);
        s.addCourse(c3,s4,90);
        s.addCourse(c8,s6,68);
        System.out.println(s.listGrades(c3));

        System.out.println(s.transcript());

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
        if (rank>=1 && rank<=5){
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
        if (rank==1){
            return "Adjunct Instructor";
        }
        else if(rank==2){
            return "Lecturer";
        }
        else if(rank==3){
            return "Assistant Professor";
        }
        else if (rank==4){
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
    private ArrayList<Double> grades ;

    public ArrayList<Double> getGrades() {
        return grades;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public ArrayList<Semester> getSemesters() {
        return semesters;
    }

    public ArrayList<Double> getOldGrades() {
        return oldGrades;
    }

    public ArrayList<Course> getOldCourses() {
        return oldCourses;
    }

    public ArrayList<Semester> getOldSemesters() {
        return oldSemesters;
    }

    public ArrayList<Course> getMaxCourse() {
        return maxCourse;
    }

    public ArrayList<Double> getMaxGrade() {
        return maxGrade;
    }

    private ArrayList<Course> courses ;
    private ArrayList<Semester> semesters;
    private ArrayList<Double> oldGrades ;
    private ArrayList<Course> oldCourses ;
    private ArrayList<Semester> oldSemesters;
    private ArrayList<Course> maxCourse;
    private ArrayList<Double> maxGrade;


    public
    Student(String name, String email, long ID, Department department) {
        super(name, email, ID, department);
        this.grades = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.semesters = new ArrayList<>();
        this.oldCourses = new ArrayList<>();
        this.oldSemesters = new ArrayList<>();
        this.oldGrades = new ArrayList<>();
        this.maxGrade=new ArrayList<>();
        this.maxCourse=new ArrayList<>();
    }

    public int getAKTS(){
        int totalPassed = 0;
        for (int i = 0; i < courses.size(); i++) {
            if (courseResult(courses.get(i)).equals("Passed")){
                totalPassed+=courses.get(i).getAKTS();
            }
        }
        return totalPassed;
    }
    public int getAttemptedAKTS(){
        int total=0;
        for (int i = 0; i < courses.size(); i++) {
            total+=courses.get(i).getAKTS();
        }
        return total;
    }


    public void addCourse(Course course, Semester semester,double grade){
        if (grade >= 0 && grade <= 100) {
            if (courses.contains(course) && semesters.get(courses.indexOf(course))!=semester ) {

                if (grade>maxGrade.get(maxCourse.indexOf(course))){
                    maxGrade.set(maxCourse.indexOf(course),grade);
                }

                oldCourses.add(course);
                oldGrades.add(grades.get(courses.indexOf(course)));
                oldSemesters.add(semesters.get(courses.indexOf(course)));

                grades.set(courses.indexOf(course),grade);
                semesters.set(courses.indexOf(course),semester);

            }
            else if (courses.contains(course) && semesters.get(courses.indexOf(course))==semester){

                if (grade>maxGrade.get(maxCourse.indexOf(course))){
                    maxGrade.set(maxCourse.indexOf(course),grade);
                }

                oldCourses.add(course);
                oldGrades.add(grades.get(courses.indexOf(course)));
                oldSemesters.add(semesters.get(courses.indexOf(course)));

                grades.set(courses.indexOf(course),grade);
            }

            else {
                courses.add(course);
                grades.add(grade);
                semesters.add(semester);
                maxCourse.add(course);
                maxGrade.add(grade);

            }

        }
        else {
            throw new InvalidGradeException(grade);
        }


    }
    public String listGrades(Semester semester){

        if (!semesters.contains(semester) && !oldSemesters.contains(semester)){
            throw new SemesterNotFoundException(this,semester);
        }else{
            ArrayList<Course> semestersCourses= new ArrayList<>();
            ArrayList<Double> semestersGrades= new ArrayList<>();
            for (int i = 0; i < oldSemesters.size(); i++) {
                if (oldSemesters.get(i)==semester){
                    semestersCourses.add(oldCourses.get(i));
                    semestersGrades.add(oldGrades.get(i));
                }}
            for (int i = 0; i < semesters.size(); i++) {
                if (semesters.get(i)==semester){
                    semestersCourses.add(courses.get(i));
                    semestersGrades.add(grades.get(i));
                }}
            String s ="";
            String s2="";
            for (int i = 0; i < semestersCourses.size(); i++) {
                double not = semestersGrades.get(i);
                if (not <= 100 && not >= 88) s2= "AA";
                else if (not <= 87 && not >= 81) s2= "BA";
                else if (not <= 80 && not >= 74) s2= "BB";
                    else if (not <= 73 && not >= 67) s2= "CB";
                        else if (not <= 66 && not >= 60) s2= "CC";
                            else if (not <= 59 && not >= 53) s2= "DC";
                                else if (not <= 52 && not >= 46) s2= "DD";
                                    else if (not <= 45 && not >= 35) s2= "FD";
                                        else s2= "FF";
                if (i == semestersGrades.size()-1){
                    s += semestersCourses.get(i).courseCode()+ " - "+s2;
                }
                else{
                    s += semestersCourses.get(i).courseCode()+ " - "+s2+"\n";
                }

        }
            return s.toUpperCase();

        }




    }
    public String listGrades(Course course){

        if (!courses.contains(course)&& !oldCourses.contains(course)){
            throw new CourseNotFoundException(this,course);
        }
        else{
            ArrayList<Semester> coursesSemesters = new ArrayList<>();
            ArrayList<Double> coursesGrades = new ArrayList<>();
            for (int i = 0; i < oldCourses.size(); i++) {
                if (oldCourses.get(i)==course){
                    coursesSemesters.add(oldSemesters.get(i));
                    coursesGrades.add(oldGrades.get(i));
                }}
            for (int i = 0; i < courses.size(); i++) {
                if (courses.get(i)==course){
                    coursesSemesters.add(semesters.get(i));
                    coursesGrades.add(grades.get(i));
                }}



            String s ="";
            String s2="";
            for (int i = 0; i <coursesSemesters.size() ; i++) {
                double not = coursesGrades.get(i);
                if (not <= 100 && not >= 88) s2= "AA";
                else if (not <= 87 && not >= 81) s2= "BA";
                else if (not <= 80 && not >= 74) s2= "BB";
                    else if (not <= 73 && not >= 67) s2= "CB";
                        else if (not <= 66 && not >= 60) s2= "CC";
                            else if (not <= 59 && not >= 53) s2= "DC";
                                else if (not <= 52 && not >= 46) s2= "DD";
                                    else if (not <= 45 && not >= 35) s2= "FD";
                                        else s2= "FF";
                if (i == coursesGrades.size()-1){
                    s += coursesSemesters.get(i).getSeason()+" - " + coursesSemesters.get(i).getYear()+
                         " - "+s2;
                }
                else{
                    s += coursesSemesters.get(i).getSeason()+" - " + coursesSemesters.get(i).getYear()+
                         " - "+s2+"\n";
                }

            }

            return s;
        }


    }
    public String transcript(){
        ArrayList<Semester> semesterLinkedHashSet = new ArrayList<>();
        ArrayList<Double> transcriptGPAs = new ArrayList<>();
        for (int i = 0; i < semesters.size(); i++) {
            if (!semesterLinkedHashSet.contains(semesters.get(i))){
                semesterLinkedHashSet.add(semesters.get(i));
            }

        }
        for (int i = 0; i < oldSemesters.size(); i++) {
            if (!semesterLinkedHashSet.contains(oldSemesters.get(i))){
                semesterLinkedHashSet.add(oldSemesters.get(i));
            }

        }
        Collections.sort(semesterLinkedHashSet, new Comparator<Semester>() {
            @Override
            public int compare(Semester o1, Semester o2) {
                if (o1.getYear()<o2.getYear() || (o1.getYear()==o2.getYear() && o1.getSeasonCount()<o2.getSeasonCount())){
                    return -1;
                }
                else
                    return 0;
            }
        });
        ArrayList<Course> semestersCourses= new ArrayList<>();
        ArrayList<Double> semestersGrades= new ArrayList<>();
        ArrayList<Semester> semestersSemesters= new ArrayList<>();
        for (int i = 0; i < semesterLinkedHashSet.size(); i++) {

            for (int j = 0; j < oldSemesters.size(); j++) {
                if (semesterLinkedHashSet.get(i)==oldSemesters.get(j)){
                    semestersCourses.add(oldCourses.get(j));
                    semestersGrades.add(oldGrades.get(j));
                    semestersSemesters.add(oldSemesters.get(j));
                } }
            for (int j = 0; j < semesters.size(); j++) {
                if (semesterLinkedHashSet.get(i)==semesters.get(j)){
                    semestersCourses.add(courses.get(j));
                    semestersGrades.add(grades.get(j));
                    semestersSemesters.add(semesters.get(j));
                }
            }
        }

        double not = 0;
        for (int i = 0; i <semesterLinkedHashSet.size() ; i++) {
            double total = 0;
            double total2 = 0;
            for (int j = 0; j < semestersCourses.size(); j++) {
                if (semestersSemesters.get(j)==semesterLinkedHashSet.get(i)){
                    not=semestersGrades.get(j);
                    if (not <= 100 && not >= 88) not= 4.0;
                    else if (not <= 87 && not >= 81) not= 3.5;
                    else if (not <= 80 && not >= 74) not= 3.0;
                        else if (not <= 73 && not >= 67) not= 2.5;
                            else if (not <= 66 && not >= 60) not= 2.0;
                                else if (not <= 59 && not >= 53) not= 1.5;
                                    else if (not <= 52 && not >= 46) not= 1.0;
                                        else if (not <= 45 && not >= 35) not= 0.5;
                                            else not= 0.0;
                    total += not*semestersCourses.get(j).getAKTS();
                    total2 += semestersCourses.get(j).getAKTS();
                }

            }
            transcriptGPAs.add(total/total2);

        }




        String s = "";
        for (int i = 0; i < semesterLinkedHashSet.size(); i++) {

            s+=semesterLinkedHashSet.get(i).getSeason()+" - "+semesterLinkedHashSet.get(i).getYear()+"\n";
            double grade=0;
            String s3;
            for (int j = 0; j <semestersCourses.size() ; j++) {
                if (semestersSemesters.get(j)==semesterLinkedHashSet.get(i)){
                    grade=semestersGrades.get(j);
                    if (grade <= 100 && grade >= 88) s3= "AA";
                    else if (grade <= 87 && grade >= 81) s3= "BA";
                    else if (grade <= 80 && grade >= 74) s3= "BB";
                        else if (grade <= 73 && grade >= 67) s3= "CB";
                            else if (grade <= 66 && grade >= 60) s3= "CC";
                                else if (grade <= 59 && grade >= 53) s3= "DC";
                                    else if (grade <= 52 && grade >= 46) s3= "DD";
                                        else if (grade <= 45 && grade >= 35) s3= "FD";
                                            else s3= "FF";
                    s+="\t"+semestersCourses.get(j).courseCode()+" - "+s3+"\n";
                }

            }
            s+="GPA: - "+transcriptGPAs.get(i)+"\n"+"\n";
               //listGrades(semesterLinkedHashSet.get(i))+"\n"+"GPA: - "+transcriptGPAs.get(i)+"\n"+"\n";
        }
        return s+"Overall GPA: "+getGPA();
    }


    public double courseGPAPoints(Course course){

        if (!this.courses.contains(course)){
            throw new CourseNotFoundException(this,course);
        }else {
            double not=maxGrade.get(maxCourse.indexOf(course));

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
        if (!this.courses.contains(course)){
            throw new CourseNotFoundException(this,course);
        }else{
            double not=maxGrade.get(maxCourse.indexOf(course));
            double not2=grades.get(this.courses.indexOf(course));
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
        if (!this.courses.contains(course)){
            throw new CourseNotFoundException(this,course);
        }
        else{
            double not=maxGrade.get(maxCourse.indexOf(course));
            double not2=grades.get(this.courses.indexOf(course));
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
        for (int i = 0; i < this.maxCourse.size(); i++) {
            total+=courseGPAPoints(this.maxCourse.get(i))*(this.maxCourse.get(i).getAKTS());
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
        return grades;
    }

    public
    ArrayList<Course> getCourse() {
        return courses;
    }

}
class GradStudent extends Student{
    private ArrayList<Course> courseArrayList;
    private ArrayList<GradStudent> gradStudentArrayList;
    private String thesis;

    public GradStudent(String name, String email, long ID, Department department, String thesis) {
        super(name, email, ID, department);
        this.thesis=thesis;
        courseArrayList=new ArrayList<>();
        gradStudentArrayList=new ArrayList<>();

    }
    public Course getTeachingAssistant() {
        return courseArrayList.get(0);
    }

    public void setTeachingAssistant(Course course) {
        if (!getMaxCourse().contains(course)){
            throw new CourseNotFoundException(this,course);
        }
        double not=getMaxGrade().get(getMaxCourse().indexOf(course));
        if (not <=100 && not >=80){
            courseArrayList.add(course);
            gradStudentArrayList.add(this);
        }else {
            throw new CourseNotFoundException(this,course);
        }

    }

    @Override
    public
    double courseGPAPoints(Course course) {
        if (!getCourses().contains(course)){
            throw new CourseNotFoundException(this,course);
        }else{
            double not=getMaxGrade().get(getMaxCourse().indexOf(course));
            if ( not<= 100 && not >= 90) return 4.0;
            else if (not <= 89 && not >= 85) return 3.5;
            else if (not <= 84 && not >= 80) return 3.0;
                else if (not <= 79 && not >= 75) return 2.5;
                    else if (not <= 74 && not >= 70) return 2.0;
                        else return 0.0;
        }





    }

    @Override
    public
    String courseGradeLetter(Course course) {
        if (!getCourses().contains(course)){
            throw new CourseNotFoundException(this,course);
        }else{
        double not=getMaxGrade().get(getMaxCourse().indexOf(course));
        if ( not<= 100 && not >= 90) return "AA";
        else if (not <= 89 && not >= 85) return "BA";
        else if (not <= 84 && not >= 80) return "BB";
            else if (not <= 79 && not >= 75) return "CB";
                else if (not <= 74 && not >= 70) return "CC";
                    else return "FF";


    }}

    @Override
    public
    String courseResult(Course course) {
        if (!getCourses().contains(course)){
            throw new CourseNotFoundException(this,course);
        }else{
        double not=getMaxGrade().get(getMaxCourse().indexOf(course));
        if ( not<= 100 && not >= 90) return "Passed";
        else if (not <= 89 && not >= 85) return "Passed";
        else if (not <= 84 && not >= 80) return "Passed";
            else if (not <= 79 && not >= 75) return "Passed";
                else if (not <= 74 && not >= 70) return "Passed";
                    else return "Failed";


    }}

    public
    String getThesis() {
        return thesis;
    }

    public
    void setThesis(String thesis) {
        this.thesis = thesis;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}




class Semester{

    private int season;
    private int year;
    public int getSeasonCount() {
        return seasonCount;
    }

    private int seasonCount;

    public Semester(int season , int year){
        if (season==1 || season==2 || season==3){
            this.season=season;
            this.seasonCount = season;
        }
        this.year=year;

    }

    public String getSeason() {
        if (season == 1){
            return "Fall";
        }
        else if (season == 2){
            return "Spring";
        }
        else{
            return "Summer";
        }
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "{"+getSeason()+"} - "+"{"+year+"}";
    }
}
class SemesterNotFoundException extends RuntimeException{
    private Student student;
    private Semester semester;

    public SemesterNotFoundException(Student student, Semester semester) {
        this.student = student;
        this.semester = semester;
    }

    @Override
    public String toString() {
        return  "SemesterNotFoundException: " + "{"+student.getID()+"}" + " has not taken any courses in " + "{"+semester+"}";
    }
}
class CourseNotFoundException extends RuntimeException {

    private Student student;
    private Course course;

    public CourseNotFoundException(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    @Override
    public String toString() {
        return "CourseNotFoundException: " + student.getID()+" has not yet taken "+course.courseCode();
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
