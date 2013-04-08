package application;

import wrappers.JMSSender;

/**
 * Main class of application.
 * @author skuarch
 */
public class Main {

    //==========================================================================
    /**
     * create a instance
     */
    public Main() {

    } // end Main
    
    /**
     * 
     * @param args the command line arguments are ignored
     */
    public static void main(String[] args) {
        new Main().startProgram();
        System.exit(0);
    } // end main
    
    //==========================================================================
    private void startProgram(){
        
        try {            
            new JMSSender().sendMessage();
            System.out.println("the message was sent");
        } catch (Exception e) {
            e.printStackTrace();
        }        
        
    } // end startProgram

} // end class
