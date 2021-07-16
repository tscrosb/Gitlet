package gitlet;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author Thomas Crosbie-Walsh
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            throw Utils.error("Must have at least one argument");
        }
        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                Repository.init();
                break;
            case "add":
                Repository.add(args[1]);
                break;
            case "commit":
                Repository.commit(args[1]);
                break;
            case "rm":
                break;

        }
    }
}
