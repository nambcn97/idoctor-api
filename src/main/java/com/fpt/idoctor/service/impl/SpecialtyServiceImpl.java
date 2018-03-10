package com.fpt.idoctor.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.idoctor.api.response.BaseResponse;
import com.fpt.idoctor.api.response.GetSpecialtyResponse;
import com.fpt.idoctor.model.Specialty;
import com.fpt.idoctor.model.User;
import com.fpt.idoctor.repository.SpecialtyRepository;
import com.fpt.idoctor.security.SecurityUtils;
import com.fpt.idoctor.service.SpecialtyService;

@Service
@Transactional(rollbackOn = Exception.class)
public class SpecialtyServiceImpl implements SpecialtyService {
	@Autowired
	private SpecialtyRepository specialtyRepository;

	@Override
	public BaseResponse getSpecialty(Long id) {
		User user = SecurityUtils.getCurrentUser();
		BaseResponse baseRes = new BaseResponse();
		GetSpecialtyResponse res = new GetSpecialtyResponse();
		Specialty specialty = specialtyRepository.getSpecialtyById(id);
		if (specialty == null) {
			baseRes.buildFailed();
			return baseRes;
		}
		res.setSpecialty(specialty.convertToBean());
		res.buildSuccessful();
		return res;

	}

}
