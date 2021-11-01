package xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import xml.domain.Author;
import xml.domain.Book;
import xml.domain.Library;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Stack;

/**
 * @author Anton German &lt;AGerman@luxoft.com&gt;
 * @version 1.0 11.01.14
 */
public class LibraryBuilderHandler {

    private Library library = new Library();
     public Library getLibrary(){
         return library;
     }
}
