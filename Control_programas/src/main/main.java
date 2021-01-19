package main;

import Frames.Principal;

public class main {
    public static void main(String[] args) {
        
        instanceValidator();
    }

    private static void instanceValidator() {
        if (new singleInstance().comprobar()) {
            new Principal().setVisible(true);
        } else {
            System.exit(0);
        }
    }
}
