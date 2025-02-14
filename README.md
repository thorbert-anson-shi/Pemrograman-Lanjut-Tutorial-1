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

# Reflection 2

1. I feel like the unit tests can keep my code functional for more than just initial testing. It can keep my code from
   breaking in the future when the implementation changes. I believe that unit tests should be done on a large enough
   component that its behaviour is predictable, but not so large that smaller bugs can slip through. In order to verify
   that the unit tests are sufficient, they have to be able to detect most if not all the points of error, such as
   where errors are thrown, alongside the positive route that the user should be running.

   Having a code coverage of 100% does not necessarily mean that the code is bug free. Code coverage is a measure of the
   proportion of how much of the code is tested, not the quality of the test itself. If the test quality isn't
   sufficient, then 100% code coverage doesn't guarantee bug-free code.

2. I believe that the code cleanliness would be reduced due to code repetition. I believe that because verifying the
   number of elements would require product creation to function properly anyway, it would be more preferable to build
   off from the already existing test suite. This means abstracting the common elements from both tests, such as the
   setup process and navigation, then placing these features in a separate class that both functional tests would
   implement. This reduces code repetition and enforces separation of concerns, which would increase code quality and
   increase maintainability.