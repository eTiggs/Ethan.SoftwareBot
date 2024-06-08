package software.ethan;

import org.apache.commons.cli.*;

public class Main {
    public static void main(String[] args) {
        Options options = new Options();

        options.addOption("h", "help", false, "Displays this help menu.");
        options.addOption("t", "token", true, "Provide the token during startup.");

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("help")) {
                formatter.printHelp("Help Menu", options);
                System.exit(0);
            }

            String token = cmd.hasOption("token") ? cmd.getOptionValue("token") : null;
            if (token == null) {
                System.out.println("ERROR: No token provided, please provide a token using the -t or --token flag.");
                formatter.printHelp(" ", options);
                System.exit(0);
            }

            MrBot.selfBot = new MrBot(token);
        } catch (ParseException e) {
            System.out.println("ERROR: " + e.getMessage());
            formatter.printHelp("", options);
            System.exit(0);
        }
    }
}
