package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;


/**
 * @author Faisal Isse
 * Add the contents of registers {result} and {source} and store the result in register {result}
 */
public class AddInstruction extends Instruction {
    private final RegisterName result;
    private final RegisterName source;

    public static final String OP_CODE = "add";

    public AddInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        m.getRegisters().set(result, value1 + value2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return "AddInstruction { " + "label=" + getLabelString() + ", " + "opcode=" + getOpcode() + ", " + "result=" + result + ", " + "source=" + source + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof AddInstruction that) {
            return ((AddInstruction) o).result.equals(that.result) && ((AddInstruction) o).source.equals(that.source);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, source);
    }

}
