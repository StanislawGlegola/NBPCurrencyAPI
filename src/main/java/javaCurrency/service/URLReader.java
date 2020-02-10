package javaCurrency.service;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class URLReader extends Reader {

    TableNrDecoder tableNrDecoder;

    public void buildLink() {
        Scanner scanner = new Scanner(System.in);

        //a , b , c , h
        System.out.println("Wybierz tabele kursów średnich walut obcych. a, b, c, h");
        String tabelaKursow = scanner.next();
        System.out.println("Podaj trzyliczbowy numer tabeli");
        String numerTabeli = scanner.next();

        String elementStaly = "z";

        System.out.println("Podaj rok (YY/mm/dd)");
        String rok = scanner.next();
        System.out.println("Podaj miesiac (yy/MM/dd)");
        String miesiac = scanner.next();
        System.out.println("Podaj dzien (yy/mm/DD)");
        String dzien = scanner.next();

        String xml = ".xml";

        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder stringBuild = new StringBuilder();
        StringBuilder xmlAppend = stringBuilder.append(tabelaKursow).append(numerTabeli).append(elementStaly).append(rok).append(miesiac).append(dzien).append(xml);
        String tableNameWithXml = xmlAppend.toString();

        StringBuilder withoutXmlAppend = stringBuild.append(tabelaKursow).append(numerTabeli).append(elementStaly).append(rok).append(miesiac).append(dzien);
        String tableNameWithoutXml = withoutXmlAppend.toString();

        if (checkIfTablenameExsist(tableNameWithoutXml) == true) {
            createLinkToGivenUrl(tableNameWithXml);
        } else {
            System.out.println("Tabela nieistnieje");
        }
        scanner.close();
    }

    public static void readFromUrl(String url) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new URL(url).openStream());
            doc.getDocumentElement().normalize();
            publicationDate(doc);
            //printResultsFromAandBtable(doc);
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
        String preparedLink = "https://www.nbp.pl/kursy/xml/" + xmlName;
        readFromUrl(preparedLink);
    }

    public void printMostRecentCurrenciesResultFromWeb() {
        String najnowszeNotowanieTabelaA = "https://www.nbp.pl/kursy/xml/LastA.xml";
        readFromUrl(najnowszeNotowanieTabelaA);
    }

    public List addAllTablenamesFromUrlToList() {
        List<String> list = new ArrayList<>();
        String tableCodesURL = "https://www.nbp.pl/kursy/xml/dir.txt";
        try {
            URL website = new URL(tableCodesURL);
            URLConnection connection = website.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                list.add(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean checkIfTablenameExsist(String tablenameWithoutXml) {
        List<String> list = addAllTablenamesFromUrlToList();
        if (list.isEmpty()) {
            System.out.println("Lista pusta.");
            return false;
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(tablenameWithoutXml)) {
                    System.out.println("This tablename exists.");
                    return true;
                }
            }
            return true;
        }
    }

    public void findAllTablenamesAvailable() {
        List<String> list = addAllTablenamesFromUrlToList();
        if (list.isEmpty()) {
            System.out.println("Pusta lista");
        } else {
            for (String lista : list) {
                System.out.println(lista);
            }
        }
    }

    public void printListOfAllTablenames() {
        List<String> list = addAllTablenamesFromUrlToList();
        for (String lista : list) {
            System.out.println(lista);
        }
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

