import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileApp {

    static ArrayList<File> fileList = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("===========================================================");
        System.out.println("\t\t\tWelcome to Lockers Pvt. Ltd.\t\t\n\t\t\t\t\tLockedMe.com");
        System.out.println("===========================================================");

        FileApp fa = new FileApp();
        fa.loadFiles();
        //fa.chooseOperation();
        fa.subOperation();

    }

    private void chooseOperation() {

        String[] options1 = {"Show current files in Locker.","Select a file from the Locker.","Close LockedMe"};

        System.out.println();
        System.out.println("Kindly select the desired operation:-\t\t|(eg.. 1 and hit enter!)");
        for(int i=0;i<options1.length;i++){
            System.out.println((i+1)+": "+options1[i]);
        }

        switch (new Scanner(System.in).nextInt()) {
            case 1 -> displayFiles();
            case 2 -> subOperation();
            case 3 -> exitOnclose();
            default -> {
                System.out.println("Incorrect option!...please choose wisely");
                chooseOperation();
            }
        }

    }

    private void displayFiles() {
        System.out.println();
        fileList.sort(Comparator.naturalOrder());
        fileList.iterator().forEachRemaining(e-> System.out.println(e.getName()));
        System.out.println();

        chooseOperation();
    }

    private void subOperation() {

        String[] options2 = {"Add a file to the Locker.", "Delete a file from the Locker.",
                "Search a file from the Locker","Return to previous menu.", "Close LockedMe"};

        System.out.println();
        System.out.println("Kindly select the desired operation:-\t\t|(eg.. 1 and hit enter!)");
        for (int i = 0; i < options2.length; i++) {
            System.out.println((i + 1) + ": " + options2[i]);
        }

        switch (new Scanner(System.in).nextInt()) {
            case 1 -> createFile();
            case 2 -> deleteFile();
            case 3 -> searchFile();
            case 4 -> previousMenu();
            case 5 -> exitOnclose();
            default -> {
                System.out.println("Incorrect option!...please choose wisely");
                subOperation();
            }

        }
    }

    private void previousMenu() {
        chooseOperation();
    }

    private void searchFile() {
        System.out.println();
        System.out.println("Enter the name of the file you want to search:- ");
        String name = caseCorrect(new Scanner(System.in).nextLine());
        String s;
        boolean flag = false;
        int index=0;

        for(int i=0;i<fileList.size();i++){

            if(fileList.get(i).getName().contains(".")){
                s=fileList.get(i).getName().substring(0,fileList.get(i).getName().indexOf("."));
            }else {
                s=fileList.get(i).getName();
            }
            if(s.equals(name)){
                flag = true;
                index = i;
                break;
            }
        }

        if(flag){
            System.out.println(fileList.get(index).getName()+" is present at "+index+"th location!");
        }else{
            System.out.println("There is no such file or folder present by the name of: "+name);
        }

        subOperation();
    }

    private String caseCorrect(String name) {

        StringBuilder sb = new StringBuilder(name.toLowerCase());
        sb.setCharAt(0,Character.toUpperCase(sb.charAt(0)));
        //sb.append(".txt");

        return sb.toString();
    }

    private void deleteFile() {

    }


    private void createFile() {

        System.out.println("Enter file name");
        String fname = caseCorrect(new Scanner(System.in).nextLine());
        String strPattern = "[^a-zA-Z0-9_]";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(fname);

        if(m.find()){

            System.out.println("!!No special characters or spaces are allowed");
            System.out.println("underscores (_) and numbers are eligible");

        } else {

            File file1 = new File("src/FilesLockerRoom/" + fname + ".txt");

            if (file1.exists()) {
                System.out.println("File already present!!");
            } else {
                try {
                    //noinspection ResultOfMethodCallIgnored
                    file1.createNewFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(file1.getName() + " is created!!");
                fileList.add(file1);
            }

        }

    }

    private void exitOnclose() {
        System.out.println("Thanks for using LockedMe!");
        System.exit(706839); // :)  {easter egg!!}
    }

    private void loadFiles() {
        //loaded directory
        final File locker = new File("src/FilesLockerRoom");
        //listed all items in locker as a file[] and added them in arraylist of files
        Arrays.stream(Objects.requireNonNull(locker.listFiles())).iterator().forEachRemaining(fileList::add);

        System.out.println("\n\t\t\t=======Files loaded!!========");
    }

}
