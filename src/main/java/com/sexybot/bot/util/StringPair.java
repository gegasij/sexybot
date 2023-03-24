package com.sexybot.bot.util;

public class StringPair {
    private final Pair<String, String> pair;

    public StringPair(String first, String second) {
        this.pair = Pair.of(first, second);
    }

    public static StringPair of(String first, String second) {
        return new StringPair(first, second);
    }

    public String getFirst() {
        return pair.getFirst();
    }

    public String getSecond() {
        return pair.getSecond();
    }
}
