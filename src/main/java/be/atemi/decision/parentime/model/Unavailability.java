package be.atemi.decision.parentime.model;

public class Unavailability {

    private UnavailabilitySlot from;
    private UnavailabilitySlot to;
    private UnavailabilityType type;

    private Unavailability(UnavailabilitySlot from, UnavailabilitySlot to) {
        this.from = from;
        this.to = to;
    }

    public static ToUnavailabilitySlot from(int day, int timeslot) {
        return new ToUnavailabilitySlot(day, timeslot);
    }

    public static class ToUnavailabilitySlot {

        UnavailabilitySlot from;

        private ToUnavailabilitySlot(int day, int timeslot) {
            from = new UnavailabilitySlot(day, timeslot);
        }

        public Unavailability to(int day, int timeslot) {
            UnavailabilitySlot to = new UnavailabilitySlot(day, timeslot);
            return new Unavailability(from, to);
        }

    }

    public static class UnavailabilitySlot {
        private int day;
        private int timeslot;

        public UnavailabilitySlot(int day, int timeslot) {
            this.day = day;
            this.timeslot = timeslot;
        }
    }

    public Unavailability occassional() {
        type = UnavailabilityType.OCCASIONAL;
        return this;
    }

    public Unavailability recurrent() {
        type = UnavailabilityType.RECURRENT;
        return this;
    }

    public enum UnavailabilityType {
        OCCASIONAL, RECURRENT
    }

    @Override
    public String toString() {
        return String.format("%s unavailability from day %s at slot %s to day %s at slot %s.", type.toString().toLowerCase(), from.day, from.timeslot, to.day, to.timeslot);
    }
}
