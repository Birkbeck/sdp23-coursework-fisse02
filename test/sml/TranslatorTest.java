package sml;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TranslatorTest {

  private Translator translator;
  private Labels labels;
  private List<Instruction> program;

  @BeforeEach
  void setUp() {
    translator = new Translator("src/sml/file.txt");
    labels = new Labels();
    program = new ArrayList<>();
  }

  @AfterEach
  void tearDown() {
    translator = null;
    labels = null;
    program = null;
  }

  @Test
  void testReadAndTransport() throws IOException {

    translator.readAndTranslate(labels, program);

    assertEquals(7, program.size());

    Instruction instruction1 = program.get(0);

   assertEquals("mov", instruction1.opcode);

    Instruction instruction2 = program.get(1);
    assertEquals("mov",  instruction2.opcode);

    Instruction instruction3 = program.get(2);
    assertEquals("mov", instruction3.opcode);
  }
}
