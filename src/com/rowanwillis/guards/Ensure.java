package com.rowanwillis.guards;

public class Ensure {

    private static final String DefaultName = "Value";

    public static Guard<Object> that(Object value, String name)
    {
        return new Guard<Object>(value, name);
    }

    public static Guard<Object> that(Object value)
    {
        return that(value, DefaultName);
    }

    public static StringGuard thatString(String value, String name)
    {
        return new StringGuard(value, name);
    }

    public static StringGuard thatString(String value)
    {
        return thatString(value, DefaultName);
    }
}
