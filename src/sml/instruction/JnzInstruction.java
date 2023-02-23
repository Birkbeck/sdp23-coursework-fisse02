package sml.instruction;

import sml.*;

// TODO: write a JavaDoc for the class

/**
 * @author Faisal Isse
 */

public class JnzInstruction extends Instruction {
	private final RegisterName result;
	private final String source;
	public static final String OP_CODE = "jnz";

	public JnzInstruction(String label, RegisterName result, String source) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(result);
		if(value1 != 0){
			Labels labels = m.getLabels();
			int programCounter = labels.getAddress(source);
		    return programCounter;

		}
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public String toString() {
		return getLabelString() + getOpcode() + " " + result  + " " + source;
	}
}
