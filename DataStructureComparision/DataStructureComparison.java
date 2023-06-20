package dataCompare;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * @author VeerrajuMareddi
 *
 */
public class DataStructureComparison {
    public static void main(String[] args) {
        // Create instances of data structures
        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        HashSet<Integer> hashSet = new HashSet<>();
        TreeSet<Integer> treeSet = new TreeSet<>();
        HashMap<Integer, String> hashMap = new HashMap<>();
        TreeMap<Integer, String> treeMap = new TreeMap<>();


        
      //Insertion operation with 1 lakh records for all data structures
        performInsertOperation(arrayList, 100_000);
        performInsertOperation(linkedList, 100_000);
        performInsertOperation(hashSet, 100_000);
        performInsertOperation(treeSet, 100_000);
        performInsertOperation(hashMap, 100_000);
        performInsertOperation(treeMap, 100_000);
        print();
 

        //Delete operation with 10000 records for all data structures 
        performDeleteOperation(arrayList, 10_000);
        performDeleteOperation(linkedList, 10_000);
        performDeleteOperation(treeSet, 10_000);
        performDeleteOperation(hashSet, 10_000);
        performDeleteOperation(hashMap, 10_000);
        performDeleteOperation(treeMap, 10_000);
        print();
    }

/** Tests insertion performance based on time of insertion.
  *  Takes instances of the List as parameters.
  * 
  * @param list
  * @param numElements
  */

    private static void performInsertOperation(java.util.List<Integer> list, int numElements) {
        long startTime = System.nanoTime();
        long startMemory = getUsedMemory(); // Get initial memory usage
        for (int i = 0; i < numElements; i++) {
            list.add(i);
        }
        long endMemory = getUsedMemory();
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        long spaceComplexity = endMemory - startMemory;

 

        System.out.println("Insert operation completed for " + list.getClass().getSimpleName() + ".");
        System.out.println("Time taken: " + elapsedTime + " nanoseconds.");
        System.out.println("Space taken: " + spaceComplexity + " bytes.\n");
    }

 
/**
 * Tests insertion performance based on time of insertion.
 * Takes instances of the Set as parameters.
 * @param set
 * @param numElements
 */
    private static void performInsertOperation(java.util.Set<Integer> set, int numElements) {
        long startTime = System.nanoTime();
        long startMemory = getUsedMemory(); // Get initial memory usage
        for (int i = 0; i < numElements; i++) {
            set.add(i);
        }
        long endMemory = getUsedMemory();
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        long spaceComplexity = endMemory - startMemory;

        System.out.println("Insert operation completed for " + set.getClass().getSimpleName() + ".");
        System.out.println("Time taken: " + elapsedTime + " nanoseconds.");
        System.out.println("Space taken: " + spaceComplexity + " bytes.\n");
    }

 

    // Helper method to perform insert operation for HashMap and TreeMap
    private static void performInsertOperation(java.util.Map<Integer, String> map, int numElements) {
        long startTime = System.nanoTime();
        long startMemory = getUsedMemory(); // Get initial memory usage
        for (int i = 0; i < numElements; i++) {
            map.put(i, "Value" + i);
        }
        long endMemory = getUsedMemory();
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        long spaceComplexity = endMemory - startMemory;

 

        System.out.println("Insert operation completed for " + map.getClass().getSimpleName() + ".");
        System.out.println("Time taken: " + elapsedTime + " nanoseconds.");
        System.out.println("Space taken: " + spaceComplexity + " bytes.\n");
    }

 

    // Helper method to perform delete operation for ArrayList and LinkedList
    private static void performDeleteOperation(java.util.List<Integer> list, int numElements) {
        long startTime = System.nanoTime();
        long startMemory = getUsedMemory(); // Get initial memory usage
        for (int i = 0; i < numElements; i++) {
            list.remove(0);
        }
        long endMemory = getUsedMemory();
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        long spaceComplexity = startMemory - endMemory;

 

        System.out.println("Delete operation completed for " + list.getClass().getSimpleName() + ".");
        System.out.println("Time taken: " + elapsedTime + " nanoseconds.");
        System.out.println("Space taken: " + spaceComplexity + " bytes.\n");
    }

 

    // Helper method to perform delete operation for HashSet and TreeSet
    private static void performDeleteOperation(java.util.Set<Integer> set, int numElements) {
        long startTime = System.nanoTime();
        long startMemory = getUsedMemory(); 
        for (int i = 0; i < numElements; i++) {
            set.remove(i);
        }
        long endMemory = getUsedMemory();
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        long spaceComplexity = startMemory - endMemory;

 

        System.out.println("Delete operation completed for " + set.getClass().getSimpleName() + ".");
        System.out.println("Time taken: " + elapsedTime + " nanoseconds.");
        System.out.println("Space taken: " + spaceComplexity + " bytes.\n");
    }

 

    // Helper method to perform delete operation for HashMap and TreeMap
    private static void performDeleteOperation(java.util.Map<Integer, String> map, int numElements) {
        long startTime = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        long startMemory = runtime.totalMemory(); // Get initial memory usage
        for (int i = 0; i < numElements; i++) {
            map.remove(i);
        }
        long endMemory = getUsedMemory();
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        long spaceComplexity = startMemory - runtime.freeMemory();

 

        System.out.println("Delete operation completed for " + map.getClass().getSimpleName() + ".");
        System.out.println("Time taken: " + elapsedTime + " nanoseconds.");
        System.out.println("Space taken: " + spaceComplexity + " bytes.\n");
    }

    private static void print() {
for(int i=0;i<3;i++)
{
	System.out.println("==============================================================================================");
}
   	}

    private static long getUsedMemory() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); // Run garbage collection to minimize memory usage
        return runtime.totalMemory() - runtime.freeMemory();
    }

 

}