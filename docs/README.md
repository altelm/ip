# Esm User Guide

![Image of GUI](https://altelm.github.io/ip/Ui.png)

Esm is a CLI chatbot for easy and quick managing of tasks. User can seamlessly add, delete, and view tasks as well as more features mentioned below.

## Feature deadline 

Adds a task with a deadline to your list of tasks.
Format: 'deadline n/name /by d/YYYY-MM-DD
Example: `deadline (finish assignment) /by (2026-02-20)`

ESM confirms the item was added, echoes the task, and shows the size of the new list.

## Feature todo

Adds a task to your list of tasks.
Format: 'todo n/name'
Example: `todo (finish project)`

ESM confirms the item was added, echoes the task, and shows the size of the new list.

## Feature event

Adds a task with a start and end date to your list of tasks.
Format: 'event n/name / d/YYYY-MM-DD / d/YYYY-MM-DD
Example: `event (festival) / (2026-03-04) / (2026-03-05)`

ESM confirms the item was added, echoes the task, and shows the size of the new list.

## Feature mark
Marks a task in your list as done.
Format: 'mark i/index of task'
Example: `mark (2)`

ESM confirms the task was marked done and outputs it. 

## Feature unmark
Unmarks a done task in your list.
Format: 'unmark i/index of task'
Example: `unmark (2)`

ESM confirms the task was unmarked and outputs it. 

## Feature delete
Deletes a task from your list.
Format: 'delete i/index of task'
Example: `delete (2)`

ESM confirms the task was deleted done and outputs it. 

## Feature List
Shows the list of all your tasks.
Format: 'list'

## Feature help
Shows the list of all possible commands you can use and correct format to use them
Format: 'help'

## Feature Find
Finds a task within your list and returns a list of all found matches
* Search is case-sensitive
* Full phrase is searchec, no partial matches are returned
Format: 'find n/name or keyword'
Example: 'find (read)'

ESM shows a list of all possible matches or "No such task was found" if no matches were made.

## Feature sort
Sorts the list in your task alphabetically or according to nearing deadline first.
* Use a for alphabetical sorting.
* Use d for sorting according to nearest deadline. 
Format: 'sort a/d'
Example: 'sort a'

Esm ouptuts the sorted list.

****NOTE**** using sort doesn't modify the saved list!

## Feature bye
Closes the program.
Format 'bye'

## Saving the data
Data is saved in the hard disk only after you enter the bye command. Force quits will lose any progress made. 

