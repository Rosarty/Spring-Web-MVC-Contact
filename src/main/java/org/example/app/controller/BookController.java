package org.example.app.controller;

import org.example.app.entity.Book;
import org.example.app.service.impl.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public String fetchAllBooks(Model model) {
        List<Book> list = bookService.fetchAll();
        model.addAttribute("title", "Books");
        model.addAttribute("books", list);
        model.addAttribute("fragmentName", "book_list");
        return "layout";
    }

    @RequestMapping("/create-book")
    public String createBook(Model model) {
        model.addAttribute("title", "Add Book");
        model.addAttribute("fragmentName", "book_add");
        return "layout";
    }

    @RequestMapping(value = "/add-book", method = RequestMethod.POST)
    public RedirectView addBook(@ModelAttribute Book book) {
        RedirectView redirectView = new RedirectView("/books");
        if (bookService.create(book))
            return redirectView;
        else return redirectView;
    }

    @RequestMapping("/update-book/{id}")
    public String updateBook(@PathVariable("id") Long id, Model model) {
        model.addAttribute("title", "Update Book");
        Book book = bookService.fetchById(id);
        model.addAttribute("book", book);
        model.addAttribute("fragmentName", "book_update");
        return "layout";
    }

    @RequestMapping(value = "/change-book", method = RequestMethod.POST)
    public RedirectView changeBook(@ModelAttribute Book book) {
        RedirectView redirectView = new RedirectView("/books");
        if (bookService.update(book.getId(), book))
            return redirectView;
        else return redirectView;
    }

    @RequestMapping("/delete-book/{id}")
    public RedirectView deleteBook(@PathVariable("id") Long id) {
        RedirectView redirectView = new RedirectView("/books");
        if (bookService.delete(id)) return redirectView;
        else return redirectView;
    }
}
