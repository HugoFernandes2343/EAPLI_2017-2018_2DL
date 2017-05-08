/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.util.io;

/**
 * utility class for file manipulation.
 *
 * @author Paulo Gandra Sousa
 */
public class Files {

    private Files() {
	// to make sure this is an utility class
    }

    public static String currentDirectory() {
	return new java.io.File(".").getAbsolutePath();
    }

    public static String ensureExtension(final String filename, final String extension) {
	if (!filename.endsWith(extension)) {
	    return filename + extension;
	} else {
	    return filename;
	}
    }
}
