package dev.spider.boot.config;

/**
 * @author spider
 */
public class AppMetric {
    private String appName;
    private String appDate;
    private String random;


    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }
}
