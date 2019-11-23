package org.cap.controller;
import java.util.*; 
import org.cap.entities.Account_Details;
import org.cap.entities.Login_Details;
import org.cap.entities.Transaction_Details;
import org.cap.service.AccountDetailsService;
import org.cap.service.LoginDetailsService;
import org.cap.service.TransactionDetailsService;
import org.cap.entities.User_Profiles;
import org.cap.service.User_ProfilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;




import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
	
	@Autowired
	private LoginDetailsService service; //service for create user

	public LoginDetailsService getService() {
		return service;
	}

	public void setService(LoginDetailsService service) {
		this.service = service;
	}

	@Autowired
	private User_ProfilesService service1; //service for user profile

	public User_ProfilesService getService1() {
		return service1;
	}

	public void setService1(User_ProfilesService service1) {
		this.service1 = service1;
	}
	
	@Autowired
	private AccountDetailsService service2; //service for account details

	public AccountDetailsService getService2() {
		return service2;
	}

	public void setService2(AccountDetailsService service2) {
		this.service2 = service2;
	}
	
	@Autowired
	private TransactionDetailsService service3; //service for account details

	public TransactionDetailsService getService3() {
		return service3;
	}

	public void setService3(TransactionDetailsService service3) {
		this.service3 = service3;
	}
	
	
	
	
	@RequestMapping("/")
	public ModelAndView index() {
		return new ModelAndView("index", new HashMap<>());
	}
	/*@RequestMapping("/logout")
	public ModelAndView indexagain(HttpServletRequest request) {
		
		String uname=(String)request.getSession().getAttribute("user_id");
		System.out.println("inside /logout method in controller");
		System.out.println(uname);
		//request.getSession().invalidate();
		return new ModelAndView("login", new HashMap<>());
	}*/

	@RequestMapping("/loggingout")
	public ModelAndView indexagain(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		
		session.invalidate();
		return new ModelAndView("index", new HashMap<>());
	}
	@RequestMapping("/getdetails")
	public ModelAndView detailsForm() {
		return new ModelAndView("createuser", new HashMap<>());
	}

	@RequestMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("login", new HashMap<>());
	}
	
	@RequestMapping("/adminlogin")
	public ModelAndView adminlogin() {
		return new ModelAndView("adminlogin", new HashMap<>());
	}
	
	

	@RequestMapping("/createuserprofile")
	public ModelAndView createuserprofile() {
		return new ModelAndView("userprofileDetails", new HashMap<>());
	}

	/*@RequestMapping("/accountdetails")
	public ModelAndView fundstransfer() {
		return new ModelAndView("updateuser", new HashMap<>());
	}*/
	
	@RequestMapping("/fundstransfer")
	public ModelAndView fundstransfer(HttpSession session, HttpServletRequest request) {
		String uname=(String)request.getSession().getAttribute("user_id");
		Map<String, Object> map = new HashMap<>();
		System.out.println("good");
		map.put("user_id",uname);
		ModelAndView mv = new ModelAndView("transferfunds", map);
		return new ModelAndView("transferfunds", new HashMap<>());
	}
	
	//I AM WORKING ON THIS RIGHT NOW
	@RequestMapping("/viewrequests")
	public ModelAndView view() {
		
		/*
		List<String> uname =new ArrayList<String>();
		uname.add("saga");
		uname.add("veds");
		Map<String, Object> map = new HashMap<>();
		map.put("user_id",uname);
	    */
		
		Map<String, Object> map = new HashMap<>();
		
		List<List<String>> listOfProfiles = new ArrayList<List<String>>();
		
		List<User_Profiles> profiles=service1.allRequests();
		
		for (User_Profiles up : profiles) {
			List<String> profileDetails =new ArrayList<String>();
			
			String user_profile_id_string=up.getUser_profile_id().toString();
			profileDetails.add(user_profile_id_string);
			profileDetails.add(up.getAadhar_no());
			profileDetails.add(up.getF_name());
			profileDetails.add(up.getL_name());
			profileDetails.add(up.getM_name());
			profileDetails.add(up.getFathers_name());
			profileDetails.add(up.getMobile_no());
			profileDetails.add(up.getEmail_id());

			profileDetails.add(up.getDob().toString());

			profileDetails.add(up.getRes_addr());
			profileDetails.add(up.getPer_addr());
			profileDetails.add(up.getOcc_details());
			
			listOfProfiles.add(profileDetails);
		
	        //System.out.print(profileDetails);
	        
	 
		}

		System.out.print(listOfProfiles);
		map.put("user_id",listOfProfiles);
		
		return new ModelAndView("requests", map);
		
		
	
		
	}	
	
	
	
	
	@RequestMapping("/createprocess")
	public ModelAndView createProcess(@RequestParam String acc_no_fk, @RequestParam String user_id,
			@RequestParam String password, @RequestParam String trans_pass) {

		try {
			
			Account_Details reg = service2.findUserByAccNo(acc_no_fk);
			System.out.println("acc no exists");
			
		} catch (Exception e) {
			Map<String, String> map = new HashMap<>();
			map.put("error", "This acc no doesn't exist");
			System.out.println("acc no doesn't exist");
			ModelAndView mv = new ModelAndView("createuser", map);
			return mv;
		}
		
		
		try {
			Login_Details regacc = service.findUserByUser_Id(user_id);
			System.out.println("user already exists");
			Map<String, String> map = new HashMap<>();
			map.put("error", "user already exists");
			ModelAndView mv = new ModelAndView("createuser", map);
			return mv;
		} catch (Exception e) {
			Map<String, Object> map = new HashMap<>();
			System.out.println("good");
			Login_Details reg = service.createUser(acc_no_fk, user_id, password, trans_pass);
			map.put("acc_no_fk", reg.getAcc_no_fk());
			map.put("user_id", reg.getUser_id());
			ModelAndView mv = new ModelAndView("usercreated", map);
			return mv;
		}

		
	}

	/*@RequestMapping("/updateprocess")
    public ModelAndView createProcess(@RequestParam String acc_no,@RequestParam Integer balance){
        Account_Details user=service2.updateFunds(acc_no,balance);
        Map<String,Object>map=new HashMap<>();
        map.put("acc_no",user.getAcc_no());
        map.put("balance",user.getBalance());
        ModelAndView mv=new ModelAndView("userupdated",map);
        return mv;
    }*/
	
	/*@RequestMapping("/createprocess")
    public ModelAndView createProcess(@RequestParam String sender_acc_no,@RequestParam String receiver_acc_no, @RequestParam Integer amount){
        Transaction_Details user=ss.createtrans(sender_acc_no, receiver_acc_no, amount);
        Map<String,Object>map=new HashMap<>();
        map.put("id",user.getId());
        map.put("name",user.getName());
        ModelAndView mv=new ModelAndView("usercreated",map);
        return mv;
 	}*/
	
	@RequestMapping("/fundstransferprocess")
    public ModelAndView createProcess(@RequestParam String sender_acc_no,@RequestParam String receiver_acc_no,@RequestParam Integer amount,HttpSession session, HttpServletRequest request){
        
		String uname=(String)request.getSession().getAttribute("user_id");
		System.out.println(uname);
		
		
		Account_Details senderuser=service2.sendFunds(sender_acc_no,amount);
        Account_Details receiveruser=service2.receiveFunds(receiver_acc_no,amount);
        Transaction_Details transaction=service3.createtrans(sender_acc_no,receiver_acc_no,amount);
        Map<String,Object>map=new HashMap<>();
        map.put("acc_no",senderuser.getAcc_no());
        map.put("balance",senderuser.getBalance());
        ModelAndView mv=new ModelAndView("userupdated",map);
        return mv;
    }
	
	@RequestMapping("/userprofileprocess")
	public ModelAndView userprofileprocess(@RequestParam String aadhar_no,@RequestParam String f_name,@RequestParam String l_name,@RequestParam String m_name,@RequestParam String fathers_name,@RequestParam String mobile_no,@RequestParam String email_id,@RequestParam Date dob,@RequestParam String res_addr,@RequestParam String per_addr,@RequestParam String occ_details){
			
			User_Profiles up = service1.createUser_Profiles(aadhar_no,f_name,l_name,m_name,fathers_name,mobile_no,email_id,dob, res_addr, per_addr, occ_details);
			Map<String, Object> map = new HashMap<>();
			System.out.println("good");
			map.put("user_profile_id",up.getUser_profile_id());
			map.put("aadhar_no", up.getAadhar_no());
			map.put("f_name", up.getF_name());
			ModelAndView mv = new ModelAndView("userinfoCreated", map);
			return mv;
	}
	
	@RequestMapping("/loginprocess")
	public ModelAndView loginProcess(HttpServletRequest request, HttpSession session,@RequestParam String user_id, @RequestParam String password) {
		
		
		boolean flag =service.validateCredentials(user_id,password);
		if(flag) {
			/*session=request.getSession();
			session.setAttribute("session", user_id);
			session.setMaxInactiveInterval(10);*/
			
			session.setAttribute("user_id",user_id);
			
			//session.invalidate();
			Map<String,Object>map=new HashMap<>();
			map.put("user_id",user_id);
			ModelAndView mv=new ModelAndView("dashboard",map);
			return mv;
		}
		else {
			
			Map<String,String>map=new HashMap<>();
			map.put("error","Incorrect credentials");
			ModelAndView mv=new ModelAndView("login",map);
			return mv;
		}

	}
	
	@RequestMapping("/adminloginprocess")
	public ModelAndView adminloginProcess(HttpServletRequest request, HttpSession session,@RequestParam String user_id, @RequestParam String password) {
		
		
		boolean flag =service.validateCredentials(user_id,password);
		if(flag) {
			
			
			//session.setAttribute("user_id",user_id);
			
			//session.invalidate();
			Map<String,Object>map=new HashMap<>();
			map.put("user_id",user_id);
			ModelAndView mv=new ModelAndView("adminview",map);
			return mv;
		}
		else {
			
			Map<String,String>map=new HashMap<>();
			map.put("error","Incorrect credentials");
			ModelAndView mv=new ModelAndView("adminlogin",map);
			return mv;
		}

	}
	 
	
	
	
	
	
	
	

}
