// Ensure this script runs after the DOM is fully loaded
document.addEventListener('DOMContentLoaded', () => {

    // Select the anchor tag with the class we just added
    const checkIcon = document.querySelector('.js-task-check');
    const editIcon = document.querySelector('.js-task-edit');
    const deleteIcon = document.querySelector('.js-task-delete');
    //const addTaskBtn = document.querySelector('#addTaskBtn');
    const isTaskCreated = document.querySelector('#isTaskCreated');

    isTaskCreated.style.display = 'none';
    // Add a click event listener
    checkIcon.addEventListener('click', (event) => {
        // Prevent the default anchor tag behavior (e.g., jumping to the top of the page)
        event.preventDefault();

        // This is where you would call your function or add your logic
        console.log('The check icon was clicked!');

        // Example: Call a function to mark the task as complete
        markTaskAsComplete();
    });

    editIcon.addEventListener('click', (event) => {
        event.preventDefault();

        console.log('The check icon was clicked!');

        editTask();
    });

    deleteIcon.addEventListener('click', (event) => {
        event.preventDefault();

        console.log('The check icon was clicked!');

        deleteTask();
    });

    // Add a click event listener
//    addTaskBtn.addEventListener('click', (event) => {
//        event.preventDefault();
//
//        const taskInput = document.querySelector('#addtask');
//        const taskText = taskInput.value;
//
//        if (taskText) {
//            console.log('Adding a new task: ', taskText);
//            // Example: Call a function to add the task to your list
//            // addTask(taskText);
//            taskInput.value = ''; // Clear the input field
//        }
//    });
});

function markTaskAsComplete() {
    alert('Task completed!');
}

function editTask() {
    alert('Edit Task completed!');
}

function deleteTask() {
    alert('delete Task completed!');
}

