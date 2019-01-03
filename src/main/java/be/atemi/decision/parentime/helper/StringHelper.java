package be.atemi.decision.parentime.helper;

public final class StringHelper {

    public static final String formatTimeslot(int begin, int end) {
        return new StringBuffer(formatTimeslotHour(begin)).append(":00 - ")
                .append(formatTimeslotHour(end)).append(":00")
                .toString();
    }

    private static String formatTimeslotHour(int hour) {
        return String.format("%2s", hour).replace(' ', '0');
    }
}
