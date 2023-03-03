package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;


class JnzInstructionTest {
  private Machine machine;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
  }

  @AfterEach
  void tearDown() {
    machine = null;
  }
  @Test
  void executeValid() {
    Instruction instruction = new MovInstruction(null, Registers.Register.EAX, 2);
    instruction.execute(machine);
    Instruction instruction2 = new MovInstruction(null, Registers.Register.EBX, 1);
    instruction2.execute(machine);
    Instruction instruction3 = new MovInstruction(null, Registers.Register.ECX, 1);
    instruction3.execute(machine);

    Instruction instruction5 = new MulInstruction("f3:", Registers.Register.EBX, Registers.Register.EAX);
    machine.getLabels().addLabel("f3:", machine.getProgram().size());
   instruction5.execute(machine);
    Instruction instruction6 = new SubInstruction(null, Registers.Register.EAX, Registers.Register.ECX);
    instruction6.execute(machine);
    Instruction instruction7 = new JnzInstruction(null, Registers.Register.EAX, "f3");
    instruction7.execute(machine);
     Assertions.assertEquals(2, machine.getRegisters().get(Registers.Register.EBX));
    Assertions.assertEquals(1, machine.getRegisters().get(Registers.Register.EAX));
  }


}