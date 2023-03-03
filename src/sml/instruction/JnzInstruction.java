package sml.instruction;

import sml.Instruction;
import sml.Labels;
import sml.Machine;
import sml.RegisterName;

/**
 * @author Faisal Isse
 * If the contents of register {result} is not zero,
 * then make the statement labeled {source} the next statement to execute
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
		// Check contents of register s is not zero
		if(value1 != 0){
			Labels labels = m.getLabels();
		    return labels.getAddress(source);

		}

		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public String toString() {
		return "JnzInstruction { " + "label=" + getLabelString() + ", " + "opcode=" + getOpcode() + ", " + "result=" + result + ", " + "source=" + source + '}';
	}
}
