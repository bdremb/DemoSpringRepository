package com.example.spring.demospringrest.web.filter;

import com.example.spring.demospringrest.scopes.IdHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@Order(1)
@RequiredArgsConstructor
public class IdLoggingFilter extends OncePerRequestFilter {

    private final List<IdHolder> idHolders;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        idHolders.forEach(IdHolder::logId);
        filterChain.doFilter(request, response);
    }
}
