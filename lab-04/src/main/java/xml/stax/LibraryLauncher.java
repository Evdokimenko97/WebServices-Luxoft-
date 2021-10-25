package xml.stax;

import xml.domain.Author;
import xml.domain.Book;
import xml.domain.Library;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 11.01.14
 */
public class LibraryLauncher {

    public static void main(String[] args) throws IOException, XMLStreamException {
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

            System.out.println("---");
            writeLibrary(library, System.out);
            System.out.flush();

        } finally {
            is.close();
        }
    }

    private static Library buildLibrary(final InputStream is) throws XMLStreamException {
        // Write your code here
        return null;
    }

    private static void writeLibrary(final Library library, final OutputStream os) throws XMLStreamException {
        // Write your code here
    }
}
