package com.example.demospringstarter.configuration.analyzer;

import com.example.demospringstarter.exception.BackgroundTaskPropertyException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

import java.text.MessageFormat;

public class BackgroundTaskPropertyFailureAnalyzer extends AbstractFailureAnalyzer<BackgroundTaskPropertyException> {

    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, BackgroundTaskPropertyException cause) {
        return new FailureAnalysis(
                MessageFormat.format("Exception when try to set property: {0}", cause.getMessage()),
                "set-application-properties",
                cause
        );
    }
}
