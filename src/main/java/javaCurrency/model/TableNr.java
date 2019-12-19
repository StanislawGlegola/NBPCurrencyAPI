package javaCurrency.model;

import java.time.LocalDate;

public class TableNr {
    private char table;
    private int tableEncodedNumber;
    private char controlLetter = 'z';
    private LocalDate localDate;

    public TableNr(char table, int tableEncodedNumber, LocalDate localDate) {
        this.table = table;
        this.tableEncodedNumber = tableEncodedNumber;
        this.localDate = localDate;
    }

    public char getTable() {
        return table;
    }

    public void setTable(char table) {
        this.table = table;
    }

    public int getTableEncodedNumber() {
        return tableEncodedNumber;
    }

    public void setTableEncodedNumber(int tableEncodedNumber) {
        this.tableEncodedNumber = tableEncodedNumber;
    }

    public LocalDate getDate() {
        return localDate;
    }

    public void setDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return "TableNr{" +
                "table=" + table +
                ", tableEncodedNumber=" + tableEncodedNumber +
                ", controlLetter=" + controlLetter +
                ", localDate=" + localDate +
                '}';
    }
}
