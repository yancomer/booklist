package com.omer.booklist.controller;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.omer.booklist.dao.model.ReadList;
import com.omer.booklist.dto.UsersBookListDTO;
import com.omer.booklist.service.ReadListService;

@RestController
@RequestMapping("/readlist")
public class ReadListController {

    @Autowired
    private ReadListService readListService;

    @PostMapping
    public ResponseEntity<ReadList> createReadList(@RequestBody ReadList readList) {
        ReadList createdReadList = readListService.createReadList(
            readList.getUserId(), readList.getBookId(), 
            readList.getStartDate(), readList.getEndDate(), 
            readList.getRate());
        return ResponseEntity.ok(createdReadList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReadList> updateReadList(@PathVariable Long id, 
                                                   @RequestParam Long userId, 
                                                   @RequestParam Long bookId, 
                                                   @RequestParam Date startDate, 
                                                   @RequestParam Date endDate, 
                                                   @RequestParam int rate) {
        Optional<ReadList> updatedReadList = readListService.updateReadList(id, userId, bookId, startDate, endDate, rate);
        return updatedReadList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReadList(@PathVariable Long id) {
        boolean deleted = readListService.deleteReadList(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadList> getReadListById(@PathVariable Long id) {
        Optional<ReadList> readList = readListService.getReadListById(id);
        return readList.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ReadList>> getAllReadLists() {
        List<ReadList> readLists = readListService.getAllReadLists();
        return ResponseEntity.ok(readLists);
    }

    @GetMapping("/getAllUsersBooks")
    public ResponseEntity<Collection<UsersBookListDTO>> getAllUsersBooksList(){
        Collection<UsersBookListDTO> response = readListService.getUsersBooks();
        return ResponseEntity.ok(response);
    }
}
