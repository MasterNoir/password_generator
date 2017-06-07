package de.masternoir.pw_gen;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class Password_Generator 
{
	private boolean b_letters_lower_case = false;
	private boolean b_letters_upper_case = false;
	private boolean b_numbers = false;
	private boolean b_additional_characters = false;
	
	private String letters_lower_case = "abcdefghijklmnopqrstuvwxyz";
	private String letters_upper_case = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String numbers = "0123456789";
	private String additional_characters = "!?#+~-_/*$%&";
	
    public static void main( String[] args )
    {	
    	Password_Generator pw_gen = new Password_Generator();
    	try {
			pw_gen.getComplexPassword(3, pw_gen.getSimplePw(3,pw_gen.numbers));
		} catch (PwTooShortException e) {
			e.printStackTrace();
		}
    }
    
    public String getSimplePw(int len, String chars){
    	
    	SecureRandom random = new SecureRandom();
    	StringBuilder pw = new StringBuilder(len);
    	
    	for(int i = 0; i < len; i++){
    		pw.append(chars.charAt(random.nextInt(chars.length())));
    	}
    	
    	return pw.toString();
    }
    
    public String getComplexPassword(int len, String simple_pw) throws PwTooShortException{
    	
    	if(len < 4){
			throw new PwTooShortException("Length of pw: " + len);
    	}
    	String pw = simple_pw;
    	
    	Pattern pat;
    	Matcher mat;
    	boolean matches = true;
    	String[] cases = {letters_lower_case, letters_upper_case, numbers, "\\p{Punct}"};
    	
    	for(String s : cases){
    		pat = Pattern.compile(".*[" + s + "].*");
    		mat  = pat.matcher(pw);
    		matches = mat.matches();
    		
    		/*System.out.println("##############");
    		System.out.println(".*[" + s + "].*");
    		System.out.println(pw);*/
    		
    		//if(!Pattern.matches( ".*([" + s + "]*).*", pw)){
    		if(!matches){
    			SecureRandom random = new SecureRandom();
    	    	StringBuilder modified_pw = new StringBuilder(len);
    	    	modified_pw.append(pw);
    	    	
    	    	modified_pw.setCharAt(random.nextInt(modified_pw.length()), (additional_characters.charAt(random.nextInt(additional_characters.length()))));
    	    	
    	    	System.out.println(modified_pw.toString());
    	    	return getComplexPassword(len, modified_pw.toString());
    		}
    	}
    	
    	System.out.println(pw);
    	return pw;
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
