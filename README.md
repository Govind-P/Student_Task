# Student_Task
UI design for student task management app
   
   Student Task App is an app designed to manage student task. No real-time database is used(Focused on UI). The app is designed such that is layout adapts well to different screen sizes. The app consist of a main activity page which is divided into three fragments. One fragment deals with task, other with notification and last one with settings. Main activity consist of toolbar with app title and bottom navigation bar to navigate into fragments.
TaskFragment:
Task fragment consist of card view with task title and a check box to set the task as completed. Two floating buttons are present at left and right bottom to filter and add task. By clicking on add task button user can add task. The title of task is only saved in an array list to display it on task page. Filter button is used to apply filter in task. In this app we can choose filter and the chosen filter is shown in task page. When selected a task it will direct the user in to a task detail page.
Add Task Page:
All the fields except due time is mandatory. Date and time can be chosen by clicking on this icon on the right.  The default value of due time is 23:59. When ‘ADD TASK’ button is clicked data(title) is saved and redirected into task page.
Task completed: 
To set the task completed set check box checked.                                       
Task Details Page:
Show the details of task selected. Edit and delete button to edit and delete respectively. When click on delete button it redirect to task page after deleting data. When click on edit, it redirect to update page.                   
Notification:
Contains a list of notification. When clicked on ‘X’ at the right of each notification it will be removed. When notification is selected its details are shown.
Notification details: show details of notification
                      
Note:
Array list and other local variables are used for storage purpose to show the functionalities of each activity and to check if it is working properly. No real-time database is used.
