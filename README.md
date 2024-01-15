# A destructurable Result type for Java 21+!

This library is a simple `Result` sealed interface allowing you to eliminate `null` in all kinds of places in a way that [Rustaceans have enjoyed for a long time](https://doc.rust-lang.org/std/result/)~

Meaning you can do things like this!

```java
if(somethingThatReturnsAResult() instanceof Some(File found)){
    file.delete();
}
```

or this!

```java
return switch(somethingThatReturnsAResult()){
    case Some(String message) -> message;
    case None() -> "Uh oh, nothing??";
};
```

or even this!

```java
return switch(somethingThatReturnsAResult()){
    case Some(val num) when num == 1 -> num + " thing";
    case Some(val num) -> num + " things!";
    case None() -> "Uh oh, nothing??";
};
```

All you need to do is wrap your return value with a `Result.of()`. It even works with wrapped `Optional` values!