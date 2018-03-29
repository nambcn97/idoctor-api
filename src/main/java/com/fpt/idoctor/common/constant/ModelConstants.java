package com.fpt.idoctor.common.constant;

public class ModelConstants {
	public static final String TABLE_ROLE = "Role";
	public static final String TABLE_USER = "User";
	public static final String TABLE_SPECIALTY = "Specialty";
	public static final String TABLE_DISEASE = "Disease";
	public static final String TABLE_LOCATION = "Location";
	public static final String TABLE_SYMPTOMS = "Symptoms";
	public static final String TABLE_AGE = "Age";
	public static final String TABLE_EMERGENCY_CALL = "EmergencyCall";
	public static final String TABLE_MESSAGE = "Message";

	public static final String DEFAULT_PASSWORD = "MTIzNDU2";

	public static class InitRoleId {
		/**
		 * UserRoleID: 1
		 */
		public static final Long USER = 1L;
		/**
		 * DoctorRoleID: 2
		 */
		public static final Long DOCTOR = 2L;
		/**
		 * AnonymousRoleID: 3
		 */
		public static final Long ANONYMOUS = 3L;
	}

	public static class InitUserId {
		/**
		 * USER ID = 1
		 */
		public static final Long USER = 1L;
		/**
		 * DOCTOR ID = 2
		 */
		public static final Long DOCTOR = 2L;
	}

	public static class InitSpecialtyId {
		public static final Long HUYET_HOC = 1L;
		public static final Long DA_LIEU = 2L;
		public static final Long TAM_THAN = 3L;
		public static final Long NHA_KHOA = 4L;
		public static final Long NHI_KHOA = 5L;
	}

	public enum UserStatus {
		ONLINE("Online"), BUSY("Busy"), OFFLINE("Offline");
		private String status;

		private UserStatus(String status) {
			this.status = status;
		}

		public String getValue() {
			return status;
		}
	}

	public enum EmergencyStatus {
		NEW("New"), ACCEPTED("Accepted"), MISSED("Missed");
		private String status;

		private EmergencyStatus(String status) {
			this.status = status;
		}

		public String getValue() {
			return status;
		}
	}

	public enum RoleEnum {
		USER("User"), DOCTOR("Doctor"), ANONYMOUS("Anonymous");
		private String code;

		private RoleEnum(String code) {
			this.code = code;
		}

		public String getValue() {
			return code;
		}
	}

}
