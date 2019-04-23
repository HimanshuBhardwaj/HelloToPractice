package com.himanshu.practice.y2019.April.april4;

public interface Logger {
    enum Level{INFO, DEBUG, ERROR; }
    void logMessage(String message, Level logLevel);
}
