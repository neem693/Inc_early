package com.anylogic.iot.api.admin.tenant.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.anylogic.iot.api.admin.tenant.mapper.TenantMapper;
import com.anylogic.iot.api.admin.tenant.vo.CompanyInfoVO;
import com.anylogic.iot.api.admin.tenant.vo.guestListVO;
/*import com.anylogic.iot.api.app.vo.FilePathVO;
import com.anylogic.iot.api.app.vo.FileVO;*/
import com.anylogic.iot.api.common.vo.FilePathVO;
import com.anylogic.iot.api.common.vo.FileVO;

@Service
public class TenantService {
	
	
	/*@Autowired
	private BoardService boardService;*/

	@Autowired
	private TenantMapper tenantMapper;
	
	
	public int deleteContractInfo(Map<String, Object> parameter) {
		return tenantMapper.deleteContractInfo(parameter);
	}
	
	
	public int insertContractInfo(Map<String, Object> parameter) {
		return tenantMapper.insertContractInfo(parameter);
	}
	public int updateContractInfo(Map<String, Object> parameter) {
		return tenantMapper.updateContractInfo(parameter);
	}
	
	public int addTime(Map<String, Object> parameter) {
		return tenantMapper.addTime(parameter);
	}
	
	
	public List<Object> getContract(Map<String, Object> parameter) {
		return tenantMapper.getContract(parameter);
	}
	
	public List<Object> getGuestList(Map<String, Object> parameter) {
		return tenantMapper.getGuestList(parameter);
	}


	public List<guestListVO> getGuestListExcel(Map<String, Object> parameter) {
		return tenantMapper.getGuestListExcel(parameter);
	}
	

	public List<Object> getOfficeRentUseInfo(Map<String, Object> parameter) {
		return tenantMapper.getOfficeRentUseInfo(parameter);
	}
	
public int resetOffice(Map<String, Object> parameter) {
	return tenantMapper.resetOffice(parameter);
}

public int deleteMember(Map<String, Object> parameter) {
	return tenantMapper.deleteMember(parameter);
}

public int resetOfficeStatus(Map<String, Object> parameter) {
	return tenantMapper.resetOfficeStatus(parameter);
}

public int resetOfficeRentUse(Map<String, Object> parameter) {
	return tenantMapper.resetOfficeRentUse(parameter);
}

public int resetMngInfo(Map<String, Object> parameter) {
	return tenantMapper.resetMngInfo(parameter);
}

public int updateSetMngInfo(Map<String, Object> parameter) {
	return tenantMapper.updateSetMngInfo(parameter);
}
	
	public int updateOfficeStatus(Map<String, Object> parameter) {
		return tenantMapper.updateOfficeStatus(parameter);
	}
	
	public int deleteCompany(Map<String, Object> parameter) {
		return tenantMapper.deleteCompany(parameter);
	}
	

public int reinsertCompanyFile(Map<String, Object> parameter) {
	return tenantMapper.reinsertCompanyFile(parameter);
}

	public int resetCompantFile(Map<String, Object> parameter) {
		return tenantMapper.resetCompantFile(parameter);
	}
	
	public List<Object> getCompanyFile(Map<String, Object> parameter) {
		return tenantMapper.getCompanyFile(parameter);
	}

	public int updateMngInfo(Map<String, Object> parameter) {
		return tenantMapper.updateMngInfo(parameter);
	}

	public int insertOfficeRentUse(Map<String, Object> parameter) {
		return tenantMapper.insertOfficeRentUse(parameter);
	}

	public int insertCompanyInfo(Map<String, Object> parameter) {
		return tenantMapper.insertCompanyInfo(parameter);
	}

	public int insertMngInfo(Map<String, Object> parameter) {
		return tenantMapper.insertMngInfo(parameter);
	}

	public List<Object> dupCheckId(Map<String, Object> parameter) {
		return tenantMapper.dupCheckId(parameter);
	}

	public List<Object> getCompanyMember(Map<String, Object> parameter) {
		return tenantMapper.getCompanyMember(parameter);
	}

	public List<Object> getOfficeInfo(Map<String, Object> parameter) {
		return tenantMapper.getOfficeInfo(parameter);
	}
	
	public List<Object> getCompanyList(Map<String, Object> parameter) {
		return tenantMapper.getCompanyList(parameter);
	}


	public List<CompanyInfoVO> getCompanyListExcel(Map<String, Object> parameter) {
		return tenantMapper.getCompanyListExcel(parameter);
	}

	public int updateCompanyInfo(Map<String, Object> parameter) {
		return tenantMapper.updateCompanyInfo(parameter);
	}

	@SuppressWarnings("unchecked")
	public String insertFileData(Map<String, Object> sendInfo, String atcFileId, HttpServletRequest request,
			HttpServletResponse response) {
		
		//return boardService.insertFileData(sendInfo, atcFileId, request, response);

		String rtnFileId = "";

		try {
			if (request.getParameter("files") == null) {
				rtnFileId = atcFileId;
				return rtnFileId;
			}

			String json = request.getParameter("files").toString();
			List<HashMap<String, Object>> fileList = new ObjectMapper().readValue(json, List.class);

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; // 다중파일
																									// 업로드
			List<MultipartFile> mf = multipartRequest.getFiles("fileField1");
			
			
			Map<String,Object> parameter = null;
			
			Map<String,Object> fileparameter = null;
			
			String json2 = request.getParameter("data").toString();
			
			//String fileNameList = request.getParameter("fileName").toString();
			try {
				parameter = new ObjectMapper().readValue(json2, HashMap.class);
			} catch (Exception e) {
				throw new com.anylogic.iot.base.exception.KTBCCException();
			}
			
			//try {
			//	fileparameter = new ObjectMapper().readValue(fileNameList, HashMap.class);
			//} catch (Exception e) {
			//	throw new com.anylogic.iot.base.exception.KTBCCException();
			//}
			
			Map<String, Object> companyInfo = new HashMap<String, Object>(); 
			if(parameter.get("companyInfo") != null){
				companyInfo =   (Map<String, Object>) parameter.get("companyInfo");
			}

			List<Map<String, Object>> recvFileData = new ArrayList<Map<String, Object>>();
			recvFileData = (List<Map<String, Object>>) companyInfo.get("tempFileList");
			
			
			Random random = new Random();
			String genId = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); // 현재시간
			genId = genId.substring(2, genId.length()) + String.format("%02d", random.nextInt(10));

			if ("".equals(atcFileId) || atcFileId == null) {
				// 파일 중복명 처리
				// return file ID
				rtnFileId = genId;
			} else {
				// 기존 파일 아이디에 계속 연결 시킴
				rtnFileId = atcFileId;
			}
			// 첨부 파일이 없을 경우 return

			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = sdfDate.format(System.currentTimeMillis());

			for (int i = 0; i < mf.size(); i++) {

				FileVO fileVO = new FileVO(rtnFileId);
				// 본래 파일명
				//String originalfileName = mf.get(i).getOriginalFilename();
				String originalfileName = (String) recvFileData.get(i).get("name");
				System.out.println("originalfileName : !!!!!!!!!!" + originalfileName);
				
				
				
				String ext = originalfileName.substring(originalfileName.lastIndexOf(".") + 1,
						originalfileName.length());

				String saveFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "_"
						+ originalfileName;
				String atc_file_type_cd = "";

				try {
					atc_file_type_cd = fileList.get(i).get("fileTypeCd").toString();
				} catch (Exception e) {
					// 오류 시 기본 DEF 경로
					atc_file_type_cd = "";
				}

				FilePathVO path = new FilePathVO(atc_file_type_cd);

				String rootPath = path.getSystemRootPath() + "/" + strDate + "/";
				String urlPath = path.getUrlPath() + "/" + strDate + "/";

				File path22 = new File(".");
				String saveUrl = path22.getCanonicalPath();

				saveUrl = saveUrl + "/" + "src" + "/" + "main" + "/" + "webapp" + "/" + "upload" + "/";
				String saveUrl2 = "upload" + "/";
				File dir = new File(saveUrl);
				// 디렉토리가 없으면
				if (!dir.isDirectory()) {
					// 디렉토리 생성
					dir.mkdirs();
				}

				System.out.println("URL11@@@@@@@@@@@@@@@1111 : " + request.getSession().getServletContext().getRealPath("/"));
				String tmp = request.getSession().getServletContext().getRealPath("/");
				if (tmp.endsWith("/")) tmp = tmp + "/";
				String savePath = tmp + "upload/" + saveFileName; // 저장 될 파일 경로

				System.out.println("saveUrl : " + saveUrl);
				System.out.println("savePath : " + savePath);
				String urlSavePath = saveUrl2 + saveFileName;
				System.out.println("urlSavePath : " + urlSavePath);
				long fileSize = mf.get(i).getSize(); // 파일 사이즈
				mf.get(i).transferTo(new File(savePath)); // 파일 저장

				// fileVO.setMemId(memId);
				// sendInfo.put("tableName", "tb_question");
				fileVO.setFileTypeCd(atc_file_type_cd); // 첨부파일유형코드
				fileVO.setFileNm(saveFileName); // 파일명
				fileVO.setFileSize(fileSize); // 파일크기
				fileVO.setFileDivVal(ext); // 파일구분값
				fileVO.setUpldPathSbst(urlSavePath); // 업로드경로내용
				fileVO.setAtcFilePathSbst(savePath);

				sendInfo.put("file_name", fileVO.getFileNm());
				sendInfo.put("file_path", fileVO.getAtcFilePathSbst());
				tenantMapper.insertFileData(sendInfo);
			}

		} catch (Exception e) {
			throw new com.anylogic.iot.base.exception.KTBCCException(e.getMessage());
		}

		return rtnFileId;

	}
}
