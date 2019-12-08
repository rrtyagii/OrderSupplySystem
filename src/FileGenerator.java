import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileGenerator {
    private static char[] alphabet={'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'Y', 'X', 'Z'};

    private static final String generator = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";


    private static final Random rand = new Random();

     private static final Set<String> storage = new HashSet<String>();

    public static String randomNameGenerator() {
        StringBuilder checking = new StringBuilder();
        while(checking.toString().length() == 0) {
            int length = rand.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                checking.append(generator.charAt(rand.nextInt(generator.length())));
            }
            if(storage.contains(checking.toString())) {
                checking = new StringBuilder();
            }
        }
        return checking.toString();
    }


    public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            while (true) {
                System.out.println("" +
                        "Hey! If you want to create a new File press N/n \n" +
                        "OTHERWISE\n" +
                        "press Q/q to quit!");
                char in = scan.next().charAt(0);
                if (in == 'Q' || in=='q') break;
                else if (in == 'N' || in=='n') creatingNewFile();
                else {
                    System.out.println("Error:- Maybe your input was invalid. Check again!");
                }

            }
        }


    public static void creatingNewFile(){
    ArrayList<String> fileInput=new ArrayList<String>();
      try{
      Scanner scanner=new Scanner(new File("companylist.txt"));
      while(scanner.hasNextLine()){
                   fileInput.add(scanner.nextLine()); }
                 throw new FileGeneratorException(" ") ;
      } catch (FileGeneratorException | FileNotFoundException e){
               e.getMessage();
      }
      try{
          String randomName=randomNameGenerator();
          System.out.println(randomName+".txt");
          File fileNew = new File(randomName+".txt");

          if(!fileNew.exists()) fileNew.createNewFile();

          System.out.println("File creation successfull");

          FileWriter fileWriter=new FileWriter(fileNew);
          fileWriter.write("");

          Random randomGenerator=new Random();

          for(int i=0; i<50; i++){

              int repeated=randomGenerator.nextInt(1+1);
              int letter=randomGenerator.nextInt(25)+1;
              String company=fileInput.get(randomGenerator.nextInt(fileInput.size()));
              int digit = randomGenerator.nextInt(10);
              int amount=randomGenerator.nextInt(100);
              int orderMonth=randomGenerator.nextInt(12);
              int orderDay=randomGenerator.nextInt(30);

              if(repeated == 0){
                  String repeat ="O";
                  String or = repeat+","+company+","+alphabet[letter]+digit+","+orderMonth+"/"+orderDay+"/2019,"+amount;
                  fileWriter.append(or+ "\n");
              }
              else{
                  String repeat = "R";
                  int newMonth = randomGenerator.nextInt(12);
                  int newDay = randomGenerator.nextInt(30);
                  String or = repeat+","+company+","+alphabet[letter]+digit+","+orderMonth+"/"+orderDay+"/2019,"+amount+","+newMonth+"/"+newDay+"/2019";
                  fileWriter.append(or+ "\n");
              }
          }
          fileWriter.flush();
          fileWriter.close();
      } catch (IOException e) {
          e.printStackTrace();
      }
    }







    }
