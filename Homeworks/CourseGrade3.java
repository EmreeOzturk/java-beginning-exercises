//author Mustafa Emre Öztürk
//since 25.12.2020

import java.util.Scanner;

public
class CourseGrade3 {
    public static
    String gradeLetter(double x) {
        if (x <= 100 && x >= 88) return "AA";
        else if (x <= 87 && x >= 81) return "BA";
        else if (x <= 80 && x >= 74) return "BB";
        else if (x <= 73 && x >= 67) return "CB";
        else if (x <= 66 && x >= 60) return "CC";
        else if (x <= 59 && x >= 53) return "DC";
        else if (x <= 52 && x >= 46) return "DD";
        else if (x <= 45 && x >= 35) return "FD";
        else return "FF";
    }

    public static
    String status(double x) {
        if (x <= 100 && x > 60) return "passed";
        else if (x <= 60 && x > 40) return "conditionally passed";
        else return "failed";
    }

    public static
    double gpaPoints(double x) {
        if (x <= 100 && x >= 88) return 4.0;
        else if (x <= 87 && x >= 81) return 3.5;
        else if (x <= 80 && x >= 74) return 3.0;
        else if (x <= 73 && x >= 67) return 2.5;
        else if (x <= 66 && x >= 60) return 2.0;
        else if (x <= 59 && x >= 53) return 1.5;
        else if (x <= 52 && x >= 46) return 1.0;
        else if (x <= 45 && x >= 35) return 0.5;
        else return 0.0;
    }

    public static
    boolean validQuantity(int[] x) {
        for (int i = 0; i < x.length; i++) {
            if (x[i] < 0){
                System.out.println("ERROR: Invalid quantity entered.");
                System.exit(0);
            }
        }
        return true;
    }


    public static
    boolean validWeight(int[] x) {
        int sum=0;
        for (int i =0; i<x.length ;i++){
            sum+=x[i];
        }
        for (int i = 0; i < x.length; i++) {

            if (x[i]<0){
                System.out.println("ERROR: Invalid weight entered.");
                System.exit(0);
            }
            if (sum<100 || sum>100){
                System.out.println("ERROR: Invalid weight entered.");
                System.exit(0);
            }
        }
        return true;
    }

    public static
    int displayMenu(String[] item, Scanner input) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to our university grade system.");
        for (int i = 0; i < item.length; i++) {
            System.out.println(item[i]);
        }
        int choice= scanner.nextInt();
        return choice;
    }

    public static
    void courseGrade(String[] x, int[] quantity,
                     int[] weight) {
        Scanner scanner = new Scanner(System.in);
        validQuantity(quantity);
        validWeight(weight);
        String[] options = {"Please enter a choice below :"
                , "1 - Enter all grades", "2 - Change a single grade",
                "3 - Display grade information", "0 - to Exit"};
        double[] a1 = new double[quantity[0]];
        double[] a2 = new double[quantity[1]];
        double[] a3 = new double[quantity[2]];
        double[] a4 = new double[quantity[3]];
        while (true) {

            int sec = displayMenu(options,scanner);
            if (sec == 0) {
                System.out.println("Thank you for using our system.\nHave a "
                                   + "nice day");
                break;
            } else if (sec == 1) {
                for (int i = 0; i < quantity[0]; i++) {
                    System.out.print("Please enter grade for "
                                     +capitalize(x[0]) + " " + (i + 1) + " >>");
                    a1[i] = scanner.nextDouble();

                }
                for (int i = 0; i < quantity[1]; i++) {
                    System.out.print("Please enter grade for "
                                     + capitalize(x[1]) +
                                     " " + (i + 1) + " >>");
                    a2[i] = scanner.nextDouble();
                }
                for (int i = 0; i < quantity[2]; i++) {
                    System.out.print("Please enter grade for "
                                     + capitalize(x[2]) + " " + (i + 1) + " "
                                     + ">>");
                    a3[i] = scanner.nextDouble();
                }
                for (int i = 0; i < quantity[3]; i++) {
                    System.out.print("Please enter grade for "
                                     +capitalize(x[3]) + " " + (i + 1) + " >>");
                    a4[i] = scanner.nextDouble();
                }
            } else if (sec == 2) {
                System.out.println("Please enter the category ");
                for (int i = 0; i < x.length; i++) {
                    System.out.println((i + 1) + " - " + capitalize(x[i]));
                }
                System.out.println("0 - to Exit");
                int sec2 = scanner.nextInt();
                if (sec2 == 0) {
                    continue;
                }
                System.out.print("Please enter which "
                                 + capitalize(x[sec2 - 1]) +
                                 " you would" + " like to chance"
                                 + " (1 - " + quantity[sec2 - 1] +
                                 ") >>");
                int sec3 = scanner.nextInt();
                if (sec3 > quantity[sec2 - 1]) {
                    System.out.println("Invalid choice");
                    continue;
                }
                if (sec2 == 1) {
                    System.out.println("The current grade for "
                                       +capitalize(x[sec2 - 1]) + " " + sec3 +
                                       " is " + a1[sec3 - 1]);
                    System.out.print("Please enter the new grade value >> ");
                    a1[sec3 - 1] = scanner.nextInt();
                } else if (sec2 == 2) {
                    System.out.println("The current grade for "
                                       + capitalize(x[sec2 - 1]) + " " + sec3 +
                                       " is " + a2[sec3 - 1]);
                    System.out.print("Please enter the new grade value >> ");
                    a2[sec3 - 1] = scanner.nextInt();
                } else if (sec2 == 3) {
                    System.out.println("The current grade for "
                                       + capitalize(x[sec2 - 1]) + " " + sec3 +
                                       " is " + a3[sec3 - 1]);
                    System.out.print("Please enter the new grade value >> ");
                    a3[sec3 - 1] = scanner.nextInt();
                } else if (sec2 == 4) {
                    System.out.println("The current grade for "
                                       + capitalize(x[sec2 - 1]) + " " + sec3 +
                                       " is " + a4[sec3 - 1]);
                    System.out.print("Please enter the new grade value >> ");
                    a4[sec3 - 1] = scanner.nextInt();
                } else {
                    System.out.println("Error");
                }


            } else if (sec == 3) {
                System.out.println("Category information : ");
                double a1total = 0;
                double a2total = 0;
                double a3total = 0;
                double a4total = 0;
                for (int i = 0; i < a1.length; i++) {
                    a1total += a1[i];

                }
                for (int i = 0; i < a2.length; i++) {
                    a2total += a2[i];

                }
                for (int i = 0; i < a3.length; i++) {
                    a3total += a3[i];

                }
                for (int i = 0; i < a4.length; i++) {
                    a4total += a4[i];

                }

                double gradea1 = (a1total / quantity[0]);
                double gradea2 = (a2total / quantity[1]);
                double gradea3 = (a3total / quantity[2]);
                double gradea4 = (a4total / quantity[3]);
                double averagea1 = (gradea1 * weight[0]) / 100;
                double averagea2 = (gradea2 * weight[1]) / 100;
                double averagea3 = (gradea3 * weight[2]) / 100;
                double averagea4 = (gradea4 * weight[3]) / 100;
                double totalaverage = (averagea1 + averagea2 + averagea3
                                       + averagea4);
                System.out.println(capitalize(x[0]) + " - " + averagea1);
                System.out.println(capitalize(x[1]) + " - " + averagea2);
                System.out.println(capitalize(x[2]) + " - " + averagea3);
                System.out.println(capitalize(x[3]) + " - " + averagea4);
                System.out.println();
                System.out.println("Overall Grade - " + totalaverage);
                System.out.println("Grade Letter - " +
                                   gradeLetter(totalaverage));
                System.out.println("GPA Points - " + gpaPoints(totalaverage));
                System.out.println("Status - " + status(totalaverage));
            }
            else {
                System.out.println("Invalid choice");
            }
        }


    }

    public static
    String capitalize(String x) {
        return
                x.substring(0, 1).toUpperCase()
                + x.substring(1).toLowerCase();
    }

    public static
    void main(String[] args) {
        String[] category = {"QUIZ", "hoMEwork", "MidTeRm Exam", "FINAL Exam"};
        int[] quantity = {4, 3, 1, 1};
        int[] weight = {10, 20, 30, 40};
        courseGrade(category, quantity, weight);
    }
}
