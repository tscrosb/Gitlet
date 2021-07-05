package gitlet;

// TODO: any imports you need here

import java.awt.*;
import java.io.File;
import java.io.Serializable;
import java.util.*;
import java.util.List;

/** Represents a gitlet commit object.
 *  @author Thomas Crosbie-Walsh
 */
public class Commit implements Serializable {

    /** The message of this Commit. */
    private String message;
    /** The time of creation of this Commit. */
    private final Calendar calendar = Calendar.getInstance();
    /** The first parent of this Commit. */
    private String father;
    /** The second parent of this Commit. */
    private String mother;
    /** The NAMES of the blobs this commit is tracking. */
    private List<String> blobNames;
    /** Hashes of the blobs this commit is tracking */
    private Map<String, String> blobHashes;
    /** My SHA1 hash value */
    private String sha1;


    /* TODO: Figure out what is stored in the very first commit. */
    public Commit () {
        message = "initial commit";
        calendar.setTimeZone(TimeZone.getDefault());
        calendar.setTime(new Date(0));
        father = null;
        mother = null;
        blobHashes = new HashMap<>();
        blobNames = new ArrayList<>();
        sha1 = Utils.sha1(this.message, calendar.toString());
    }

    /* TODO: Create a new commit object with message and stores a list of associated blobs */
    public Commit (String message, List<String> blobNames) {
        // store the message
        this.message = message;
        // give this commit a date
        // assign parent commits
        if (this.father == null && this.mother == null) {
            // assign
        }
        // loop through list of blobs and check if any were added, removed, or changed
        calendar.setTimeZone(TimeZone.getDefault());
    }






    public void serialize () {
        File outFile = new File(".gitlet/objects/" + this.sha1);
        Utils.writeObject(outFile, this);
    }

    public File deserialize () {
        return new File(".gitlet/objects/" + this.sha1);
    }

    public Commit getFather() {
        File father = new File(".gitlet/objects/" + this.father);
        return Utils.readObject(father, Commit.class);
    }

    public Commit getMother() {
        File mother = new File(".gitlet/objects/" + this.mother);
        return Utils.readObject(mother, Commit.class);
    }

    public String getSha1() {
        return this.sha1;
    }

    public boolean blobExists(String name) {
        return this.blobHashes.containsKey(name);
    }

    public Blob getBlob(String name) {
        String blobSha1 = this.blobHashes.get(name);
        File file = new File(".gitlet/objects" + blobSha1);
        return Utils.readObject(file, Blob.class);
    }



}
