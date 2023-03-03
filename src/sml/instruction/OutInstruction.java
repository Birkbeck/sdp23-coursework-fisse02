package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;


/**
 * @author Faisal Isse
 * Print the contents of register {result} on the console
 */
public class OutInstruction extends Instruction {
	private final RegisterName result;

	public static final String OP_CODE = "out";

	public OutInstruction(String label, RegisterName result) {
		super(label, OP_CODE);
		this.result = result;
	}

	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(result);
		System.out.println("Print the contents " + result + " on the console " + value1);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public String toString() {
		return "OutInstruction { " + "label=" + getLabelString() + ", " + "opcode=" + getOpcode() + ", " + "result=" + result + '}';
	}
}
