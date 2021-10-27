package xml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import xml.domain.Author;
import xml.domain.Book;
import xml.domain.Library;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.HashSet;
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

    private static Node findBookNode(Node root) {
        if (root.getNodeName().equals("books")) {
            return root;
        }
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (findBookNode(nodeList.item(i)) != null) {
                return findBookNode(nodeList.item(i));
            }
        }
        return null;
    }

    private static Library buildLibrary(final InputStream is) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        Library library = new Library();

        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(is);
            document.getDocumentElement().normalize();

            Node libraryNode = findBookNode(document.getFirstChild());
            library.setAuthors(new HashSet<Author>());
            library.setBooks(new HashSet<Book>());
            Book book;
            Author author;

            for (int i = 0; i < libraryNode.getChildNodes().getLength(); i++) {
                if (libraryNode.getChildNodes().item(i).getNodeName().equals("book")) {
                    book = new Book();
                    book.setAuthors(new HashSet<Author>());

                    for (int j = 0; j < libraryNode.getChildNodes().item(i).getChildNodes().getLength(); j++) {
                        if (libraryNode.getChildNodes().item(i).getChildNodes().item(j).getNodeName().equals("author")) {
                            author = new Author();
                            author.setName(libraryNode.getChildNodes().item(i).getChildNodes().item(j).getAttributes().getNamedItem("name").getNodeValue());
                            book.getAuthors().add(author);
                            library.getAuthors().add(author);
                        }

                        if (libraryNode.getChildNodes().item(i).getChildNodes().item(j).getNodeName().equals("title")) {
                            book.setTitle(libraryNode.getChildNodes().item(i).getChildNodes().item(j).getTextContent());
                        }
                    }
                    library.getBooks().add(book);
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return library;
    }
}