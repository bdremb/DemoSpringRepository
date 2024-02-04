package com.example.spring.demojooq.repository;

import com.example.spring.demojooq.exception.TaskNotFoundException;
import com.example.spring.demojooq.jooq.db.tables.records.TaskRecord;
import com.example.spring.demojooq.model.Task;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.spring.demojooq.jooq.db.Tables.TASK;

@Slf4j
@Repository
@Primary
@RequiredArgsConstructor
public class JooqTaskRepository implements TaskRepository {

    private final DSLContext dslContext;

    @Override
    public List<Task> findAll() {
        log.info("JooqTaskRepository finding all tasks");
        return dslContext.selectFrom(TASK)
                .fetchInto(Task.class);
    }

    @Override
    public Optional<Task> getById(Long id) {
        log.info("JooqTaskRepository getting task by id");
        return dslContext.selectFrom(TASK)
                .where(TASK.ID.eq(id))
                .fetchOptional()
                .map(taskRecord -> taskRecord.into(Task.class));
    }

    @Override
    public Task save(Task task) {
        log.info("JooqTaskRepository save");
        task.setId(System.currentTimeMillis());

        TaskRecord taskRecord = dslContext.newRecord(TASK, task);
        taskRecord.store();

        return taskRecord.into(Task.class);
    }

    @Override
    @SneakyThrows
    public Task update(Task task) {
        log.info("JooqTaskRepository update");

        final Optional<TaskRecord> updatedTaskRecord = dslContext.update(TASK)
                .set(dslContext.newRecord(TASK, task))
                .where(TASK.ID.eq(task.getId()))
                .returning()
                .fetchOptional();

        return updatedTaskRecord.map(taskRecord -> taskRecord.into(Task.class))
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

    @Override
    public void deleteById(Long id) {
        log.info("JooqTaskRepository deleting task by id " + id);
        dslContext.deleteFrom(TASK)
                .where(TASK.ID.eq(id))
                .execute();
    }

    @Override
    public void batchInsert(List<Task> tasks) {
        log.info("JooqTaskRepository batchInserting tasks");

        List<Query> insertQueries = new ArrayList<>();

        for (Task task : tasks) {
            insertQueries.add(
                    dslContext.insertInto(
                            TASK,
                            TASK.ID,
                            TASK.TITLE,
                            TASK.DESCRIPTION,
                            TASK.PRIORITY
                    ).values(
                            task.getId(),
                            task.getTitle(),
                            task.getDescription(),
                            task.getPriority()
                    )
            );
        }
        dslContext.batch(insertQueries).execute();
    }


}
