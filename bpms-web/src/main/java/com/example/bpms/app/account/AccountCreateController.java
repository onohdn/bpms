package com.example.bpms.app.account;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.bpms.domain.model.User;
import com.example.bpms.domain.service.user.UserSharedService;
import com.github.dozermapper.core.Mapper;

@Controller
@RequestMapping("account/create")
@SessionAttributes(value = { "accountCreateForm" })
public class AccountCreateController {

	@Inject
	UserSharedService userSharedService;
	
	@Inject
	Mapper beanMapper;
	
	@ModelAttribute(value = "accountCreateForm")
	public AccountCreateForm setUpAccountForm() {
		return new AccountCreateForm();
	}
	
	@GetMapping(params = "form")
	public String showCreateForm() {
		return "account/createForm";
	}
	
	@PostMapping(params = "confirm")
	public String confirmCreate(@Validated AccountCreateForm form, BindingResult result) {
		// 入力チェックエラーがあった場合は、フォーム表示画面に戻す
		if (result.hasErrors()) {
			return "account/createForm";
		}
		return "account/createConfirm";
	}
	
	@PostMapping(params = "redoForm")
	public String redoCreateForm() {
		return showCreateForm();
	}
	
	@PostMapping
	public String create(@Validated AccountCreateForm form, BindingResult result) {
		// 入力チェックエラーがあった場合は、フォーム表示画面に戻す
		if (result.hasErrors()) {
			return "account/createForm";
		}
		
		User user = beanMapper.map(form, User.class);
		userSharedService.create(user, form.getPassword());
		
		return "redirect:/account/create?finish";
	}
	
	@GetMapping(params = "finish")
	public String finishCreate() {
		return "account/createFinish";
	}
}
