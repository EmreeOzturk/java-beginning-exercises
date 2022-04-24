//author Mustafa Emre ÖZTÜRK
//since 22.11.2020
import java.util.Scanner;
public class CourseGrade2 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("********** Category Information Entry **********");

        //category1

        System.out.print("Please enter the name of the category 1 : ");
        String cat1=capitalize(scanner.nextLine());
        System.out.print("Please enter how many items of type "+cat1 +" were "
                         + "given : ");
        int quantity = scanner.nextInt();
        while (!validQuantity(quantity)){
            System.out.print("Please enter how many items of type "+cat1 +" "
                             + "were "
                             + "given : ");
            quantity = scanner.nextInt();
        }
        System.out.print("Please enter the percentage weight of "+cat1+" : ");
        int totalWeight=0;
        int weight=scanner.nextInt();
        while (!validWeight(weight,totalWeight)){
            System.out.print("Please enter "
                             + "the percentage weight of "+cat1+" : ");
            weight=scanner.nextInt();
        }
        totalWeight+=weight;

        //category2

        scanner.nextLine();
        System.out.print("Please enter the name of the category 2 : ");
        String cat2=capitalize(scanner.nextLine());
        System.out.print("Please enter how many items of type "+cat2 +" were "
                         + "given : ");
        int quantity2 = scanner.nextInt();
        while (!validQuantity(quantity2)){
            System.out.print("Please enter how many items of type "+cat2 +
                             " were "
                             + "given : ");
            quantity2 = scanner.nextInt();
        }
        System.out.print("Please enter the percentage weight of "+cat2+" : ");
        int weight2=scanner.nextInt();
        while (!validWeight(weight2,totalWeight)){
            System.out.print("Please enter the percentage weight of "+cat2+" "
                             + ": ");
            weight2=scanner.nextInt();
        }
        totalWeight+=weight2;

        //category3

        scanner.nextLine();
        System.out.print("Please enter the name of the category 3 : ");
        String cat3=capitalize(scanner.nextLine());
        System.out.print("Please enter how many items of type "+cat3 +" were "
                         + "given : ");
        int quantity3 = scanner.nextInt();
        while (!validQuantity(quantity3)){
            System.out.print("Please enter how many items of type "+cat3 +
                             " were "
                             + "given : ");
            quantity3 = scanner.nextInt();
        }
        System.out.print("Please enter the percentage weight of "+cat3+" : ");
        int weight3=scanner.nextInt();
        while (!validWeight(weight3,totalWeight)){
            System.out.print("Please enter the percentage weight of "+cat3+" "
                             + ": ");
            weight3=scanner.nextInt();
        }
        totalWeight+=weight3;

        //category4

        scanner.nextLine();
        System.out.print("Please enter the name of the category 4 : ");
        String cat4=capitalize(scanner.nextLine());
        System.out.print("Please enter how many items of type "+cat4 +" were "
                         + "given : ");
        int quantity4 = scanner.nextInt();
        while (!validQuantity(quantity4)){
            System.out.print("Please enter how many items of type "+cat4 +
                             " were "
                             + "given : ");
            quantity4 = scanner.nextInt();
        }
        System.out.print("Please enter the percentage weight of "+cat4+" : ");
        int weight4=scanner.nextInt();
        while (!validWeight(weight4,totalWeight)){
            System.out.print("Please enter the percentage weight of "+cat4+" "
                             + ": ");
            weight4=scanner.nextInt();
        }
        totalWeight+=weight4;


        if (totalWeight==100){
            System.out.println("******** Student Grades Entry ********");

            //category1

            double totalcat1=0;
        for (int i=0;i<quantity;i++){
            System.out.print(cat1+(i+1)+" : ");
            double x= scanner.nextDouble();
            totalcat1+=x;
        }
        double gradecat1=totalcat1/quantity;
        double averagecat1=(gradecat1*weight)/100;

             //category2

            double totalcat2=0;
        for (int i=0;i<quantity2;i++){
            System.out.print(cat2+(i+1)+" : ");
            double x= scanner.nextDouble();
            totalcat2+=x;
        }
        double gradecat2=totalcat2/quantity2;
        double averagecat2=(gradecat2*weight2)/100;

            //category3

            double totalcat3=0;
        for (int i=0;i<quantity3;i++){
            System.out.print(cat3+(i+1)+" : ");
            double x= scanner.nextDouble();
            totalcat3+=x;
        }
        double gradecat3=totalcat3/quantity3;
        double averagecat3=(gradecat3*weight3)/100;

            //category4

            double totalcat4=0;
        for (int i=0;i<quantity4;i++){
            System.out.print(cat4+(i+1)+" : ");
            double x= scanner.nextDouble();
            totalcat4+=x;
        }
        double gradecat4=totalcat4/quantity4;
        double averagecat4=(gradecat4*weight4)/100;

        double grade=averagecat1+averagecat2+averagecat3+averagecat4;

        System.out.println("******** Student Result ********");
            System.out.println(cat1+": "+gradecat1);
            System.out.println(cat2+": "+gradecat2);
            System.out.println(cat3+": "+gradecat3);
            System.out.println(cat4+": "+gradecat4);
            System.out.println("The student has "+status(grade)+" CSE 101 with"
                               + " a score "+grade+", GPA points of "
                               +gpaPoints(grade)+
                               ", and a grade letter of "
                               +gradeLetter(grade)+ ".");
        }
        else  {
            System.out.println("ERROR : The values sum to " +totalWeight+" "
                               + "but should sum to 100");
        }
    }
    public static String capitalize(String x){
       return x.substring(0,1).toUpperCase()
                  +x.substring(1).toLowerCase();
    }
    public static boolean validQuantity(int x){
        return x>0;
        }
    public static boolean validWeight(int x ,int y){
        return x>=0 && x+y<=100;
    }
    public static String gradeLetter(double x){
        if (x<=100 && x>=88) return "AA";
        else if (x<=87 && x>=81) return "BA";
        else if (x<=80 && x>=74) return "BB";
        else if (x<=73 && x>=67) return "CB";
        else if (x<=66 && x>=60) return "CC";
        else if (x<=59 && x>=53) return "DC";
        else if (x<=52 && x>=46) return "DD";
        else if (x<=45 && x>=35) return "FD";
        else return "FF";
    }
    public static double gpaPoints(double x){
        if (x<=100 && x>=88) return 4.0;
        else if (x<=87 && x>=81) return 3.5;
        else if (x<=80 && x>=74) return 3.0;
        else if (x<=73 && x>=67) return 2.5;
        else if (x<=66 && x>=60) return 2.0;
        else if (x<=59 && x>=53) return 1.5;
        else if (x<=52 && x>=46) return 1.0;
        else if (x<=45 && x>=35) return 0.5;
        else return 0.0;
    }
    public static String status(double x){
        if (x<=100 && x>60) return "passed";
        else if (x<=60 && x>40) return "conditionally passed";
        else return "failed";
    }
    }




