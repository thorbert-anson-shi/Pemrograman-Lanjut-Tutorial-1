# Reflection 1

## Summary

I believe I have written reasonably clean code. The code I've written does its job decently well, and tries to avoid any
major pitfalls that might affect maintainability and reliability of the code.

## Positives

### Separation of concerns

My codebase has its components (model, controller, views) separated into different files, and each section can function
independently of one another.

### Handling of null values

My codebase makes sure to throw errors whenever a null value might be returned from a function. This prevents
NullPointerExceptions and frustrating debugging.

### Not repeating oneself

My codebase minimizes self repetition by using helper functions where code is repeated. This minimizes code repetition
and makes it more readable.

### Declarative programming style

Instead of an imperative style of programming which might be prone to bugs and logical errors, a more declarative
approach is used to make code more readable and understandable.

### Assigning single tasks to functions

Each function handles one task, and one task only. Bigger functions can then be built from these smaller functions.

### Meaningful symbols

Variable and function names are representative of what they are or do, respectively.

## Negatives

### Improper error display

The errors are still being handled by a global handler, and being redirected every time a form receives a misinput is
not necessarily the best user experience.

Apart from this, I'm afraid I might be blindsided to the flaws of the code. I look forward to learning more, and looking
back to find more errors in this program.

