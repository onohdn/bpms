package com.example.bpms.app.bpms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public String create(@Validated BpmsForm bpmsForm, BindingResult result, Locale locale,
    		Model model, RedirectAttributes attributes, @AuthenticationPrincipal UserDetails userDetails) {
    	
    	// 入力チェックエラーがあったら、初期表示画面に戻る
    	if (result.hasErrors()) {
    		return home(locale, model);
    	}
    	
    	// formからBattingresultsオブジェクトを生成する
    	Battingresults battingresults = mapper.map(bpmsForm, Battingresults.class);
    	battingresults.setId(userDetails.getUsername());
    	// サービス実行
    	Battingresults calculatedBattingresults = battingresultsService.create(battingresults);
    	
    	// 計算結果を詰め込む
    	model.addAttribute("calculatedBattingresults", calculatedBattingresults);
    	
    	return "bpms/result";
    	
    }
    
    /**
     * ファイルアップロード
     * @throws IOException 
     */
    @PostMapping("upload")
    public String upload(BpmsForm bpmsForm, Model model, RedirectAttributes attributes, @AuthenticationPrincipal UserDetails userDetails) throws IOException {
    	
    	// Battingresultsオブジェクトを生成する
    	Battingresults battingresults = createBattingResults(bpmsForm, userDetails);
    	
    	// サービス実行
    	Battingresults calculatedBattingresults = battingresultsService.create(battingresults);
    	
    	// 計算結果を詰め込む
    	model.addAttribute("calculatedBattingresults", calculatedBattingresults);
    	
    	return "bpms/result";
    }

    /**
     * アップロードされたファイルとユーザ認証情報を受け取り、BattingResultsオブジェクトを生成するメソッドです
     * @param arr
     * @param userDetails
     * @return bpmsForm
     * @throws IOException 
     */
	private Battingresults createBattingResults(BpmsForm bpmsForm, UserDetails userDetails) throws IOException {
		
		// ファイルの読み込み
    	InputStreamReader is = new InputStreamReader(bpmsForm.getFile().getInputStream());
    	BufferedReader br = new BufferedReader(is);
    	
    	// 入力行をカンマ区切りで配列化
    	String[] arr = (br.readLine()).split(",");
    	
    	// Battingresultsオブジェクトを生成
		Battingresults battingresults = new Battingresults();
		battingresults.setId(userDetails.getUsername());
		battingresults.setPlate_appearance(Integer.parseInt(arr[0]));
		battingresults.setHit(Integer.parseInt(arr[1]));
		battingresults.setTwo_base_hit(Integer.parseInt(arr[2]));
		battingresults.setThree_base_hit(Integer.parseInt(arr[3]));
		battingresults.setHome_run(Integer.parseInt(arr[4]));
		battingresults.setWalks(Integer.parseInt(arr[5]));
		battingresults.setHit_by_pitch(Integer.parseInt(arr[6]));
		battingresults.setSacrifice_bunt(Integer.parseInt(arr[7]));
		battingresults.setSacrifice_fly(Integer.parseInt(arr[8]));
		battingresults.setFaux_pas(Integer.parseInt(arr[9]));
		
		return battingresults;
	}
}
