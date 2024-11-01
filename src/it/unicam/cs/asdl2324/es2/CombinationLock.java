package it.unicam.cs.asdl2324.es2;

/**
 * Un oggetto cassaforte con combinazione ha una manopola che può essere
 * impostata su certe posizioni contrassegnate da lettere maiuscole. La
 * serratura si apre solo se le ultime tre lettere impostate sono uguali alla
 * combinazione segreta.
 * 
 * @author Luca Tesei
 */
public class CombinationLock {

    //Variabili istanza che servono
	private String aCombination;	//Stringa contenete la Combinazione
	private boolean opened; //Flag combinazione aperta o chiusa
	private char[] lastPosition;	//Vettore contenete le ultime 3 cifre dell'input
	private int aPosition;	//Posizione

    /**
     * Costruisce una cassaforte <b>aperta</b> con una data combinazione
     * 
     * @param aCombination
     *                         la combinazione che deve essere una stringa di 3
     *                         lettere maiuscole dell'alfabeto inglese
     * @throw IllegalArgumentException se la combinazione fornita non è una
     *        stringa di 3 lettere maiuscole dell'alfabeto inglese
     * @throw NullPointerException se la combinazione fornita è nulla
     */
    public CombinationLock(String aCombination) {
    	
    	if(aCombination == null) {
        	throw new NullPointerException("Combinazione nulla");
        }
    	else if((aCombination.length()!=3) || (!aCombination.matches("[A-Z]+")) ){
    		throw new IllegalArgumentException("Combinazione contenente lettere non maiuscoli delll'alfabeto inglese"
    				+ " opure non contenete 3 caratteri");
    	} else {
    		this.aCombination=aCombination;
    		this.opened=true;
    		this.lastPosition=new char[]{' ',' ',' '};	//Combinazione inserita vuota
    		this.aPosition=0;
    	}
    	
    }

    /**
     * Imposta la manopola su una certaposizione.
     * 
     * @param aPosition
     *                      un carattere lettera maiuscola su cui viene
     *                      impostata la manopola
     * @throws IllegalArgumentException
     *                                      se il carattere fornito non è una
     *                                      lettera maiuscola dell'alfabeto
     *                                      inglese
     */
    public void setPosition(char aPosition) {
        if((Character.isLowerCase(aPosition)) || (aPosition < 'A') || (aPosition > 'Z')) { //Uso l'oggetto java Character per effettuare il controllo
        	throw new IllegalArgumentException("Combinazione non maiuscola");
        }
        this.lastPosition[this.aPosition]=aPosition;
        
        if(this.aPosition==2) { //Se mi trovo nell'ultima posizione del vettore (pos: x,x,*) devo tornare alla prima posizione
        	this.aPosition=0;
        }
        else this.aPosition++;	//Altrimenti incremento la posizione
    }

    /**
     * Tenta di aprire la serratura considerando come combinazione fornita le
     * ultime tre posizioni impostate. Se l'apertura non va a buon fine le
     * lettere impostate precedentemente non devono essere considerate per i
     * prossimi tentativi di apertura.
     */
    public void open() {
    	if(this.lastPosition[0]==this.aCombination.charAt(0) && 
    	   this.lastPosition[1]==this.aCombination.charAt(1) && 
    	   this.lastPosition[2]==this.aCombination.charAt(2)) {
    		
    		this.opened=true;
    	} else {
    		this.lastPosition=new char[]{' ',' ',' '};	//Combinazione inserita vuota
    		this.aPosition=0;	//Posizione di inserimento 0
    	}
    		
    }

    /**
     * Determina se la cassaforte è aperta.
     * 
     * @return true se la cassaforte è attualmente aperta, false altrimenti
     */
    public boolean isOpen() {
        return this.opened;
    }

    /**
     * Chiude la cassaforte senza modificare la combinazione attuale. Fa in modo
     * che se si prova a riaprire subito senza impostare nessuna nuova posizione
     * della manopola la cassaforte non si apre. Si noti che se la cassaforte
     * era stata aperta con la combinazione giusta le ultime posizioni impostate
     * sono proprio la combinazione attuale.
     */
    public void lock() {
        this.opened=false;
        this.lastPosition=new char[]{' ',' ',' '};	//Combinazione inserita vuota
		this.aPosition=0;
    }

    /**
     * Chiude la cassaforte e modifica la combinazione. Funziona solo se la
     * cassaforte è attualmente aperta. Se la cassaforte è attualmente chiusa
     * rimane chiusa e la combinazione non viene cambiata, ma in questo caso le
     * le lettere impostate precedentemente non devono essere considerate per i
     * prossimi tentativi di apertura.
     * 
     * @param aCombination
     *                         la nuova combinazione che deve essere una stringa
     *                         di 3 lettere maiuscole dell'alfabeto inglese
     * @throw IllegalArgumentException se la combinazione fornita non è una
     *        stringa di 3 lettere maiuscole dell'alfabeto inglese
     * @throw NullPointerException se la combinazione fornita è nulla
     */
    public void lockAndChangeCombination(String aCombination) {
    	if(!this.isOpen())
    		return;
    	
    	if(aCombination == null) {
        	throw new NullPointerException("Combinazione nulla");
        }
    	else if((aCombination.length()!=3) || (!aCombination.matches("[A-Z]+")) ){
    		throw new IllegalArgumentException("Combinazione contenente lettere non maiuscoli delll'alfabeto inglese"
    				+ " opure non contenete 3 caratteri");
    	} else {
    		this.aCombination=aCombination;
    		this.opened=false;
    		this.lastPosition=new char[]{' ',' ',' '};	//Combinazione inserita vuota
    		this.aPosition=0;
    	}
    }
}