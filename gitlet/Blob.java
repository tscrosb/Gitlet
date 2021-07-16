package gitlet;

import java.io.File;
import java.io.Serializable;

/** Represents an object that stores the contents of a file.
 *  @author Thomas Crosbie-Walsh
 */

public class Blob implements Serializable {

    private final String filename;
    private final byte[] contents;
    private final String sha1;

    public Blob(byte[] contents, String filename) {
        this.contents = contents;
        this.filename = filename;
        this.sha1 = Utils.sha1(this.contents, filename);
    }

    public void serialize() {
        File outFile = new File(".gitlet/objects/" + this.sha1);
        Utils.writeObject(outFile, this);
    }

    public static Blob deserialize(String filename) {
        File inFile = new File(".gitlet/objects/" + filename);
        return Utils.readObject(inFile, Blob.class);
    }

    public String getName() {
        return this.filename;
    }

    public String getSHA1() {
        return this.sha1;
    }

    public byte[] getContents() {
        return this.contents;
    }
}
