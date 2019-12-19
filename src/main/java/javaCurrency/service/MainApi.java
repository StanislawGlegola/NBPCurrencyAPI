package javaCurrency.service;

import java.util.Scanner;

public class MainApi {

    XMLReader xmlReader = new XMLReader();
    URLReader urlReader = new URLReader();
    Scanner scan = new Scanner(System.in);

    public void printMenu() {
        System.out.println("Wybierz swoje działanie: ");
        System.out.println("1. Sprawdz aktualny kurs NBP.");
        System.out.println("2. Odczytaj wszystkie waluty z pliku.");

        int pos1 = scan.nextInt();
        switchOptions(pos1);
    }

    public void switchOptions(int pos) {
        switch (pos) {
            case 1:
                printMostRecentCurrenciesResultFromWeb();
                break;
            case 2:
                urlReader.buildLink();
                break;
        }
    }

    public void printMostRecentCurrenciesResultFromWeb() {
        String najnowszeNotowanieTabelaA = "http://www.nbp.pl/kursy/xml/LastA.xml";
        urlReader.readFromUrl(najnowszeNotowanieTabelaA);
    }

    /*public void printResultFromXMLFile(String xmlFile) {
        xmlReader.readFromFilePath(xmlFile);
    }*/

}
