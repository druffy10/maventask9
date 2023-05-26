import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class TodosTest {
    private Todos todos;
    private SimpleTask t1;
    private Epic t2;
    private Meeting t3;

    @BeforeEach
    public void setUp() {
        t1 = new SimpleTask(1, "Купить продукты");
        t2 = new Epic(2, new String[]{"Написать код", "Протестировать код"});
        t3 = new Meeting(3, "Проверить код", "Project X", "1 июня 2023, 12-00");
        todos = new Todos();
        todos.add(t1);
        todos.add(t2);
        todos.add(t3);
    }

    @Test
    public void searchTestMultipleMatches() {
        Task[] expected = {t2, t3};
        Task[] result = todos.search("код");
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void searchTestSingleMatch() {
        Task[] expected = {t1};
        Task[] result = todos.search("продукты");
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void searchTestNoMatches() {
        Task[] expected = new Task[0];
        Task[] result = todos.search("выход");
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
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

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }
}