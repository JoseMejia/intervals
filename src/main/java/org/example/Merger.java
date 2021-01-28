package org.example;

import org.example.model.Interval;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.example.model.Interval.interval;

public class Merger {

    public static List<Interval> merge(final Stream<Interval> intervalStream) {
        return List.of();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter amount of intervals - ");
        int n = sc.nextInt();
        final List<Interval> intervals = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            System.out.println("Interval " + i);
            System.out.print("Enter start ");
            int start = sc.nextInt();
            System.out.print("Enter end ");
            int end = sc.nextInt();
            intervals.add(interval(start, end));
        }

        System.out.println("The merged intervals are: " + merge(intervals.stream()));
    }
}
