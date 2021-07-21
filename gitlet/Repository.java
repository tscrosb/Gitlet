package gitlet;

import java.io.File;
import java.io.Serial;
import java.util.Arrays;

import static gitlet.Utils.*;


/** Represents a gitlet repository.
 *  @author Thomas Crosbie-Walsh
 */
public class Repository {

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");

    static void init () {
        boolean success = GITLET_DIR.mkdir();
        if (!success) {
            System.out.println("A Gitlet version-control system already exists in the current directory.");
            return;
        }
        try {
            File staging = new File(".gitlet/staging");
            File objects = new File(".gitlet/objects");
            staging.mkdir();
            objects.mkdir();
            Commit initial = new Commit();
            CommitTree commitTree = new CommitTree(initial);
            commitTree.serialize();
            initial.serialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void add(String filename) {
        File toAdd = new File(filename);
        if (!toAdd.exists()) {
            System.out.println("File does not exist.");
            return;
        }
        try {
            byte[] toAddContents = Utils.readContents(toAdd);
            CommitTree commitTree = Utils.readObject(new File(".gitlet/objects/" + "CT"), CommitTree.class);
            System.out.println(commitTree.getHeadSHA1());
            Commit headCommit = commitTree.getHeadCommit();
            if (headCommit.blobExists(filename)) {
                Blob committed = headCommit.getBlob(filename);
                byte[] commitContents = committed.getContents();
                if (Arrays.equals(toAddContents, commitContents)) {
                    new File(".gitlet/staging/" + filename).delete();
                    return;
                }
                if (commitTree.getMarked().contains(filename)) {
                    commitTree.removeRmMarked(filename);
                    commitTree.serialize();
                }
            }
            File toStage = new File(".gitlet/staging/" + filename);
            Utils.writeContents(toStage, toAddContents);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void commit(String commit) {

    }
}
