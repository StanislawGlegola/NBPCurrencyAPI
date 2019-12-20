package javaCurrency.service;

import java.util.Scanner;

public class MainApi {

    XMLReader xmlReader = new XMLReader();
    URLReader urlReader = new URLReader();
    Scanner scan = new Scanner(System.in);

    public void printMenu() {
        System.out.println("Wybierz swoje działanie: ");
        System.out.println("1. Sprawdz aktualny kurs NBP.");
        System.out.println("2. Wyswietl wybrana tabele.");
        System.out.println("3. Odczytaj tabele z pliku xml.");

        int pos1 = scan.nextInt();
        switchOptions(pos1);
    }

    public void switchOptions(int pos) {
        switch (pos) {
            case 1:
                urlReader.printMostRecentCurrenciesResultFromWeb();
                break;
            case 2:
                urlReader.buildLink();
                break;
            case 3:
                xmlReader.getFileName();
                break;
        }
    }
}
