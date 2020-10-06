package com.company;

import java.util.*;

public class InvoiceWriter {

    /**InvoiceWriter() method
     *writer
     * takes in information about invoice and writes them into
     * Invoice.txt file
     *
     * @param Data input data which is going to be written into Invoice.txt file
     */
    public void Invoice(String Data){
        try {
            Formatter IF = new Formatter("Invoice.txt");

            IF.format("%s",Data);
            IF.close();
        }catch (Exception e){
            System.out.println("Invoice writing failed!");
        }
    }
}
