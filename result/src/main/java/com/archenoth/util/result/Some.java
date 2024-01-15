package com.archenoth.util.result;

import java.util.Optional;

/**
 * A destructurable record type indicating the existence of a value
 */
public record Some<T>(T value) implements Result<T> {
    @Override
    public Optional<T> toOptional() {
        return Optional.of(value);
    }
}
