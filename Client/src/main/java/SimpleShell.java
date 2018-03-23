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
                    System.out.println("To retrieve all registered github ids, press enter\nTo register a new id " +
                            "or change the name associated, type the name followed by the github id");
                    specificCommand = console.readLine();
                    if(specificCommand.equals("\n")) {
                        String results = idCont.get_ids(commandList);
                        SimpleShell.prettyPrint(results);
                        continue;
                    } else {
                        String [] c = specificCommand.split(" ");
                        commandList.addAll(Arrays.asList(c));
                        String save = idCont.saveId(commandList);
                        SimpleShell.prettyPrint(save);
                    }
                }

                if (commandLine.equals("messages")) {
                    System.out.println("To retrieve the last 20 messages posted on the timeline, press enter\n" +
                            "To retrieve the last twenty messages sent to you enter your github id");
                    specificCommand = console.readLine();
                    if(specificCommand.equals("\n")) {
                        String results = mesCont.get_messages(commandList);
                        SimpleShell.prettyPrint(results);
                        continue;
                    } else {
                        commandList.add(specificCommand);
                        String results = mesCont.get_my_messages(commandList);
                        SimpleShell.prettyPrint(results);
                    }
                }


                if(commandLine.equalsIgnoreCase("send")){
                    System.out.println("To send a message on the timeline type in 'your message' and your github id.\n" +
                            "To send a message to a friend, type 'your message', within single quotes, followed by your github id and then the");
                    specificCommand = console.readLine();
                    Pattern pattern = Pattern.compile("'.+'");
                    Matcher matcher = pattern.matcher(specificCommand);
                    if(matcher.find()){
                        String one = matcher.group();
                        String two = specificCommand.substring(matcher.end());
                        String[] twoSplit = two.split(" ");
                        commandList.add(one);
                        if(twoSplit.length == 1){
                            commandList.add(twoSplit[0]);
                            String results = mesCont.post_world(commandList);
                            SimpleShell.prettyPrint(results);
                        }
                        if(twoSplit.length == 2){
                            commandList.add(twoSplit[0]);
                            commandList.add(twoSplit[1]);
                            String results = mesCont.post_friend(commandList);
                            SimpleShell.prettyPrint(results);
                        }
                    }
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