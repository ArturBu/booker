package com.booker.model;

import java.time.LocalDateTime;

public enum AppointmentRange {


    MIN_15 {
        @Override
        public boolean isValidTime(final LocalDateTime time) {
            //TODO
            return false;
        }

        @Override
        public int getAppointmentLength() {
            return 15;
        }

        @Override
        public LocalDateTime setToNextValidTime(LocalDateTime time) {
            //TODO
            return null;
        }
    },
    MIN_30 {
        @Override
        public boolean isValidTime(final LocalDateTime time) {
            if (isValidWorkingHours(time)) {
                //is valid minutes range
                if (time.getMinute() == 0 || time.getMinute() == 30) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int getAppointmentLength() {
            return 30;
        }

        @Override
        public LocalDateTime setToNextValidTime(LocalDateTime time) {
            while (true) {
                if (!isValidWorkingHours(time)) {
                    time = time.plusDays(1).withHour(START_WORK_HOUR).withMinute(0);
                }
                if (time.getMinute() == 0 || time.getMinute() == 30) {
                    return time;
                }
                time = time.plusMinutes(1);
            }
        }
    };

    public static int START_WORK_HOUR = 9;
    public static int END_WORK_HOUR = 17;

    public abstract boolean isValidTime(final LocalDateTime time);

    public abstract int getAppointmentLength();

    public abstract LocalDateTime setToNextValidTime(final LocalDateTime time);

    public static boolean isValidWorkingHours(final LocalDateTime time) {
        return time.getHour() >= START_WORK_HOUR && time.getHour() < END_WORK_HOUR;
    }
}
