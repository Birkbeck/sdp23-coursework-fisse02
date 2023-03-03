package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

/**
 * @author Faisal Isse
 * Store integer {source} in register {result}
 */
public class MovInstruction extends Instruction {
	private final RegisterName result;
	private final int source;

	public static final String OP_CODE = "mov";

	public MovInstruction(String label, RegisterName result, int value) {
		super(label, OP_CODE);
		this.result = result;
		this.source = value;
	}

	@Override
	public int execute(Machine m) {
		m.getRegisters().set(result, source);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public String toString() {
		return "MovInstruction { " + "label=" + getLabelString() + ", " + "opcode=" + getOpcode() + ", " + "result=" + result + ", " + "source=" + source + '}';
	}
}
