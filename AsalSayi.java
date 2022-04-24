package Practice;

import java.util.Scanner;

public class AsalSayi {
    public static void main(String[] args) {
        System.out.println("sayı gir");
        int x = new Scanner(System.in).nextInt();
        for (int i = 2; i < x; i++) {
            if (x%i==0){
                System.out.println("Sayı asal değil");
                return;
            }
        }
        System.out.println("Sayı asal");

    }
}

