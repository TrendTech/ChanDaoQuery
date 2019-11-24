package com.ChanDao.model;

/**
 * Author ajiang
 * Created 2019/11/24 10:12
 */
public class Severity {
    private int severity;
    private int summary;

    public void display() {
        System.out.println( "Severity{" +
                "severity=" + severity +
                ", summary=" + summary +
                '}');
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public int getSummary() {
        return summary;
    }

    public void setSummary(int summary) {
        this.summary = summary;
    }

    public Severity(){

    }
    public Severity(int severity, int summary) {
        this.severity = severity;
        this.summary = summary;
    }
}
