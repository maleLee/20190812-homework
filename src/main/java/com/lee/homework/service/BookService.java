package com.lee.homework.service;

import com.lee.homework.mapper.BookMapper;
import com.lee.homework.model.Book;
import com.lee.homework.model.vo.BookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2019/8/13 16:55
 * @Description
 **/
@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    public List<BookVo> selectAll(String tableName) {
        return bookMapper.selectTaleName(tableName);
    }

}
