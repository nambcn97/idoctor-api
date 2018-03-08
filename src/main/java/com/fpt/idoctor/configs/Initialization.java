
package com.fpt.idoctor.configs;

import static com.fpt.idoctor.common.constant.ModelConstants.DEFAULT_PASSWORD;
import static com.fpt.idoctor.common.constant.ModelConstants.StatusString.ENABLE;

import java.util.Currency;
import java.util.Date;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


import com.fpt.idoctor.common.constant.ModelConstants.InitCurrencyId;
import com.fpt.idoctor.common.constant.ModelConstants.InitModuleId;
import com.fpt.idoctor.common.constant.ModelConstants.InitPaymentTypeId;
import com.fpt.idoctor.common.constant.ModelConstants.InitRoleId;
import com.fpt.idoctor.common.constant.ModelConstants.InitUserId;
import com.fpt.idoctor.common.constant.ModelConstants.InitWithdrawalTypeId;
import com.fpt.idoctor.common.constant.ModelConstants.RoleEnum;

import com.fpt.idoctor.model.Role;
import com.fpt.idoctor.model.User;

import com.fpt.idoctor.repository.RoleRepository;
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
	private TokenSessionRepository tokenRepository;
	private Date date;
	private User createdUser;

	private void initRole() {
		LOGGER.info("Initializing Role");
		if (roleRepository.getUserRole() == null) {
			roleRepository.addRole(new Role(InitRoleId.USER, RoleEnum.USER.getValue()));
		}
		if (roleRepository.getUserRole() == null) {
			roleRepository.addRole(new Role(InitRoleId.DOCTOR, RoleEnum.DOCTOR.getValue()));
		}

	}

	private void initAge() throws Exception {
		LOGGER.info("Initializing Age");
		//TODO:
	}

	private void initUser() throws Exception {
		LOGGER.info("Initializing User");
//		Bank bank = bankRepository.findById(InitBankId.MAURIQUE);
//		Role adminRole = roleRepository.getAdminRole();
//		Role bankerRole = roleRepository.getBankerRole();
//		Role systemRole = roleRepository.getSystemRole();
//		if (userRepository.findById(InitUserId.ADMIN) == null) {
//			User admin = new User(InitUserId.ADMIN, DEFAULT_PASSWORD, "admin", "Admin", "", "", "",
//					"admin@fsoft.com.vn", ENABLE.getValue(), adminRole, bank);
//			userRepository.addUser(admin);
//		}
//		if (userRepository.findById(InitUserId.SYSTEM) == null) {
//			User system = new User(InitUserId.SYSTEM, DEFAULT_PASSWORD, "system", "System", "", "", "",
//					"system@fsoft.com.vn", ENABLE.getValue(), systemRole, bank);
//			userRepository.addUser(system);
//		}
//		if (userRepository.findById(InitUserId.BANKER) == null) {
//			User banker = new User(InitUserId.BANKER, DEFAULT_PASSWORD, "banker", "Banker", "", "", "",
//					"banker@fsoft.com.vn", ENABLE.getValue(), bankerRole, bank);
//			userRepository.addUser(banker);
//		}
	}

	private void initPaymentType() {
//		LOGGER.info("Initializing Payment Type");
//		if (paymentTypeRepository.findById(InitPaymentTypeId.CASH) == null) {
//			paymentTypeRepository.addPaymentType(new PaymentType(InitPaymentTypeId.CASH, "Cash", "Espèces",
//					ENABLE.getValue(), date, createdUser, date, createdUser));
//		}
//		if (paymentTypeRepository.findById(InitPaymentTypeId.AUTO_DEBIT) == null) {
//			paymentTypeRepository.addPaymentType(new PaymentType(InitPaymentTypeId.AUTO_DEBIT, "Auto Debit",
//					"Prélèvement", ENABLE.getValue(), date, createdUser, date, createdUser));
//		}
//		if (paymentTypeRepository.findById(InitPaymentTypeId.TRANSFER) == null) {
//			paymentTypeRepository.addPaymentType(new PaymentType(InitPaymentTypeId.TRANSFER, "Transfer", "Virement",
//					ENABLE.getValue(), date, createdUser, date, createdUser));
//		}
	}

	private void initWithdrawalType() {
//		LOGGER.info("Initializing Withdrawal Type");
//		if (withdrawalTypeRepository.findById(InitWithdrawalTypeId.IN_AGENCY) == null) {
//			withdrawalTypeRepository
//					.addWithdrawalType(new WithdrawalType(InitWithdrawalTypeId.IN_AGENCY, "Withdrawal in agency",
//							"Retrait en agence", ENABLE.getValue(), date, createdUser, date, createdUser));
//		}
//		if (withdrawalTypeRepository.findById(InitWithdrawalTypeId.HOME_DELIVERY) == null) {
//			withdrawalTypeRepository.addWithdrawalType(new WithdrawalType(InitWithdrawalTypeId.HOME_DELIVERY,
//					"Home Delivery", "Livraison  à domicile", ENABLE.getValue(), date, createdUser, date, createdUser));
//		}
	}

	private void initModule() {
//		LOGGER.info("Initializing Module");
//		if (moduleRepository.getModuleStatus(ModuleEnum.ALERT.getValue()) == null) {
//			moduleRepository.addModule(new Module(InitModuleId.ALERT, ModuleEnum.ALERT.getValue(), ENABLE.getValue()));
//		}
	}

	private void initCurrency() throws Exception {
//		LOGGER.info("Initializing Currency");
//		if (currencyRepository.findById(InitCurrencyId.ROUPIE) == null) {
//			currencyRepository.addCurrency(new Currency(InitCurrencyId.ROUPIE, "ROUPIE", "Mauritius Rupee", "MUR", 1l,
//					"République de Maurice", "/images/flag/MUR_flg_01.png", ENABLE.getValue(), date, createdUser, date,
//					createdUser, null));
//		}
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		LOGGER.debug("Inintialization is starting");
		this.date = new Date();
		initRole();
		initUser();
//		this.createdUser = userRepository.findById(InitUserId.ADMIN);
		initPaymentType();
		initWithdrawalType();
		initModule();
		initCurrency();
		initTokenStore();
	}

	private void initTokenStore() throws Exception {
		tokenRepository.initTokenStore();
	}
}
