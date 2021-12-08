package dev.spider.gw.hook;

import org.apache.commons.lang3.tuple.Pair;

public class ReqSidCar {

    private final static ThreadLocal<Pair<String, Class>> tl = new ThreadLocal<>();

    public static void put(Pair<String, Class> errAndServiceClass) {
        tl.set(errAndServiceClass);
    }

    public static Pair<String, Class> get() {
        return tl.get();
    }

    public static void clear() {
        tl.remove();
    }

    public static boolean isPass() {
        return tl.get().getLeft().isBlank();
    }
}