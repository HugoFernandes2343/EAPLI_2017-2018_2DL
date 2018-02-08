/**
 *
 */
package eapli.framework.dto;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A generic DTO that implements the Map interface. This class can be used when
 * you don't want to build your own custom DTO classes.
 *
 * @author Paulo Gandra Sousa
 *
 */
public class GenericDTO implements DTO, Map<String, Object> {

	private final Map<String, Object> data = new HashMap<>();
	private final String type;

	public GenericDTO(String type) {
		if (type == null || type.trim().length() == 0) {
			throw new IllegalArgumentException();
		}
		this.type = type;
	}

	/**
	 * Builds a DTO from an object using reflection.
	 *
	 * @param o
	 * @return
	 */
	public static GenericDTO of(Object o) {
		final GenericDTO out = new GenericDTO(o.getClass().getName());
		final List<Field> fields = getInheritedFields(o.getClass());
		for (final Field aField : fields) {
			ofField(o, out, aField);
		}
		return out;
	}

	@SuppressWarnings("unchecked")
	private static void ofField(Object o, final GenericDTO out, final Field aField) {
		try {
			aField.setAccessible(true);
			if (aField.getType().isPrimitive() || aField.getType() == String.class) {
				out.put(aField.getName(), aField.get(o));
			} else if (aField.getType().isArray()) {
				if (aField.getType().getComponentType().isPrimitive()
						|| aField.getType().getComponentType() == String.class) {
					buildDtoForArray(aField.getType().getComponentType(), aField.getName(), aField.get(o), out);
				} else {
					buildDtoForIterable(aField.getName(), (Iterable<Object>) (aField.get(o)), out);
				}
			} else if (Collection.class.isAssignableFrom(aField.getType())) {
				buildDtoForIterable(aField.getName(), (Iterable<Object>) (aField.get(o)), out);
			} else {
				out.put(aField.getName(), of(aField.get(o)));
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Iterable<GenericDTO> ofMany(Iterable<?> col) {
		final List<GenericDTO> data = new ArrayList<>();
		for (final Object member : col) {
			data.add(of(member));
		}
		return data;
	}

	/**
	 * @param o
	 * @param aField
	 * @param out
	 * @throws IllegalAccessException
	 */
	private static void buildDtoForIterable(String name, Iterable<Object> col, final GenericDTO out)
			throws IllegalAccessException {

		final Iterable<GenericDTO> data = ofMany(col);
		out.put(name, data);
	}

	/**
	 * @param o
	 * @param aField
	 * @param out
	 * @throws IllegalAccessException
	 */
	private static void buildDtoForArray(Class<?> type, String name, Object array, final GenericDTO out)
			throws IllegalAccessException {
		final int length = Array.getLength(array);
		Object data = null;
		if (type == int.class) {
			data = Arrays.copyOf((int[]) array, length);
		} else if (type == long.class) {
			data = Arrays.copyOf((long[]) array, length);
		} else if (type == boolean.class) {
			data = Arrays.copyOf((boolean[]) array, length);
		} else if (type == double.class) {
			data = Arrays.copyOf((double[]) array, length);
		} else if (type == float.class) {
			data = Arrays.copyOf((float[]) array, length);
		} else if (type == char.class) {
			data = Arrays.copyOf((char[]) array, length);
		} else if (type == String.class) {
			data = Arrays.copyOf((String[]) array, length);
		}

		out.put(name, data);
	}

	public static Object valueOf(GenericDTO dto) {
		throw new UnsupportedOperationException("Not implemented yet");
	}

	private static List<Field> getInheritedFields(Class<?> type) {
		final List<Field> fields = new ArrayList<>();
		for (Class<?> c = type; c != null; c = c.getSuperclass()) {
			fields.addAll(Arrays.asList(c.getDeclaredFields()));
		}
		return fields;
	}

	/**
	 * Returns the name of the type contained in this DTO. Might be helpful for
	 * client code to parse the DTO.
	 *
	 * @return
	 */
	public String type() {
		return this.type;
	}

	@Override
	public void clear() {
		this.data.clear();
	}

	@Override
	public boolean containsKey(Object arg0) {
		return this.data.containsKey(arg0);
	}

	@Override
	public boolean containsValue(Object arg0) {
		return this.data.containsValue(arg0);
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return this.data.entrySet();
	}

	@Override
	public Object get(Object arg0) {
		return this.data.get(arg0);
	}

	@Override
	public boolean isEmpty() {
		return this.data.isEmpty();
	}

	@Override
	public Set<String> keySet() {
		return this.data.keySet();
	}

	@Override
	public Object put(String arg0, Object arg1) {
		return this.data.put(arg0, arg1);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> arg0) {
		this.data.putAll(arg0);
	}

	@Override
	public Object remove(Object arg0) {
		return this.data.remove(arg0);
	}

	@Override
	public int size() {
		return this.data.size();
	}

	@Override
	public Collection<Object> values() {
		return this.data.values();
	}
}
