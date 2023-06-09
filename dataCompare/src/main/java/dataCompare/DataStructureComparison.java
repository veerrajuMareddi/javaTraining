package dataCompare;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.TreeMap;

public class DataStructureComparison {
	public static void main(String[] args) {
		// Create instances of data structures
		ArrayList<Integer> arrayList = new ArrayList<>();
		LinkedList<Integer> linkedList = new LinkedList<>();
		HashSet<Integer> hashSet = new HashSet<>();
		TreeSet<Integer> treeSet = new TreeSet<>();
		HashMap<Integer, String> hashMap = new HashMap<>();
		TreeMap<Integer, String> treeMap = new TreeMap<>();

		// Perform insert operation for each data structure
		performInsertOperation(arrayList, 100_000);
		performInsertOperation(linkedList, 100_000);
		performInsertOperation(hashSet, 100_000);
		performInsertOperation(treeSet, 100_000);
		performInsertOperation(hashMap, 100_000);
		performInsertOperation(treeMap, 100_000);
		print();
		// Measure time and memory usage for each data structure
		measureTimeAndMemory(arrayList, "ArrayList");
		measureTimeAndMemory(linkedList, "LinkedList");
		measureTimeAndMemory(hashSet, "HashSet");
		measureTimeAndMemory(treeSet, "TreeSet");
		measureTimeAndMemory(hashMap, "HashMap");
		measureTimeAndMemory(treeMap, "TreeMap");
		print();

		// Perform delete operation for each data structure
		performDeleteOperation(arrayList, 10_000);
		performDeleteOperation(linkedList, 10_000);
		performDeleteOperation(treeSet, 10_000);
		performDeleteOperation(hashSet, 10_000);
		performDeleteOperation(hashMap, 10_000);
		performDeleteOperation(treeMap, 10_000);
		print();

		// Measure time and memory usage after delete operation
		measureTimeAndMemory(arrayList, "ArrayList");
		measureTimeAndMemory(linkedList, "LinkedList");
		measureTimeAndMemory(hashSet, "HashSet");
		measureTimeAndMemory(treeSet, "TreeSet");
		measureTimeAndMemory(hashMap, "HashMap");
		measureTimeAndMemory(treeMap, "TreeMap");
		print();

		// Display the results
		displayResults();
	}

	// Helper method to perform insert operation for ArrayList and LinkedList
	private static void performInsertOperation(java.util.List<Integer> list, int numElements) {
		long startTime = System.nanoTime();

		for (int i = 0; i < numElements; i++) {
			list.add(i);
		}

		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;

		System.out.println("Insert operation completed for " + list.getClass().getSimpleName() + ".");
		System.out.println("Time taken: " + elapsedTime + " nanoseconds.");
	}

	// Helper method to perform insert operation for HashSet and TreeSet
	private static void performInsertOperation(java.util.Set<Integer> set, int numElements) {
		long startTime = System.nanoTime();

		for (int i = 0; i < numElements; i++) {
			set.add(i);
		}

		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;

		System.out.println("Insert operation completed for " + set.getClass().getSimpleName() + ".");
		System.out.println("Time taken: " + elapsedTime + " nanoseconds.");
	}

	// Helper method to perform insert operation for HashMap and TreeMap
	private static void performInsertOperation(java.util.Map<Integer, String> map, int numElements) {
		long startTime = System.nanoTime();

		for (int i = 0; i < numElements; i++) {
			map.put(i, "Value" + i);
		}

		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;

		System.out.println("Insert operation completed for " + map.getClass().getSimpleName() + ".");
		System.out.println("Time taken: " + elapsedTime + " nanoseconds.");
	}

	// Helper method to perform delete operation for ArrayList and LinkedList
	private static void performDeleteOperation(java.util.List<Integer> list, int numElements) {
		long startTime = System.nanoTime();

		for (int i = 0; i < numElements; i++) {
			list.remove(0);
		}

		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;

		System.out.println("Delete operation completed for " + list.getClass().getSimpleName() + ".");
		System.out.println("Time taken: " + elapsedTime + " nanoseconds.");
	}

	// Helper method to perform delete operation for HashSet and TreeSet
	private static void performDeleteOperation(java.util.Set<Integer> set, int numElements) {
		long startTime = System.nanoTime();

		for (int i = 0; i < numElements; i++) {
			set.remove(i);
		}

		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;

		System.out.println("Delete operation completed for " + set.getClass().getSimpleName() + ".");
		System.out.println("Time taken: " + elapsedTime + " nanoseconds.");
	}

	// Helper method to perform delete operation for HashMap and TreeMap
	private static void performDeleteOperation(java.util.Map<Integer, String> map, int numElements) {
		long startTime = System.nanoTime();

		for (int i = 0; i < numElements; i++) {
			map.remove(i);
		}

		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;

		System.out.println("Delete operation completed for " + map.getClass().getSimpleName() + ".");
		System.out.println("Time taken: " + elapsedTime + " nanoseconds.");
	}

	// Helper method to measure time and memory usage for data structures
	private static void measureTimeAndMemory(Object dataStructure, String name) {
		long startTime = System.nanoTime();

		// Perform any operation to measure memory usage (e.g., iterate over elements)

		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;

		Runtime runtime = Runtime.getRuntime();
		long memoryUsed = runtime.totalMemory() - runtime.freeMemory();

		System.out.println("Time and memory usage measured for " + name + ".");
		System.out.println("Time taken: " + elapsedTime + " nanoseconds.");
		System.out.println("Memory used: " + memoryUsed + " bytes.");
	}

	// Method to display the results
	private static void displayResults() {
		System.out.println("Data structure comparison results:");
		// Display the results in a readable format
		// Include the time and space complexities for each operation and data structure
		// Compare the results with the expected time and space complexities
	}

	private static void print() {
		for (int i = 0; i < 3; i++) {
			System.out.println(
					"================================================================================================");

		}
	}
}
