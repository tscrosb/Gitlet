package gitlet;

import java.io.File;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class CommitTree implements Serializable {



    private String headBranch;

    private ArrayList<String> removed;

    private ArrayList<String> marked;

    private ArrayList<String> commits;

    private ArrayList<String> branches;

    private HashMap<String, String> branchMap;

    CommitTree(Commit head) {
        String headsha1 = head.getSha1();
        branches = new ArrayList<>();
        headBranch = "master";
        branchMap = new HashMap<>();
        branchMap.put(headBranch, headsha1);
        branches.add(headBranch);
        marked = new ArrayList<>();
        commits = new ArrayList<>();
        commits.add(headsha1);
        removed = new ArrayList<>();
    }

    Commit getHeadCommit() {
        String headsha1 = this.branchMap.get(this.headBranch);
        File file = new File(".gitlet/objects/" + headsha1);
        return Utils.readObject(file, Commit.class);
    }

    Commit getHeadCommit(String branchName) {
        String headsha1 = this.branchMap.get(branchName);
        File file = new File(".gitlet/objects/" + headsha1);
        return Utils.readObject(file, Commit.class);
    }

    void setHead(String sha) {
        String headsha = sha;
        branchMap.replace(headBranch, headsha);
    }

    void setHeadBranchName(String name) {
        headBranch = name;
    }

    void addBranch(String branchName) {
        branchMap.put(branchName, this.getHeadSHA1());
        branches.add(branchName);
    }

    void rmBranch(String branchName) {
        branchMap.remove(branchName);
        branches.remove(branchName);
    }

    String getHeadName() {
        return headBranch;
    }

    void addCommit(String sha) {
        commits.add(sha);
    }

    ArrayList<String> getCommits() {
        return commits;
    }

    ArrayList<String> getMarked() {
        return marked;
    }

    void addRmMarked(String filename) {
        marked.add(filename);
    }

    void removeRmMarked(String filename) {
        marked.remove(filename);
    }

    String getHeadSHA1() {
        return branchMap.get(headBranch);
    }

    void addRemoved(String filename) {
        removed.add(filename);
    }

    ArrayList<String> getRemoved() {
        return removed;
    }

    /** Clears _rmMarked. */
    void clearrmMarked() {
        marked.clear();
    }

    /** Returns _branchMap. */
    HashMap<String, String> getBranchMap() {
        return branchMap;
    }

    /** Returns _branches. */
    ArrayList<String> getBranches() {
        return branches;
    }

    /** Serialize this commit tree. */
    void serialize() {
        File f = new File(".gitlet/objects/" + "CT");
        Utils.writeObject(f, this);
    }

    Commit getCommit(String sha) {
        String[] serializedfiles = new File(".gitlet/objects/").list();
        if (serializedfiles != null) {
            for (String serializedfile: serializedfiles) {
                if (!serializedfile.equals("CT")
                        && serializedfile.contains(sha)) {
                    return Utils.readObject(
                            new File(".gitlet/objects/" + serializedfile),
                            Commit.class);
                }
            }
        }
        System.out.println("No commit with that id exists.");
        return null;
    }

}
