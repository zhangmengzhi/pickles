package org.zhangmz.pickles.service.exception;

public enum ErrorCode {

	BAD_REQUEST(400, 400), 
	UNAUTHORIZED(401, 401), 
	FORBIDDEN(403, 403), 
	INTERNAL_SERVER_ERROR(500, 500),

	ACCOUNT_STATUS_WRONG(1100, 400), 
	ACCOUNT_OWNERSHIP_WRONG(1101, 403), 
	NO_TOKEN(1102, 401), 
	
	PARAMETER_NON_EXISTENT(1103, 401), 
	PHONE_HAS_EXIST(1104, 401),
	EMAIL_HAS_EXIST(1105, 401),
	NAME_HAS_EXIST(1104, 401);

	public int code;
	public int httpStatus;

	ErrorCode(int code, int httpStatus) {
		this.code = code;
		this.httpStatus = httpStatus;
	}

}
