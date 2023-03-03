package sml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import static sml.Registers.Register;

/**
 * This class ....
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author ...
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    public Translator(String fileName) {
        this.fileName = fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {

                    if (label != null) {
                        labels.addLabel(label, program.size());
                    }

                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label){
        if (line.isEmpty())
            return null;
        try {
            String opcode = scan();
            opcode = opcode.substring(0, 1).toUpperCase() + opcode.substring(1);
            return (Instruction) builder(label, opcode);
        } catch (ClassNotFoundException e) {
            System.out.println("Unknown instruction: " + e.getMessage());
        }
        return null;
    }


    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @param opcode operation name
     * @return the new instruction
     * <p>
     * uses reflection to create the instances
     */
    public Object builder(String label, String opcode) throws ClassNotFoundException {
        String className = "sml.instruction." + opcode + "Instruction";

        for (Constructor<?> candidateConstructor : Class.forName(className).getConstructors()) {
            try {
                Object[] parameterObjs = new Object[candidateConstructor.getParameterCount()];
                parameterObjs[0] = label;
                for (int i = 1; i < parameterObjs.length; i++) {
                    String s = scan();
                    // returns true  if s is a Register
                    boolean enumCheck = isRegister(s, Register.class);
                    if (enumCheck) {
                        parameterObjs[i] = Register.valueOf(s);
                    } else {
                        if (isStringDigit(s)) {
                            parameterObjs[i] = Integer.parseInt(scan());
                        } else {
                            // if s is not Register or Integer
                            parameterObjs[i] = s;
                        }

                    }

                }

                return candidateConstructor.newInstance(parameterObjs);
            } catch (Exception e) {
                System.out.println("Error ....");
            }
        }

        return null;
    }

    public static boolean isStringDigit(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isRegister(String value, Class<? extends Enum<?>> enumClass) {
        for (Enum<?> enumValue : enumClass.getEnumConstants()) {
            if (enumValue.name().equals(value)) {
                return true;
            }
        }
        return false;
    }

    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}