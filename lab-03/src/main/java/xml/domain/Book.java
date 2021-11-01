package xml.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 11.01.14
 */
public class Book {
    private String title = null;
    private Set<Author> authors = new HashSet<Author>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }
}
