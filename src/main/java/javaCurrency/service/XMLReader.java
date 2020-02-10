package javaCurrency.service;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class XMLReader extends Reader {

    public void readFromFilePath(String filePath) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(filePath);
            doc.getDocumentElement().normalize();
            printResultsFromAandBtable(doc);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public void getByFilename() {
        System.out.println("Podaj nazwe pliku: ");
        Scanner scan = new Scanner(System.in);
        String fileName = scan.next();
        if (fileName.endsWith(".xml")) {
            readFromFilePath(fileName);
        } else {
            String fileNameWithXMLAdded = fileName + ".xml";
            readFromFilePath(fileNameWithXMLAdded);
        }
    }
}
