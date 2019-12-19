package javaCurrency;

import javaCurrency.model.TableNr;
import javaCurrency.service.ExchangeRateHistoryReader;
import javaCurrency.service.MainApi;
import javaCurrency.service.XMLReader;

import java.io.IOException;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        MainApi mainApi = new MainApi();
        XMLReader xmlReader = new XMLReader();
        ExchangeRateHistoryReader exchangeRateHistoryReader = new ExchangeRateHistoryReader();

        mainApi.printMenu();

        //https://www.nbp.pl/kursy/xml/dir.txt
        String xmlFile = "a225z191121.xml";
        //mainApi.printMostRecentCurrenciesResult();
        //mainApi.printResultFromGivenUrl("http://www.nbp.pl/kursy/xml/" + xmlFile);
        //xmlReader.readFromFilePath(xmlFile);

        try {
            exchangeRateHistoryReader.readStringFromURL();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*char table = 's';
    int tableEncodedNumber=225;
    final char controlLetter = 'z';
    LocalDate localDate = LocalDate.of(2019,11,21);

    TableNr nowaTabela = new TableNr(table, tableEncodedNumber, controlLetter, localDate);
*/
}
