package tec.bd.proyectos.errors;

public class ExceptionReformatter {
    private Exception e;

    public ExceptionReformatter(Exception e) {
        this.e = e;
    }

    public String getErrorMessage() {
        return e.getMessage();
    }

    public String getErrorType() {
        return e.getClass().getSimpleName();
    }

    public String getErrorCause() {
        return e.getCause().getClass().getSimpleName();
    }

    public String getErrorCauseMessage() {
        return e.getCause().getMessage();
    }

    public String getErrorStackTrace() {
        return e.getStackTrace().toString();
    }

    public String getErrorCauseStackTrace() {
        return e.getCause().getStackTrace().toString();
    }

    public Exception getException() {
        return e;
    }

    public void setException(Exception e) {
        this.e = e;
    }

    public String getFormattedMessage() {
        return String.format("%s: %s", getErrorType(), getErrorMessage());
    }
}
