public class Solution {
    public String solve(String[] strings) {
        String best = strings[0];
        for (int i = 1; i < strings.length; i++) {
            if (best.length() < strings[i].length()) {
                best = strings[i];
            }
        }
        return best;
    }
}
