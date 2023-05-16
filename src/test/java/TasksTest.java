import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TasksTest {
    private Todos todos;

    @BeforeEach
    public void setUp() {
        todos = new Todos();
        todos.add(new SimpleTask(1, "Купить продукты"));
        todos.add(new Epic(2, new String[] {"Написать код", "Протестировать код"}));
        todos.add(new Meeting(3, "Проверить код", "Project X", "1 июня 2023, 12-00"));
    }

    @Test
    public void simpleTaskMatchesTest() {
        SimpleTask task = new SimpleTask(1, "Купить продукты");
        Assertions.assertEquals(true, task.matches("продукты"));
        Assertions.assertEquals(false, task.matches("уборка"));
    }

    @Test
    public void epicMatchesTest() {
        String[] subtasks = { "Написать код", "Протестировать код" };
        Epic epic = new Epic(2, subtasks);
        Assertions.assertEquals(true, epic.matches("код"));
        Assertions.assertEquals(false, epic.matches("развертывание"));
    }

    @Test
    public void meetingMatchesTest() {
        Meeting meeting = new Meeting(3, "Проверить код", "Project X", "1 июня 2023, 12-00");
        Assertions.assertEquals(true, meeting.matches("Project"));
        Assertions.assertEquals(true, meeting.matches("Проверить"));
        Assertions.assertEquals(false, meeting.matches("встреча"));
    }
}