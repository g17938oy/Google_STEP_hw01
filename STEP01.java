import java.io.*;
import java.util.*;
public class STEP01 {
    
    static ArrayList<String> sus;
    static ArrayList<ArrayList<String>> dictionary;
    static ArrayList<ArrayList<String>> dictionary2;
    
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	String word = sc.next();
	sus = doInput(word);
	Collections.sort(sus);

	File file = new File("dictionary.words");
	try {
	    FileReader fr = new FileReader(file);
	    BufferedReader br = new BufferedReader(fr);

	    dictionary = new ArrayList<ArrayList<String>>();
	    dictionary2 = new ArrayList<ArrayList<String>>();

	    String dicWord;

	    int num = 0;

	    //qについて調べたい
	    //for(int g = 0; g < 53200; g++)dicWord = br.readLine();
		
	    while ((dicWord = br.readLine()) != null) {
		ArrayList<String> p = doInput(dicWord);
		dictionary.add(p);
		ArrayList<String> q = doInput(dicWord);
		Collections.sort(q);
		dictionary2.add(q);
		num++;
	    }	      
	    br.close();	    
	} catch (FileNotFoundException e) {
	    e.printStackTrace();	
	} catch (IOException e) {
	    e.printStackTrace();
	}

	System.out.println(Search());
    }
	
    private static String Search() {
	String s = "";
	for (int i = 0; i < dictionary2.size(); i++) {
	    ArrayList<String> c = dictionary2.get(i);
	    if (c.size() != sus.size()) continue;
	    
	    boolean flag = true;
	    
	    for (int j = 0; j < c.size(); j++) {
		if(!c.get(j).equals(sus.get(j))) {	
		    flag = false;
		    break;	
		}
	    }
	    
	    if (flag) {
		ArrayList<String> ans = dictionary.get(i);
		for (int k = 0; k < ans.size(); k++) s = s + ans.get(k);
		break;
	    }	   	    
	}
	return (s.length() != 0) ? s:"NO ANSWER";
    }


    private static ArrayList<String> doInput(String s) {
	ArrayList<String> c = new ArrayList<String>();
	for (int i = 0; i < s.length(); i++) {
	    /*今回はアルファベットのアナグラムでASCIIコードの範囲内なので
	     *気を使う必要がない。日本語だったらエンコーディング指定に注意
	     */

	    char p = ( (int)s.charAt(i) < 97 ) ? (char)(s.charAt(i) + 32) : s.charAt(i);
	    String q;
	    if (p == 'q') {
		q = "qu";
		i++;
	    } else {
		q = String.valueOf(p);
	    }
	    c.add(q);		    
	}
	
	/*//表示
	System.out.println("doInput:");
	for (int i = 0; i < c.size(); i++) {
	    System.out.print(c.get(i)+" ");
	}
	System.out.println();
	*/
	return c;
	
    }
}
