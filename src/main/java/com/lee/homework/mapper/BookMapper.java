package com.lee.homework.mapper;

import com.lee.homework.model.Book;
import com.lee.homework.model.vo.BookVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Book record);

    Book selectByPrimaryKey(Long id);

    List<Book> selectAll();

    int updateByPrimaryKey(Book record);

    List<BookVo> selectTaleName(@Param("tableName") String tableName);
}