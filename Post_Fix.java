import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Post_Fix {
	private String a;
	private Post_Fix fils_gauche, fils_droit;
	
	 public Post_Fix(){	 }
	 public Post_Fix(String val){
		 this.a = val;
	 }
	 
	  
	    public String getValeur() {
	        return(a);
	    }
	 
	 public Post_Fix getArbre_Gauche() {
	        return(fils_gauche);
	    }   

	    public Post_Fix getArbre_Droit() {
	        return(fils_droit);
	    }
	 
	 public void setfilsgauche(Post_Fix ab){
		 this.fils_gauche = ab;
	 }
	 
	 public void setfilsdroit(Post_Fix ab){
		 this.fils_droit = ab;
	 }
	 
	//------->>>> AFFICHER
	    public String toString() {
	        return toString("\t");
	    }

	    public String toString(String s) {
		if (fils_gauche!=null) {
		if (fils_droit!=null) 
		    return(s+"_("+a+")___\n"+fils_gauche.toString(s+"\t")+fils_droit.toString(s+"\t"));
		else
		    return(s+a+"\n"+fils_gauche.toString(s+"\t")+"\n");
	        }
	        else 
		if (fils_droit!=null) 
		    return(s+a+"\n\n"+fils_droit.toString(s+"\t"));
		else
		    return(s+a+" \n");
	    }

	private static Scanner scanner;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Post_Fix aux,courant, racine;
		
		 scanner = new Scanner(System.in);
		  System.out.println("+===========================Veiller entrer l'expression postfixée===========================+");
		  ArrayList<String> arraylist ; 
		  String in = scanner.nextLine();
		  
		  arraylist = new ArrayList<>(Arrays.asList(in.split(" ")));
		  
		  ArrayDeque<Post_Fix> pil = new ArrayDeque<Post_Fix>();
		  for (String s : arraylist){
			  courant = new Post_Fix(s);
			  if (is_Operator(s)){
				 aux =  pil.pop();
				 courant.setfilsgauche(pil.pop());
				 courant.setfilsdroit(aux);
			  }System.out.println("===========================+++++++++++++++++++========================");
			  pil.push(courant);
			  
		  }
		  racine = pil.pop();
                  System.out.println("===========================+++++++++++++++++++========================");
		  System.out.println("L'arbre est :---> " +racine);
		  System.out.println("===========================+++++++++++++++++++========================");
		  
		  //------------->>>>>>Prefix
		  String v1,v2,xor;
		  
		  ArrayDeque<String> operst = new ArrayDeque<String>();
		  for (String n : arraylist){
			  if (!is_Operator(n))
				  operst.push(n);
			  else{
				v2  = operst.pop();
			  	v1  = operst.pop();
			  	xor = n +" "+ v1 +" "+ v2;
			            operst.push(xor);
			  }
			            			  
		  }
                  System.out.println("===========================+++++++++++++++++++========================");
		  System.out.println("L'expression prefixee --->: " +operst.pop());
                  System.out.println("===========================+++++++++++++++++++========================");
		  
                   		  
		  
		  //----->>>>>EVALUATION
		  Double P, P1,som;
		  ArrayDeque<Double> abc = new ArrayDeque<Double>();
		  for (String n : arraylist){
			  if (!is_Operator(n))
				  abc.push(Double.valueOf(n));			
			  else{
				  P1 = abc.pop();			  	
				  P = abc.pop();
			  	som = operate(P,P1,n);			           
			  		abc.push(som);
			  }
			            			  
		  }
                  System.out.println("===========================+++++++++++++++++++========================");
		  System.out.println("l'evaluation --->: " +abc.pop());
                  System.out.println("===========================+++++++++++++++++++========================");
		
		
	}
	
	public void Afficher(Post_Fix a){
		System.out.println("\t\t\t\t"+a.getValeur());
		try{
		Post_Fix b = a.getArbre_Gauche();
		Post_Fix c = a.getArbre_Droit();
		System.out.println("\t\t\t"+b.getValeur()+" \t\t "+c.getValeur());
		Post_Fix d = b.getArbre_Gauche();	
                Post_Fix f,g;
		Post_Fix e = b.getArbre_Droit();
		f= c.getArbre_Gauche(); 
                g = c.getArbre_Droit();
		System.out.println("\t\t"+d.getValeur()+" \t"+e.getValeur()+"\t \t "+f.getValeur()+"\t"+g.getValeur());}
		finally{}
	}
	
	 private static double operate(Double a, Double b, String op){
	       
	        switch (op){
	            case "+": return Double.valueOf(a) + Double.valueOf(b);
	            case "-": return Double.valueOf(a) - Double.valueOf(b);
	            case "*": return Double.valueOf(a) * Double.valueOf(b);
	            case "/": try{
	                return Double.valueOf(a) / Double.valueOf(b);
	            }catch (Exception e){
	                 e.getMessage();
	            }
	            default: return -1;
	        }
	    }
	 
	    private static boolean is_Operator(String op){
	        switch (op){
	            case "+":
	            case "-":
	            case "*":
	            case "/":return true;
	            default: return false;
	        }
	    }
}
