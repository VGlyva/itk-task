public class FilterIml implements Filter<String> {
    @Override
    public String apply(String s) {
        return s.toLowerCase();
    }
}
