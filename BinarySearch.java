// Only works on sorted lists
// May need to use a Comparator interface to search a list of objects

public class BinarySearch {
	
	public static void main(String[] args) {
		int[] intList = {2, 4, 6, 8, 10, 12};
		System.out.println(binarySearch(intList, 8));
		System.out.println(recursiveBinarySearch(intList, 0, intList.length, 12));
		String[] strList = {"Alfa", "Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot"};
		System.out.println(stringBinarySearch(strList, "Alpha"));
	}
	
	// Time complexity: O(logn)
	// Space complexity: O(1)
	public static int binarySearch(int[] list, int target) {
		int min = 0;
		int max = list.length - 1;
		while (min <= max) {
			int mid = (min+max) / 2;
			if (list[mid] == target) {
				return mid;
			} else if(list[mid] > target) {
				max = mid - 1;
			} else {
				min = mid + 1;
			}
		}
		return -1;
	}
	
	// Time complexity: O(logn)
	// Space complexity: O(logn) (logn stack frames)
	public static int recursiveBinarySearch(int[] list, int min, int max, int target) {
		if (min > max) {
			return -1;
		} else {
			int mid = (min+max)/2;
			if (list[mid] == target) {
				return mid;
			}
			if (list[mid] > target) {
				return recursiveBinarySearch(list, min, max-1, target);
			} else {
				return recursiveBinarySearch(list, min+1, max, target);
			}
		}
	}
	
	// Time complexity: O(logn)
	// Space complexity: O(1)
	// We assume the list is sorted by alphabetical order
	public static int stringBinarySearch(String[] list, String target) {
		int min = 0;
		int max = list.length-1;
		while (min <= max) {
			int mid = (min+max)/2;
			int compare = list[mid].compareTo(target);
			if (compare == 0) {
				return mid;
			} else if (compare > 0) {
				max = mid - 1;
			} else {
				min = mid + 1;
			}
		}
		return -1;
	}
	
}
