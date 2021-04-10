package ru.yauroff.education.concurrency.thread;

import java.util.function.IntConsumer;

/**
 * 1195. Fizz Buzz Multithreaded (https://leetcode.com/)
 */
class FizzBuzz {
    private int n;
    private int currN = 1;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public synchronized void fizz(Runnable printFizz) throws InterruptedException {
        while (currN <= n) {
            while (!(currN % 3 == 0 && currN % 5 != 0) && currN <= n)  {
                wait();
            }
            if (currN <= n) {
                printFizz.run();
                currN += 1;
                notifyAll();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
        while (currN <= n) {
            while (!(currN % 3 != 0 && currN % 5 == 0) && currN <= n)  {
                wait();
            }
            if (currN <= n) {
                printBuzz.run();
                currN += 1;
                notifyAll();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        while (currN <= n) {
            while (!(currN % 3 == 0 && currN % 5 == 0) && currN <= n)  {
                wait();
            }
            if (currN <= n) {
                printFizzBuzz.run();
                currN += 1;
                notifyAll();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void number(IntConsumer printNumber) throws InterruptedException {
        while (currN <= n) {
            while (!(currN % 3 != 0 && currN % 5 != 0) && currN <= n)  {
                wait();
            }
            if (currN <= n) {
                printNumber.accept(currN);
                currN += 1;
                notifyAll();
            }
        }
    }
}
