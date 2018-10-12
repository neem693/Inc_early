package com.anylogic.iot.base.mvc.message;

/**
 * 컨트롤러 클래스의 메서드에서 사용되는 메시지 인터페이스.
 * 해당 인터페이스를 통하여 UI에 넘길 메시지를 추가한다.
 * 여러건의 메시지 추가가 가능하다.
 *
 * @author jeado
 *
 */
public interface Messages {

	void addMessage(String code, String msg);

}
