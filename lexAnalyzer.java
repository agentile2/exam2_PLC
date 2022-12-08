package exam2;
import java.util.*;
import java.io.*;

public class lexAnalyzer {
  //variables
	//to read file
	static File inputFile;
    static BufferedReader myReader;
  //assigns characters, lexemes, tokens  
    static String lexemes = "";
    static char nextCharacter;
    static int lexemeLen;
    static int characterClass;
    static int currentCharacter;
    static int nextToken;
    static int token;
    
  //rules for new language we're identifying
  //begin and end file codes 
    static final int start_stmnt = 0;
    static final int end_stmnt = 99;
    static final int nd_file = -1;
    //characters
    static final int lowerCase = 3;
    static final int Num = 7;
    static final int nt_defined = 6;
    
    //loop and conditional
    static final int loop_stmnt = 12; 
    static final int wthr_stmnt = 13;
   
    //data types
    static final int num_typ = 50;
    static final int strg_typ = 26;
    static final int bool_typ = 1;

 // tokens and codes  
    static final int num_lit = 27;
    static final int idn_fr = 44;
    static final int ass_n = 66;
    static final int decl_re = 55;
  
    //operations
    static final int div_de = 34;
    static final int mod_le = 35;
    static final int add_itn = 31;
    static final int sub_trct = 32;
    static final int mult_ply = 33;
  
    static final int lss_thn = 36;
    static final int gr_thn = 37; 
    static final int eq_to = 40;
    static final int nt_eq = 41;
    static final int less_eqthn = 38;
    static final int gr_eqthn = 39;
    
    static final int blk_lft = 95;
    static final int blk_rt = 96;
    static final int lt_prnth = 97;
    static final int rt_prnth = 98;
   

    // function to get the next character from file and find character class
    public static void getChar(){
        try{
            if((currentCharacter = myReader.read()) != nd_file){
                nextCharacter = (char) currentCharacter;
                if(Character.isLetter(nextCharacter)){
                    characterClass = lowerCase;
                }
                else if(Character.isDigit(nextCharacter)){
                    characterClass = Num;
                }
                else{
                    characterClass = nt_defined;
                }
            }
            else {
                characterClass = nd_file;
            }
        } catch (IOException e) {
            System.out.println("ERROR: Unable to read the file!");
        }
    }

    //adds new character to the existing lexemes
    public static void addChar(){
        lexemes += nextCharacter;
    }
    //calls for a new  character from getChar function to find non-whitespace
    public static void getNonWhiteSpace(){
        while(Character.isWhitespace(nextCharacter)){
            getChar();
        }
    }
    //function will match the unknown characters with operators, parenthesis, relational operators, and end statement symbols etc. and return token code*/
    public static int identifyUnknown(char myChar){
        switch(myChar){
        //order of operation: divide/mod/add/sub/mult
        	case '/':
        		addChar();
        		nextToken = div_de;
        		break;
        	case '%':
        		addChar();
        		nextToken = mod_le;
        		break;
            case '+':
                addChar();
                nextToken = add_itn;
                break;
            case '-':
                addChar();
                nextToken = sub_trct;
                break;
            case '*':
                addChar();
                nextToken = mult_ply;
                break;
            
            case '<':
                addChar();
                if(!Character.isWhitespace((char) currentCharacter++)){
                    getChar();
                    addChar();
                    nextToken = less_eqthn;
                    break;
                }
                nextToken = lss_thn;
                break;
            case '>':
                addChar();
                if(!Character.isWhitespace((char) currentCharacter++)){
                    getChar();
                    addChar();
                    nextToken = gr_eqthn;
                    break;
                }
                nextToken = gr_thn;
                break;
            case '=':
                addChar();
                if(!Character.isWhitespace((char) currentCharacter++)){
                    getChar();
                    addChar();
                    nextToken = eq_to;
                    break;
                }
                nextToken = ass_n;
                break;
            case '!':
                addChar();
                getChar();
                addChar();
                nextToken = nt_eq;
                break;
            case '(':
                addChar();
                nextToken = lt_prnth;
                break;
            case ')':
                addChar();
                nextToken = rt_prnth;
                break;
            case '{':
                addChar();
                nextToken = blk_lft;
                break;
            case '}':
                addChar();
                nextToken = blk_rt;
                break;
            default:
                addChar();
                nextToken = nd_file;
                break;
        }
        return nextToken;
    }
    //function will check if the current lexemes is equal to any keyword in the language and match the token code
    public static void identifyKeyword(){
        switch(lexemes){
            case "start":
                nextToken = start_stmnt;
                break;
            case "trats":
                nextToken = end_stmnt;
                break;
            case "repeat":
                nextToken = loop_stmnt;
                break;
            case "declare":
                nextToken = decl_re;
                break;
            case "whether":
                nextToken = wthr_stmnt;
                break;
            case "num":
                nextToken = num_lit;
                break;
            case "string":
                nextToken = strg_typ;
                break;
            case "bool":
                nextToken = bool_typ;
                break;
            default:
                nextToken = idn_fr;
                break;
        }
    }
    //main function for the lexical analyzer to get the token codes for each character class until the end of file is reached
    public static void analyzer(){
        lexemeLen = 0;
        getNonWhiteSpace();
        switch(characterClass){
            case lowerCase:
                addChar();
                getChar();
                while(characterClass == lowerCase || characterClass == nt_defined && nextCharacter == '_'){
                    addChar();
                    getChar();
                }
                identifyKeyword();
                break;
            case Num:
                addChar();
                getChar();
                while(characterClass == Num){
                    addChar();
                    getChar();
                }
                nextToken = num_lit;
                break;
            case nt_defined:
                identifyUnknown(nextCharacter);
                getChar();
                break;
            case nd_file:
                nextToken = nd_file;
                lexemes = "END";
                break;
        }
        System.out.println("Token: " + nextToken + ", \nLexeme: " + lexemes);
    }
  //main function - calls all other functions and reads a file/user input
    public static void main (String args[]) {
        //calls user input for file name 
        Scanner myFile = new Scanner(System.in);  
        System.out.println("Please enter your filename: ");
        String filename = myFile.nextLine();  
        myFile.close();

        //checks if you can open file 
        //sends error message if unable to open
        //else begin running code
        try{
            inputFile = new File(filename);
            FileReader readFileObj = new FileReader(inputFile);
            myReader = new BufferedReader(readFileObj);

            getChar();
            while(nextToken != nd_file)
            {
                analyzer();
                lexemes = "";
            }
        } 
        catch(FileNotFoundException error) {
            System.out.println("ERROR! Unable to open file! Please try again");
        }
    }
}