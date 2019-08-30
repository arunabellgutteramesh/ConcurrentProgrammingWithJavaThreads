# ConcurrentProgrammingWithJavaThreads
Concurrent Programming with Java Threads. The project imitates the scenario of a company containing 10 departments, making transactions over 50 of its internal accounts.

# Assignment Problem Statement
I was supposed to develop a thread-based Java Program, capable of executing on multiple cores, for the following scenario.

A company wishes to implement a simple accounting system that allows each of its 10 departments to perform the following transactions on its 50 internal accounts.

1. Deposit an amount into a named account.
2. Withdraw an amount from a named account.
3. Transfer an amount between named accounts.

The application must allow for the concurrent transfer between different pairs of accounts when possible. The application must be:

1. correct,
2. fair,
3. have no deadlock, and
4. not have any individual thread "starved".

Application should be able to handle at least 10,000 transactions efficiently.

# Solution and Justification

1. Absence of thread starvation and fairness
One can confidently say that a program doesn’t starve / choke its threads if the threads
are well managed. In my program, I have used the ExecutorService and Executors
interface which manages the threads created fair and square. It queues the tasks that are
yet to be executed until at least one of its threads become free. The thread that becomes
free first takes up the next task in the queue. This ensures absence of thread starvation
and fairness.

2. Absence of deadlocks
I have used synchronized methods to ensure that there is no deadlocks. Upon arrival of
a critical section, synchronized methods helps a thread access the shared resources of a
critical section, synchronously, ensuring other threads don’t gain access to the critical
section until the first thread leaves the said critical section. This way, there will never be
a situation when two or more threads block each other.

3. Correctness
We can say that a program is correct if it is free of both dead locks and thread starvation.
Through points 1 and 2, I can also conclude that my program is correct.

4.  Application works efficiently to loads even higher than 10,000 transactions.  While 
testing, i have seen upto 20,000 transactions.

# Detailed Report

A detailed report along with test cases and screenshots is provided in the repo.
