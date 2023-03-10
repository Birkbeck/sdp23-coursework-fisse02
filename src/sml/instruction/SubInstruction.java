package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;


/**
 @author Faisal Isse
  * Subtract the contents of register {result} from the contents of {source} and store the result in register {result}
 */
public class SubInstruction extends Instruction {
	private final RegisterName result;
	private final RegisterName source;

	public static final String OP_CODE = "sub";

	public SubInstruction(String label, RegisterName result, RegisterName source) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(result);
		int value2 = m.getRegisters().get(source);
		m.getRegisters().set(result, value1 - value2);
		return NORMAL_PROGRAM_COUNTER_UPDATE;
	}

	@Override
	public String toString() {
		return "SubInstruction { " + "label=" + getLabelString() + ", " + "opcode=" + getOpcode() + ", " + "result=" + result + ", " + "source=" + source + '}';
	}
}
