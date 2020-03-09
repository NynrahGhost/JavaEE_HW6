package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	@Autowired
	private ApplicationContext applicationContext;
	
    @RequestMapping({ "/", "" })
    public String index() {
        return "index";
    }
    
    
    
    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String book(Model model, @PathVariable("id") long id) {
        return applicationContext.getBean(BookService.class).getBook((int)id).get(0).toString();
    }
    
    @RequestMapping(value = "/book/ISBN/{isbn}", method = RequestMethod.GET)
    @ResponseBody
    public String bookByISBN(Model model, @PathVariable("isbn") String isbn) {
        return applicationContext.getBean(BookService.class).getBookByISBN(isbn).toString();
    }
    
    @RequestMapping(value = "/book/title/{title}", method = RequestMethod.GET)
    @ResponseBody
    public String bookByTitle(Model model, @PathVariable("title") String title) {
        return applicationContext.getBean(BookService.class).getBookByTitle(title).toString();
    }
    
    @RequestMapping(value = "/book/author/{author}", method = RequestMethod.GET)
    @ResponseBody
    public String bookByAuthor(Model model, @PathVariable("author") String author) {
        return applicationContext.getBean(BookService.class).getBookByAuthor(author).toString();
    }
    
    
    
    @RequestMapping(value = "/books-list", method = RequestMethod.GET)
    public String booksList(Model model) {
    	
        model.addAttribute("books", applicationContext.getBean(BookService.class).getBooks());
        return "index";
    }
    
    @RequestMapping(value = "/book-form", method = RequestMethod.GET)
    public String bookForm(Model model) {
        return "bookForm";
    }

    @RequestMapping(value = "/add-book", method = RequestMethod.POST)
    public String addNewBook(
    		@ModelAttribute(name = "title") String title,
    		@ModelAttribute(name = "isbn") String isbn,
    		@ModelAttribute(name = "author") String author
    ) {
    	applicationContext.getBean(BookService.class).createBook(title, isbn, author);
        return "redirect:/books-list";
    }

}