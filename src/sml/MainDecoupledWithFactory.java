package sml;

public class MainDecoupledWithFactory {
    /**
     * Initialises the system and executes the program.
     * @param args name of the file containing the program text.
     */
    public static void main(String... args) {
        MessageSupportFactory factory = MessageSupportFactory.getInstance();
        if (args.length != 1) {
            System.out.println("Incorrect number of arguments - Machine <file> - required");
            System.exit(-1);
        }




        try {
            Translator t = new Translator(args[0]);
            Machine m = new Machine(new Registers());
           t.readAndTranslate(m.getLabels(), m.getProgram());

            MessageRenderer mr = factory.getMessageRenderer();
            factory.getMessageProvider().setMessage(getMessage(m));
            MessageProvider mp = factory.getMessageProvider();
            mr.setMessageProvider(mp);
            mr.render();
            m.execute();
            System.out.println("Ending program execution.");

            System.out.println("Values of registers at program termination:" + m.getRegisters() + ".");
        }
        catch (Exception e) {
            System.out.println("Error reading the program from " + e.getMessage());
       }
    }

    public static String getMessage(Machine m) {
        String message = "Here is the program; it has " + m.getProgram().size() + " instructions.\n" + m + "\n" +
                "Beginning program execution.\n" ;
        return message;
    }


}