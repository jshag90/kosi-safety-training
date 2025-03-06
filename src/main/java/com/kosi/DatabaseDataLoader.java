package com.kosi;

import com.kosi.dao.BoardDao;
import com.kosi.dao.CourseDao;
import com.kosi.util.CourseCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class DatabaseDataLoader implements CommandLineRunner {

    private final BoardDao boardDao;
    private final CourseDao courseDao;


    @Override
    public void run(String... args) throws Exception {
        boardDao.insertFaqType();
        courseDao.insertCourseCategoryType();
    }
}
