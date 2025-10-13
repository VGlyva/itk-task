import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static <T> T[] filter(T[] array, Filter<T> filterImpl) {
        List<T> result = new ArrayList<>();
        for (T item : array) {
            result.add(filterImpl.apply(item));
        }

        @SuppressWarnings("unchecked")
        T[] resultArray = (T[]) Array.newInstance(array.getClass().getComponentType(), result.size());
        return result.toArray(resultArray);
    }
}
