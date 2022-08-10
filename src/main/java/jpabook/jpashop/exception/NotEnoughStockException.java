package jpabook.jpashop.exception;

/**
 * packageName    : jpabook.jpashop.exception
 * fileName       : NotEnoughStockException
 * author         : kanghyun Kim
 * date           : 2022/08/10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/10        kanghyun Kim      최초 생성
 */
//extends RuntimeException 하면 Alt+insert로 override method 가능
public class NotEnoughStockException extends RuntimeException {

    public NotEnoughStockException() {
        super();
    }

    public NotEnoughStockException(String message) {
        super(message);
    }

    public NotEnoughStockException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughStockException(Throwable cause) {
        super(cause);
    }

}
