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
		 * ID: 1
		 */
		public static final int USER = 1;
		/**
		 * ID: 2
		 */
		public static final int DOCTOR = 2;
	}

	public static class InitUserId {
		public static final Long ADMIN = 1L;
		public static final Long SYSTEM = 2L;
		public static final Long BANKER = 3L;
	}

	public static class InitPaymentTypeId {
		public static final Long CASH = 1L;
		public static final Long AUTO_DEBIT = 2L;
		public static final Long TRANSFER = 3L;
	}

	public static class InitWithdrawalTypeId {
		public static final Long IN_AGENCY = 1L;
		public static final Long HOME_DELIVERY = 2L;
	}

	public static class InitModuleId {
		public static final Long ALERT = 1L;
	}

	public static class InitCurrencyId {
		public static final Long ROUPIE = 1L;
	}

	public enum StatusString {
		ENABLE("1"), DISABLE("2");
		private String status;

		private StatusString(String status) {
			this.status = status;
		}

		public String getValue() {
			return status;
		}
	}


	public enum BookingStatus {
		NEW("New"), IN_PROGRESS("In Progress"), READY("Ready"), FINISHED("Finished"), CANCELLED("Cancelled");
		private String code;

		private BookingStatus(String code) {
			this.code = code;
		}

		public String getValue() {
			return code;
		}

	}

	public enum RoleEnum {
		USER("User"), DOCTOR("Doctor");
		private String code;

		private RoleEnum(String code) {
			this.code = code;
		}

		public String getValue() {
			return code;
		}
	}


}
