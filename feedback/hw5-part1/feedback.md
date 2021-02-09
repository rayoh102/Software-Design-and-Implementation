### Written Answers: 18/20

### Design: 3/3

### Documentation & Specification (including JavaDoc): 2/3

### Testing (test suite quality & implementation): 3/3

### Code quality (code stubs/skeletons only, nothing else): 2/3

### Mechanics: 3/3

#### Overall Feedback

Make sure to be specific with your specifications - look at the specific
comments below. It looks like you have the methods you need to get
started on part 2.

#### More Details

The abstraction function for `IntQueue2` ought to be:
```
AF(this) = [..., entries[(i + front) % entries.length], ...]
           for front <= i < size
```

For 1.3c, note that the method is private, so it cannot directly cause
representation exposure.

Fields and private methods are both implementation details, so those are
not part of the specification and should not have been included in this
assignment.

From the assignment spec: "We strongly recommend you DO NOT use Java generics for this assignment. Students who have had an informal introduction to Java generics, and who try to use generics on this assignment, tend to struggle on subsequent assignments."

Why are edges Comparable?

Make sure to cover all the cases - for example, you say when true is returned but not when false is.

What is the toString format?

What is the purpose of the set view of the whole graph? (as opposed to just nodes or edges)
