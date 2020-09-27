package javaCurrency.service;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Reader {

    //http://www.nbp.pl/kursy/xml/c025z100205.xml

    public static void getTableType(Document doc) {
        NodeList nodes = doc.getElementsByTagName("tabela_kursow");
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                System.out.println("Numer tabeli " + getValue("numer_tabeli", element));
            }
        }
        System.out.println();
    }

    public static void printMenuFromGivenTableLetter(Document doc) {
        NodeList nodes = doc.getElementsByTagName("tabela_kursow");
        System.out.println("==========================");
        String tableName;
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                //System.out.println("Waluta: " + getValue("numer_tabeli", element));
                tableName = getValue("numer_tabeli", element);
                String tableLLetter = checkTableTypeLetter(tableName);

                switch (tableLLetter) {
                    case "A":
                        printResultsFromAorBtable(doc);
                        break;
                    case "B":
                        printResultsFromAorBtable(doc);
                        break;
                    case "C":
                        printResultsFromCtable(doc);
                        break;
                    default:
                        System.out.println("Nie napotkalo kodu tabeli.");
                        break;
                }
            }
        }
        System.out.println();
    }

    // ta metoda musi wyszukiwac Stringa tuz po pierwszym slashu /
    // 25/C/NBP/2010  <-- w typ wypadku 3+1
    private static String checkTableTypeLetter(String nazwaTabeli) {
        int letterPosition = nazwaTabeli.indexOf("/");
        String letter = String.valueOf(nazwaTabeli.charAt(letterPosition + 1));
        return letter;
    }

    public static void printResultsFromAorBtable(Document doc) {
        getTableType(doc);
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

    public static void printResultsFromCtable(Document doc) {
        //tableType(doc);
        //System.out.println("Nazwa tabeli: " + doc.getDocumentElement().getNodeName());
        NodeList nodes = doc.getElementsByTagName("pozycja");
        System.out.println("==========================");
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                System.out.println("Waluta: " + getValue("kod_waluty", element));
                System.out.println("Kurs kupna: " + getValue("kurs_kupna", element));
                System.out.println("Kurs sprzedazy: " + getValue("kurs_sprzedazy", element));
                System.out.println("***********************************");
            }
        }
    }

    public static String getValue(String symbol, Element element) {
        NodeList nodes = element.getElementsByTagName(symbol).item(0).getChildNodes();
        Node node = nodes.item(0);
        return node.getNodeValue();
    }


}
