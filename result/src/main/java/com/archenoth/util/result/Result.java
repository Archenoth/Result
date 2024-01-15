package com.archenoth.util.result;

import java.util.Optional;

/**
 * A sealed interface hierarchy to represent to existence or lack of a value,
 * for use in destructuring
 *
 * Returning a Result.of(value) or Result.empty() will give the ability to
 * destructure like:
 *
 * <pre>
 *   if(result instanceof Some(File found)){
 *     // code with the found File in scope
 *   }
 * </pre>
 *
 * Or
 *
 * <pre>
 *   return switch(someOperation()){
 *     Some(String message) -> message;
 *     None() -> "Nothing??";
 *   };
 * </pre>
 *
 * Results can be used directly with {@link Optional}<T> values, present or not,
 * and can be easily converted to and from
 */
public sealed interface Result<T> permits Some, None {
    /**
     * Wraps a value as a Result<T> for use in <code>null</code> removal or destructuring
     * @param <T> the type of the value that will be destructurable
     * @param value the value you wish to wrap (including <code>null</code>!)
     * @return a {@link Some} containing the value if one exists, or a {@link None} if there doesn't
     */
    public static <T> Result<T> of(T value){
        return value == null ? new None<>() : new Some<>(value);
    }

    /**
     * Wraps a value as a Result<T> for use in <code>null</code> removal or destructuring
     * @param <T> the type of the value that will be destructurable
     * @param value the Optional containing the value you wish to wrap
     * @return a {@link Some} containing the value inside the {@link Optional} if one exists,
     * or a {@link None} if there doesn't (or the passed-in {@link Optional} is null itself)
     */
    public static <T> Result<T> of(Optional<T> value){
        if(value == null){
            return new None<>();
        }
        return value.map(Result::of).orElseGet(Result::empty);
    }

    /**
     * @param <T> the type that the None is representing
     * @return a {@link None}, containing information about the type that is absent
     */
    public static <T> None<T> empty(){
        return new None<>();
    }

    /**
     * @return an {@link Optional}-equivalent of this Result type, {@link None} returning an empty
     * {@link Optional}, and a {@link Some} returning one that contains the same wrapped value
     */
    public abstract Optional<T> toOptional();
}
