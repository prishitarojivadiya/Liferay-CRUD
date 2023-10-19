package com.emp.client.serdes.v1_0;

import com.emp.client.dto.v1_0.EmployeeDetails;
import com.emp.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author ignek
 * @generated
 */
@Generated("")
public class EmployeeDetailsSerDes {

	public static EmployeeDetails toDTO(String json) {
		EmployeeDetailsJSONParser employeeDetailsJSONParser =
			new EmployeeDetailsJSONParser();

		return employeeDetailsJSONParser.parseToDTO(json);
	}

	public static EmployeeDetails[] toDTOs(String json) {
		EmployeeDetailsJSONParser employeeDetailsJSONParser =
			new EmployeeDetailsJSONParser();

		return employeeDetailsJSONParser.parseToDTOs(json);
	}

	public static String toJSON(EmployeeDetails employeeDetails) {
		if (employeeDetails == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (employeeDetails.getEmailAddress() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"emailAddress\": ");

			sb.append("\"");

			sb.append(_escape(employeeDetails.getEmailAddress()));

			sb.append("\"");
		}

		if (employeeDetails.getEmployeeId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"employeeId\": ");

			sb.append(employeeDetails.getEmployeeId());
		}

		if (employeeDetails.getFirstName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"firstName\": ");

			sb.append("\"");

			sb.append(_escape(employeeDetails.getFirstName()));

			sb.append("\"");
		}

		if (employeeDetails.getLastName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"lastName\": ");

			sb.append("\"");

			sb.append(_escape(employeeDetails.getLastName()));

			sb.append("\"");
		}

		if (employeeDetails.getMobileNumber() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"mobileNumber\": ");

			sb.append("\"");

			sb.append(_escape(employeeDetails.getMobileNumber()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		EmployeeDetailsJSONParser employeeDetailsJSONParser =
			new EmployeeDetailsJSONParser();

		return employeeDetailsJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(EmployeeDetails employeeDetails) {
		if (employeeDetails == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (employeeDetails.getEmailAddress() == null) {
			map.put("emailAddress", null);
		}
		else {
			map.put(
				"emailAddress",
				String.valueOf(employeeDetails.getEmailAddress()));
		}

		if (employeeDetails.getEmployeeId() == null) {
			map.put("employeeId", null);
		}
		else {
			map.put(
				"employeeId", String.valueOf(employeeDetails.getEmployeeId()));
		}

		if (employeeDetails.getFirstName() == null) {
			map.put("firstName", null);
		}
		else {
			map.put(
				"firstName", String.valueOf(employeeDetails.getFirstName()));
		}

		if (employeeDetails.getLastName() == null) {
			map.put("lastName", null);
		}
		else {
			map.put("lastName", String.valueOf(employeeDetails.getLastName()));
		}

		if (employeeDetails.getMobileNumber() == null) {
			map.put("mobileNumber", null);
		}
		else {
			map.put(
				"mobileNumber",
				String.valueOf(employeeDetails.getMobileNumber()));
		}

		return map;
	}

	public static class EmployeeDetailsJSONParser
		extends BaseJSONParser<EmployeeDetails> {

		@Override
		protected EmployeeDetails createDTO() {
			return new EmployeeDetails();
		}

		@Override
		protected EmployeeDetails[] createDTOArray(int size) {
			return new EmployeeDetails[size];
		}

		@Override
		protected void setField(
			EmployeeDetails employeeDetails, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "emailAddress")) {
				if (jsonParserFieldValue != null) {
					employeeDetails.setEmailAddress(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "employeeId")) {
				if (jsonParserFieldValue != null) {
					employeeDetails.setEmployeeId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "firstName")) {
				if (jsonParserFieldValue != null) {
					employeeDetails.setFirstName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "lastName")) {
				if (jsonParserFieldValue != null) {
					employeeDetails.setLastName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "mobileNumber")) {
				if (jsonParserFieldValue != null) {
					employeeDetails.setMobileNumber(
						(String)jsonParserFieldValue);
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}
			else {
				sb.append(String.valueOf(entry.getValue()));
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}