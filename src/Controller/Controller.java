package Controller;

public class Controller {

    public Controller() {
        
    }
    
    public String createIDKainCustom(int noKain){
        String id = "CUSTOM-" + String.valueOf(noKain);
        return id;
    }
    
}
