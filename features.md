# Possible feature decomposition.

- Author: Sébastien Mosser

## Minimal and Viable product

- F1: Cross a minimal maze from left to right 
  - A minimal maze is a 3x3 maze with one straight path in the middle
  - Task 1.1: read the maze from a file
  - Task 1.2: find the entry point
  - Task 1.3: find the exit point
  - Task 1.4: go from the entry point to the exit point (i.e., produce FFF)
  - Task 1.5: print the canonical path to stdout

The contents of this feature are identified as tasks, as, independently, finding the entry point does not bring any value to the end user. Crossing the maze brings value, so we're focusing as a feature on crossing the most minimal situation, while being viable.

That should give a walking skeleton composed of `Maze` (responsibilities of getting entry and exit points, know which tile is of which kid), `MazeReader` (to build the maze from the file), `Path` (is it really necessary?), `MazeExplorer` to build a path in a naive way by moving forward until we reach the exit point.

## Mandatory Features

- F2: Produce a factorized path
  - 2 options: factorizing a canonical path into a factorized one, or directly build the factorized one while building it.

- F3: Cross "turn-right" mazes
  - a "cross-right" maze is a specific maze where you can find the exit by always turning right, and no U-turns involved.
  - We can modify the explorer defined for feature 1, or we can create a new class and reify a common interface of `MazeExploration` to switch easily from the previous "straight" version to this one.

- F4: Verify path for a given maze, left to right
  - for a given path and a given maze, check that the path is correct, from left to right

- F5: Verify path for a given maze, right to left
  - same as F4

- F6: Verify path for a given maze, regardless of the direction
  - try both left to right and right to left.

Note: F4, F5 are features and not tasks because they bring value to the user, even if not the "final" one. If we model F6 including two subtasks, we'll only deliver "both" or "nothing", while considering F4, then F5 and finally F6 allows us to increase the delivered value incrementally.

- F7: Cross arbitrary (but still perfect) maze
  - cross any perfect maze produced by the maze generator.

Feature prioritization:
  1. I would develop it by focusing first on F3 (to check that I can turn right) and F4 (to check that a path can be validated). A product containing F1, F3 and F4 is a good candidate for a second release, as it covers all the specs, but on a subset of cases: we can cross mazes (not any arbitrary maze, but still, we can cross), and we can validate (only one direction, but still, we can validate).
  2. I'd then focus on solving an arbitrary maze (F7). that'd be a solid candidate for a third release, bringing maximal value to end-user.
  3. I'll polish my product by adding F2, F5 and F6, which are useful but not critical.

## Bonus Features

- F8: User-friendly paths.
  - print paths by adding extra spaces (FFLFF -> FF L FF) so that the user can read them easily.

- F9: Tremaux's exploration
  - We'll need a MazeExploration interface to switch from Turnright to the other easily.
  