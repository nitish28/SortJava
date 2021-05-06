package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.collections.comparators.ComparatorChain;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, CsvException {
        CSVFile csv = new CSVFile("C:\\Users\\Samaira\\Downloads\\freshman_kgs_4.csv");
        csv.setSortDirection(1);
        ComparatorChain chain = new ComparatorChain();
        for (int i = 0; i < csv.getColsCount(); i++) {

            chain.addComparator(csv.CreateComparator(i));
        }

        csv.sortByCol(chain);

        CSVFile csv1 = new CSVFile("C:\\Users\\Samaira\\Downloads\\freshman_kgs_3.csv");
        csv1.setSortDirection(1);
        ComparatorChain chain1 = new ComparatorChain();

        for (int i = 0; i < csv1.getColsCount(); i++) {

            chain1.addComparator(csv1.CreateComparator(i));
        }

        csv1.sortByCol(chain1);

        System.out.println("------------------");
        csv.print();
        System.out.println("------------------");
        csv1.print();
        System.out.println("------------------");

         for (int i=0; i< csv.getColsCount(); i++)
        {
            for (int j=0; j< csv.records.size(); j++)
            {
                if(!csv.records.get(j)[i].toString().equals(csv1.records.get(j)[i].toString()))
                {
                    System.out.println("row index  " + j +" column index" + i);
                    System.out.println("left " + csv.records.get(j)[i] + " right" + csv1.records.get(j)[i]  );
                }

            }
        }


    }

}
