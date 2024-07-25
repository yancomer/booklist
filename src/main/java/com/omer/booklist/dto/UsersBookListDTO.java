package com.omer.booklist.dto;

public class UsersBookListDTO {
    private Long bookId;
    private Long userId;
    private String userFirstName;
    private String bookName;
    
    public UsersBookListDTO(Long bookId, Long userId, String userFirstName, String bookName) {
        this.bookId = bookId;
        this.userId = userId;
        this.userFirstName = userFirstName;
        this.bookName = bookName;
    }
    public Long getBookId() {
        return bookId;
    }
    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUserFirstName() {
        return userFirstName;
    }
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }    
}
