package kr.co.lookst.sns;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.lookst.sns.domain.FollowDto;
import kr.co.lookst.sns.domain.ProfileFeedDto;
import kr.co.lookst.sns.domain.SnsProfileDto;
import kr.co.lookst.sns.service.FollowService;
import kr.co.lookst.sns.service.SnsService;

@Controller
@RequestMapping("/sns")
public class SnsController {
	HttpSession session;

	@Autowired
	SnsService snsService;

//	@Autowired
//	FollowService followService;

	@GetMapping("/snsProfile")
	public String snsProfile(String member_id, Model m, @RequestParam("member_id") String member_nick) {

		try {

			SnsProfileDto pro_info = snsService.getinfoselect(member_id);
			List<ProfileFeedDto> pro_feed = snsService.getProfileFeed(member_id);
			List<FollowDto> pro_follower = snsService.getFollower(member_id);
			List<FollowDto> pro_following = snsService.getFollowing(member_id);

//			String login_Id = (String)session.getAttribute("res");
//			System.out.println(session.getAttribute("res"));

			m.addAttribute("pro_info", pro_info);
			m.addAttribute("pro_feed", pro_feed);
			m.addAttribute("pro_follower", pro_follower);
			m.addAttribute("pro_following", pro_following);
//			m.addAttribute("login_Id", login_Id);
			System.out.println(m);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sns/snsProfile";
	}

	@GetMapping("/personalPost")
	public String personalPost(String member_id, Model m, @RequestParam("member_id") String member_nick) {
	
		
		return "sns/personalPost";
	}

	@PostMapping("/follow/")

//
//	@PostMapping("/modify")
//	public String modify(SnsProfileDto snsProfileDto, Model model, HttpSession session, RedirectAttributes rattr) {
//		String member_id = (String) session.getAttribute("member_id");
//		snsProfileDto.setMember_id(member_id);
//
//		try {
//			
//			member_id = "@database";
//			
//			if (snsService.modify(snsProfileDto) != 1) {
//				throw new Exception("Modify failed");
//			}
//			
//			rattr.addFlashAttribute("msg", "MOD_OK");
//			return "redirect:/sns/snsProfile";
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute(snsProfileDto);
//			model.addAttribute("msg", "MOD_ERR");
//			return "/sns/snsProfile";
//		}
//	}

//
//		/* 1. 파일 한개만 업로드 하기 */
//		@PostMapping("/single-file")
//		public String singleFileUpload(@RequestParam String fileContent, @RequestParam MultipartFile singleFile,
//				HttpServletRequest request) {
//
//			// 1. 전송받은 파일 및 파일설명 값 가져오기
//			System.out.println("singleFile : " + singleFile);
//			System.out.println("fileContent : " + fileContent);
//
//			// 2. 저장할 경로 가져오기
//			String path = request.getSession().getServletContext().getRealPath("resources");
//			System.out.println("path : " + path);
//			String root = path + "img/uploadFiles";
//
//			File file = new File(root);
//
//			// 만약 uploadFiles 폴더가 없으면 생성해라 라는뜻
//			if (!file.exists())
//				file.mkdirs();
//
//			// 업로드할 폴더 설정
//			String originFileName = singleFile.getOriginalFilename();
//			String ext = originFileName.substring(originFileName.lastIndexOf("."));
//			String ranFileName = UUID.randomUUID().toString() + ext;
//
//			File changeFile = new File(root + "\\" + ranFileName);
//
//			// 파일업로드
//			try {
//				singleFile.transferTo(changeFile);
//				System.out.println("파일 업로드 성공");
//			} catch (IllegalStateException | IOException e) {
//				System.out.println("파일 업로드 실패");
//				e.printStackTrace();
//			}
//
//			return "result";
//		}

	@GetMapping("/postUpload")
	public String postUpload() {
		return "sns/postUpload";
	}

}
