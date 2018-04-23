package cn.edu.hust.logMonitor.exception;

import cn.edu.hust.logMonitor.bean.RuleRecord;

public class LogException extends Exception {

    private String message;
    private RuleRecord record;

    public LogException()
    {

    }

    public LogException(String message)
    {
        this.message=message;
    }

    public LogException(String message,RuleRecord record)
    {
        this.message=message;
        this.record=record;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public RuleRecord getRecord() {
        return record;
    }

}
