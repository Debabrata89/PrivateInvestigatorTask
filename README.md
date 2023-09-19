# PrivateInvestigatorTask
**How to run ?**<br>
Ans : PrivateInvestigator.java has main method. Run this class to get the output.Before running empty the output.txt file.<br>
Open the project in any editor like intelliJ . Build the project and run PrivateInvestigator.java
**1. What can you say about the complexity of your code?**<br>
Ans : Basically we are generating new sentence by removing one word and putting this new sentence as HashMap key. We  grouping them as we read sentences.<br>
Therefore **time complexity** is O(nk) where n is the number of sentences and K is number of words.
**Space complexity** : O(s) where s is the size of HasMap<br>
**2. How will your algorithm scale?**
The algorithem would scale well given the time complexity of O(nk).<br>
 But if the file size is very big read/write operation could be a bottleneck. Reading and writing can be parallelized with multithreading to improve performance.<br>
**3. If you had two weeks to do this task, what would you have done differently? What would be
better?**<br>
Would have explored other algorithm for creating diff alorithm like Mayer's algorithm .
Also implemented using some design pattern. Better error handling and logging.
