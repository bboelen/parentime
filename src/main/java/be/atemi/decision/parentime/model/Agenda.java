package be.atemi.decision.parentime.model;

import org.apache.commons.lang3.Range;

import java.security.InvalidParameterException;

public class Agenda implements Timable {

    private final int days;
    private final int timeslots;

    private final Range<Integer> dRange;
    private final Range<Integer> tRange;

    private Entry[][] entries;

    public Agenda(int days, int timeslots) {
        this.days = days;
        this.timeslots = timeslots;
        this.dRange = Range.between(0, days - 1);
        this.tRange = Range.between(0, timeslots - 1);
        initEntries();
    }

    private void initEntries() {
        entries = new Entry[days][timeslots];
        for (int d = 0; d < days; d++) {
            for (int t = 0; t < timeslots; t++) {
                entries[d][t] = Entry.AVAILABILITY;
            }
        }
    }

    public void setEntry(int day, int timeslot, Entry entry) {
        checkParametersValidity(day, timeslot);
        entries[day][timeslot] = entry;
    }

    public Entry getEntry(int day, int timeslot) {
        checkParametersValidity(day, timeslot);
        return entries[day][timeslot];
    }

    private void checkParametersValidity(int day, int timeslot) {
        if (!(dRange.contains(day) || tRange.contains(timeslot))) {
            throw new InvalidParameterException(String.format("Parameters not in range !"));
        }
    }

    public enum Entry {

        AVAILABILITY(0), FLEXIBLE_UNAVAILABILITY(1), FIXED_UNAVAILABILITY(2);

        private int internalValue;

        Entry(int internalValue) {
            this.internalValue = internalValue;
        }

        public int value() {
            return internalValue;
        }

        public static Entry from(String value) {
            for (Entry e : values()) {
                if (value.equals(e.toString())) {
                    return e;
                }
            }
            return null;
        }
    }

    public int days() {
        return this.days;
    }

    public int timeslots() {
        return this.timeslots;
    }
}
