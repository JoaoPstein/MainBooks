package mainbooks.com.br.MainBooks.Repositories;

import mainbooks.com.br.MainBooks.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
