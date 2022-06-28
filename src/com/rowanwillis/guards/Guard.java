package com.rowanwillis.guards;

public class Guard<TValue> {

    private TValue value;
    private String name;

    Guard(TValue value, String name)
    {
        this.value = value;
        this.name = name;
    }

    public TValue getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public Guard<TValue> isNotNull()
    {
        if (value == null)
            throw new IllegalArgumentException(getName() + " must not be null.");

        return this;
    }
}
