package com.omer.booklist.service;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omer.booklist.dao.model.ReadList;
import com.omer.booklist.dao.repository.ReadListRepository;
import com.omer.booklist.dto.UsersBookListDTO;

@Service
public class ReadListService {

    @Autowired
    private ReadListRepository readListRepository;

    public ReadListService(ReadListRepository readListRepository) {
        this.readListRepository = readListRepository;
    }

    public ReadList createReadList(Long userId, Long bookId, Date startDate, Date endDate, int rate) {
        ReadList readList = new ReadList();
        readList.setId(generateId());
        readList.setUserId(userId);
        readList.setBookId(bookId);
        readList.setStartDate(startDate);
        readList.setEndDate(endDate);
        readList.setRate(rate);
        return readListRepository.save(readList);
    }
    private Long generateId() {
        return readListRepository.findAll().size() + 1L;
    }
    public Optional<ReadList> updateReadList(Long id, Long userId, Long bookId, Date startDate, Date endDate, int rate) {
        Optional<ReadList> readListOpt = readListRepository.findById(id);
        if (readListOpt.isPresent()) {
            ReadList readList = readListOpt.get();
            readList.setUserId(userId);
            readList.setBookId(bookId);
            readList.setStartDate(startDate);
            readList.setEndDate(endDate);
            readList.setRate(rate);
            return Optional.of(readListRepository.save(readList));
        }
        return Optional.empty();
    }

    public boolean deleteReadList(Long id) {
        readListRepository.deleteById(id);
        return true;
    }

    public Optional<ReadList> getReadListById(Long id) {
        return readListRepository.findById(id);
    }

    public List<ReadList> getAllReadLists() {
        return readListRepository.findAll();
    }

    public Collection<UsersBookListDTO> getUsersBooks() {
        List<Object[]> results = readListRepository.showUsersBooks();
        return results.stream().map(result -> 
            new UsersBookListDTO(
                ((Number) result[0]).longValue(), // book_id
                ((Number) result[1]).longValue(), // user_id
                (String) result[2], // user_first_name
                (String) result[3] // book_name
            )
        ).collect(Collectors.toList());
    }
    
}
