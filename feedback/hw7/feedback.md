### Design: 3/3

### Documentation & Specification (including JavaDoc): 3/3

### Code quality (code and internal comments including RI/AF when appropriate): 3/3

### Testing (test suite quality & implementation): 3/3

### Mechanics: 3/3

#### Overall Feedback

Good work this week! Make sure to take a look at my comments for next week! Keep it up!

#### More Details
- We want to document what our type parameters refer to. The class comment of `Graph` and `Path`
should have some `@param` tags.

- The `LabelEdge` class should be an inner class of `Graph`. We don't want to spread a single
ADT across multiple files.

- Your fields in `CampusMap` should be declared as `private` to ensure total encapsulation.

- The `CampusMap` should not expose any graph specific implementation details,
since ideally our graph implementation should be able to be swapped out for some
other backend, like a table of shortest paths, or a `Google Maps` client.

- You never refer to `shortNames`, `paths`, or `buildings` at all outside of the constructor, you
should consider if you truly need these as persistent fields.

- Your `CampusMap` ADT is missing a `checkRep`.

- Overriden methods do not need to be redocumented since the javadoc is inherited.

- Your `createGraph` function is missing documentation.

- We also want to include implementation tests of our graph building operation in order to make
sure our set up is correct.
