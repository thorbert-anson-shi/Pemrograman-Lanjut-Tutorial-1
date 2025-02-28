# Module 1

## Reflection 1

### Summary

I believe I have written reasonably clean code. The code I've written does its job decently well, and tries to avoid any
major pitfalls that might affect maintainability and reliability of the code.

### Positives

#### Separation of concerns

My codebase has its components (model, controller, views) separated into different files, and each section can function
independently of one another.

#### Handling of null values

My codebase makes sure to throw errors whenever a null value might be returned from a function. This prevents
NullPointerExceptions and frustrating debugging.

#### Not repeating oneself

My codebase minimizes self repetition by using helper functions where code is repeated. This minimizes code repetition
and makes it more readable.

#### Declarative programming style

Instead of an imperative style of programming which might be prone to bugs and logical errors, a more declarative
approach is used to make code more readable and understandable.

#### Assigning single tasks to functions

Each function handles one task, and one task only. Bigger functions can then be built from these smaller functions.

#### Meaningful symbols

Variable and function names are representative of what they are or do, respectively.

### Negatives

#### Improper error display

The errors are still being handled by a global handler, and being redirected every time a form receives a misinput is
not necessarily the best user experience.

Apart from this, I'm afraid I might be blindsided to the flaws of the code. I look forward to learning more, and looking
back to find more errors in this program.

## Reflection 2

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

# Module 2

Check out the application deployed on Koyeb [here](https://corresponding-shaylah-thorbert-advpro-1b5743d8.koyeb.app/)

## Reflection

1. I received a bunch of warnings that my code contained code duplication. This was because in my
   GlobalExceptionHandler, I repetitively returned the string of the error template. I resolved this by defining the
   name of the template in a variable, and then referring to the variable instead. Although a simple fix, this is good
   practice, as it ensures that if I were to change my error template in the future, then I would only have to change
   the name of the variable itself.
2. I believe that it does fulfill the definition of CI/CD in its most basic form. For CI, we can continuously develop
   and integrate changes to the code while making sure that code quality is maintained by using the provided code
   scanning tools. For CD, the platform itself (Koyeb), provides auto-deploy on-push features that allow automated
   deployment of the application as updates are made to the codebase.

# Module 3

## Reflection

1. My project attempts to best follow SOLID principles. It adheres to the Single Responsibility Principle (SRP) by ensuring each class has a well-defined role—controllers handle HTTP requests, services contain business logic, and repositories manage data access. Furthermore, I've separated the controllers into view controllers and API controllers for more granulated separation of concern. Additionally, the Interface Segregation Principle (ISP) ensures that each interface defines only the necessary methods, preventing unnecessary dependencies.

   At this point in the project, the Liskov Substitution Principle doesn't really need to hold, as subtyping still isn't happening frequently if at all (no interfaces seem to be related in any meaningful way). Meanwhile, the Dependency Inversion Principle (DIP) is applied by making controllers depend on interfaces rather than concrete implementations. This is achieved through injection of the necessary dependencies, reducing tight coupling and improving flexibility.

2. Generally, applying SOLID principles when building an application improves maintainability, scalability, and flexibility in the long run. For example, the Single Responsibility Principle (SRP) ensures that `CarServiceImpl` only manages car-related operations, making debugging and updates to a particular component easier. The Open-Closed Principle (OCP) allows adding new features, like a `BikeServiceImpl`, without modifying existing services, instead utilizing the common contract (interface) shared by `BikeService` and `CarService` to ensure compatibility. Dependency Inversion Principle (DIP) enables flexible dependency injection with, making unit testing easier by allowing mock dependencies. These principles promote clean architecture, reducing bugs and simplifying future modifications.

3. Ignoring SOLID leads to rigid and tightly coupled code, making modifications difficult. For example, if `CarController` directly instantiated `CarServiceImpl` instead of depending on an interface, replacing or extending functionalities would require modifying multiple files. Without SRP, mixing database logic inside controllers would make them harder to understand, test, and maintain. Ignoring Liskov Substitution Principle (LSP) (e.g., forcing `CarController` to extend `ProductController`) could break existing functionality when substitutions fail. Tightly coupled and unstructured code increases technical debt, making the project difficult to scale or refactor. In the long run, this results in higher maintenance costs and more bugs.