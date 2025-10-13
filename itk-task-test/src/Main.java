import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        LocalDateTime currentTime = LocalDateTime.now();
        getDataTime(currentTime);

        SnapShotStringBuilder snapShotStringBuilder = new SnapShotStringBuilder();
        snapShotStringBuilder.append("Clean");
        System.out.println(snapShotStringBuilder);

        snapShotStringBuilder.append(" code");
        System.out.println(snapShotStringBuilder);

        snapShotStringBuilder.delete(5, 10);
        System.out.println(snapShotStringBuilder);

        snapShotStringBuilder.delete(0, 5);
        System.out.println(snapShotStringBuilder);

        snapShotStringBuilder.undo();
        System.out.println(snapShotStringBuilder);

        snapShotStringBuilder.undo();
        System.out.println(snapShotStringBuilder);

        snapShotStringBuilder.undo();
        System.out.println(snapShotStringBuilder);

        snapShotStringBuilder.undo();
        System.out.println(snapShotStringBuilder);

        snapShotStringBuilder.undo();
        System.out.println(snapShotStringBuilder);

    }

    // Task one
    public static void getDataTime(LocalDateTime inputDataTime) {
        LocalDate toLocalDate = inputDataTime.toLocalDate();
        LocalTime toLocalTime = inputDataTime.toLocalTime();
        String resultLocalDateTime = toLocalDate + "##" + toLocalTime;
        System.out.println(resultLocalDateTime);
    }
}