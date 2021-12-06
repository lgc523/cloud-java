package dev.spider.gw.hook;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.Optional;

/**
 * repeat read io
 */
@Slf4j
public class ServletRequestWrapper extends HttpServletRequestWrapper {
    /**
     * store body thread safe
     */
    private final byte[] body;

    public ServletRequestWrapper(HttpServletRequest request) {
        super(request);
        body = getBodyString(request).getBytes(Charset.defaultCharset());
    }

    /**
     * get body
     *
     * @param request request
     * @return String
     */
    public String getBodyString(final ServletRequest request) {
        try {
            return extractByteArr(request.getInputStream());
        } catch (IOException e) {
            log.error("", e);
            throw new RequestWrapperException(e);
        }
    }

    /**
     * get wrapped bodyString
     *
     * @return String
     */
    public Optional<String> getWrappedBodyString() {
        final InputStream inputStream = new ByteArrayInputStream(body);

        String realBody = extractByteArr(inputStream);
        if (Objects.equals(realBody, "{}")) {
            return Optional.empty();
        } else {
            return Optional.of(realBody);
        }
    }

    /**
     * byte arr -> string
     *
     * @param inputStream inputStream
     * @return String
     */
    private String extractByteArr(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            log.error("", e);
            throw new RequestWrapperException(e);
        }

        return sb.toString();
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() {

        final ByteArrayInputStream inputStream = new ByteArrayInputStream(body);

        return new ServletInputStream() {
            @Override
            public int read() {
                return inputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }
        };
    }

    public static class RequestWrapperException extends RuntimeException {

        public RequestWrapperException(Throwable cause) {
            super(cause);
        }
    }
}