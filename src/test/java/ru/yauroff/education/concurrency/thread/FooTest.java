package ru.yauroff.education.concurrency.thread;


import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.assertEquals;

public class FooTest {
    Foo foo;

    @Before
    public void setUp() {
        foo = new Foo();
    }

    @Test
    public void runTest() {
        AtomicReference<String> res = new AtomicReference<>("");
        Thread thFirst = new Thread(() -> {
            try {
                foo.first(() -> res.set(res + "first"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thSecond = new Thread(() -> {
            try {
                foo.second(() -> res.set(res + "second"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thThird = new Thread(() -> {
            try {
                foo.third(() -> res.set(res + "third"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thThird.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thSecond.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thFirst.start();

        try {
            thFirst.join();
            thSecond.join();
            thThird.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(res.get(), "firstsecondthird");

    }
}