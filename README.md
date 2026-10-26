# Compilation
Compile in terminal with:
> javac ToDoList.java
> java ToDoList.java

# How it works
The program will ask you to write the name of a file when you run it. 

If it does not exist, it will create the file. If it does, it will copy the text inside into an array. 

This file will act as a permanent storage for the array so that you may modify or remove tasks.

# Current bugs
- Lack of input validation leads to crashes
- Does not check for negative integers