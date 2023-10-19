package com.emp.client.serdes.v1_0;

import com.emp.client.dto.v1_0.Status;
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
public class StatusSerDes {

	public static Status toDTO(String json) {
		StatusJSONParser statusJSONParser = new StatusJSONParser();

		return statusJSONParser.parseToDTO(json);
	}

	public static Status[] toDTOs(String json) {
		StatusJSONParser statusJSONParser = new StatusJSONParser();

		return statusJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Status status) {
		if (status == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (status.getMessage() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"message\": ");

			sb.append("\"");

			sb.append(_escape(status.getMessage()));

			sb.append("\"");
		}

		if (status.getStatusCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"statusCode\": ");

			sb.append(status.getStatusCode());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		StatusJSONParser statusJSONParser = new StatusJSONParser();

		return statusJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Status status) {
		if (status == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (status.getMessage() == null) {
			map.put("message", null);
		}
		else {
			map.put("message", String.valueOf(status.getMessage()));
		}

		if (status.getStatusCode() == null) {
			map.put("statusCode", null);
		}
		else {
			map.put("statusCode", String.valueOf(status.getStatusCode()));
		}

		return map;
	}

	public static class StatusJSONParser extends BaseJSONParser<Status> {

		@Override
		protected Status createDTO() {
			return new Status();
		}

		@Override
		protected Status[] createDTOArray(int size) {
			return new Status[size];
		}

		@Override
		protected void setField(
			Status status, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "message")) {
				if (jsonParserFieldValue != null) {
					status.setMessage((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "statusCode")) {
				if (jsonParserFieldValue != null) {
					status.setStatusCode(
						Integer.valueOf((String)jsonParserFieldValue));
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