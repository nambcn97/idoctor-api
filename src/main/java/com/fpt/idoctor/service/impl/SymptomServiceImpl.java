package com.fpt.idoctor.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.idoctor.api.response.BaseResponse;
import com.fpt.idoctor.api.response.GetAllSymptomResponse;
import com.fpt.idoctor.bean.SymptomBean;
import com.fpt.idoctor.model.Symptom;
import com.fpt.idoctor.repository.SymptomRepository;
import com.fpt.idoctor.service.SymptomService;
@Service
@Transactional(rollbackOn = Exception.class)
public class SymptomServiceImpl implements SymptomService {
	@Autowired
	SymptomRepository symptomRepository;
	@Override
	public BaseResponse getAllSymptom() {
		List<Symptom> lstSymptom = symptomRepository.getAllSymptom();
		List<SymptomBean> lstSymptomBean = new ArrayList<SymptomBean>();
		for (Symptom symptom : lstSymptom) {
			lstSymptomBean.add(symptom.convertToBean());
		}
		GetAllSymptomResponse res = new GetAllSymptomResponse();
		res.buildSuccessful();
		res.setSymptoms(lstSymptomBean);
		return res;
	}
}
