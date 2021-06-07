package gitlet;

// TODO: any imports you need here

import java.awt.*;
import java.io.File;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set; // TODO: You'll likely use this in this class

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private String message;
    /** The time this Commit was created. */
    private final Date date;
    /** The first parent of this Commit. */
    private Commit father;
    /** The second parent of this Commit. */
    private Commit mother;
    /** The NAMES of the blobs this commit is tracking. */
    private ArrayList<String> blobNames;
    /** Hashes of the blobs this commit is tracking */
    private HashMap<String, String> blobHashes;
    /** My SHA1 hash value */
    private String sha1;


    /* TODO: Figure out what is stored in the very first commit. */
    public Commit () {
        date = new Date(0);
        message = "initial commit";
        father = null;
        mother = null;
        blobHashes = new HashMap<>();
        sha1 = Utils.sha1(this.message, date.toString());
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
        date = null;
    }


    /* TODO: fill in the rest of this class. */


    /* TODO: serialize this commit. */
    public void serialize (String sha1) {
        File outFile = new File(".gitlet/objects/" + this.sha1);
        // Utils.writeObject(outFile, this);
    }

}
