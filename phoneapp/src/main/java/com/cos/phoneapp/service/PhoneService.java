package com.cos.phoneapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.phoneapp.domain.Phone;
import com.cos.phoneapp.domain.PhoneRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PhoneService {
	private final PhoneRepository phoneRepository;
	@Transactional(readOnly = true)
	public List<Phone> 전체보기() {
		return phoneRepository.findAll();
	}
	@Transactional(readOnly = true)
	public Phone 한건보기(Long id) {
		return phoneRepository.findById(id).get();
	}

	@Transactional
	public Phone 저장하기(Phone phone) {
		return phoneRepository.save(phone);
	}
	@Transactional
	public Phone 수정하기(Long id,Phone phone) {
		//영속화 - 먼저 영속화를 시켜서 영속성 컨텍스트에 넣고 그 컨텍스트에 넣은 데이터를 변경하면 감지(더티체킹)
		//수정
		Phone phoneEntity = phoneRepository.findById(id).get();
		
		//영속화 된 객체 PhoneEntity를 수정해야한다.
		phoneEntity.setName(phone.getName());
		phoneEntity.setTel(phone.getTel());
		
		return phoneEntity;
		//서비스 종료시에 영속성 컨텍스트가 변경을 감지해서 flush로 DB에 반영함.
	}
	@Transactional
	public void 삭제하기(Long id) {
		phoneRepository.deleteById(id);
	}
}
