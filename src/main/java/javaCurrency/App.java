package javaCurrency;

public class App {
    public static void main(String[] args) {
        XMLReader xmlReader = new XMLReader();

        String nrTabeli = "a225z191121.xml";
        String url = "http://www.nbp.pl/kursy/xml/"+nrTabeli;
        xmlReader.readFromUrl(url);

        String filePath = "a225z191121.xml";
        xmlReader.readFromFilePath(filePath);
    }
}
