package xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import xml.domain.Author;
import xml.domain.Book;
import xml.domain.Library;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 11.01.14
 */
public class LibraryLauncher {
    public static void main(String[] args) throws IOException {

        final InputStream is = LibraryLauncher.class.getResourceAsStream("/library.xml");
        try {
            final Library library = buildLibrary(is);
            library.fillAuthors();

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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            is.close();
        }
    }

    private static Library buildLibrary(final InputStream is) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLReader reader = parser.getXMLReader();

        final Library library = new Library();
        reader.setContentHandler(new DefaultHandler(){
            private Book currentBook;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                if("book".equals(qName)){
                    currentBook = new Book();
                }
                if("author".equals(qName)){
                    Author author = new Author();
                    author.setName(attributes.getValue("name"));
                    currentBook.getAuthors().add(author);
                    library.getBooks().add(currentBook);
                }
                if("title".equals(qName)){
                    sb = new StringBuilder();
                }
            }
            StringBuilder sb = null;

            @Override
            public void endElement(String uri, String localName, String qName) {
                if("book".equals(qName)) {
                    library.getBooks().add(currentBook);
                }
                if("title".equals(qName)){
                    currentBook.setTitle(sb.toString());
                    sb = null;
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) {
                if(sb != null)
                sb.append(ch,start,length);
            }
        });

        reader.parse(new InputSource(is));

        return library;
    }
}