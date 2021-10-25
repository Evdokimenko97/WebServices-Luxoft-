package xml.jaxb;

import xml.domain.Author;
import xml.domain.Book;
import xml.domain.Library;
import xml.domain.ObjectFactory;

import javax.xml.bind.*;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 11.01.14
 */
public class LibraryLauncher {
    public static void main(String[] args) throws Exception {

        final Library library = buildLibrary(LibraryLauncher.class.getResource("/library.xml"));

        System.out.println("Books:");
        for (final Book book : library.getBook()) {
            final StringBuffer authors = new StringBuffer();

            final Iterator<Author> it = book.getAuthor().iterator();
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

    }

    private static Library buildLibrary(final URL source) throws JAXBException {

        // Write your code here ...

        return null;
    }

    private static void writeLibrary(final Library library, final OutputStream os) throws JAXBException {
        // Write your code here ...
    }
}
