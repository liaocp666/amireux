package cn.liaocp.amireux.core.exception;

import cn.liaocp.amireux.core.enums.RestResultEnum;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
public class AmireuxException extends RuntimeException {

	private static final long serialVersionUID = 1379298486940312833L;

	public AmireuxException() {
		super();
	}

	public AmireuxException(RestResultEnum restResultEnum) {
		super(restResultEnum.getMsg());
	}

}
