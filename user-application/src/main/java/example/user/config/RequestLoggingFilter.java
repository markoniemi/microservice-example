package example.user.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.CommonsRequestLoggingFilter;

import lombok.Getter;
import lombok.Setter;

public class RequestLoggingFilter extends CommonsRequestLoggingFilter {
    @Getter
    @Setter
    boolean includeActuator = false;

    public RequestLoggingFilter() {
        super.setBeforeMessagePrefix("[");
        super.setIncludeQueryString(true);
        super.setIncludePayload(true);
        super.setMaxPayloadLength(512);
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
        // suppress after request logging
    }

    @Override
    protected boolean shouldLog(HttpServletRequest request) {
        if (!includeActuator && request.getRequestURI().contains("/actuator")) {
            return false;
        }
        return super.shouldLog(request);
    }

}
