import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        Solution solution = new Solution();
        int result = solution.add(a, b);
        System.out.println(result);
    }
}
