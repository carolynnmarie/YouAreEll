import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SimpleShell {


    public static void prettyPrint(String output) {
        System.out.println(output);
    }
    
    public static void main(String[] args) throws java.io.IOException {

        YouAreEll webber = new YouAreEll();
        String commandLine;
        BufferedReader console = new BufferedReader
                (new InputStreamReader(System.in));

        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> userInputHistory = new ArrayList<String>();
        int index = 0;

        //we break out with <ctrl c>
        while (true) {
            System.out.println("To receive all registered github ids, register a new id, or change the associated name, type 'ids'.\n" +
                    "To get messages, type 'messages'. \nTo send a message, type 'send'.\n" +
                    "To receive a history of previous commands, type 'history'.\n" +
                    "To exit, type exit. ");
            commandLine = console.readLine();
            if (commandLine.equals("")) continue;
            if (commandLine.equals("exit")) {
                System.out.println("Goodbye");
                break;
            }

            try {
                String specificCommand = "";
                ArrayList<String> commandList = new ArrayList<>();
                commandList.add(commandLine);
                MessageController mesCont = new MessageController();
                IdController idCont = new IdController();

                if (commandLine.equalsIgnoreCase("ids")) {
                    String results = "";
                    System.out.println("To retrieve all registered github ids, press enter\n" +
                            "To register a new id or change the name associated, type your name");
                    specificCommand = console.readLine();
                    if(specificCommand.equals("")) {
                        results = idCont.get_ids(commandList);
                    } else {
                        commandList.add(specificCommand);
                        System.out.println("Please enter your github id");
                        specificCommand = console.readLine();
                        commandList.add(specificCommand);
                        results = idCont.saveId(commandList);
                    }
                    SimpleShell.prettyPrint(results);
                    continue;
                }

                if (commandLine.equals("messages")) {
                    String results = "";
                    System.out.println("To retrieve the last 20 messages posted on the timeline, press enter\n" +
                            "To retrieve the last twenty messages sent to you enter your github id");
                    specificCommand = console.readLine();
                    if(specificCommand.equals("\n") || specificCommand.equals("")) {
                        results = mesCont.get_messages(commandList);
                    } else {
                        commandList.add(specificCommand);
                        results = mesCont.get_my_messages(commandList);
                    }
                    SimpleShell.prettyPrint(results);
                    continue;
                }


                if(commandLine.equalsIgnoreCase("send")){
                    String results = "";
                    System.out.println("Please enter your message");
                    specificCommand = console.readLine();
                    if(!specificCommand.equals("")) {
                        commandList.add(specificCommand);
                    }
                    System.out.println(("Enter your github id"));
                    if(!specificCommand.equals("")) {
                        commandList.add(specificCommand);
                        results = mesCont.post_world(commandList);
                    }
                    System.out.println(("If you wish to send your message to another github user, enter their github id." +
                            "If you wish to send a general message to the timeline, press enter"));
                    if (specificCommand.equals("")) {
                        results = mesCont.post_world(commandList);
                    }
                    if(!specificCommand.equals("")) {
                        commandList.add(specificCommand);
                        results = mesCont.post_friend(commandList);
                    }
                    SimpleShell.prettyPrint(results);
                    continue;
                    }


                //print history, moved it here so full commands will be in history
                userInputHistory.addAll(commandList);
                if (commandLine.equalsIgnoreCase("history")) {
                    for (String s : userInputHistory)
                        System.out.println((index++) + " " + s);
                    continue;
                }


                //the !! command returns the last command in userInputHistory
                if (commandList.get(commandList.size() - 1).equals("!!")) {
                    processBuilder.command(userInputHistory.get(userInputHistory.size() - 2));
                }
                // !<integer value i> command
                else if (commandList.get(commandList.size() - 1).charAt(0) == '!') {
                    int b = Character.getNumericValue(commandList.get(commandList.size() - 1).charAt(1));
                    if (b <= userInputHistory.size())
                        processBuilder.command(userInputHistory.get(b));
                } else {
                    processBuilder.command(commandList);
                }

                // wait, wait, what curiousness is this?
                Process process = processBuilder.start();

                //obtain the input stream
                InputStream is = process.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(isr);

                //read output of the process
                String line;
                while ((line = reader.readLine()) != null)
                    System.out.println(line);
                reader.close();


            }

            //catch ioexception, output appropriate message, resume waiting for input
            catch (IOException e) {
                System.out.println("Input Error, Please try again!");
            }
            // So what, do you suppose, is the meaning of this comment?
            /** The steps are:
             * 1. parse the input to obtain the command and any parameters
             * 2. create a ProcessBuilder object
             * 3. start the process
             * 4. obtain the output stream
             * 5. output the contents returned by the command
             */

        }


    }

}