package de.masternoir.pw_gen;

import java.security.SecureRandom;

/**
 * Hello world!
 *
 */
public class Password_Generator 
{
	public boolean b_letters_lower_case = false;
	public boolean b_letters_upper_case = false;
	public boolean b_numbers = false;
	public boolean b_additional_characters = false;
	
	String letters_lower_case = "abcdefghijklmnopqrstuvwxyz";
	String letters_upper_case = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String numbers = "0123456789";
	String additional_characters = "!?#+~-_/*§$%&";
	
    public static void main( String[] args )
    {	
    	Password_Generator pw_gen = new Password_Generator();
    	pw_gen.getSimplePw(5,pw_gen.numbers);
    }
    
    public String getSimplePw(int len, String chars){
    	
    	SecureRandom random = new SecureRandom();
    	StringBuilder pw = new StringBuilder(len);
    	
    	for(int i = 0; i < len; i++){
    		pw.append(chars.charAt(random.nextInt(chars.length())));
    	}
    	
    	System.out.println(pw.toString());
    	
    	return pw.toString();
    }
    
    public String getCharacters(){
    	
    	String characters = "";
    	
    	if(b_letters_lower_case){
    		characters = characters + letters_lower_case;
    	}
		if(b_letters_upper_case){
			characters = characters + letters_upper_case;
		}
		if(b_numbers){
			characters = characters + numbers;
		}
		if(b_additional_characters){
			characters = characters + additional_characters;
		}    	
    	
    	return characters;
    }
}
