package Metod_Ve_Parametre;

import java.util.Random;
import java.util.Scanner;

public class ZarOyunu {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("1.Oyuncunun parası : ");
        int ilk = scanner.nextInt();
        System.out.println("2.oyuncucun parası : ");
        int iki = scanner.nextInt();
        while (ilk > 0 && iki > 0){
            System.out.println("1.Oyuncu zar atıyor...");
            int ilkZar = random.nextInt(7);
            System.out.println(ilkZar);
            Thread.sleep(2000);
            System.out.println("2.oyuncu zar atıyor...");
            int ikinciZar = random.nextInt(7);
            System.out.println(ikinciZar);
            Thread.sleep(2000);

            if (ilkZar<ikinciZar){
                int fark = ikinciZar - ilkZar;
                iki+=fark;
                ilk-=fark;
                System.out.println("1. OYUNCU "+ilk);
                System.out.println("2. OYUNCU "+iki);
            }
            else if(ikinciZar < ilkZar){
                int fark = ilkZar - ikinciZar;
                iki-=fark;
                ilk+=fark;
                System.out.println("1. OYUNCU "+ilk);
                System.out.println("2. OYUNCU "+iki);

            }
            else{
                System.out.println("zarlar eşit");
            }
        }

    }
}
