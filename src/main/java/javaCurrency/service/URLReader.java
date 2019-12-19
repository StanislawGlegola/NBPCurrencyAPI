package javaCurrency.service;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class URLReader extends Reader {

    public void buildLink() {
        Scanner scanner = new Scanner(System.in);

        //a , b , c , h
        System.out.println("Wybierz tabele kursów średnich walut obcych. a, b, c, h");
        String tabelaKursow = scanner.next();
        System.out.println("Podaj trzyliczbowy numer tabeli");
        String numerTabeli = scanner.next();

        String elementStaly = "z";

        System.out.println("Podaj rok");
        String rok = scanner.next();
        System.out.println("Podaj miesiac");
        String miesiac = scanner.next();
        System.out.println("Podaj dzien");
        String dzien = scanner.next();

        String xml = ".xml";

        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder xmlAppend = stringBuilder.append(tabelaKursow).append(numerTabeli).append(elementStaly).append(rok).append(miesiac).append(dzien).append(xml);
        String xmlName = xmlAppend.toString();
        scanner.close();

        createLinkToGivenUrl(xmlName);
    }

    TableNrDecoder tableNrDecoder;

    public static void readFromUrl(String url) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse((new URL(url).openStream()));
            doc.getDocumentElement().normalize();
            printResults(doc);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printResultFromGivenUrl(String url) {
        readFromUrl(url);
    }

    public void createLinkToGivenUrl(String xmlName) {
        String preparedLink = "http://www.nbp.pl/kursy/xml/" + xmlName;
        readFromUrl(preparedLink);
    }
}


        /* Nazwy wszystkich plików mają taki sam format:

'xnnnzrrmmdd.xml'

gdzie poszczególne litery oznaczają:

x – litera określająca typ tabeli:
a - tabela kursów średnich walut obcych;
b - tabela kursów średnich walut niewymienialnych;
c - tabela kursów kupna i sprzedaży;
h - tabela kursów jednostek rozliczeniowych.
nnn – trzyznakowy (liczbowy) numer tabeli w roku;

z – litera ‘z’ (element stały)

rrmmdd – data publikacji/obowiązywania tabeli w formacie (bez odstępów): dwie ostatnie cyfry numeru roku, dwie cyfry numeru miesiąca oraz dwie cyfry numeru dnia.

.xml – standardowe rozszerzenie nazwy pliku w formacie XML.

Przykład pobrania z serwisu internetowego NBP pliku XML z tabelą A kursów średnich opublikowaną w dniu 5 lutego 2010 r.
W pliku dir2010.txt szukamy według daty:

...
a024z100204
c025z100205
h025z100205
a025z100205
c026z100208
h026z100208
...

A zatem szukany plik znajduje się pod adresem:
http://www.nbp.pl/kursy/xml/a025z100205.xml
Aktualne (ostanie) pliki z tabelami kursów A, B i C dostępne są również pod następującymi nazwami (odpowiednio): LastA.xml, LastB.xml, LastC.xml.*/

