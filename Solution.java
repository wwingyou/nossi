import java.util.Arrays;

public class Solution {
    int LIMIT = 100_000;
    public int solve(int n, int[][] escalator) {
        //✅ dp 테이블의 첫 행을 채운다. 사람이 서있는 위치라면 매우 큰 값을 추가한다.
        escalator[0][0] = escalator[0][0] == 1 ? LIMIT : 1;
        escalator[0][1] = escalator[0][1] == 1 ? LIMIT : 0;
        escalator[0][2] = escalator[0][2] == 1 ? LIMIT : 1;
        //✅ 다음 행부터 dp 테이블을 채운다.
        for (int r = 1; r < escalator.length; r++) {
            for (int c = 0; c < 3; c++) {
                if (escalator[r][c] == 1) escalator[r][c] = LIMIT;
                else {
                    //✅ 이전 행 중에서 가장 빠르게 현재 위치로 도착할 수 있는 경우를 찾아 기록한다.
                    int min = LIMIT;
                    for (int k = 0; k < 3; k++) {
                        min = Math.min(min, escalator[r-1][k] + Math.abs(k - c));
                    }
                    escalator[r][c] = min;
                }
            }
        }
        //✅ 마지막 행 중 가장 작은 값을 반환한다.
        return Arrays.stream(escalator[escalator.length-1])
                    .min().getAsInt();
    }
}
