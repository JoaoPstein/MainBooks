package mainbooks.com.br.MainBooks.Controllers;

import jakarta.validation.Valid;
import mainbooks.com.br.MainBooks.Dto.RequestNewBookDto;
import mainbooks.com.br.MainBooks.Models.Book;
import mainbooks.com.br.MainBooks.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/books")
public class BookController {

    private BookRepository iBookRepository;

    public BookController(BookRepository iBookRepository){
        this.iBookRepository = iBookRepository;
    }

    @GetMapping()
    public ModelAndView Index(){
        List<Book> listBook = this.iBookRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("books/index");

        modelAndView.addObject("listBook", listBook);

        return modelAndView;
    }

    @GetMapping("/create")
    public String New(RequestNewBookDto request){
        return "books/create";
    }

    @PostMapping()
    public ModelAndView Create(@Valid RequestNewBookDto request, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.out.println("\n************* TEM ERROS ***************\n");
            return  new ModelAndView("books/create");
        }

        Book book = request.toBook();

        this.iBookRepository.save(book);

        return new ModelAndView("redirect:/books");
    }

    @GetMapping("{id}/edit")
    public ModelAndView Edit(@PathVariable Long id, RequestNewBookDto request){
        Optional<Book> optional = this.iBookRepository.findById(id);

        if (optional.isPresent()) {
            Book book = optional.get();
            request.fromBook(book);

            ModelAndView mv = new ModelAndView("books/edit");
            mv.addObject("bookId", book.getId());
            return mv;
        }
        else {
            System.out.println("$$$$$$$$$$$ NÃO ACHOU O LIVRO DE ID "+ id + " $$$$$$$$$$$");
            return this.returnErrorBook("EDIT ERROR: Livro #" + id + " não encontrado no banco!");
        }
    }

    @PostMapping("/{id}")
    public ModelAndView Update(@PathVariable Long id, @Valid RequestNewBookDto request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ModelAndView mv = new ModelAndView("books/edit");
            mv.addObject("bookId", id);
            return mv;
        } else {
            Optional<Book> optional = this.iBookRepository.findById(id);

            if (optional.isPresent()) {
                Book book = request.fromBook(optional.get());
                this.iBookRepository.save(book);

                return new ModelAndView("redirect:/books/" + book.getId());
            }
            else {
                System.out.println("############ NÃO ACHOU O LIVRO DE ID " + id + " ############");
                return this.returnErrorBook("UPDATE ERROR: Livro #" + id + " não encontrado no banco!");
            }
        }
    }

    @GetMapping("/{id}/delete")
    public ModelAndView Delete(@PathVariable Long id) {
        ModelAndView mv = new ModelAndView("redirect:/books");

        try {
            this.iBookRepository.deleteById(id);
            mv.addObject("mensagem", "Livro #" + id + " deletado com sucesso!");
            mv.addObject("erro", false);
        }
        catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            mv = this.returnErrorBook("DELETE ERROR: Livro #" + id + " não encontrado no banco!");
        }

        return mv;
    }


    private ModelAndView returnErrorBook(String msg) {
        ModelAndView mv = new ModelAndView("redirect:/books");
        mv.addObject("mensagem", msg);
        mv.addObject("erro", true);
        return mv;
    }
}
