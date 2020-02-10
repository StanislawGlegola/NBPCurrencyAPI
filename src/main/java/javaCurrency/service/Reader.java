package javaCurrency.service;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.management.StringValueExp;

public class Reader {

    //http://www.nbp.pl/kursy/xml/c025z100205.xml


    //metoda surowa, moze nie dzialc
    public static void checkWhichTableIsCalled(Document doc) {
        NodeList nodes = doc.getElementsByTagName("tabela_kursow");
        System.out.println("==========================");
        String nazwaTabeli;
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                //System.out.println("Waluta: " + getValue("numer_tabeli", element));
                nazwaTabeli = getValue("numer_tabeli", element);
                String tableLLetter = checkLetter(nazwaTabeli);

                switch (tableLLetter) {
                    case "A":
                        System.out.println("Nazwa tabeli to: A " + nazwaTabeli);
                        printResultsFromAandBtable(doc);
                        break;
                    case "B":
                        System.out.println("Nazwa tabeli to: B " + nazwaTabeli);
                        printResultsFromAandBtable(doc);
                        break;
                    case "C":
                        System.out.println("Nazwa tabeli to: C " + nazwaTabeli);
                        break;
                    default:
                        System.out.println("Nie napotkalo kodu tabeli.");
                        break;
                }
            }
        }
    }

    // ta metoda musi wyszukiwac Stringa tuz po pierwszym slashu /
    private static String checkLetter(String nazwaTabeli) {
        int letterPosition = nazwaTabeli.indexOf("/");
        String letter = String.valueOf(nazwaTabeli.charAt(letterPosition+1));
        return letter;
    }

    public static void printResultsFromAandBtable(Document doc) {
        tableType(doc);
        //System.out.println("Nazwa tabeli: " + doc.getDocumentElement().getNodeName());
        NodeList nodes = doc.getElementsByTagName("pozycja");
        System.out.println("==========================");
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                System.out.println("Waluta: " + getValue("kod_waluty", element) + " " + getValue("nazwa_waluty", element));
                System.out.println("Przelicznik: " + getValue("przelicznik", element));
                System.out.println("Sredni kurs: " + getValue("kurs_sredni", element));
                System.out.println("***********************************");
            }
        }
    }

    public static void tableType(Document doc) {
        NodeList nodes = doc.getElementsByTagName("tabela_kursow");

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                //System.out.println("Numer tabeli " + getValue("numer_tabeli", element));
                System.out.println("Data publikacji tabeli " + getValue("data_publikacji", element));
            }
        }
        System.out.println();
    }

    /*public static void checkTableType(Document doc) {
        NodeList nodes = doc.getElementsByTagName("tabela_kursow");

    }*/

    // ta metoda wypisuje tabele a i B


    public static String getValue(String symbol, Element element) {
        NodeList nodes = element.getElementsByTagName(symbol).item(0).getChildNodes();
        Node node = nodes.item(0);
        return node.getNodeValue();
    }
}
