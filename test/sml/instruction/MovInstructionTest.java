package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.EAX;

class MovInstructionTest {
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
    Instruction instruction = new MovInstruction(null, EAX, 2);
    instruction.execute(machine);
    Assertions.assertEquals(2, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    Instruction instruction = new MovInstruction(null, EAX, -3);
    instruction.execute(machine);
    Assertions.assertEquals(-3, machine.getRegisters().get(EAX));
  }
}