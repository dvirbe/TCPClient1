import java.io.*;
import java.net.*;
import java.util.Scanner;

class TCPClient {

    public static void main(String argv[]) {
        String sentence;
        String modifiedSentence;

        try {

            Scanner inFromUser = new Scanner(System.in);

            Socket clientSocket = new Socket("localhost", 10000);


            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());

            BufferedReader inFromServer =
                    new BufferedReader(new
                            InputStreamReader(clientSocket.getInputStream()));

            while (true) {
                System.out.println("""
                        choose option:
                        Enter 1 to add new student
                        Enter 2 to check if student exist
                        Enter 3 to delete  student
                        Enter 4 to update student information
                        """);
				String option = inFromUser.nextLine();
				if (!option.matches("[1-4]")) {
					System.out.println("Invalid option. Please enter a valid option (1-4).");
					continue;
				}
				switch (option){
					case "1"->{
						outToServer.writeObject(option);
						System.out.println("insert id");
						String id = inFromUser.nextLine();

						System.out.println("insert name");
						String name = inFromUser.nextLine();

						System.out.println("insert phone");
						String phone = inFromUser.nextLine();

						Student s = new Student(id, name, phone);

						outToServer.writeObject(s);
					}
					case  "2"->{
						outToServer.writeObject(option);
						System.out.println("id of student you looking for");
						String id = inFromUser.nextLine();
						Student s = new Student(id, null,null);
						outToServer.writeObject(s);
					}
					case "3"->{
						outToServer.writeObject(option);
						System.out.println("id of student you want to delete ");
						String id = inFromUser.nextLine();
						Student s = new Student(id, null,null);
						outToServer.writeObject(s);
					}
					case "4"->{
						outToServer.writeObject(option);
						System.out.println("plz insert id of student you want to update");
						String id = inFromUser.nextLine();
						System.out.println("plz insert new student name");
						String name = inFromUser.nextLine();
						Student s = new Student(id, name,null);

						outToServer.writeObject(s);
					}
				}
                modifiedSentence = inFromServer.readLine();
                System.out.println("FROM SERVER: " + modifiedSentence);

            }


        } catch (ConnectException e) {
            System.out.println(" 404 C'ant connect to the Server");
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

