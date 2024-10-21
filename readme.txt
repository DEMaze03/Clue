Team Names:
Elijas Sliva
Daylon Maze

Test Case Changes:

testCalcTarget():
Cells (0,4) and (-1,3) are outside of the game which would cause this test to fail every time because the target
cells would never contain those.

NUM_ROWS and NUM_COLUMNS were originally 27 and 33 but were changed to 28 and 34 because
we forgot that we had our board as 0 index so they were off by one.

testCellInitial(): the 4th test the cell location was changed to a cell that had an upward door
in order to properly test if it was upwards. Also changed room name from Doorway to walkway because there is no doorway in our setup file

TestTargetsInDoorway()/TestTargetsInMarquez()/TestTargetsOccupied: Misunderstanding in how moving between secret passages/ entering a room would end a turn led to some incorrectly written target tests. These were updated to accurately reflect how the target calculation properly works.
