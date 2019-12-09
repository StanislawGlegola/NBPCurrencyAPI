package javaCurrency.service;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Reader  {

    public static void publicationDate(Document doc) {
        NodeList nodes = doc.getElementsByTagName("tabela_kursow");
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                System.out.println("Data publikacji tabeli " + getValue("data_publikacji", element));
            }
        }
        System.out.println();
    }

    public static void printResults(Document doc) {
        publicationDate(doc);
        System.out.println("Nazwa tabeli: " + doc.getDocumentElement().getNodeName());
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

    public static String getValue(String symbol, Element element) {
        NodeList nodes = element.getElementsByTagName(symbol).item(0).getChildNodes();
        Node node = nodes.item(0);
        return node.getNodeValue();
    }
}
