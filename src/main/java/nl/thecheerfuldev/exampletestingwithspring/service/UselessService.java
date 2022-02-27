package nl.thecheerfuldev.exampletestingwithspring.service;

import nl.thecheerfuldev.uselessdomain.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UselessService {

    private final List<String> theList;
    private final Book book;

    public UselessService(List<String> theList, Book book) {
        this.theList = theList;
        this.book = book;
    }

    public List<String> getTheList() {
        return this.theList;
    }

    public Book getBook() {
        return book;
    }
}
