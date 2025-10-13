import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Integer[] numbers = {1, 2, 1, 3, 4, 4};

        Map<Integer, Integer> result = getCountMap(numbers);

        System.out.println(result);
    }

    public static <T> Map<T, Integer> getCountMap(T[] array) {
        Map<T, Integer> countMap = new HashMap<>();
        for (T num : array) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        return countMap;
    }
}