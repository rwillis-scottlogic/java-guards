package com.rowanwillis.guards;

public class StringGuard extends Guard<String> {
    StringGuard(String value, String name) {
        super(value, name);
    }

    public StringGuard isNotEmpty()
    {
        if (getValue() == "")
            throw new IllegalArgumentException(getName() + " must not be empty.");

        return this;
    }

    public StringGuard hasLength(int length)
    {
        final var value = getValue();

        if (value == null || value.length() != length)
            throw new IllegalArgumentException(getName() + " must have length " + length + ".");

        return this;
    }

    public StringGuard hasLengthAtMost(int maxLength)
    {
        final var value = getValue();

        if (value != null && value.length() > maxLength)
            throw new IllegalArgumentException(getName() + " must have length at most " + maxLength + ".");

        return this;
    }

    public StringGuard hasLengthAtLeast(int minLength)
    {
        final var value = getValue();

        if (value == null || value.length() < minLength)
            throw new IllegalArgumentException(getName() + " must have length at least " + minLength + ".");

        return this;
    }
}
