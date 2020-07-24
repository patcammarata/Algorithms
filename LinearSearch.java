
public class LinearSearch {
	
	public static void main(String[] args) {
		String[] strList = {"Alfa", "Bravo", "Charlie", "Delta", "Echo"};
		System.out.println(linearSearch(strList, "Delta"));
		System.out.println(linearSearch(strList, "Foxtrot"));
	}
	
	// Time complexity: O(n)
	// Space complexity: O(1)
	public static int linearSearch(Object[] list, Object target) {
		for (int i = 0; i < list.length; i++) {
			if (list[i] == target) {
				return i;
			}
		}
		return -1;
	}
}
