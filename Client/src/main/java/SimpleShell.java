import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SimpleShell {


    public static void prettyPrintId(String output) {
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder result = new StringBuilder();
        Id[] users = null;
        try {
             users= objectMapper.readValue(output, Id[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Id user: users){
            result.append("name: ")
                    .append(user.getName())
                    .append(", github: ")
                    .append(user.getGithub())
                    .append("\n");
        }
        System.out.println(result.toString());
    }

    public static void prettyPrintMessage(String output){
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder result = new StringBuilder();

        Message[] messages = null;
        try {
            messages = objectMapper.readValue(output, Message[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Message mess: messages){
            if(mess.getToid().equals("")) {
                result.append("from: ")
                        .append(mess.getFromid())
                        .append(", message: ")
                        .append(mess.getMessage())
                        .append("\n");
            } else {
                result.append("from: ")
                        .append(mess.getFromid())
                        .append(" to: ")
                        .append(mess.getToid())
                        .append(", message: ")
                        .append(mess.getMessage())
                        .append("\n");
            }
        }
        System.out.println(result.toString());
    }

    public static boolean nextCommand()throws java.io.IOException{
        String commandLine;
        BufferedReader console = new BufferedReader
                (new InputStreamReader(System.in));
        System.out.println("To enter another command, press enter.  To exit, type exit.");
        commandLine = console.readLine();
        if (commandLine.equals("")) return true;
        if (commandLine.equals("exit")) {
            System.out.println("Goodbye");
        }
        return false;
    }
    
    public static void main(String[] args) throws java.io.IOException {

        String commandLine;
        BufferedReader console = new BufferedReader
                (new InputStreamReader(System.in));

        ProcessBuilder processBuilder = new ProcessBuilder();
        List<String> userInputHistory = new ArrayList<>();
        int index = 0;
        boolean next = true;

        //we break out with <ctrl c>
        while (next) {
            System.out.println("To receive all registered github ids, register a new id, or change the associated name, type 'ids'.\n" +
                    "To get messages, type 'messages'. \nTo send a message, type 'send'.\n" +
                    "To receive a history of previous commands, type 'history'.\n" +
                    "To exit, type exit. ");
            commandLine = console.readLine();
            if (commandLine.equals("")) next = true;
            if (commandLine.equals("exit")) {
                System.out.println("Goodbye");
                next = false;
            }
            String results = "";
            try {
                String specificCommand = "";
                ArrayList<String> commandList = new ArrayList<>();
                commandList.add(commandLine);
                MessageController mesCont = new MessageController();
                IdController idCont = new IdController();

                if (commandLine.equalsIgnoreCase("ids")) {
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
                    SimpleShell.prettyPrintId(results);
                    next = SimpleShell.nextCommand();

                } else if (commandLine.equals("messages")) {
                    System.out.println("To retrieve the last 20 messages posted on the timeline, press enter\n" +
                            "To retrieve the last twenty messages sent to you enter your github id");
                    specificCommand = console.readLine();
                    if(specificCommand.equals("\n") || specificCommand.equals("")) {
                        results = mesCont.get_messages(commandList);
                    } else {
                        commandList.add(specificCommand);
                        results = mesCont.get_my_messages(commandList);
                    }
                    SimpleShell.prettyPrintMessage(results);
                    next = SimpleShell.nextCommand();

                } else if(commandLine.equalsIgnoreCase("send")){
                    System.out.println("Please enter your message");
                    specificCommand = console.readLine();
                    if(!specificCommand.equals("")) {
                        commandList.add(specificCommand);
                    }
                    System.out.println(("Enter your github id"));
                    specificCommand = console.readLine();
                    if(!specificCommand.equals("")) {
                        commandList.add(specificCommand);
                        results = mesCont.post_world(commandList);
                    }
                    System.out.println(("If you wish to send your message to another github user, enter their github id." +
                            "If you wish to send a general message to the timeline, press enter"));
                    specificCommand = console.readLine();
                    if (specificCommand.equals("")) {
                        results = mesCont.post_world(commandList);
                    } else if(!specificCommand.equals("")) {
                        commandList.add(specificCommand);
                        results = mesCont.post_friend(commandList);
                    }
                    SimpleShell.prettyPrintMessage(results);
                    next = SimpleShell.nextCommand();
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