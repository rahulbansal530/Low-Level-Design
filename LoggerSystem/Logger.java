package LoggerSystem;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


enum LogLevel {
    INFO, WARN, ERROR
}


// strategy design pattern
interface LogSink {
    void log(String message);
}

class ConsoleLogSink implements LogSink{

    @Override
    public void log(String message) {
        System.out.println(message);
    }
}

class FileLogSink implements LogSink {

    private String filename;

    public FileLogSink(String filename) {
        this.filename = filename;
    }

    @Override
    public void log(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename,true))) {
            writer.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Chain of Responsibility design pattern
abstract class LogProcessor {

    LogSink logSink;
    LogProcessor nextLogProcessor;

    public LogProcessor(LogProcessor nextLogProcessor, LogSink logSink) {
        this.logSink = logSink;
        this.nextLogProcessor = nextLogProcessor;
    }

    public void log(LogLevel logLevel, String message) {
        if (nextLogProcessor != null) {
            logSink.log(message);
        } else {
            System.out.println("not suported.");
        }
    }
    
}

class InfoLogProcessor extends LogProcessor{

    public InfoLogProcessor(LogProcessor nextLogProcessor, LogSink logSink) {
        super(nextLogProcessor, logSink);
    }

    @Override
    public void log(LogLevel logLevel, String message) {
        if (LogLevel.INFO.equals(logLevel)) {
            String logMessage = String.format("[%s] : %s", logLevel, message);
            logSink.log(logMessage);
        } else {
            nextLogProcessor.log(logLevel, message);
        }
    }
    
}

class ErrorLogProcessor extends LogProcessor{

    public ErrorLogProcessor(LogProcessor nextLogProcessor, LogSink logSink) {
        super(nextLogProcessor, logSink);
    }

    @Override
    public void log(LogLevel logLevel, String message) {
        if (LogLevel.ERROR.equals(logLevel)) {
            String logMessage = String.format("[%s] : %s", logLevel, message);
            logSink.log(logMessage);
        } else {
            nextLogProcessor.log(logLevel, message);
        }
    }
}

class WarnLogProcessor extends LogProcessor{

    public WarnLogProcessor(LogProcessor nextLogProcessor, LogSink logSink) {
        super(nextLogProcessor, logSink);
    }

    @Override
    public void log(LogLevel logLevel, String message) {
        if (LogLevel.WARN.equals(logLevel)) {
            String logMessage = String.format("[%s] : %s", logLevel, message);
            logSink.log(logMessage);
        } else {
            nextLogProcessor.log(logLevel, message);
        }
    }
}


// singleton design pattern
public class Logger {
    private static Logger instance;
    private LogSink logSink;
    private LogLevel logLevel;

    private Logger() {
        this.logSink = new ConsoleLogSink(); // Default log sing
        this.logLevel = LogLevel.INFO; // Default log level
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void setLogSink(LogSink logSink) {
        this.logSink = logSink;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    public void log(LogLevel level, String message) {
        String logMessage = String.format("[%s] : %s", level, message);
        logSink.log(logMessage);
    }
}

class Main {
    public static void main(String[] args) {
//        LogProcessor logProcessor = new InfoLogProcessor(new De, null);
    }
}