package logic;

import tiedot.Hakemisto;

public class Tulkki {
    private static final String ERROR_MESSAGE = "ERROR!";

    private Hakemisto juuri;
    private Hakemisto sijainti;



    public Tulkki() {
        helloText();
    }

    public boolean doCommand(String valinta) {

        switch (valinta) {

            case "md":
                System.out.println("md");
                break;
            case "mf":
                System.out.println("mf");
                break;
            case "cd":
                System.out.println("cd");
                break;
            case "ls":
                System.out.println("ls");
                break;
            case "rm":
                System.out.println("rm");
                break;
            case "mv":
                System.out.println("mv");
                break;
            case "cp":
                System.out.println("cp");
                break;
            case "find":
                System.out.println("find");
                break;
            case "exit":
                System.out.println("exit");
                return false;
            case "--help":
                helpText();
                break;
                default:
                    System.out.println(ERROR_MESSAGE);
        }


        return true;
    }


    /*
     * Print functions
     */

    public void helloText(){
        System.out.println("Welcome to SOS." +
                "\n/>");
    }

    public void helpText(){
        System.out.println("Commands you can do: " +
                "\nmd - creates new directory" +
                "\nmf - creates new file" +
                "\ncd - changes directory" +
                "\nls - lists directory" +
                "\nrm - removes file" +
                "\nmv - moves file" +
                "\ncp - copies file" +
                "\nfind - searchs for a file" +
                "\nexit - exit program");
    }
}
