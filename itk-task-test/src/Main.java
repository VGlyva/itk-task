import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        LocalDateTime currentTime = LocalDateTime.now();

        getDataTime(currentTime);
    }

    public static void getDataTime(LocalDateTime inputDataTime) {
        LocalDate toLocalDate = inputDataTime.toLocalDate();
        LocalTime toLocalTime = inputDataTime.toLocalTime();
        String resultLocalDateTime = toLocalDate + "##" + toLocalTime;
        System.out.println(resultLocalDateTime);
    }
}