package xml.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 11.01.14
 */
public class Library {
    private Set<Author> authors = new HashSet<Author>();
    private Set<Book> books = new HashSet<Book>();


    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void fillAuthors(){
        for (Book book: books) {
            authors.addAll(book.getAuthors());
        }

    }
}
