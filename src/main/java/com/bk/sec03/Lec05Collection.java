package com.bk.sec03;

import com.bk.models.sec03.Book;
import com.bk.models.sec03.Library;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Lec05Collection {
    private static final Logger log = LoggerFactory.getLogger(Lec05Collection.class);
    public static void main(String[] args) {
        //create book
        var book1 = Book.newBuilder()
                .setTitle("Java 8")
                .setAuthor("Bob")
                .setPublicationYear(1970)
                .build();

        var book2 = Book.newBuilder()
                .setTitle("Java 9")
                .setAuthor("Bob")
                .setPublicationYear(1990)
                .build();
        var book3 = Book.newBuilder()
                .setTitle("Java 11")
                .setAuthor("Bob")
                .setPublicationYear(1999)
                .build();
        var library = Library.newBuilder()
                .setName("Programming Language library")
//                .addBooks(book1)
//                .addBooks(book2)
//                .addBooks(book3)
                .addAllBooks(List.of(book1, book2, book3))
                .build();
        log.info(library.toString());


    }
}
