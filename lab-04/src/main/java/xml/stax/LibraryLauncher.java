package xml.stax;

import xml.domain.Author;
import xml.domain.Book;
import xml.domain.Library;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
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
        Library library = null;
        Author author = new Author();
        Book book = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        try {
            XMLEventReader reader = factory.createXMLEventReader(is);
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    if (startElement.getName().getLocalPart().equals("books")) {
                        library = new Library();
                        library.setAuthors(new HashSet<Author>());
                        library.setBooks(new HashSet<Book>());
                    } else if (startElement.getName().getLocalPart().equals("book")) {
                        book = new Book();
                        book.setAuthors(new HashSet<Author>());
                    } else if (startElement.getName().getLocalPart().equals("author")) {
                        author = new Author();
                        Attribute attribute = startElement.getAttributeByName(new QName("name"));
                        author.setName(attribute.getValue());
                        book.getAuthors().add(author);
                    } else if (startElement.getName().getLocalPart().equals("title")) {
                        event = reader.nextEvent();
                        book.setTitle(event.asCharacters().getData());
                    }
                }
                if (event.isEndDocument()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals("book")) {
                        library.getBooks().add(book);
                    }
                    if (endElement.getName().getLocalPart().equals("author")) {
                        library.getAuthors().add(author);
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return library;
    }

    private static void writeLibrary(final Library library, final OutputStream os) throws XMLStreamException {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory.createXMLStreamWriter(os);
        writer.writeStartDocument();
        writer.writeStartElement("library");
        writer.writeStartElement("books");
        for (Book book : library.getBooks()) {
            writer.writeStartElement("book");
            for (Author author : book.getAuthors()) {
                writer.writeEmptyElement("author");
                writer.writeAttribute("name", author.getName());
            }
            writer.writeStartElement("title");
            writer.writeCharacters(book.getTitle());
            writer.writeEndElement();
            writer.writeEndElement();
        }
        writer.writeEndElement();
        writer.writeEndElement();
    }
}