
package com.fpt.idoctor.configs;

import static com.fpt.idoctor.common.constant.ModelConstants.DEFAULT_PASSWORD;

import java.util.Date;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fpt.idoctor.common.constant.ModelConstants.InitRoleId;
import com.fpt.idoctor.common.constant.ModelConstants.InitSpecialtyId;
import com.fpt.idoctor.common.constant.ModelConstants.InitUserId;
import com.fpt.idoctor.common.constant.ModelConstants.RoleEnum;
import com.fpt.idoctor.model.Role;
import com.fpt.idoctor.model.Specialty;
import com.fpt.idoctor.model.User;
import com.fpt.idoctor.repository.RoleRepository;
import com.fpt.idoctor.repository.SpecialtyRepository;
import com.fpt.idoctor.repository.TokenSessionRepository;
import com.fpt.idoctor.repository.UserRepository;

@Component
@Transactional
public class Initialization implements ApplicationRunner {

	private final Log LOGGER = LogFactory.getLog(Initialization.class);

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SpecialtyRepository specialtyRepository;
	@Autowired
	private TokenSessionRepository tokenRepository;

	private Date date;
	private User createdUser;

	private void initRole() {
		LOGGER.info("Initializing Role");
		if (roleRepository.getRole(InitRoleId.USER) == null) {
			roleRepository.addRole(
					new Role(InitRoleId.USER, RoleEnum.USER.getValue()));
		}
		if (roleRepository.getRole(InitRoleId.DOCTOR) == null) {
			roleRepository.addRole(
					new Role(InitRoleId.DOCTOR, RoleEnum.DOCTOR.getValue()));
		}
		if (roleRepository.getRole(InitRoleId.ANONYMOUS) == null) {
			roleRepository.addRole(new Role(InitRoleId.ANONYMOUS,
					RoleEnum.ANONYMOUS.getValue()));
		}

	}

	private void initAge() throws Exception {
		LOGGER.info("Initializing Age");
		// TODO:
	}

	private void initUser() throws Exception {
		LOGGER.info("Initializing User");
		Role userRole = roleRepository.getRole(InitRoleId.USER);
		Role doctorRole = roleRepository.getRole(InitRoleId.DOCTOR);
		Specialty huyetHoc = specialtyRepository
				.getSpecialtyById(InitSpecialtyId.HUYET_HOC);
		if (userRepository.findById(InitUserId.USER) == null) {
			User user = new User(InitUserId.USER, userRole, null, "user",
					DEFAULT_PASSWORD, "Bui Nam", "0962052697", true,
					"Thai Nguyen");
			userRepository.addUser(user);
		}
		if (userRepository.findById(InitUserId.DOCTOR) == null) {
			User doctor = new User(InitUserId.DOCTOR, doctorRole, huyetHoc,
					"doctor", DEFAULT_PASSWORD, "Bac sy A", "0918691234", false,
					"Nghe An");
			userRepository.addUser(doctor);
		}
	}

	private void initSpecialty() {
		LOGGER.info("Initializing Specialty");
		Specialty specialty;
		if (specialtyRepository
				.getSpecialtyById(InitSpecialtyId.HUYET_HOC) == null) {
			specialty = new Specialty(InitSpecialtyId.HUYET_HOC, "Huyết học",
					"Khoa huyết học");
			specialtyRepository.add(specialty);
		}
		if (specialtyRepository
				.getSpecialtyById(InitSpecialtyId.DA_LIEU) == null) {
			specialty = new Specialty(InitSpecialtyId.DA_LIEU, "Da liễu",
					"Khoa da liễu");
			specialtyRepository.add(specialty);
		}
		if (specialtyRepository
				.getSpecialtyById(InitSpecialtyId.TAM_THAN) == null) {
			specialty = new Specialty(InitSpecialtyId.TAM_THAN, "Tâm thần",
					"Khoa tâm thần");
			specialtyRepository.add(specialty);
		}
		if (specialtyRepository
				.getSpecialtyById(InitSpecialtyId.NHA_KHOA) == null) {
			specialty = new Specialty(InitSpecialtyId.NHA_KHOA, "Nha khoa",
					"Nha khoa");
			specialtyRepository.add(specialty);
		}
		if (specialtyRepository
				.getSpecialtyById(InitSpecialtyId.NHI_KHOA) == null) {
			specialty = new Specialty(InitSpecialtyId.NHI_KHOA, "Nhi khoa",
					"Nhi khoa");
			specialtyRepository.add(specialty);
		}
	}

	private void initWithdrawalType() {
		// LOGGER.info("Initializing Withdrawal Type");
		// if (withdrawalTypeRepository.findById(InitWithdrawalTypeId.IN_AGENCY)
		// == null) {
		// withdrawalTypeRepository
		// .addWithdrawalType(new WithdrawalType(InitWithdrawalTypeId.IN_AGENCY,
		// "Withdrawal in agency",
		// "Retrait en agence", ENABLE.getValue(), date, createdUser, date,
		// createdUser));
		// }
		// if
		// (withdrawalTypeRepository.findById(InitWithdrawalTypeId.HOME_DELIVERY)
		// == null) {
		// withdrawalTypeRepository.addWithdrawalType(new
		// WithdrawalType(InitWithdrawalTypeId.HOME_DELIVERY,
		// "Home Delivery", "Livraison Ã  domicile", ENABLE.getValue(), date,
		// createdUser, date, createdUser));
		// }
	}

	private void initModule() {
		// LOGGER.info("Initializing Module");
		// if (moduleRepository.getModuleStatus(ModuleEnum.ALERT.getValue()) ==
		// null) {
		// moduleRepository.addModule(new Module(InitModuleId.ALERT,
		// ModuleEnum.ALERT.getValue(), ENABLE.getValue()));
		// }
	}

	private void initCurrency() throws Exception {
		// LOGGER.info("Initializing Currency");
		// if (currencyRepository.findById(InitCurrencyId.ROUPIE) == null) {
		// currencyRepository.addCurrency(new Currency(InitCurrencyId.ROUPIE,
		// "ROUPIE", "Mauritius Rupee", "MUR", 1l,
		// "RÃ©publique de Maurice", "/images/flag/MUR_flg_01.png",
		// ENABLE.getValue(), date, createdUser, date,
		// createdUser, null));
		// }
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		LOGGER.debug("Inintialization is starting");
		this.date = new Date();
		initRole();
		initSpecialty();
		initUser();
		// this.createdUser = userRepository.findById(InitUserId.ADMIN);

		initWithdrawalType();
		initModule();
		initCurrency();
		initTokenStore();
	}

	private void initTokenStore() throws Exception {
		tokenRepository.initTokenStore();
	}
}
