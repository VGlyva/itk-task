import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] data = {"ABC", "DEF"};
        FilterIml filter = new FilterIml();
        String[] res = Utils.filter(data, filter);
        System.out.println(Arrays.toString(res));
    }
}