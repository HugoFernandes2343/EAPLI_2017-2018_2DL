package eapli.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * utility class for string formating
 *
 * @author Paulo Gandra Sousa
 *
 */
public final class StringFormater {

    private StringFormater() {
	// ensure utility
    }

    public static String prettyFormatXml(final String input) {
	return prettyFormatXml(input, 2);
    }

    /**
     * returns an XML formated output.
     * <p>
     * based in code from <a href=
     * "http://stackoverflow.com/questions/139076/how-to-pretty-print-xml-from-java">
     * stack overflow</a>
     *
     * @param input
     * @param indent
     * @return
     */
    public static String prettyFormatXml(final String input, final int indent) {
	final Source xmlInput = new StreamSource(new StringReader(input));
	final StringWriter stringWriter = new StringWriter();
	final StreamResult xmlOutput = new StreamResult(stringWriter);
	final TransformerFactory transformerFactory = TransformerFactory.newInstance();
	transformerFactory.setAttribute("indent-number", indent);
	try {
	    final Transformer transformer = transformerFactory.newTransformer();
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.transform(xmlInput, xmlOutput);
	} catch (final TransformerException e) {
	    throw new IllegalStateException(e);
	}
	return xmlOutput.getWriter().toString();
    }
}
