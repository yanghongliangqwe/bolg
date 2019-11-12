package InvitationController;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.bean.BlogKind;
import com.company.service.IBlogKindService;
import com.company.service.IBlogService;
import com.company.vo.BlogVO;


@Controller
public  class InvitationController {
	@Resource
	private IBlogService blogService;
	@Resource
	private IBlogKindService kindService;
	//进去首页
	@RequestMapping("/index")
	public String index(Model model){
		System.out.println("index...");
		List<BlogKind> blogKindList = kindService.findList();
		List<BlogVO> hotList = blogService.findHotList();
		List<BlogVO> indexList = blogService.findIndexList();
		model.addAttribute("blogKindList", blogKindList);
		model.addAttribute("hotList", hotList);
		model.addAttribute("indexList", indexList);
		return "forward:/index.jsp";
	}
	//注册
	/*@RequestMapping("/regist")
	public String regist(){
		
	}*/
}
