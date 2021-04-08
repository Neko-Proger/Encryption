
import org.kohsuke.args4j.*;

import java.util.ArrayList;
import java.util.List;


public class Parser {

    @Option(name = "-c", usage = "encryption file")
    private boolean encryption = false;

    @Option(name = "-d", usage = "decryption file")
    private boolean decryption = false;

    @Option(name = "-o", usage = "output to this file (default: inputname.txt)", metaVar = "OUTPUT")
    private String out;

    @Argument
    private List<String> arguments = new ArrayList<String>();

    public static void main(String[] args) {
        new Parser().parseArgs(args);
    }
    public void parseArgs(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            throw new IllegalArgumentException("");
        }
        if (out == null){
            out =arguments.get(2);
        }
        encryption e = new encryption(arguments.get(1),arguments.get(2),out);
        if (encryption)
            e.fileCoding();
        else
            e.fileDecoding();
    }
}