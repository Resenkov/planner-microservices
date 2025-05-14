package resenkov.work.plannertodo.service;

import jakarta.transaction.Transactional;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import resenkov.work.plannertodo.entity.Categories;
import resenkov.work.plannertodo.entity.Priority;
import resenkov.work.plannertodo.entity.Task;

import java.util.Calendar;
import java.util.Date;


@Service
public class TestDataService {

    private final CategoryService categoryService;
    private final PriorityService priorityService;
    private final TaskService taskService;

    public TestDataService(CategoryService categoryService, PriorityService priorityService, TaskService taskService) {
        this.categoryService = categoryService;
        this.priorityService = priorityService;
        this.taskService = taskService;
    }

    @KafkaListener(topics = "resenkov-test")
    public void listenKafka(Long userId){
        System.out.println("Новый айди пользователя: " + userId);
        initTestData(userId);
    }

    public void initTestData(Long userId){
        Priority prior1 = new Priority();
        prior1.setColor("#fff");
        prior1.setTitle("Важный");
        prior1.setUserId(userId);

        Priority prior2 = new Priority();
        prior2.setColor("#ffе");
        prior2.setTitle("Неважный");
        prior2.setUserId(userId);

        priorityService.add(prior1);
        priorityService.add(prior2);


        Categories cat1 = new Categories();
        cat1.setTitle("Работа");
        cat1.setUserId(userId);

        Categories cat2 = new Categories();
        cat2.setTitle("Семья");
        cat2.setUserId(userId);

        categoryService.add(cat1);
        categoryService.add(cat2);


        Date tomorrow = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(tomorrow);
        c.add(Calendar.DATE, 1);
        tomorrow = c.getTime();

        // неделя
        Date oneWeek = new Date();
        Calendar c2 = Calendar.getInstance();
        c2.setTime(oneWeek);
        c2.add(Calendar.DATE, 7);
        oneWeek = c2.getTime();

        Task task1 = new Task();
        task1.setTitle("Покушать");
        task1.setCategory(cat1);
        task1.setPriority(prior1);
        task1.setCompleted(true);
        task1.setTaskDate(tomorrow);
        task1.setUserId(userId);

        Task task2 = new Task();
        task2.setTitle("Поспать");
        task2.setCategory(cat2);
        task2.setCompleted(false);
        task2.setPriority(prior2);
        task2.setTaskDate(oneWeek);
        task2.setUserId(userId);
        taskService.add(task1);
        taskService.add(task2);

    }
}

