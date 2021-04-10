package ru.yauroff.education.concurrency.thread;


import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;

public class FizzBuzzTest {
    FizzBuzz fizzBuzz;

    @Before
    public void setUp() {
        fizzBuzz = new FizzBuzz();
    }

    @Test
    public void runTest() {
        AtomicReference<String> res = new AtomicReference<>("");
        fizzBuzz.setN(15);
        Thread th1 = new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> res.set(res + "buzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread th2 = new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> res.set(res + "fizz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread th3 = new Thread(() -> {

            try {
                fizzBuzz.fizzbuzz(() -> res.set(res + "fizzbuzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread th4 = new Thread(() -> {

            try {
                fizzBuzz.number((int i) -> res.set(res + String.valueOf(i)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        th1.start();
        th2.start();
        th3.start();
        th4.start();

        try {
            th1.join();
            th2.join();
            th3.join();
            th4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(res.get(), "12fizz4buzzfizz78fizzbuzz11fizz1314fizzbuzz");
    }
}