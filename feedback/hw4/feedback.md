### Written Answers: 23/26

#### 2.2
In part 2b, the changes required are limited to the following methods:
checkRep, equals, toString, getExpt, and hashCode. (-1)

#### 3.1
Immutability is a property of the specification, and checkRep does not assume
the specification was correctly implemented.  So, in general, regardless of
whether or not they are immutable, ADTs need calls to checkRep at the
beginning and end of all public methods. (-2)

### Code Quality: 2/3

Missing calls to checkRep at the beginning and end of every public method in RatPoly and/or RatPolyStack.

Please remove TODO after implementing the methods.

Missing inline comment on complicated code such as sortedInsert and div.

Missing invariant

### Mechanics: 3/3

### General Feedback

### Specific Feedback

