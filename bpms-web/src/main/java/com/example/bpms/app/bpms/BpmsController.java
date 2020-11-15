package com.example.bpms.app.bpms;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.bpms.domain.model.Battingresults;
import com.example.bpms.domain.service.battingresults.BattingresultsService;
import com.example.bpms.domain.service.userdetails.UserDetails;
import com.github.dozermapper.core.Mapper;

@Controller
@RequestMapping("bpms")
public class BpmsController {

	/** ロガー **/
	private static final Logger logger = LoggerFactory
            .getLogger(BpmsController.class);

	/** サービス **/
	@Inject
	BattingresultsService battingresultsService; 
	
	/** マッパー **/
	@Inject
	Mapper mapper;
	
	/** Form初期化 **/
	@ModelAttribute
	public BpmsForm setUpForm() {
		BpmsForm form = new BpmsForm();
		return form;
	}
	
    /**
     * 初期表示ページ
     */
    @GetMapping("/")
    public String home(Locale locale, Model model) {
        logger.info("Welcome home! The client locale is {}.", locale);

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
                DateFormat.LONG, locale);

        String formattedDate = dateFormat.format(date);
        model.addAttribute("serverTime", formattedDate);
        
        return "bpms/home";
    }

    /**
     * 指標計算結果表示
     */
    @PostMapping("create")
    public String create(@Valid BpmsForm bpmsForm, BindingResult bindingResult, Locale locale,
    		Model model, RedirectAttributes attributes, @AuthenticationPrincipal UserDetails userDetails) {
    	
    	// 入力チェックエラーがあったら、初期表示画面に戻る
    	// TODO formにはまだ入力チェックを付けていないので、無意味なコード
    	if (bindingResult.hasErrors()) {
    		return home(locale, model);
    	}
    	
    	// formからBattingresultsオブジェクトを生成する
    	Battingresults battingresults = mapper.map(bpmsForm, Battingresults.class);
    	battingresults.setId(userDetails.getUsername());
    	System.out.println(userDetails.getUsername());
    	// サービス実行
    	Battingresults calculatedBattingresults = battingresultsService.create(battingresults);
    	
    	// 計算結果を詰め込む
    	model.addAttribute("calculatedBattingresults", calculatedBattingresults);
    	
    	return "bpms/result";
    	
    }
}
