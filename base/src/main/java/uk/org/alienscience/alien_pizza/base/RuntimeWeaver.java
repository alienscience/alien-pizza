package uk.org.alienscience.alien_pizza.base;


import kilim.analysis.ClassInfo;
import kilim.tools.Weaver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that performs runtime weaving
 */
public class RuntimeWeaver {

    /**
     * Weaves the class files in the given directory and loads them
     * @param packageName The package that the files belong to
     * @param dir The directory
     */
    public static ClassLoader load(String packageName, File dir) throws IOException {
        List<ClassInfo> classes = new ArrayList<ClassInfo>();

        // Read in the classes
        addClasses(classes, packageName, dir);

        // Weave the classes
        classes = new Weaver().weave(classes);

        // Load the classes
        PizzaLoader loader = new PizzaLoader();
        for (ClassInfo classInfo : classes ) {
            loader.load(classInfo);
        }

        return loader;
    }

    /**
     * A class loader that loads weaved classes
     */
    static class PizzaLoader extends ClassLoader {
        public void load(ClassInfo cl) {
            Class<?> c = super.defineClass(cl.className, cl.bytes, 0, cl.bytes.length);
            super.resolveClass(c);
        }
    }

    /**
     * A recursive method taken from {@link kilim.tools.Javac}
     * @param ret  A list of class information that is dynamically built
     * @param packageName The name of the package that the class files belong to
     * @param dir
     * @throws IOException
     */
    private static void addClasses(List<ClassInfo> ret, String packageName, File dir)
            throws IOException {

        File[] fileArray = dir.listFiles();
        if (fileArray == null) {
            throw new IOException(dir.getAbsolutePath() + " is not a valid directory");
        }

        for (File f : fileArray) {
            String fname = f.getName();
            if (f.isDirectory()) {
                String qname = packageName + fname + ".";
                addClasses(ret, qname, f);
            } else if (fname.endsWith(".class")) {
                String qname = packageName + fname.substring(0, fname.length() - 6);
                ret.add(new ClassInfo(qname, readFile(f)));
            } else {
                System.err.println("Unexpected file : " + f.getAbsolutePath());
            }
        }
    }

    /**
     * A method taken from {@link kilim.tools.Javac} that reads a file into a byte array
     * @param file The file object describing the file
     * @return The file read in as a byte array
     * @throws IOException
     */
    private static byte[] readFile(File file) throws IOException {
        int len = (int) file.length();
        byte[] buf = new byte[len];
        FileInputStream fis = new FileInputStream(file);
        int off = 0;
        while (len > 0) {
            int n = fis.read(buf, off, len);
            if (n == -1)
                throw new IOException("Unexpected EOF reading " + file.getAbsolutePath());
            off += n;
            len -= n;
        }
        return buf;
    }

}
