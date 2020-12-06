package com.example.bpms.app.account;

import javax.inject.Inject;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.example.bpms.domain.service.userdetails.UserDetails;
import com.github.dozermapper.core.Mapper;

@Controller
@RequestMapping("account/update")
@SessionAttributes(value = { "accountUpdateForm" })
public class AccountUpdateController {

	@Inject
	UserSharedService userSharedService;
	
	@Inject
	Mapper beanMapper;
	
	@ModelAttribute(value = "accountUpdateForm")
	public AccountUpdateForm setUpAccountForm() {
		return new AccountUpdateForm();
	}
	
	@GetMapping(params = "form")
	public String showUpdateForm(@AuthenticationPrincipal UserDetails userDetails, AccountUpdateForm form) {
		
		// 認証情報のidを使用して、アカウント情報を取得する
		User user = userSharedService.findOne(userDetails.getUsername());
		beanMapper.map(user, form);
		
		// 認証情報からidを取得してformに格納する
		form.setId(userDetails.getUsername());
		
		return "account/updateForm";
	}
	
	@PostMapping(params = "confirm")
	public String confirmUpdate(@Validated AccountUpdateForm form, BindingResult result) {
		// 入力チェックエラーがあった場合は、フォーム表示画面に戻す
		if (result.hasErrors()) {
			return "account/updateForm";
		}
		
		return "account/updateConfirm";
	}
	
	@PostMapping(params = "redoAccountForm")
	public String redoUpdateForm() {
		return "account/updateForm";
	}
	
	@PostMapping
	public String update(@Validated AccountUpdateForm form, BindingResult result) {
		// 入力チェックエラーがあった場合は、フォーム表示画面に戻す
		if (result.hasErrors()) {
			return "account/updateForm";
		}
		
		User user = beanMapper.map(form, User.class);
		userSharedService.update(user);
		
		return "redirect:/account/update?finish";
	}
	
	@GetMapping(params = "finish")
	public String finishUpdate() {
		return "account/updateFinish";
	}
}
