package nossi.deserializer;

import static nossi.Util.isArrayElementSeparator;
import static nossi.Util.isArrayEndDelimiter;
import static nossi.Util.isArrayStartDelimiter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Int2DArrayDeserializer implements Deserializer {
    static final Pattern MATCH = Pattern.compile("^\\[\\[[0-9]+");

    @Override
    public String getName() {
        return "Int2DArrayDeserializer";
    }

    @Override
    public Class<?> getType() {
        return int[][].class;
    }

    @Override
    public boolean match(String valueString) {
        return Int2DArrayDeserializer.MATCH.matcher(valueString).find();
    }

    @Override
    public Object deserialize(String valueString) {
        List<List<Integer>> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        List<Integer> row = new ArrayList<>();
        boolean open = false;
        for (char c : valueString.toCharArray()) {
            if (isArrayStartDelimiter(c)) {
                open = true;
                continue;
            } else if (isArrayEndDelimiter(c)) {
                if (open) {
                    open = false;
                    row.add(Integer.parseInt(sb.toString()));
                    sb = new StringBuilder();
                    list.add(row);
                    row = new ArrayList<>();
                }
            } else if (isArrayElementSeparator(c)) {
                if (open) {
                    row.add(Integer.parseInt(sb.toString()));
                    sb = new StringBuilder();
                }
            } else {
                sb.append(c);
            }
        }
        int[][] result = new int[list.size()][list.get(0).size()];
        for (int r = 0; r < result.length; r++) {
            for (int c = 0; c < result[0].length; c++) {
                result[r][c] = list.get(r).get(c);
            }
        }
        return result;
    }

}
