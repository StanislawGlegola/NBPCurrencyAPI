package javaCurrency.service;

import javaCurrency.model.TableNr;

import java.time.LocalDate;

public class TableNrDecoder {

    public char[] tableNrToCharArray(String tableNr){
        //a225z191121.xml
        char[] tableNrChars= new char[tableNr.length()];
        for (int i = 0; i < tableNrChars.length; i++) {
            tableNrChars[i] = tableNr.charAt(i);
        }
        return tableNrChars;
    }

    public TableNr decodedTableUrl(String nrTabeli) {

        char tableType = nrTabeli.charAt(0);
        int tableNumberInGivenYear = Integer.parseInt((nrTabeli.substring(1,4)));
        int tableYear = 2000+Integer.parseInt(nrTabeli.substring(5,7));
        int tableMonth = Integer.parseInt(nrTabeli.substring(7,9));
        int tableDay = Integer.parseInt(nrTabeli.substring(9,11));
        LocalDate dateOfTableCreation = LocalDate.of(tableYear,tableMonth,tableDay);
        return new TableNr(tableType,tableNumberInGivenYear, dateOfTableCreation);

        // 01234567891
        //"a225z191121.xml"
    }

    public TableNr codeTableUrl(String nrTabeli) {

        char tableType = nrTabeli.charAt(0);
        int tableNumberInGivenYear = Integer.parseInt((nrTabeli.substring(1,4)));
        int tableYear = 2000+Integer.parseInt(nrTabeli.substring(5,7));
        int tableMonth = Integer.parseInt(nrTabeli.substring(7,9));
        int tableDay = Integer.parseInt(nrTabeli.substring(9,11));
        LocalDate dateOfTableCreation = LocalDate.of(tableYear,tableMonth,tableDay);
        return new TableNr(tableType,tableNumberInGivenYear,dateOfTableCreation);

        // 01234567891
        //"a225z191121.xml"
    }

    /*
    Uwaga!
    Od dnia 1 lipca 2015 r. plik dir.txt zawiera dane tylko z bieżącego roku. Dane z lat poprzednich
    umieszczone są w plikach dir2002.txt, dir2003.txt ... dir 2015.txt. Format pliku nie uległ zmianie.

    Nazwy wszystkich plików mają taki sam format:

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

    Aktualne (ostanie) pliki z tabelami kursów A, B i C dostępne są również pod następującymi nazwami (odpowiednio): LastA.xml, LastB.xml, LastC.xml
    */
}
