package org.example.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.String.format;

public class Interval {

    private static final Logger LOGGER = LogManager.getLogger(Interval.class);

    private final Integer start;
    private final Integer end;

    public static Interval interval(int start, int end) {
        return new Interval(start, end);
    }

    private Interval(int start, int end) {
        if (start > end) {
            String errorMessage = format("start %d cannot be greater than end %d", start, end);
            LOGGER.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public boolean overlaps(final Interval other) {
        return this.start.compareTo(other.getEnd()) <= 0 &&
                this.end.compareTo(other.getStart()) >= 0;
    }

    public boolean isBefore(final Interval other) {
        return this.end < other.start;
    }

    public boolean isAfter(final Interval other) {
        return this.start > other.end;
    }

    public Interval merge(final Interval other) {
        return interval(min(this.start, other.start), max(this.end, other.end));
    }

    @Override
    public String toString() {
        return "[" +
                start +
                "," + end +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Interval interval = (Interval) o;
        return Objects.equals(start, interval.start) &&
                Objects.equals(end, interval.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
