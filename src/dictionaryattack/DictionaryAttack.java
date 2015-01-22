package dictionaryattack;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.apache.commons.codec.binary.Hex;


public class DictionaryAttack {
	private Map<String, String> passwordMap;
	private Map<String, String> hashDictionary;
	private Map<String, String> unhashedPasswordMap;
	
	public DictionaryAttack(){
		this.passwordMap = new HashMap<String, String>();
		this.hashDictionary = new HashMap<String, String>();
		this.unhashedPasswordMap = new HashMap<String, String>();
	}

	/**
	 * Reads a password file. Each line of the password file has the following form:
	 * username: encodedpassword
	 * 
	 * After calling this method, the passwordMap class variable should be filled with
	 * the content of the file. The key for the map should be the username and the
	 * password hash should be the content.
	 * @param filename
	 */
	public void readPasswords(String filename) throws FileNotFoundException{		
		BufferedReader bReader = new BufferedReader(new FileReader(filename));
		Scanner scanner = new Scanner(bReader);
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			String[] lineData = line.split(": ");
			this.passwordMap.put(lineData[0], lineData[1]);
		}
		scanner.close();
	}

	/**
	 * Given a password, return the MD5 hash of a password. The resulting
	 * hash (or sometimes called digest) should be hex-encoded in a String.
	 * @param password
	 * @return
	 */
	public String getPasswordHash(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] hash = md.digest(password.getBytes());
		String hashString = Hex.encodeHexString(hash);
		return hashString;
	}
	/**
	 * Check the password for the user the password list. If the user does not exist,
	 * return false.
	 * @param user
	 * @param password
	 * @return whether the password for that user was correct.
	 * @throws NoSuchAlgorithmException 
	 */
	public boolean checkPassword(String user, String password) throws NoSuchAlgorithmException {
		String otherPassword = this.passwordMap.get(user);
		if(otherPassword == null){
			return false;
		}
		return otherPassword.equals(getPasswordHash(password));
	}

	/**
	 * Reads a dictionary from file (one line per word) and use it to add entries to 
	 * a dictionary that maps password hashes (hex-encoded) to the original password.
	 * @param filename filename of the dictionary.
	 * @throws FileNotFoundException 
	 * @throws NoSuchAlgorithmException 
	 */
	public void addToHashDictionary(String filename) throws FileNotFoundException, NoSuchAlgorithmException {
		BufferedReader bReader = new BufferedReader(new FileReader(filename));
		Scanner scanner = new Scanner(bReader);		
		while (scanner.hasNextLine()){
			String word = scanner.nextLine();
			String wordHash = getPasswordHash(word);
			this.hashDictionary.put(word, wordHash);
		}
		scanner.close();
	}
	/**
	 * Do the dictionary attack.
	 * @throws FileNotFoundException 
	 */
	public void doDictionaryAttack() throws FileNotFoundException {
		for(String key: this.hashDictionary.keySet()){
			for (String username: this.passwordMap.keySet()){
				if(this.hashDictionary.get(key).equals(this.passwordMap.get(username))){
					this.unhashedPasswordMap.put(username, key);
				}
			}
		}
		
		
	}
	
	public Map<String, String> getUnhashedPasswords(){
		return this.unhashedPasswordMap;
	}
	
	public Map<String, String> getPasswordMap(){
		return this.passwordMap;
	}
	public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException{
		DictionaryAttack da = new DictionaryAttack();
		// To implement
		da.readPasswords(System.getProperty("user.dir")+"/src/dictionaryattack/bigsimplecorp_pw_hashes.txt");
		da.addToHashDictionary(System.getProperty("user.dir")+"/src/dictionaryattack/dictionary");        
		da.doDictionaryAttack();
		System.out.println(da.getUnhashedPasswords());
		System.out.println(String.format("There are %s users, %s of them have been 1337 h4xxd (W R An0nymous, W R LGN)", da.getPasswordMap().size(), da.getUnhashedPasswords().size()));
		//26^4, 26 verschillende tekens, een woord van 4 lang.
	}

}
