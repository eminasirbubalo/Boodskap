package boodskap;

public class Encryption {
	
	private String message;  
	
	public Encryption(String content)
	{
		this.message=content;  
	}
	
	public String encrypt()
	{
		int key=4;         
		String encrypted=""; 
	    for(int i=0;i<this.message.length();i++)
	    {
	        int ch=this.message.charAt(i);  
	    
	        if(Character.isUpperCase(ch))
	        {
	            ch=ch+(key%26);  //caesar formula
	            //if c value exceeds the ascii value of 'Z' reduce it by subtracting 26(no.of alphabets) to keep in boundaries of ascii values of 'A' and 'Z'
	            if(ch>'Z')
	                ch=ch-26;
	        }
	        //encryption logic for lowercase letters
	        else if(Character.isLowerCase(ch))
	        {
	             ch=ch+(key%26);
	            //if c value exceeds the ascii value of 'z' reduce it by subtracting 26(no.of alphabets) to keep in boundaries of ascii values of 'a' and 'z'
	            if(ch>'z')
	                ch=ch-26;
	        }
	     
	        encrypted=encrypted+(char)ch; //add character to encrypted string
	    }
	    return encrypted; //return encrypted
	}
	

}
