### Written Answers: 5/6
- testBaseCase failed for the same reason that testThrowsIllegalArgumentException
did, but for no other reason.  The fix identified for testBaseCase belongs as a
fix for testInductiveCase instead.

### Code Quality: 3/3

### Mechanics: 3/3

### General Feedback
- When selecting a greeting in `RandomHello`, the best style would use the length
of the array to specify the maximum value for the random integer generation:
```
String nextGreeting = greetings[rand.nextInt(greetings.length)];
```
Notice how this benefits us later on if we wanted to change the number of
possible greetings in the array.

### Specific Feedback
- Your BallContainer size methods is more complicated than they need to be. You
can simply return size using `Set.size()`

- Your BallContainer contains methods is more complicated than they need to be.
Look at the documentation for `Set.contains()` and try using these library functions
instead.

- Do not declare variables with types like `TreeSet`.  Instead, just use the
more general type of `Set`.  This will allow you to later switch out the more
specific implementation in the variable assignment rather than having to mess
with the variable type as well.

- The standard style for class names is PascalCase with an upper case first
letter, not camelCase. The name `ballComparator` violates this style.

