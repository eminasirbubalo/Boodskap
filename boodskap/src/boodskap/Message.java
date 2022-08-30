package boodskap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;



public class Message {
	
	private String content; //
	private String sender;  //
	private File file = new  File("message.txt"); //
	private FileWriter fw = new FileWriter(file.getAbsoluteFile(), true); 
    private BufferedWriter bw = new BufferedWriter(fw); // 
    //private BufferedReader br;
    private Encryption enc; //stores encryption object
  
    

	public Message(String username) throws IOException {
		this.sender=username;  //constructor, stores username

	}
	
	public void addMessage(String content) throws IOException
	{
		this.enc=new Encryption(content);   //creates new encryption object,with the content of message
		String encrypted=enc.encrypt();  //encrypts the content of the message
		this.bw.write(this.sender + ": "+ encrypted); //writes to file
		this.bw.newLine(); //new line
		this.bw.close(); //closes writer
	}
	
	public void deleteMessages()
	{
		Path path = Path.of("C:\\Users\\cutec\\eclipse-workspace\\boodskap\\");
		try {
			Files.writeString(path.resolve("message.txt"), "", StandardOpenOption.TRUNCATE_EXISTING);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	

	
}


  
   