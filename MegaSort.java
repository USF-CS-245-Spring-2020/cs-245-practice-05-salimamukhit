import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MegaSort {
    // static constant which indicates the amount of array items to be printed
    static final int PRINT = 20;

    // my megasort function reads the file and parses strings into integers, fills in the array to be sorted and passes to my quicksort algorithm
    public void megaSort(File file) {
        int arraySize = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                arraySize++;
                line = br.readLine();
            }
            int[] array = new int[arraySize];
            int i = 0;
            String[] arrayStr = new String[arraySize];
            br = new BufferedReader(new FileReader(file));
            line = br.readLine();
            while (line != null) {
                arrayStr[i] = line;
                line = br.readLine();
                i++;
            }
            br.close();
            for (int k = 0; k < array.length; k++) {
                array[k] = Integer.parseInt(arrayStr[k]);
            }
            quicksort(array, 0, array.length - 1);
            printArray(array);
        } catch (IOException e) {
            System.out.println("Could not open the file");
        }
    }

    // well known quicksort algorithm to sort the larg array retrieved from the file
    void quicksort(int[] array, int left, int right) {
        if (left < right) {
            int pivot = partition(array, left, right);
            quicksort(array, left, pivot - 1);
            quicksort(array, pivot + 1, right);
        }
    }

    // a helper function for a quicksort algorithm which returns the new index of a pivot point
    public int partition(int[] array, int left, int right) {
        int pivot = array[right - 1];
        int i = (left - 1);

        for (int j = left; j < right; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int swap = array[i + 1];
        array[i + 1] = array[right];
        array[right] = swap;

        return i + 1;
    }

    // a utility function which prints the items of an array
    void printArray(int[] array) {
        for (int i = 0; i < PRINT; i++) {
            System.out.println(array[i]);
        }
    }

    // calling my megasort function, run to see the result
    public static void main(String[] args) {
        MegaSort ms = new MegaSort();
        File file = new File("1m-ints.txt");
        ms.megaSort(file);
    }
}
