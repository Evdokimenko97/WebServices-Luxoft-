package xml.dom;

import xml.domain.Author;
import xml.domain.Book;
import xml.domain.Library;

import java.io.InputStream;
import java.util.Iterator;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 11.01.14
 */
public class LibraryLauncher {
    public static void main(String[] args) throws Exception {
        final InputStream is = LibraryLauncher.class.getResourceAsStream("/library.xml");
        try {
            final Library library = buildLibrary(is);

            System.out.println("Authors:");
            for (final Author author : library.getAuthors()) {
                System.out.println("  " + author.getName());
            }

            System.out.println();
            System.out.println("Books:");
            for (final Book book : library.getBooks()) {
                final StringBuffer authors = new StringBuffer();

                final Iterator<Author> it = book.getAuthors().iterator();
                while (it.hasNext()) {
                    Author author = it.next();
                    authors.append(author.getName());
                    if (it.hasNext()) {
                        authors.append(", ");
                    }
                }

                System.out.println("  " + book.getTitle() + " (" + String.valueOf(authors) + ")");
            }

        } finally {
            is.close();
        }
    }

    private static Library buildLibrary(final InputStream is) throws Exception {

        // Write your code here ...

        return null;
    }
}
