import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
/**
 * Quick sort test class using different array sizes and recursion limits.
 *
 * @author CS1C, Foothill College, Michael Bernal
 */
public class Main
{
    /**
     * Main method
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException
    {
        int arrSize = 20000, i = 0;
        long startTime, estimatedTime;
        FileOutputStream fileOut = new FileOutputStream("resources/results_output.xls");
        // creates a workbook and worksheet using apache pio
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("new sheet");

        System.out.println("------------------------------RUN 1-----------------------------------------");
        System.out.println("----------------------------------------------------------------------------");
        while(arrSize <= 1500000 )
        {
            Integer[] arr = initArr(arrSize);
            int recursionMin = 2;
            int recursionMax = 300;
            while(recursionMin <= recursionMax)
            {
                Integer [] arrCopy = arr.clone();
                startTime = System.nanoTime();
                sortArr(arrCopy, recursionMin);
                estimatedTime = System.nanoTime() - startTime;
                // writes data to corresponding cell and row number, apart of apache pio
                Row row = sheet.createRow(i);
                Cell cell = row.createCell(0);
                Cell cell2 = row.createCell(1);
                Cell cell3 = row.createCell(2);
                cell.setCellValue(arrCopy.length);
                cell2.setCellValue(recursionMin);
                cell3.setCellValue(estimatedTime);

                System.out.println("Array Size: " + arrCopy.length + "\n"
                        + "Recursion #: " + recursionMin + "\n"
                        + "Elapsed Time: " + TimeConverter.convertTimeToString(estimatedTime) + "\n");

                recursionMin = recursionMin + 2;
                i++;
            }
            arrSize = (2 * arrSize);
        }
        try
        {
            wb.write(fileOut); // write to excel file
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * initArr() creates an array filled with random integers
     * @param size size of the array
     * @return array with random ints
     */
    public static Integer[] initArr(int size)
    {
        Integer[] arrRandom = new Integer[size];
        Random rand = new Random();
        int num = 1;

        for(int i = 0; i < arrRandom.length; i++)
        {
            arrRandom[i] = rand.nextInt(num);
            num = num + 1;
        }
        return arrRandom;
    }

    /**
     * sortArr() sorts an array with a given recursion limit
     * @param sortedArr this is the array to be sorted
     * @param limit the recursion limit
     * @return the array sorted
     */
    public static Integer[] sortArr(Integer[] sortedArr, int limit)
    {
        FHsort.setRecursionLimit(limit);
        FHsort.quickSort(sortedArr);
        return sortedArr;
    }
}