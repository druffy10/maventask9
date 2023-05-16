import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodosTest {
    private Todos todos;

    @BeforeEach
    public void setUp() {
        todos = new Todos();
        todos.add(new SimpleTask(1, "Купить продукты"));
        todos.add(new Epic(2, new String[] {"Написать код", "Протестировать код"}));
        todos.add(new Meeting(3, "Проверить код", "Project X", "1 июня 2023, 12-00"));
    }

    @Test
    public void searchTest() {
        Task[] result = todos.search("код");

        int count = 0;
        for (Task task : result) {
            if (task.matches("код")) {
                count++;
            }
        }

        Assertions.assertEquals(2, result.length);
        Assertions.assertEquals(2, count);
    }

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
        555,
        "Выкатка 3й версии приложения",
        "Приложение НетоБанка",
        "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
        }
}