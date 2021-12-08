package dev.spider.gw.hook;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

/**
 * repeat read io
 */
@Slf4j
public class ReqWrapper extends HttpServletRequestWrapper {
    /**
     * store body thread safe
     */
    private final byte[] body;

    public ReqWrapper(HttpServletRequest request) throws IOException {
        super(request);
        body = request.getInputStream().readAllBytes();
    }

    /**
     * get wrapped bodyString
     *
     * @return String
     */
    public Optional<String> wrappedBodyString() {
        String bodyString = new String(body, StandardCharsets.UTF_8);
        if (Objects.equals(bodyString, "{}")) {
            return Optional.empty();
        } else {
            return Optional.of(bodyString);
        }
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

}