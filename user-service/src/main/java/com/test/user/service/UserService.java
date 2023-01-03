package com.test.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.test.user.VO.Department;
import com.test.user.VO.ResponseTemplateVO;
import com.test.user.entity._User;
import com.test.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;

	public _User saveUser(_User user) {
		log.info("Inside saveUser method of UserService");
		return userRepository.save(user);
	}

	public ResponseTemplateVO getUserWithDepartment(Long userId) {
		log.info("Inside getUserWithDepartment method of UserService");
		ResponseTemplateVO response = new ResponseTemplateVO();
		_User user = userRepository.findByUserId(userId);

		Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(),
				Department.class);
		response.setUser(user);
		response.setDepartment(department);

		return response;
	}

}
