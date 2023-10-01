# Budgeting Application
## CPSC 210 Jacky Feng 

***Project Description:*** My project is an application that will **help users manage their personal finances** by allowing them to create and manage budgets.
<br> Users will be able to input and categorize their revenues and expenses into personalized groups which will allow them to **visualize how much they spend, save, and take action**. <br>
In addition, users will be able to set up financial goals such as paying off student loans, mortgages, and bond amortization.

***Target audience:*** My application would be targeted towards **people who need extra assistance in financial planning or want to visualize their savings, expenditures, and financial goals**.
<br>An example would be graduating university students who want to visualize how much student debt they owe and how long it would take to pay it off given x revenues

This project is of interest to me as I have both seen and experienced how expensive it is to be a university student especially with rising food prices and housing costs.
<br>*Through this application I would be able to make better financial choices along with planning for the future*.

## User Stories:

- As a user, I want to be able to enter my revenues and expenses 
- As a user, I want to be able to categorize my revenues and expenditure into different custom groups 
- As a user, I want to be able to visualize my budget summary and see how much I spend and save 
- As a user, I want to be able to set up financial goals and track my progress towards them
- As a user, I want to be able to compare my budget with previous months or years
- As a user, I want to be able to save my budget and goals
- As a user, I want to be able to load my budget and goals

## Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by clicking on any of the New ... buttons in the File menu 
- You can generate the second required action related to adding Xs to a Y by by clicking on any of the New ... buttons in the File menu 
- You can locate my visual component by clicking on any of the graph buttons in the File menu
- You can save the state of my application by pressing the save button in the File menu
- You can reload the state of my application by pressing the load button in the File menu

## Phase 4: Task 2

A representative sample of the events that occur when the program runs:

Start of EventLog :

Tue Aug 08 21:28:38 PDT 2023 Created new controller

Tue Aug 08 21:28:44 PDT 2023 Created a new revenue with the name: Hourly Wage

Tue Aug 08 21:28:46 PDT 2023 Added 16.75 to Hourly Wage

Tue Aug 08 21:28:49 PDT 2023 Added 16.75 to Hourly Wage

Tue Aug 08 21:28:50 PDT 2023 Added Hourly Wage to list of revenues

Tue Aug 08 21:28:57 PDT 2023 Created a new expense with the name: Gas

Tue Aug 08 21:29:01 PDT 2023 Added 50.75 to Gas

Tue Aug 08 21:29:05 PDT 2023 Added 78.97 to Gas

Tue Aug 08 21:29:07 PDT 2023 Added Gas to list of expenses

Tue Aug 08 21:29:22 PDT 2023 Created a new goal with the name: Vacation and amount needed: 1000.0

Tue Aug 08 21:29:23 PDT 2023 Added Vacation to list of goals

Tue Aug 08 21:29:28 PDT 2023 Added 500.0 to Vacation

End of EventLog.

Process finished with exit code 0

## Phase 4: Task 3

If I were to improve the code, I would introduce a superclass for the Expenses and Revenues classes.
This would help reduce the duplication of code and allow for more specialized subclasses
By doing this, we could also make it easier to add new types of transactions in the future, such as Debts or
Investments.

Additionally, I would have made the Goal class into a superclass that would have had two subclasses:
ShortTermGoal and LongTermGoal. This would help to encapsulate the specific behaviour and attributes of each goal,
making the code more flexible. For example, a ShortTermGoal might have a deadline and a progress bar,
while a LongTermGoal might feature a target amount and a monthly contribution.

## References:

- Carter, P. (2021, October 16). JsonSerializationDemo. GitHub. Retrieved July 24, 2023, from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
- Gilbert, D. (2022, June 7). GitHub - jfree/orson-charts: A 3D chart library for Java applications (JavaFX, Swing or server-side). GitHub. Retrieved August 5, 2023, from https://github.com/jfree/orson-charts
- MIG Layout Java Layout Manager for Swing and SWT. (n.d.). Retrieved August 5, 2023, from https://www.miglayout.com/
- Karl T. (n.d.). GitHub - JFormDesigner/FlatLaf: FlatLaf - Swing Look and Feel (with Darcula/IntelliJ themes support). GitHub. Retrieved August 5, 2023, from https://github.com/JFormDesigner/FlatLaf
