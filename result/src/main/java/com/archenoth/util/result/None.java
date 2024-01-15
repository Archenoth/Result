package com.archenoth.util.result;

import java.util.Optional;

/**
 * A record type indicating the absence of a wrapped value
 */
public record None<T>() implements Result<T> {
    @Override
    public Optional<T> toOptional() {
        return Optional.empty();
    }
}
