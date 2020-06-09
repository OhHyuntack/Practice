package com.example.demo.common.aop;

import org.aspectj.lang.annotation.Aspect;


@Aspect
public class AOPWrokLog {
/*	@Value("#{menuAdmin}")
    private HashMap<String, String> menuAdmin;
	
	@Resource(name = "adminLogService")
	private AdminLogService adminLogService;
	
	@Resource(name = "menuSetManager")
	private MenuSetManager menuSetManager;
     
	//@Around("execution(public * everAdmin.admin..*DAO.*(..)) && !execution(public * everAdmin.admin.log.*.*(..)) && args(vo)")
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	@Around("@annotation(everAdmin.admin.cmm.annotation.AdminWorkLogTarget)")
	public Object aroundTargetMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		Object obj = joinPoint.proceed(); // 대상 method 기능 실행
		
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        System.out.println("------------------------------------------------------------");
        System.out.println("AOP AOPAdminWrokLog 시작 : " + className + "." + methodName + " executed.");
        System.out.println("------------------------------------------------------------");
        String work_type = "";
        if (methodName.lastIndexOf("NoLog") > 0)
        	work_type = "";
        else if (methodName.lastIndexOf("List") > 0)
        	work_type = "10";			// 목록
        else if (methodName.lastIndexOf("View") > 0)
        	work_type = "20";			// 상세보기
        else if (methodName.lastIndexOf("WriteProc") > 0)
        	work_type = "30";			// 신규등록
        else if (methodName.lastIndexOf("EditProc") > 0)
        	work_type = "40";			// 수정
        else if (methodName.lastIndexOf("DeleteProc") > 0)
        	work_type = "50";			// 삭제

        // 관리자 작업 로그 기록 처리
        try{
        	if (!work_type.equals("")) {
		        int idx = 0;
		        AdminDataDefaultVO dvo = new AdminDataDefaultVO();
				String arg = "";
				Object[] args = joinPoint.getArgs();
	        	HttpServletRequest request = null;
	        	HttpServletResponse response = null;
	        	BindingAwareModelMap model = null;
	        	HttpSession session = null;
	        	HashMap<String, String> loginInfo;
	        	String remoteAddr = "";
	        	String log_admin_id = "";
				for(int i=0; i < args.length; i++){
					// VO객체 이면서, 첨부파일 관련 VO가 아닐 때
	//				if(args[i].getClass().getName().endsWith("VO") && !(args[i].getClass().getName().toLowerCase().contains("file"))) {
					if(args[i] instanceof AdminDataDefaultVO) {
						dvo = (AdminDataDefaultVO) args[i];
						arg = args[i].toString();
					}
	                if ( args[i] instanceof HttpServletRequest ) {
	                    request = (HttpServletRequest)args[i];
	                    remoteAddr = request.getRemoteAddr();
	                    session = request.getSession();
	                    loginInfo = (HashMap<String, String>)session.getAttribute("adminLoginInfo");
	                    log_admin_id = loginInfo.get("S_MGR_ID");
	                }
	                if ( args[i] instanceof BindingAwareModelMap ) {
	                	model = (BindingAwareModelMap)args[i];
	                }
//	                System.out.println(args[i].getClass().getName());
				}
		        
				dvo.setWork_type(work_type);
				dvo.setLog_ip(remoteAddr);
				dvo.setLog_admin_id(log_admin_id);
				if (work_type.equals("10")) {	// 목록
					if (model != null) {
						List<Object> list = (List<Object>)model.get("listInfo");
						String before_data = "";
						for (int i = 0; i < list.size(); i++) {
							AdminDataDefaultVO vo = (AdminDataDefaultVO)list.get(i);
							if (before_data.equals("")) {
								before_data = "{ \"조회 목록\" : [ \n";
								before_data += "{ \"key value\" : \"" + vo.getQueryKey() + "\", \"조회 Data\" : \"" + vo.getQueryTitle() + "\"}";
							}
							else {
								before_data += ",\n\"key value\" : \"" + vo.getQueryKey() + "\", \"조회 Data\" : \"" + vo.getQueryTitle() + "\"";
							}
						}
						before_data += "\n]}";
						dvo.setData_before(before_data);
					}
				}
				else if (work_type.equals("20")) {	// 상세보기
					if (model != null) {
						String before_data = "";
						AdminDataDefaultVO vo = (AdminDataDefaultVO)model.get("viewInfo");
						if (vo != null) {
							before_data = "{ \"조회 Data\" : \n";
							before_data += "{ \"key value\" : \"" + vo.getQueryKey() + "\", \"조회 Data\" : \"" + vo.getQueryTitle() + "\"}";
							before_data += "\n}";
							dvo.setData_before(before_data);
						}
					}
				}
				else if (work_type.equals("50")) {	// 삭제
					if (model != null) {
						ObjectMapper om = new ObjectMapper();
						String before_data = "";
						AdminDataDefaultVO vo = (AdminDataDefaultVO)model.get("viewInfo");
						if (vo != null) {
							before_data = om.writeValueAsString(vo);
							dvo.setData_before(before_data);
						}
					}
				}


				// 기본 작업 로그내역을 insert
				idx = adminLogService.insertAdminLog(dvo);
        	}
		} catch(Exception e){
	        System.out.println("------------------------------------------------------------");
			System.out.println(e.getLocalizedMessage());
	        System.out.println("------------------------------------------------------------");
		}

    	
    	return obj;
    }
*/
}