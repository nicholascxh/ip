# Dasani - Personal Task Manager 🚀

## Introduction
Welcome to **Dasani**, your personal task management assistant! Dasani helps you **track your tasks, deadlines, and events** effortlessly using **simple text commands**. Whether you need to add, mark, delete, or find tasks, Dasani makes organizing your schedule a breeze.

---

## 📌 Table of Contents
1. [Getting Started 🛠️](#getting-started-)
    - [Prerequisites](#1-prerequisites)
    - [Running Dasani](#2-running-dasani)
    - [Exiting Dasani](#3-exiting-dasani)
2. [Features 🎯](#features-)
    - [Viewing the Task List 📋](#1-viewing-the-task-list-)
    - [Adding Tasks ➕](#2-adding-tasks-)
        - [To-Do Task](#1️⃣-to-do-task)
        - [Deadline Task](#2️⃣-deadline-task)
        - [Event Task](#3️⃣-event-task)
    - [Marking Tasks as Done/Undone ✅❌](#3-marking-tasks-as-doneundone-)
    - [Deleting a Task 🗑️](#4-deleting-a-task-)
    - [Finding Tasks 🔍](#5-finding-tasks-)
    - [Saving and Loading Tasks 💾](#6-saving-and-loading-tasks-)
    - [Help 🆘](#7-help-)
3. [Error Handling ⚠️](#error-handling-)
4. [Conclusion 🎉](#conclusion-)

---

## Getting Started 🛠️

### 1. Prerequisites
- Ensure **Java (JDK 11 or later)** is installed on your system.
- Set up a terminal or command prompt to run Java applications.

### 2. Running Dasani
1. Download and run the Dasani program.
2. You should see this image generated:

                                              .......                                               
                                             :::::::...                                             
                                             ::--::::..                                             
                                             ++++==--+=                                             
                                             =-:.....-+                                             
                                         +::.............:+                                         
                                        +-:..............::=                                        
                                       +-:.................-=                                       
                                      ++-:::............::::=+                                      
                                       %#%**#=*%=##=+#*-#%##%                                       
                                       %#%#+=:-++**-.+*#=###%                                       
                                       %%#*++-+==*=-=++=+*#%%                                       
                                       %%#*===:-=+++-::=++#%%                                       
                                       %%##**+++=====++**##%%                                       
                                       %%%++***##++**#****%%%                                       
                                       *+++++***########*****                                       
                                       #-:.................:*                                       
                                      ++-:::.............:--++                                      
                                      +:::.........  .......:=                                      
                                      =--:.......::.......::-+                                      
                                       *%*:......**......:*%+                                       
                                           --::::   ::::-                                           

3. Start entering commands to interact with Dasani!

### 3. Exiting Dasani
- Type `bye` and press **Enter** to exit the program.

---

## Features 🎯

### 1. Viewing the Task List 📋
- Displays all tasks in your list.
- **Command:** `list`

### 2. Adding Tasks ➕
Dasani allows you to add three types of tasks:

#### 1️⃣ To-Do Task
- Adds a simple task without a deadline.
- **Command:** `todo <task description>`
- **Example:** `todo Read a book`

#### 2️⃣ Deadline Task
- Adds a task with a deadline.
- **Command:** `deadline <task description> /by <yyyy-MM-dd HHmm>`
- **Example:** `deadline Submit report /by 2025-10-10 2359`

#### 3️⃣ Event Task
- Adds an event with a start and end time.
- **Command:** `event <task description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>`
- **Example:** `event Project meeting /from 2025-10-15 1400 /to 2025-10-15 1600`

### 3. Marking Tasks as Done/Undone ✅❌
#### ✅ Mark as Done
- Marks a task as **completed**.
- **Command:** `mark <task number>`
- **Example:** `mark 1`

#### ❌ Mark as Not Done
- Unmarks a task (marks it as **not completed**).
- **Command:** `unmark <task number>`
- **Example:** `unmark 1`

### 4. Deleting a Task 🗑️
- Removes a task from the list.
- **Command:** `delete <task number>`
- **Example:** `delete 1`

### 5. Finding Tasks 🔍
- Searches for tasks containing a specific **keyword**.
- **Command:** `find <keyword>`
- **Example:** `find report`

### 6. Saving and Loading Tasks 💾
- **Automatic Saving:** Dasani **automatically** saves tasks upon modification.
- **Manual Save:** You can manually save the task list.
- **Command:** `save`
- **Automatic Loading:** Tasks are **loaded automatically** when the program starts.

---

### 7. Help 🆘
- Displays a list of available commands.
- **Command:** `help`

---

## Error Handling ⚠️
- If you enter an **invalid** command, Dasani will display an **error message**.
- Ensure you use the **correct command format**, especially for dates.
- **Common Mistakes to Avoid:**
    - Forgetting to add `/by` for deadlines or `/from ... /to` for events.
    - Using incorrect date formats (`yyyy-MM-dd HHmm` is required).
    - Using task numbers that don’t exist when marking or deleting tasks.

---

## Conclusion 🎉
Dasani is designed to **simplify task management** and keep you organized. Start using Dasani today and **never miss an important deadline!** 🚀

---