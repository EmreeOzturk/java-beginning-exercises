/**
 * @author Mustafa Emre Öztürk
 * 25.03.2021
 */
public
class Assignment01_20180808030 {
    public static
    void main(String[] args) {
        Course course=new Course("CSE",101,"Computer Programming 1",6);
        Student student=new Student("Can DO","cando@akdeniz.edu.tr",123L,
                "CSE",1);
        student.passCourse(course);
        course.setNumber(course.getNumber()+10);
        System.out.println(student);
        System.out.println(course);
        course=new Course("CSE",102,"Computer Programming 2",4);
        student.passCourse(course);
        course.setNumber(course.getNumber());
        System.out.println(course);
        System.out.println(student);


}
static
class Course{
    private String department;
    private int number;
    private String title;
    private int AKTS;

    public
    Course(String department, int number, String title, int AKTS) {
        if (department.length()==3 || department.length()==4){
            this.department = department;
        }
        else{
            throw new RuntimeException();
        }
        if ((100<number && number<499) || (5000<number && number<5999) || (7000<number && number<7999)){
            this.number = number;
        }
        else{
            throw new RuntimeException();
        }
        this.title = title;
        if (AKTS>0){
            this.AKTS = AKTS;
        }
        else{
            throw new RuntimeException();
        }

    }

    public
    String getDepartment() {
        if (department.length()==3 || department.length()==4){
            return department;
        }
        else
            throw new RuntimeException();

    }

    public
    void setDepartment(String department) {
        if (department.length()==3 || department.length()==4){
            this.department = department;
        }
        else{
            throw new RuntimeException();
        }
    }

    public
    int getNumber() {
        if ((100<number && number<499) || (5000<number && number<5999) || (7000<number && number<7999)){
            return number;
        }
        else{
            throw new RuntimeException();
        }
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
    String getTitle() {
        return title;
    }

    public
    void setTitle(String title) {
        this.title = title;
    }

    public
    int getAKTS() {
        if (AKTS > 0 ){
            return AKTS;
        }
        else{
            throw new RuntimeException();
        }

    }

    public
    void setAKTS(int AKTS) {
        if (AKTS>0){
            this.AKTS = AKTS;
        }
        else{
            throw new RuntimeException();
        }
    }
    public String courseCode(){
        return department+" "+number;
    }

    @Override
    public
    String toString() {
        return department+" " + number +" - "+ title +" "+ "("+AKTS+")" ;
    }
}
static class Person{
    private String name;
    private String email;
    private long ID;
    private String department;

    public
    Person(String name, String email, long ID, String department) {
        this.name = name;
        if (email.contains("@") && email.contains(".")){
            this.email = email;
        }
        else{
            throw new RuntimeException();
        }

        this.ID = ID;
        if (department.length()==3 || department.length()==4){
            this.department = department;
        }
        else{
            throw new RuntimeException();
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
    String getEmail() {
        if (email.contains("@")&& email.contains(".")){
            return email;
        }
        else{
            throw new RuntimeException();
        }
    }

    public
    void setEmail(String email) {
        if (email.contains("@")&& email.contains(".")){
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

    public
    String getDepartment() {
        if (department.length()==3 ||department.length()==4){
            return department;
        }
        else{
            throw new RuntimeException();
        }

    }

    public
    void setDepartment(String department) {
        if (department.length()==3 ||department.length()==4){
            this.department = department;
        }
        else{
            throw new RuntimeException();
        }

        }
    @Override
    public
    String toString() {
        return getName()+" ("+getID()+") - "+getEmail();
    }
    }


static class Teacher extends Person{
    private int rank;

    public Teacher(String name, String email, long ID, String department,
                   int rank) {
        super(name, email, ID, department);
        if (0<rank && rank<=3){
            this.rank=rank;
        }
        else
            throw new RuntimeException();


    }

    public
    int getRank() {
        return rank;

    }

    public
    void setRank(int rank) {
        if (0<rank && rank<=3){
            this.rank = rank;
        }
        else{
            throw new RuntimeException();
        }

    }
    public
    String getTitle() {
        if(rank==1){
            return "Assistant Professor";
        }
        else if(rank==2){
            return "Associate Professor";
        }
        else
            return "Professor";
    }
    public void promote(){
        if (rank!=3){
            rank+=1;
        }
        else{
            throw new RuntimeException();
        }

    }
    public void demote(){
        if (rank!=0){
            rank-=1;
        }
        else{
            throw new RuntimeException();
        }

    }

    @Override
    public
    String toString() {
        return getTitle()+super.toString();
    }
}
static class Student extends Person{
    private int AKTS;
    public
    Student(String name, String email, long ID, String department,
            int AKTS) {
        super(name, email, ID, department);
        if (AKTS>0){
            this.AKTS=AKTS;
        }
        else{
            throw new RuntimeException();
        }

    }

    public
    int getAKTS() {
        if (AKTS > 0){
            return AKTS;
        }
        else{
            throw new RuntimeException();
        }


    }

    public
    void setAKTS(int AKTS) {
        if (AKTS > 0 ){
            this.AKTS = AKTS;
        }
        else{
            throw new RuntimeException();
        }

    }
    public void passCourse(Course course){
        this.AKTS+=course.getAKTS();
    }

    @Override
    public
    String toString() {
        return super.toString();
    }
}
static class GradStudent extends Student{
    private String thesis;

    public
    GradStudent(String name, String email, long ID, String department,
                int AKTS, String thesis) {
        super(name, email, ID, department, AKTS);
        this.thesis=thesis;
    }

    public
    String getThesis() {
        return thesis;
    }

    public
    void setThesis(String thesis) {
        this.thesis = thesis;
    }

    @Override
    public
    String toString() {
        return super.toString();
    }
}}


    Teacher t = new Teacher("Joseph Ledet", "josephledet@akdeniz.edu.tr", 123L, d, 2);
        System.out.println(t);

                Course c1 = new Course(d, 101, "Programming 1", 6, t);
                Course c2 = new Course(d, 102, "Programming 2", 4, t);
                System.out.println(c1.courseCode()+ " - "+c1.getTitle());
                System.out.println(c2);


                Student s = new Student("Emre Ozturk", "emreozturk@gmail.com", 456L, d);

                s.addCourse(c1, 78);
                s.addCourse(c2, 25);

                System.out.println(s.getAKTS());
                System.out.println(s.getAttemptedAKTS());
                System.out.println(s.getGPA());

                System.out.println(s);



