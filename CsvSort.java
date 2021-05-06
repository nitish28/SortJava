package org.example;

import com.opencsv.*;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.collections.comparators.ComparatorChain;

import java.io.*;
import java.util.*;

class CSVFile {

    public List<String[]> records;

    //constants for sortDirection
    final public int SortASC = 1;
    final public int SortDESC = -1;
    private int sortDirection = SortASC; //1 for ASC, -1 for DESC
    String fileName;

    private int colsCount=0;

    public CSVFile(String file) throws IOException, CsvException {
        FileReader filereader = new FileReader(file);

        // create csvReader object and skip first Line
        CSVReader csvReader = new CSVReaderBuilder(filereader)
                .withSkipLines(1)
                .build();
        records = csvReader.readAll();
        if(records.stream().count() >0 )
        {
            String[] arr = records.get(0);
            colsCount = arr.length;
        }

        // print Data
        //for (String[] row : records) {
          //  for (String cell : row) {
            //    System.out.print(cell + "\t");
            //}
            //System.out.println();
        //}
    }

    public void print(){
        for(String[] arr : records){
            for (String s:arr) {
                System.out.print(s+"\t");
            }
            System.out.println();
        }
    }

    public void save() throws IOException{
        try(BufferedWriter out = new BufferedWriter(new FileWriter(fileName)))
        {
            for(String[] arr : records){
                for (String s:arr) {
                    out.write(s+"\t");
                }
                out.write("\n");
            }
        }
    }

    public void setSortDirection(int direction){
        sortDirection = direction;
    }

    public  Comparator<String[]>  CreateComparator(final int i)
    {
        Comparator<String[]> comp = new Comparator<String[]>() {
            public int compare(String[] a, String[] b) {
                //reverse result if DESC (sortDirection = -1)
                return sortDirection * a[i].compareTo(b[i]);
            }
        };
        return  comp;
    }
    public void sortByCol(ComparatorChain chain) {

        Collections.sort(records, chain);
    }


    public int getColsCount(){
        return colsCount;
    }
}



class SortCSV {

}