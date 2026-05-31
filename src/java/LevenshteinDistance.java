package src.java;
public class LevenshteinDistance {
    public static int calculateDistance(String str1, String str2) {
        return calculateDistanceRecursive(str1, str1.length(), str2, str2.length());
    }
    private static int calculateDistanceRecursive(String str1, int len1, String str2, int len2) {
        // Base cases: if either string is empty,
          // return the length of the other string
        if (len1 == 0) {
            return len2;
        }
        if (len2 == 0) {
            return len1;
        }
        // If the last characters of the strings are equal,
          // No operation is required
        if (str1.charAt(len1 - 1) == str2.charAt(len2 - 1)) {
            return calculateDistanceRecursive(str1, len1 - 1, str2, len2 - 1);
        }
        // Calculate cost of three possible operations
          // Insertion, Deletion, and Substitution
        int insertionCost = calculateDistanceRecursive(str1, len1, str2, len2 - 1);
        int deletionCost = calculateDistanceRecursive(str1, len1 - 1, str2, len2);
        int substitutionCost = calculateDistanceRecursive(str1, len1 - 1, str2, len2 - 1);
        // Return minimum of the three
          // costs plus 1 (for the operation)
        return 1 + Math.min(Math.min(insertionCost, deletionCost), substitutionCost);
    }
    public static void main(String[] args) {
        String str1 = "Java";
        String str2 = "JavaScript";
        int distance = calculateDistance(str1, str2);
        System.out.println("Levenshtein distance between \"" + str1 + "\" and \"" + str2 + "\" is: " + distance);
    }
}