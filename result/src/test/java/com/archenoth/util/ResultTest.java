package com.archenoth.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.archenoth.util.result.None;
import com.archenoth.util.result.Result;
import com.archenoth.util.result.Some;

class ResultTest {
    @Test void resultOfDispatchTest() {
        assertTrue(
            Result.of(null) instanceof None,
            "Result of null should be a None"
        );
        assertTrue(
            Result.of(1) instanceof Some,
            "Result of 1 should be a Some"
        );
        assertTrue(
            Result.of(Optional.empty()) instanceof None,
            "Empty Optional should be a None"
        );
        assertTrue(
            Result.of(Optional.of(1)) instanceof Some,
            "Optional containing value should be a Some"
        );
    }

    @Test void destructureTest() {
        assertTrue(
            Result.of(1) instanceof Some(var value) && value == 1,
            "Result of value should destructure to Some(<value>)"
        );
        assertTrue(
            Result.of(Optional.of(1)) instanceof Some(var value) && value == 1,
            "Result of Optional value should destructure to Some(<wrapped optional value>)"
        );

        assertTrue(
            switch(Result.of(1)){
                case Some(var val) -> val == 1;
                case None() -> false;
            },
            "switch expression should destructure Result.of a value to Some(<that value>)"
        );
        assertTrue(
            switch(Result.of(Optional.of(1))){
                case Some(var val) -> val == 1;
                case None() -> false;
            },
            "switch expression should destructure Result.of an Optional value to Some(<the value inside the optional>)"
        );
    }

    @Test void toOptionalTest() {
        assertTrue(
            Result.of(1).toOptional().isPresent(),
            "Result of value should turn into an Optional containing a value"
        );
        assertTrue(
            Result.of(null).toOptional().isEmpty(),
            "Result of null should turn into an Optional containing nothing"
        );
        assertTrue(
            Result.of(Optional.of(1)).toOptional().isPresent(),
            "Result of an Optional should turn into an Optional containing a value"
        );
        assertTrue(
            Result.of(Optional.empty()).toOptional().isEmpty(),
            "Result of an empty Optional should turn into an Optional containing nothing"
        );

        assertTrue(
            Result.of(1).toOptional().filter(val -> val == 1).isPresent(),
            "Result of value should turn into an Optional containing the *original* value"
        );
        assertTrue(
            Result.of(Optional.of(1)).toOptional().filter(val -> val == 1).isPresent(),
            "Result of an Optional should turn into an Optional containing the *original* value"
        );
    }

    @Test void switchGuardTest(){
        assertTrue(
            switch(Result.of(1)){
                case Some(var val) when val == 1 -> true;
                case Some(var val) -> false;
                case None() -> false;
            },
            "switch expression should be able to guard with destructured value"
        );
        assertTrue(
            switch(Result.of(Optional.of(1))){
                case Some(var val) when val == 1 -> true;
                case Some(var val) -> false;
                case None() -> false;
            },
            "switch expression should be able to guard with destructured value (from within an Optional)"
        );

    }
}
