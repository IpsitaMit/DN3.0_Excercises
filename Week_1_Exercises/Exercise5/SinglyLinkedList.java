public class SinglyLinkedList {
    private Node head;

    private static class Node {
        Task task;
        Node next;

        Node(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Added: " + task);
    }

    public Task searchTask(int taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.getTaskId() == taskId) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    public void traverseTasks() {
        Node current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

    public void deleteTask(int taskId) {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }

        if (head.task.getTaskId() == taskId) {
            head = head.next;
            System.out.println("Deleted task with id " + taskId);
            return;
        }

        Node current = head;
        Node previous = null;
        while (current != null && current.task.getTaskId() != taskId) {
            previous = current;
            current = current.next;
        }

        if (current == null) {
            System.out.println("Task not found.");
            return;
        }

        previous.next = current.next;
        System.out.println("Deleted task with id " + taskId);
    }

    public static void main(String[] args) {
        SinglyLinkedList taskList = new SinglyLinkedList();
        Task task1 = new Task(1, "Design System", "In Progress");
        Task task2 = new Task(2, "Implement Features", "Pending");
        Task task3 = new Task(3, "Testing", "Pending");

        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);

        System.out.println("All Tasks:");
        taskList.traverseTasks();

        System.out.println("Search Task with ID 2:");
        System.out.println(taskList.searchTask(2));

        taskList.deleteTask(2);
        System.out.println("All Tasks after deletion:");
        taskList.traverseTasks();
    }
}
