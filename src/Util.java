public class Util {
    public static boolean isOneLetterDifferent(String a, String b) {
        if (a.length() != b.length()) {
            return false;
        }

        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                count++;
                if (count > 1) {
                    return false;
                }
            }
        }

        return count == 1;
    }

    // Menghitung perbedaan huruf antara dua kata (untuk greedyBFS dan UCS)
    public static int heuristic(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    // Menghitung perbedaan huruf antara dua kata (untuk A*)
    public static int fn(String startWord, String a, String goalWord) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != goalWord.charAt(i)) {
                count++;                        // h(n)
            }
        }

        count += heuristic(startWord, a);   // g(n)
        return count;
    }
}