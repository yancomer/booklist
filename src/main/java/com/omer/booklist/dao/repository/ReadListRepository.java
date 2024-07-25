package com.omer.booklist.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.omer.booklist.dao.model.ReadList;

@Repository
public interface ReadListRepository extends JpaRepository<ReadList, Long>{
    
    @Query(value="select r.book_id, r.user_id, u.user_first_name, b.book_name from bookdb.read_list r inner join bookdb.user u on r.user_id = u.id \n" + //
                "inner join bookdb.book b on r.book_id = b.id", nativeQuery=true)
    List<Object[]> showUsersBooks();
}
