
package com.util;

/**
 * Mensajes de respuesta GO CORP. Tabla origen JDBA T_STATUS_RESPONSE
 * 
 * @author xavier
 * 
 */
public enum ParametersApp {

	SERVER_ERROR(0, "Error en el servidor"),
	SUCCESSFUL(1, "Exito"),
	EMPTY_RECORD(2, "Sin registros que mostrar"),
	PROCESS_NOT_COMPLETED(3, "Tareo o proceso no completada"),
	NOT_FOUND_RECORDS(4, "No se encontraron coincidencias.");

	private final int value;

	private final String reasonPhrase;

	ParametersApp(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	public int value() {
		return this.value;
	}

	public String getReasonPhrase() {
		return this.reasonPhrase;
	}

	/**
	 * Return a string representation of this status code.
	 */
	@Override
	public String toString() {
		return this.value + " " + name();
	}

	public static ParametersApp valueOf(int statusCode) {
		ParametersApp status = resolve(statusCode);
		if (status == null) {
			throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
		}
		return status;
	}

	public static ParametersApp resolve(int statusCode) {
		for (ParametersApp status : values()) {
			if (status.value == statusCode) {
				return status;
			}
		}
		return null;
	}

}
