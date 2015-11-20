package db.exception;

import java.sql.SQLException;

public class NoSuchEmployeeException extends SQLException {

    public NoSuchEmployeeException() {
        super();
    }

    public NoSuchEmployeeException(String message) {
        super(message);
    }

    public NoSuchEmployeeException(Throwable cause) {
        super(cause);
    }

    public NoSuchEmployeeException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
