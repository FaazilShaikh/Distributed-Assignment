import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {

    public static void main(String[] args) throws IOException {
        int portNumber = 8080; //can change port number here

        ServerSocket serverSocket = new ServerSocket(portNumber); //create server socket
        while (true) {
            try {

                Socket clientSocket = serverSocket.accept(); //start accepting clients

                new Thread(new Runnable() { //create new thread
                    @Override
                    public void run() {
                        Client(clientSocket);
                    } //client handler
                }).start(); //start thread

            } catch (IOException e) { //catch any exceptions
                System.out.println(e.getMessage());
            }

        }
    }

    public static void Client(Socket clientSocket){
        //prints client ip, port
        System.out.println("Connected \nIP: " + clientSocket.getInetAddress() + "\nPort:" + clientSocket.getPort());

        //get input/output streams
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        } catch (IOException e) {

        }


        String inputLine =null;

        while (true) {
            try {  //start reading input from client
                if ((inputLine = in.readLine()) == null) break; //if input is null then break out of loop
            }
            catch (IOException e) {

            }
            if (inputLine == null) break;
            String[] splitStr = inputLine.split(" "); //split string by spaces so we can get a command followed by arguments if needed



            if (splitStr[0].equalsIgnoreCase("rps")) { //start checking commands
                if (splitStr.length < 2) {out.println("Invalid Input!"); continue;}//if no selection is chosen continue to next input
                String[] choices = {"rock", "paper", "scissors"};
                String choice = splitStr[1].toLowerCase();
                Random rand = new Random(); //create random object
                String ServerChoice = choices[(int) (rand.nextInt(choices.length))]; //select random choice
                System.out.println((int) (Math.random() * 3));
                if ((choice.equals("rock") && ServerChoice.equals("scissors") || (choice.equals("paper") && ServerChoice.equals("rock")) || (choice.equals("scissors") && ServerChoice.equals("paper")))) {
                    out.print("You beat the server!");

                } else if ((choice.equals("scissors") && ServerChoice.equals("rock") || (choice.equals("rock") && ServerChoice.equals("paper")) || (choice.equals("paper") && ServerChoice.equals("scissors")))) {
                    out.print("The server won! Better luck next time!");

                }
                else if (choice.equals(ServerChoice)){
                    out.print("You tied with the server!");
                }
                else {
                    out.print("Invalid Input!");
                }
                out.println(" Server choice: " + ServerChoice + " , Your choice: " + choice);
            }
            else if (splitStr[0].equalsIgnoreCase("mq")){ //continue checking commands

                int score = 0; //score to hold how many questions are answered correctly

                for (int i=0;i<5;i++){ //5 questions in total
                    int[] factors = {(int) (Math.random() * 10), (int) (Math.random() * 10)}; //random factors for a multiplication question
                    Integer answer = factors[0]*factors[1]; //multiplicant answer
                    out.println("What is : " + factors[0] + " * " + factors[1] + "?"); //print out a question to the user to answer
                    try {
                        if ((inputLine = in.readLine()) == null) break; //check for invalid input
                    } catch (IOException e) { }

                    try{
                        if (Integer.parseInt(inputLine) == answer){ //if input was a number and it is equal to the answer then it is correct
                            score += 1;
                        }
                    }
                    catch(NumberFormatException e){
                        out.print("Invalid Input! "); //input was invalid, input could not be parsed
                        break;
                    }

                }
                out.println("You got " + score + " questions correct!"); //print out how many questions the user got correct
            }
            else if (splitStr[0].equalsIgnoreCase("sphereVolume")) {
                if (splitStr.length < 2) {out.println("Invalid Input!"); continue;}//if no selection is chosen break
                double volume;
                double radius;
                try{
                    radius = Double.parseDouble(splitStr[1]); //parse string for radius
                }
                catch(NumberFormatException e){
                    out.print("Invalid Input! "); //input was invalid, input could not be parsed
                    break;
                }
                volume = (4/3d)*(Math.PI)*Math.pow(radius,3);//compute volume
                out.println("The sphere with radius : " + radius + " has a volume of: " + volume);
            }
            else if (splitStr[0].equalsIgnoreCase("conch")){
                if (splitStr.length < 2) {out.println("Invalid Input!"); continue;}//if no selection is chosen break
                String[] choices = {"yes", "no", "maybe", "definitely", "I don't think so"}; //choices for the conchshell
                Random rand = new Random(); //create random object
                String ServerChoice = choices[(int) (rand.nextInt(choices.length))]; //select random choice
                out.println("Conchshell says: " + ServerChoice); //
            }

            else{ //not a command, input was invalid
                out.println("Invalid Input!");
            }


        }

    }



}