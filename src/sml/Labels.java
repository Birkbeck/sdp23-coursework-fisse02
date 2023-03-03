package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static sml.Instruction.NORMAL_PROGRAM_COUNTER_UPDATE;


/**
 *
 * @author ...
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address) {
		Objects.requireNonNull(label);
		// That there are no label duplicates
		if(labels.containsKey(label)){
			System.err.println("No two instructions can have the same label " + label);
			System.exit(-1);
		}
		labels.put(label, address);

	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) {

		// If the label does not already contain in the nLabels return NORMAL_PROGRAM_COUNTER_UPDATE
		// to indicate that the instruction with the next address is to be executed
		if(!labels.containsKey(label)){
			System.err.println("Label " + label + " doesn't exist ");
			return NORMAL_PROGRAM_COUNTER_UPDATE;
		}

		return labels.get(label);
	}

	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		return labels.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.map(e -> e.getKey() + " -> " + e.getValue())
				.collect(Collectors.joining(", ", "[", "]")) ;
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o instanceof Labels other) {
			return labels.equals(other.labels);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return labels.hashCode();
	}


	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}
}
