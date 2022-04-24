//author Mustafa Emre Öztürk
//since 16.01.2021

import java.io.*;

public
class CourseGrade4 {
    public static
    int countCategory(String filename) {
        int counter = 0;

        try (BufferedReader reader = new BufferedReader
                (new FileReader(filename))) {
            while (reader.readLine() != null) {
                counter++;
            }
        } catch (Exception ignored) {
        }
        return counter;
    }

    public static
    void getCategory(String[] category, int[] quantity, int[] weight,
                     String filename) {
        String satir;
        String[] array;
        try (BufferedReader reader = new BufferedReader
                (new FileReader(filename))) {
            for (int j = 0; j < countCategory(filename); j++) {
                satir = reader.readLine();
                array = satir.split(" ");
                category[j] = array[0];
                quantity[j] += Integer.parseInt(array[1]);
                weight[j] += Integer.parseInt(array[2]);
            }
        } catch (Exception ignored) {
        }
    }

    public static
    void getName(String[] mstudent, String filename) {
        String satir;
        String[] array;
        try (BufferedReader reader = new BufferedReader
                (new FileReader(filename))) {
            for (int i = 0; i < countCategory(filename); i++) {
                satir = reader.readLine();
                array = satir.split(" ");
                mstudent[i] = array[0];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static
    void writeGrades(String[] student, double[] grade, String basefilename) {
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(basefilename +
                                                       "_StudentGrades.txt"))) {
            for (int i = 0; i < student.length; i++) {
                if (grade[i] == -1) {
                    try (BufferedWriter writer1 =
                                 new BufferedWriter(new FileWriter
                                         (basefilename + "_log"
                                          + ".txt"))) {
                        writer1.write("ERROR: Student ");
                        writer1.write(student[i]);
                        writer1.write(" - cannot calculate due to invalid "
                                      + "grade entered");
                    }
                    continue;
                }
                writer.write(student[i]);
                writer.write(" ");
                writer.write(String.valueOf(grade[i]));
                writer.write(" ");
                if (grade[i] <= 100 && grade[i] >= 88) {
                    writer.write("AA 4.0 ");
                } else if (grade[i] <= 88 && grade[i] >= 81) {
                    writer.write("BA 3.5 ");
                } else if (grade[i] <= 81 && grade[i] >= 74) {
                    writer.write("BB 3.0 ");
                } else if (grade[i] <= 74 && grade[i] >= 67) {
                    writer.write("CB 2.5 ");
                } else if (grade[i] <= 67 && grade[i] >= 60) {
                    writer.write("CC 2.0 ");
                } else if (grade[i] <= 60 && grade[i] >= 53) {
                    writer.write("DC 1.5 ");
                } else if (grade[i] <= 53 && grade[i] >= 46) {
                    writer.write("DD 1.0 ");
                } else if (grade[i] <= 46 && grade[i] >= 35) {
                    writer.write("FD 0.5 ");
                } else {
                    writer.write("FF 0.0 ");
                }
                if (grade[i] <= 100 && grade[i] > 60) {
                    writer.write("passed");
                } else if (grade[i] <= 60 && grade[i] > 40) {
                    writer.write("conditionally passed");
                } else {
                    writer.write("failed");
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static
    void hesapla(String score, String details, int[] quantity,
                 double[][] grades, double[][] notlar, int[] weight,
                 double[] ortalamanotlar) {
        String satir;
        String[] array;
        int toplam = 0;
        for (int value : quantity) {
            toplam += value;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(score))){
            for (int i = 0; i < countCategory(score); i++) {
                satir = reader.readLine();
                array = satir.split(" ");
                if (array.length - 1 >= 0)
                    System.arraycopy
                    (array, 1, array, 0, array.length - 1);
                for (int j = 0; j < toplam; j++) {
                    grades[i][j] = Double.parseDouble(array[j]);
                }
            }
        } catch (Exception ignored) {
        }
        for (int i = 0; i < countCategory(score); i++) {
            int counter = 0;
            for (int j = 0; j < countCategory(details); j++) {
                for (int k = 0; k < quantity[j]; k++) {
                    notlar[i][j] += grades[i][k + counter];
                }
                counter += quantity[j];
            }
        }
        for (int i = 0; i < notlar.length; i++) {
            for (int j = 0; j < notlar[0].length; j++) {
                notlar[i][j] = (notlar[i][j] / quantity[j] * weight[j] / 100);
            }
        }
        for (int i = 0; i < countCategory(score); i++) {
            for (int j = 0; j < countCategory(details); j++) {
                ortalamanotlar[i] += notlar[i][j];
            }
        }
        for (int i = 0; i < grades.length; i++) {
            for (int j = 0; j < grades[0].length; j++) {
                if (grades[i][j] < 0) {
                    ortalamanotlar[i] = -1;

                    break;
                }

            }
        }

    }

    public static
    void main(String[] args) throws IOException {
        String basefilename = args[0];
        double[] ortalamanotlar = new double[countCategory(basefilename+
                                                           "_StudentScores"
                                                           + ".txt")];
        double[][] notlar =
                new double[countCategory
                        (basefilename + "_StudentScores.txt")]
                   [countCategory(basefilename + "_CourseDetails.txt")];
        String[] mcategory = new String[countCategory
                (basefilename + "_CourseDetails"
                 + ".txt")];
        int[] mquantity = new int[countCategory
                (basefilename + "_CourseDetails.txt")];
        int[] mweight = new int[countCategory
                (basefilename + "_CourseDetails.txt")];
        String[] mstudent = new String[countCategory
                (basefilename + "_StudentScores.txt")];
        getCategory(mcategory, mquantity, mweight,
                basefilename + "_CourseDetails.txt");
        getName(mstudent, basefilename + "_StudentScores.txt");
        for (int i = 0; i < mquantity.length; i++) {
            if (mquantity[i] < 0) {
                try (BufferedWriter writer =
                new BufferedWriter(new FileWriter(basefilename +
                                                               "_log.txt"))) {
                writer.write("ERROR: Course details - invalid quantity - "
                                 + "does not quantity value be negative");

                }
                System.exit(0);
            }
        }
        int total = 0;
        int toplam = 0;
        for (int j : mquantity) {
            toplam += j;
        }
        double[][] grades =
                new double[countCategory(
                        basefilename + "_StudentScores.txt")][toplam];
        hesapla(basefilename + "_StudentScores.txt",
                basefilename + "_CourseDetails.txt",
                mquantity, grades, notlar, mweight, ortalamanotlar);
        for (int i = 0; i < countCategory(
                basefilename + "_CourseDetails.txt"); i++) {
            total += mweight[i];
        }
        if (total != 100) {
            try (BufferedWriter writer =
                         new BufferedWriter(new FileWriter
                                 (basefilename + "_log.txt"))) {
            writer.write("ERROR: Course details - invalid weight - does "
                             + "not sum to 100");
            }
            System.exit(0);
        }

        writeGrades(mstudent, ortalamanotlar, basefilename);
    }
}
