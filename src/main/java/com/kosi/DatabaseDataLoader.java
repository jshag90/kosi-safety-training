package com.kosi;

import com.kosi.dao.BoardDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class DatabaseDataLoader implements CommandLineRunner {

    private final BoardDao boardDao;


    @Override
    public void run(String... args) throws Exception {
        boardDao.insertFaqType();
    }
}
